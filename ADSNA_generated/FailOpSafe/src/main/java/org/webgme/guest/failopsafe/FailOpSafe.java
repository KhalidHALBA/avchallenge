package org.webgme.guest.failopsafe;

import org.webgme.guest.failopsafe.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Define the FailOpSafe type of federate for the federation.

public class FailOpSafe extends FailOpSafeBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    ////////////////////////////////////////////////////////////////////////
    // TODO instantiate objects that must be sent every logical time step //
    ////////////////////////////////////////////////////////////////////////
    // TorqueRequestsOv vTorqueRequestsOv =
    //     new TorqueRequestsOv();

    public FailOpSafe(FederateConfig params) throws Exception {
        super(params);

        //////////////////////////////////////////////////////
        // TODO register object instances after super(args) //
        //////////////////////////////////////////////////////
        // vTorqueRequestsOv.registerObject(getLRC());
    }

    private void checkReceivedSubscriptions() {

        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof DDTPerfDeviationData) {
                handleObjectClass((DDTPerfDeviationData) object);
            }
            else if (object instanceof EnvironmentScenario) {
                handleObjectClass((EnvironmentScenario) object);
            }
            else if (object instanceof OESNominalMessage) {
                handleObjectClass((OESNominalMessage) object);
            }
            else if (object instanceof OESActualMessage) {
                handleObjectClass((OESActualMessage) object);
            }
            else if (object instanceof FailSafeOpSignal) {
                handleObjectClass((FailSafeOpSignal) object);
            }
            else if (object instanceof TripPlanMessage) {
                handleObjectClass((TripPlanMessage) object);
            }
            else if (object instanceof LocationTime) {
                handleObjectClass((LocationTime) object);
            }
            else {
                log.debug("unhandled object reflection: {}", object.getClassName());
            }
        }
    }

    private void execute() throws Exception {
        if(super.isLateJoiner()) {
            log.info("turning off time regulation (late joiner)");
            currentTime = super.getLBTS() - super.getLookAhead();
            super.disableTimeRegulation();
        }

        /////////////////////////////////////////////
        // TODO perform basic initialization below //
        /////////////////////////////////////////////

        AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
        putAdvanceTimeRequest(atr);

        if(!super.isLateJoiner()) {
            log.info("waiting on readyToPopulate...");
            readyToPopulate();
            log.info("...synchronized on readyToPopulate");
        }

        ///////////////////////////////////////////////////////////////////////
        // TODO perform initialization that depends on other federates below //
        ///////////////////////////////////////////////////////////////////////

        if(!super.isLateJoiner()) {
            log.info("waiting on readyToRun...");
            readyToRun();
            log.info("...synchronized on readyToRun");
        }

        startAdvanceTimeThread();
        log.info("started logical time progression");

        while (!exitCondition) {
            atr.requestSyncStart();
            enteredTimeGrantedState();

            ////////////////////////////////////////////////////////////
            // TODO objects that must be sent every logical time step //
            ////////////////////////////////////////////////////////////
            //    vTorqueRequestsOv.set_Protocol(<YOUR VALUE HERE >);
            //    vTorqueRequestsOv.set_Value(<YOUR VALUE HERE >);
            //    vTorqueRequestsOv.updateAttributeValues(getLRC(), currentTime + getLookAhead());

            checkReceivedSubscriptions();

            ////////////////////////////////////////////////////////////////////
            // TODO break here if ready to resign and break out of while loop //
            ////////////////////////////////////////////////////////////////////

            if (!exitCondition) {
                currentTime += super.getStepSize();
                AdvanceTimeRequest newATR =
                    new AdvanceTimeRequest(currentTime);
                putAdvanceTimeRequest(newATR);
                atr.requestSyncEnd();
                atr = newATR;
            }
        }

        // call exitGracefully to shut down federate
        exitGracefully();

        //////////////////////////////////////////////////////////////////////
        // TODO Perform whatever cleanups are needed before exiting the app //
        //////////////////////////////////////////////////////////////////////
    }

    private void handleObjectClass(DDTPerfDeviationData object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(EnvironmentScenario object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(OESNominalMessage object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(OESActualMessage object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(FailSafeOpSignal object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(TripPlanMessage object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(LocationTime object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            FederateConfig federateConfig =
                federateConfigParser.parseArgs(args, FederateConfig.class);
            FailOpSafe federate =
                new FailOpSafe(federateConfig);
            federate.execute();
            log.info("Done.");
            System.exit(0);
        }
        catch (Exception e) {
            log.error(e);
            System.exit(1);
        }
    }
}

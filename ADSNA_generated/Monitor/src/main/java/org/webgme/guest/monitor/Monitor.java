package org.webgme.guest.monitor;

import org.webgme.guest.monitor.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Define the Monitor type of federate for the federation.

public class Monitor extends MonitorBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    ////////////////////////////////////////////////////////////////////////
    // TODO instantiate objects that must be sent every logical time step //
    ////////////////////////////////////////////////////////////////////////
    // OESNRevision vOESNRevision =
    //     new OESNRevision();
    // OESAResvision vOESAResvision =
    //     new OESAResvision();
    // ScenarioFeedback vScenarioFeedback =
    //     new ScenarioFeedback();
    // FailSafeOpSignal vFailSafeOpSignal =
    //     new FailSafeOpSignal();
    // DDTPerfDeviationData vDDTPerfDeviationData =
    //     new DDTPerfDeviationData();

    public Monitor(FederateConfig params) throws Exception {
        super(params);

        //////////////////////////////////////////////////////
        // TODO register object instances after super(args) //
        //////////////////////////////////////////////////////
        // vOESNRevision.registerObject(getLRC());
        // vOESAResvision.registerObject(getLRC());
        // vScenarioFeedback.registerObject(getLRC());
        // vFailSafeOpSignal.registerObject(getLRC());
        // vDDTPerfDeviationData.registerObject(getLRC());
    }

    private void checkReceivedSubscriptions() {

        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof EnvironmentScenario) {
                handleObjectClass((EnvironmentScenario) object);
            }
            else if (object instanceof VehiclePhysicsState) {
                handleObjectClass((VehiclePhysicsState) object);
            }
            else if (object instanceof RealTimeCommsData) {
                handleObjectClass((RealTimeCommsData) object);
            }
            else if (object instanceof TorqueRequestsOv) {
                handleObjectClass((TorqueRequestsOv) object);
            }
            else if (object instanceof TorqueRequestsOp) {
                handleObjectClass((TorqueRequestsOp) object);
            }
            else if (object instanceof TripPlanMessage) {
                handleObjectClass((TripPlanMessage) object);
            }
            else if (object instanceof SensorStatus) {
                handleObjectClass((SensorStatus) object);
            }
            else if (object instanceof LocationTime) {
                handleObjectClass((LocationTime) object);
            }
            else if (object instanceof CommsStatus) {
                handleObjectClass((CommsStatus) object);
            }
            else if (object instanceof SensorData) {
                handleObjectClass((SensorData) object);
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
            //    vOESNRevision.set_Protocol(<YOUR VALUE HERE >);
            //    vOESNRevision.set_Value(<YOUR VALUE HERE >);
            //    vOESNRevision.updateAttributeValues(getLRC(), currentTime + getLookAhead());
            //    vOESAResvision.set_Protocol(<YOUR VALUE HERE >);
            //    vOESAResvision.set_Value(<YOUR VALUE HERE >);
            //    vOESAResvision.updateAttributeValues(getLRC(), currentTime + getLookAhead());
            //    vScenarioFeedback.set_Protocol(<YOUR VALUE HERE >);
            //    vScenarioFeedback.set_Value(<YOUR VALUE HERE >);
            //    vScenarioFeedback.updateAttributeValues(getLRC(), currentTime + getLookAhead());
            //    vFailSafeOpSignal.set_Protocol(<YOUR VALUE HERE >);
            //    vFailSafeOpSignal.set_Value(<YOUR VALUE HERE >);
            //    vFailSafeOpSignal.updateAttributeValues(getLRC(), currentTime + getLookAhead());
            //    vDDTPerfDeviationData.set_Protocol(<YOUR VALUE HERE >);
            //    vDDTPerfDeviationData.set_Value(<YOUR VALUE HERE >);
            //    vDDTPerfDeviationData.updateAttributeValues(getLRC(), currentTime + getLookAhead());

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

    private void handleObjectClass(EnvironmentScenario object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(VehiclePhysicsState object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(RealTimeCommsData object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(TorqueRequestsOv object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(TorqueRequestsOp object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(TripPlanMessage object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(SensorStatus object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(LocationTime object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(CommsStatus object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(SensorData object) {
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
            Monitor federate =
                new Monitor(federateConfig);
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

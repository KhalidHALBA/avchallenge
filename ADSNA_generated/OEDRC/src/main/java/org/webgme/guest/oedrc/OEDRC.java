package org.webgme.guest.oedrc;

import org.webgme.guest.oedrc.rti.*;
//import org.webgme.guest.response.rti.VehiclePhysicsState;
//import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import java.util.List;

import java.util.LinkedList;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Define the OEDRC type of federate for the federation.

public class OEDRC extends OEDRCBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    ////////////////////////////////////////////////////////////////////////
    // TODO instantiate objects that must be sent every logical time step //
    ////////////////////////////////////////////////////////////////////////
    // ScenarioDeviationData vScenarioDeviationData =
    //     new ScenarioDeviationData();
    // EnvironmentScenario vEnvironmentScenario =
    //     new EnvironmentScenario();
    
    
    boolean EnvironmentScenario = false;
	public OEDRCConfig Oedrcparameter = new OEDRCConfig();
    private List<EnvironmentScenario> operands = new LinkedList<EnvironmentScenario>();
    public OEDRC(OEDRCConfig params) throws Exception {
        super(params);
        Oedrcparameter = params;
        registerOperandInstances(params.numberOfInputs);
        //////////////////////////////////////////////////////
        // TODO register object instances after super(args) //
        //////////////////////////////////////////////////////
        // vScenarioDeviationData.registerObject(getLRC());
        // vEnvironmentScenario.registerObject(getLRC());
    }

    private void checkReceivedSubscriptions() {

        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof VehiclePhysicsState) {
                handleObjectClass2((VehiclePhysicsState) object);
            }
            else {
                log.debug("unhandled object reflection: {}", object.getClassName());
            }
        }
    }
    

    private void registerOperandInstances(int numberOfInstances) {
        log.trace("registerOperandInstances {}", numberOfInstances);
        
        for (int i = 0; i < numberOfInstances; i++) {
        	EnvironmentScenario ObstacleDetectionInstance = new EnvironmentScenario();
            ObstacleDetectionInstance.registerObject(getLRC());
            operands.add(ObstacleDetectionInstance);
        }

    }

    private void updateObstacleDetectionNotification(String str) {
    	 for (EnvironmentScenario ObstacleDetectionInstance : operands) {
            ObstacleDetectionInstance.set_obstacle_presence(str); 
            ObstacleDetectionInstance.updateAttributeValues(getLRC(), currentTime + this.getLookAhead());
    	 }
   
    }
    
    
    
	public void Send_Obstacle_Notification(int ignite_t) {
		
		if ((ignite_t > Integer.parseInt(Oedrcparameter.sts1))
				&& (ignite_t < Integer.parseInt(Oedrcparameter.ste1))
				|| (ignite_t > Integer.parseInt(Oedrcparameter.sts2))
						&& (ignite_t < Integer.parseInt(Oedrcparameter.ste2))) {
			EnvironmentScenario = true;
			Oedrcparameter.Obstacle_Presence_notification = Boolean.toString(EnvironmentScenario);
		} else {
			EnvironmentScenario = false;
			Oedrcparameter.Obstacle_Presence_notification = Boolean.toString(EnvironmentScenario);
		}
		if ((ignite_t > Integer.parseInt(Oedrcparameter.sts1)
				- Integer.parseInt(Oedrcparameter.IGNITE_LOOKAHEAD))
				&& (ignite_t < Integer.parseInt(Oedrcparameter.ste1)
						- Integer.parseInt(Oedrcparameter.IGNITE_LOOKAHEAD))
				|| (ignite_t > Integer.parseInt(Oedrcparameter.sts2)
						- Integer.parseInt(Oedrcparameter.IGNITE_LOOKAHEAD))
						&& (ignite_t < Integer.parseInt(Oedrcparameter.ste2)
								- Integer.parseInt(Oedrcparameter.IGNITE_LOOKAHEAD))) {
			EnvironmentScenario = true;
			Oedrcparameter.Obstacle_Presence_notification_ahead = Boolean.toString(EnvironmentScenario);
		} else {
			EnvironmentScenario = false;
			Oedrcparameter.Obstacle_Presence_notification_ahead = Boolean.toString(EnvironmentScenario);
		}

		log.info( " time " +currentTime+  " event " + Oedrcparameter.Obstacle_Presence_notification + " event_ahead " 
				+ Oedrcparameter.Obstacle_Presence_notification_ahead ) ;
		
	}
    
    
    
	public String Build_SPN() {
		return Oedrcparameter.EventInjectionSPNs = Oedrcparameter.Obstacle_Presence_notification + " "+ Oedrcparameter.Obstacle_Presence_notification_ahead; 
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
            //    vScenarioDeviationData.set_Protocol(<YOUR VALUE HERE >);
            //    vScenarioDeviationData.set_Value(<YOUR VALUE HERE >);
            //    vScenarioDeviationData.updateAttributeValues(getLRC(), currentTime + getLookAhead());
            //    vEnvironmentScenario.set_Protocol(<YOUR VALUE HERE >);
            //    vEnvironmentScenario.set_obstacle_presence(<YOUR VALUE HERE >);
            //    vEnvironmentScenario.set_obstacle_presence_ahead(<YOUR VALUE HERE >);
            //    vEnvironmentScenario.updateAttributeValues(getLRC(), currentTime + getLookAhead());

            checkReceivedSubscriptions();
    		
    		
    		
			int osd = (int) (currentTime) % 2;
			double ignite_time = Double.parseDouble(Oedrcparameter.IGNITE_TIME_1);
			switch (osd) {
			case 0:
            Send_Obstacle_Notification((int)(ignite_time));
            updateObstacleDetectionNotification(Build_SPN());
			break;
			}

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

    private void handleObjectClass(RealTimeCommsData object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(TripPlanMessage object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(Injections object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(SensorData object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }


    private void handleObjectClass2(VehiclePhysicsState object) {
    	

    	String delims = "[ ]+";
		String[] CSPNs = object.get_otherPhysicsSignals().split(delims);

		Oedrcparameter.IGNITE_TIME_1 = CSPNs[2];


    }
    
    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            OEDRCConfig federateConfig =
                federateConfigParser.parseArgs(args, OEDRCConfig.class);
            OEDRC federate =
                new OEDRC(federateConfig);
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

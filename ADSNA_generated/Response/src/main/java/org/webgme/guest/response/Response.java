package org.webgme.guest.response;

import org.webgme.guest.response.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Define the Response type of federate for the federation.

public class Response extends ResponseBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;
    
	int e,j,g = 0;
	String Drive_Cycle_Speed, Drive_Cycle_Speed_ahead,Drive_Cycle_Speed_ahead_1  = "0";
	double speed_, speed_ahead_ = 0;

	public ResponseConfig Responseparameter = new ResponseConfig();

	private List<TorqueRequestsOp> operands1 = new LinkedList<TorqueRequestsOp>();

    private void checkReceivedSubscriptions() {

        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof VehiclePhysicsState) {
                handleObjectClass2((VehiclePhysicsState) object);
            }
            else if (object instanceof EnvironmentScenario) {
                handleObjectClass((EnvironmentScenario) object);
            }

            else {
                log.debug("unhandled object reflection: {}", object.getClassName());
            }
        }
    }
    
    public Response(ResponseConfig params) throws Exception {
        super(params);
        
        
        Responseparameter = params;
        
      	 
      	 
      	 
        registerSpeedInstances(params.numberOfInputs);

    }
    
    private void registerSpeedInstances(int numberOfInstances) {
        log.trace("registerOperandInstances {}", numberOfInstances);
        
        for (int i = 0; i < numberOfInstances; i++) {

        	TorqueRequestsOp TorqueRequestsOp = new TorqueRequestsOp();
        	TorqueRequestsOp.registerObject(getLRC());
            operands1.add(TorqueRequestsOp);

        }

    }

    private void updateSpeedRequest(String str) {
   	 for (TorqueRequestsOp TorqueRequestsOp : operands1) {
   		TorqueRequestsOp.set_Attribute(str); 
   		TorqueRequestsOp.updateAttributeValues(getLRC(), currentTime + this.getLookAhead());
   	 }
  
   }
    
    
	public void Control(double IGNITE_TIME__) {
		try {
			if ((int) (Double.parseDouble(Responseparameter.IGNITE_TIME)) >= 0) {
				Drive_Cycle_Speed = Files.readAllLines(Paths.get(Responseparameter.Drive_Cycle))
						.get((int) IGNITE_TIME__);
				Drive_Cycle_Speed_ahead = Files.readAllLines(Paths.get(Responseparameter.Drive_Cycle))
						.get((int) IGNITE_TIME__ + Integer.parseInt(Responseparameter.IGNITE_LOOKAHEAD));
				Drive_Cycle_Speed_ahead_1 = Files.readAllLines(Paths.get(Responseparameter.Drive_Cycle))
						.get((int) IGNITE_TIME__ + 2);
			}
			if (Responseparameter.EventInjection_Obstacle_Presence.equals("true")) {
				j++;
				if (j < 10) {
					Drive_Cycle_Speed_ahead = "0.2";
					int sampling_factor = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
					double sampling_step_ = Responseparameter.sampling_rate
							* (Double.parseDouble(Drive_Cycle_Speed_ahead) - Double.parseDouble(Drive_Cycle_Speed));
					DecimalFormat f = new DecimalFormat("##.000");
					speed_ = Double.parseDouble(
							f.format(Double.parseDouble(Drive_Cycle_Speed) + sampling_factor * sampling_step_));
					Responseparameter.Vehicle_Control_Speed = Double.toString(speed_);
				} else {
					Responseparameter.Vehicle_Control_Speed = "0.2";
				}
			} else {
				j = 0;
				int sampling_factor_ = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
				double sampling_step_ = Responseparameter.sampling_rate
						* (Double.parseDouble(Drive_Cycle_Speed_ahead) - Double.parseDouble(Drive_Cycle_Speed));
				DecimalFormat f = new DecimalFormat("##.000");
				speed_ = Double.parseDouble(
						f.format(Double.parseDouble(Drive_Cycle_Speed) + sampling_factor_ * sampling_step_));
				Responseparameter.Vehicle_Control_Speed = Double.toString(speed_);
			}
			if (Responseparameter.EventInjection_Obstacle_Presence_Ahead.equals("true")) {
				g++;
				if (g < 10) {
					Drive_Cycle_Speed_ahead_1 = "0.2";
					int sampling_factor_ahead = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
					double sampling_step_ahead = Responseparameter.sampling_rate
							* (Double.parseDouble(Drive_Cycle_Speed_ahead_1)
									- Double.parseDouble(Drive_Cycle_Speed_ahead));
					DecimalFormat f = new DecimalFormat("##.000");
					speed_ahead_ = Double.parseDouble(f.format(
							Double.parseDouble(Drive_Cycle_Speed_ahead) + sampling_factor_ahead * sampling_step_ahead));

					Responseparameter.Speed_Control_Ahead_VC = Double.toString(speed_ahead_);

				} else {
					Responseparameter.Speed_Control_Ahead_VC = "0.2";
				}
			} else {
				g = 0;
				int sampling_factor_ahead = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
				double sampling_step_ahead = Responseparameter.sampling_rate
						* (Double.parseDouble(Drive_Cycle_Speed_ahead_1) - Double.parseDouble(Drive_Cycle_Speed_ahead));

				DecimalFormat f = new DecimalFormat("##.000");
				speed_ahead_ = Double.parseDouble(f.format(
						Double.parseDouble(Drive_Cycle_Speed_ahead) + sampling_factor_ahead * sampling_step_ahead));

				Responseparameter.Speed_Control_Ahead_VC = Double.toString(speed_ahead_);
			}
		} catch (Exception e) {

		}
	}
	public String Build_SPN()

	{
		log.info("time "+ Double.toString(currentTime) +" speed  " + Responseparameter.Vehicle_Control_Speed +" Speed "+ Responseparameter.Speed_Control_Ahead_VC);
		return Responseparameter.VehicleControlSPNs = Responseparameter.Vehicle_Control_Speed + " "
				+ Responseparameter.UCEF_Control_Speed + " " + Responseparameter.VehicleControl_Event_Status
				+ " " + Responseparameter.IGNITE_LOOKAHEAD + " " + Responseparameter.Speed_Control_Ahead_VC + " " +Responseparameter.Vehicle_Control_Speed;
	}
    
    


    private void execute() throws Exception {
        if(super.isLateJoiner()) {
            currentTime = super.getLBTS() - super.getLookAhead();
            super.disableTimeRegulation();
        }

        /////////////////////////////////////////////
        // TODO perform basic initialization below //
        /////////////////////////////////////////////

        AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
        putAdvanceTimeRequest(atr);

        if(!super.isLateJoiner()) {
            readyToPopulate();
        }

        if(!super.isLateJoiner()) {
            //log.info("waiting on readyToRun...");
            readyToRun();
            //log.info("...synchronized on readyToRun");
        }

        startAdvanceTimeThread();
        while (!exitCondition) {
            atr.requestSyncStart();
            enteredTimeGrantedState();
            checkReceivedSubscriptions();
            int osd = (int) (currentTime) %2;
            double IGNITE_TIME_d = (Double.parseDouble(Responseparameter.IGNITE_TIME));
			switch (osd) {
			case 0:
				Control(IGNITE_TIME_d);
				updateSpeedRequest( Build_SPN());
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

        exitGracefully();

    }

    private void handleObjectClass(DDTPerfDeviationData object) {

    }

    private void handleObjectClass(EnvironmentScenario object) {

		String delims = "[ ]+";
		String[] CSPNs = object.get_obstacle_presence().split(delims);

		Responseparameter.EventInjection_Obstacle_Presence = CSPNs[0];
		Responseparameter.EventInjection_Obstacle_Presence_Ahead = CSPNs[1];

    }



    private void handleObjectClass2(VehiclePhysicsState object) {
    	String delims = "[ ]+";
		String[] CSPNs = object.get_otherPhysicsSignals().split(delims);

		Responseparameter.IGNITE_TIME = CSPNs[2];


    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            ResponseConfig federateConfig =
                federateConfigParser.parseArgs(args, ResponseConfig.class);
            Response federate =
                new Response(federateConfig);
            federate.execute();
            //log.info("Done.");
            System.exit(0);
        }
        catch (Exception e) {
            log.error(e);
            System.exit(1);
        }
    }
}
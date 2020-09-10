package org.webgme.guest.physicsengine;

import org.webgme.guest.physicsengine.rti.*;

import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import java.util.List;
import java.util.Queue;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Define the PhysicsEngine type of federate for the federation.

public class PhysicsEngine extends PhysicsEngineBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    ////////////////////////////////////////////////////////////////////////
    // TODO instantiate objects that must be sent every logical time step //
    ////////////////////////////////////////////////////////////////////////
    // VehiclePhysicsState vVehiclePhysicsState =
    //     new VehiclePhysicsState();

    
	String status = "0";

	public PhysicsEngineConfig PhysicsEngineparameter = new PhysicsEngineConfig();

	 private List<VehiclePhysicsState> operands1 = new LinkedList<VehiclePhysicsState>();


	 
	Queue<String> s = new LinkedList<String>();
	Queue<Double> t = new LinkedList<Double>();
	Queue<String> s1 = new LinkedList<String>();
	Queue<Double> t1 = new LinkedList<Double>();

	public PhysicsEngine(PhysicsEngineConfig params) throws Exception {
		super(params);

		PhysicsEngineparameter = params;
		registerIGNITEInstances(params.numberOfInputs);
		


	}
	
    
    private void registerIGNITEInstances(int numberOfInstances) {
        log.trace("registerOperandInstances {}", numberOfInstances);
        
        for (int i = 0; i < numberOfInstances; i++) {

        	VehiclePhysicsState VehiclePhysicsState = new VehiclePhysicsState();

        	VehiclePhysicsState.registerObject(getLRC());
            operands1.add(VehiclePhysicsState);


        }

    }

    private void updateEnginePhysics(String str) {
    	
    
    	 for (VehiclePhysicsState VehiclePhysicsState1 : operands1) {
    		 VehiclePhysicsState1.set_otherPhysicsSignals(str); 
    		 VehiclePhysicsState1.updateAttributeValues(getLRC(), currentTime + this.getLookAhead());


    	 }
   
    }

    
    private void checkReceivedSubscriptions() {

        ObjectReflector reflector = null;
        if ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof TorqueRequestsOp) {
                handleObjectClass((TorqueRequestsOp) object);
            }
            
            else {
                log.debug("unhandled object reflection: {}", object.getClassName());
            }
        }
        
        
        else
        	
        	
        {
        	
        	
			if (s.size() == 0)

			{
				s.add("0");
				t.add(0.0);
			} else {
				s.add(s.peek());
				t.add(t.peek());

			}
        	
        	
        	
        }
    }
    
    
    
    
    
    
    private void execute() throws Exception {
        if(super.isLateJoiner()) {
            ////log.info("turning off time regulation (late joiner)");
            currentTime = super.getLBTS() - super.getLookAhead();
            super.disableTimeRegulation();
        }

        /////////////////////////////////////////////
        // TODO perform basic initialization below //
        /////////////////////////////////////////////

        AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
        putAdvanceTimeRequest(atr);

        if(!super.isLateJoiner()) {
            ////log.info("waiting on readyToPopulate...");
            readyToPopulate();
            ////log.info("...synchronized on readyToPopulate");
        }

        ///////////////////////////////////////////////////////////////////////
        // TODO perform initialization that depends on other federates below //
        ///////////////////////////////////////////////////////////////////////

        if(!super.isLateJoiner()) {
            ////log.info("waiting on readyToRun...");
            readyToRun();
            ////log.info("...synchronized on readyToRun");
        }

        startAdvanceTimeThread();
        ////log.info("started logical time progression");

        while (!exitCondition) {
            atr.requestSyncStart();
            enteredTimeGrantedState();



            checkReceivedSubscriptions();


            
            
			int osd = (int) (currentTime) % 2;
			switch (osd) {

			case 0:

				byte[] receiveData = new byte[1024];
				updateEnginePhysics(Build_SPN());

				Send_Receive(receiveData);

				break;

			}

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

    
	public void Send_Receive(byte[] receiveData) {

		try {
			log.info("on sending : speed "+  PhysicsEngineparameter.UCEF_Vehicle_Speed_Control + " speed_ahead	" +PhysicsEngineparameter.Speed_Control_Ahead_GW );

			DatagramSocket clientSocket = new DatagramSocket();

			InetAddress IPAddress = InetAddress.getByName(PhysicsEngineparameter.IGNITE_IP);
			byte[] sendData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			sendData = ((int) (1000 * Double.parseDouble(PhysicsEngineparameter.UCEF_Vehicle_Speed_Control)) + " " + (int) (1000 * Double.parseDouble(PhysicsEngineparameter.Speed_Control_Ahead_GW))).getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
			clientSocket.send(sendPacket);
			receivePacket.setLength(receiveData.length);
			clientSocket.receive(receivePacket);
			String vehicleResponse = new String(receivePacket.getData());
			String[] speeds = vehicleResponse.split(" ");
			float f = Float.valueOf(speeds[0].trim()).floatValue();
			float f1 = f / 100;
			PhysicsEngineparameter.Vehicle_Speed_Response = Double.toString(f1);
			float b = Float.valueOf(speeds[1].trim()).floatValue();
			float b1 = b / 100;
			PhysicsEngineparameter.Brake_Pressure = Double.toString(b1);
			float t = Float.valueOf(speeds[2].trim()).floatValue();
			float t1 = t / 100;
			PhysicsEngineparameter.IGNITE_Cycle_Time = Double.toString(t1);
			clientSocket.close();

			log.info("on receiving : vehicle_response "+ PhysicsEngineparameter.Vehicle_Speed_Response  + "	vehicle_braking " +PhysicsEngineparameter.Brake_Pressure + " ignite_time " + PhysicsEngineparameter.IGNITE_Cycle_Time);

		} catch (Exception e) {

			 System.out.println("THIS is an exception "+e.getMessage());

		}

	}
    
    
	public String Build_SPN() {


		
		return PhysicsEngineparameter.UCEFGatewaySPNs = PhysicsEngineparameter.Vehicle_Speed_Response + " "
				+ PhysicsEngineparameter.Brake_Pressure + " " + PhysicsEngineparameter.IGNITE_Cycle_Time ;
		
		
	}
    
	   public static boolean isNullOrEmpty(String str) {
	        if(str != null && !str.trim().isEmpty())
	            return false;
	        return true;
	    }
	
private void handleObjectClass(TorqueRequestsOp object) {
	
try

{
    		String delims = "[ ]+";
    		String[] CSPNs = object.get_Attribute().split(delims);
    		PhysicsEngineparameter.Speed_Control_Ahead_GW = CSPNs[4];    
    		PhysicsEngineparameter.UCEF_Vehicle_Speed_Control = CSPNs[5];   
}

catch(ArrayIndexOutOfBoundsException e)

{


}



    }
    

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            PhysicsEngineConfig federateConfig =
                federateConfigParser.parseArgs(args, PhysicsEngineConfig.class);
            PhysicsEngine federate =
                new PhysicsEngine(federateConfig);
            federate.execute();
            System.exit(0);
        }
        catch (Exception e) {
            log.error(e);
            System.exit(1);
        }
    }
}

package org.webgme.guest.vehiclecontrol;

import org.webgme.guest.vehiclecontrol.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.RandomStringUtils;
// Define the VehicleControl type of federate for the federation.

public class VehicleControl extends VehicleControlBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;
    
    String speed1 = "0";
	   
		String speed = "0";


    public VehicleControlConfig VehicleControlparameter = new VehicleControlConfig();
    CAN VehicleControlCAN = create_CAN();
    

    public VehicleControl(VehicleControlConfig params) throws Exception {
        super(params);
        

        VehicleControlparameter.UCEFGateway_Motor_Operating_Mode= params.UCEFGateway_Motor_Operating_Mode;
        	
        VehicleControlparameter.UCEFGateway_Motor_Torque_cmd= params.UCEFGateway_Motor_Torque_cmd;
       	
        VehicleControlparameter.UCEFGateway_Motor_Speed= params.UCEFGateway_Motor_Speed;
       	
        VehicleControlparameter.UCEFGateway_Volt_Cmd= params.UCEFGateway_Volt_Cmd;
        
        
        
        VehicleControlparameter.messageTime= params.messageTime;
        VehicleControlparameter.Wheel_Speed= params.Wheel_Speed;
        VehicleControlparameter.VehicleControl_Event_Status= params.VehicleControl_Event_Status; 
        VehicleControlparameter.Traction_Stability_Torque_Request= params.Traction_Stability_Torque_Request;   
//        VehicleControlparameter.UCEFGateway_Motor_Torque= params.UCEFGateway_Motor_Torque; 
        VehicleControlparameter.UCEFGateway_PGN = params.UCEFGateway_PGN;
        VehicleControlparameter.Wheel_Speed_Sensors= params.Wheel_Speed_Sensors; 
        VehicleControlparameter.Hydraulic_Valve_Commands= params.Hydraulic_Valve_Commands;
        VehicleControlparameter.VehicleControlPGN= params.VehicleControlPGN;  
        VehicleControlparameter.VehicleControlSPNs= params.VehicleControlSPNs; 
        VehicleControlparameter.Vehicle_Speed= params.Vehicle_Speed;
        VehicleControlparameter.EventInjection_Obstacle_Presence_distance = params.EventInjection_Obstacle_Presence_distance;
            
    }
    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof CAN) {
                handleInteractionClass((CAN) interaction);
            }
            else {
                log.debug("unhandled interaction: {}", interaction.getClassName());
            }
        }
    }

    
    public void VehicleControl_Event_Status  ()
    
    {
 	   
 	    int length = 10;
 	    boolean useLetters = true;
 	    boolean useNumbers = false;
 	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
 	    VehicleControlparameter.VehicleControl_Event_Status= generatedString;
 	   
 	  }
    
    
    
    
    
    public void Control(int speedline)
    
    {
 	   
 	   
 	   
 	   
 
 	   
 	   
 		try {

 			
 			if(speedline-1>=0)
 			{
 			 speed1 = Files.readAllLines(Paths.get("/home/vagrant/avchallenge/ADS_generated/VehicleControl/src/main/java/org/webgme/guest/vehiclecontrol/FTP75.txt")).get(speedline);
 			}

 		     
 			 log.info("Control Input :  Vehicle Response " + VehicleControlparameter.UCEFGateway_Motor_Torque_cmd+ " Obstacle Notification " +  VehicleControlparameter.EventInjection_Obstacle_Presence_distance  + " DriveCycle Data "+ speed1 );
// 			 double average = (Double.valueOf(VehicleControlparameter.EventInjection_Obstacle_Presence_distance))*0.1 +Double.valueOf(speed);
 			 
// 			log.info("average drivecycle and vehicle response" + speed1);
// 		      
 		      if(VehicleControlparameter.EventInjection_Obstacle_Presence_distance.equals("true") )
 		      {
 		    	  
 		    	  
 		    	  speed = "0";
 		    	  VehicleControlparameter.Wheel_Speed = speed;
 		    	 VehicleControlparameter.Wheel_Speed_Sensors = speed1;
 		    	  System.out.println(" obstacle detected : speed " + VehicleControlparameter.Wheel_Speed + " time " + speedline);
 		    	  // replace with transmit frame
// 		      sendData = speed.getBytes();
 		      }
 		      else
 		      {

 		    	 VehicleControlparameter.Wheel_Speed_Sensors = speed1;
 		       	VehicleControlparameter.Wheel_Speed = speed1;
 		       System.out.println("  obstacle NOT detected : speed " + 	VehicleControlparameter.Wheel_Speed + " time " + speedline + " currentTime" + currentTime);
 		       	  
// 		       	VehicleControlparameter.Wheel_Speed = Double.toString(average);
 		    		  // replace with transmit frame
// 		      sendData = speed.getBytes();
 		      }


 		      
 		   
          } catch (Exception e) {
          System.out.println(e);
          }
 	   
 	   
 	   
 	   
    }
    
    
    
    
    
    
//public void Control(int speedline)
//    
//    {
// 	   
// 	   
// 	   
// 	   
// 	   
// 		String speed = "0";
//
//// 	   
//// 	   
//// 		try {
////
//// 			
// 			if(speedline-1>=0)
// 			{
// 			 try {
//				speed = Files.readAllLines(Paths.get("/home/vagrant/Desktop/ADS/ADS_generated/VehicleControl/src/main/java/org/webgme/guest/vehiclecontrol/FTP75.txt")).get(speedline);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
// 			}
////
//// 		     
//// 			 log.info("Control Input :  Vehicle Response " + VehicleControlparameter.UCEFGateway_Motor_Torque_cmd+ " Obstacle Notification " +  VehicleControlparameter.EventInjection_Obstacle_Presence_distance  + " DriveCycle Data "+ speed );
////// 			 double average = (Double.valueOf(VehicleControlparameter.EventInjection_Obstacle_Presence_distance))*0.1 +Double.valueOf(speed);
//// 			 
//// 			log.info("average drivecycle and vehicle response" + speed);
////// 		      
//// 		      if(VehicleControlparameter.EventInjection_Obstacle_Presence_distance.equals("true") )
//// 		      {
//// 		    	  
//// 		    	  
//// 		    	  speed = "0";
//// 		    	  VehicleControlparameter.Wheel_Speed = speed;
//// 		    	  
//// 		    	  System.out.println(" obstacle detected stopping car" + speed + " time " + speedline);
//// 		    	  // replace with transmit frame
////// 		      sendData = speed.getBytes();
//// 		      }
//// 		      else
//// 		      {
//
// 		       	  System.out.println(" speed " + speed + " time " + speedline + " currentTime" + currentTime);
// 		       	VehicleControlparameter.Wheel_Speed = speed;
// 		       	  
//// 		       	VehicleControlparameter.Wheel_Speed = Double.toString(average);
// 		    		  // replace with transmit frame
//// 		      sendData = speed.getBytes();
//// 		      }
//
//
// 		      
//// 		   
////          } catch (Exception e) {
////          System.out.println(e);
////          }
//// 	   
// 	   
// 	   
// 	   
//    }
//    
    
    
    
    
    
    
    
    public String Build_SPN()
    
    {
 	   return VehicleControlparameter.VehicleControlSPNs= VehicleControlparameter.Wheel_Speed +" "+  VehicleControlparameter.Wheel_Speed_Sensors +" "+ VehicleControlparameter.VehicleControl_Event_Status+" " + VehicleControlparameter.Traction_Stability_Torque_Request;
    }
     
     
     
     public void Build_and_Send_CAN_Frame(String pgn,String spn)
     
     {

  	
  	   
  
         VehicleControlCAN.set_ID18B(pgn);
         VehicleControlCAN.set_DataField(spn);
         VehicleControlCAN.sendInteraction(getLRC(), currentTime + getLookAhead());
      
  	   
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
            // TODO send interactions that must be sent every logical //
            // time step below                                        //
            ////////////////////////////////////////////////////////////

            // Set the interaction's parameters.
            //
            //    CAN vCAN = create_CAN();
            //    vCAN.set_ACKslot( < YOUR VALUE HERE > );
            //    vCAN.set_CRC( < YOUR VALUE HERE > );
            //    vCAN.set_DLC( < YOUR VALUE HERE > );
            //    vCAN.set_DataField( < YOUR VALUE HERE > );
            //    vCAN.set_EndOfFrame( < YOUR VALUE HERE > );
            //    vCAN.set_ID11B( < YOUR VALUE HERE > );
            //    vCAN.set_ID18B( < YOUR VALUE HERE > );
            //    vCAN.set_IDE( < YOUR VALUE HERE > );
            //    vCAN.set_Parameter( < YOUR VALUE HERE > );
            //    vCAN.set_RTR( < YOUR VALUE HERE > );
            //    vCAN.set_ReservedBit1( < YOUR VALUE HERE > );
            //    vCAN.set_ReservedBit2( < YOUR VALUE HERE > );
            //    vCAN.set_SRR( < YOUR VALUE HERE > );
            //    vCAN.set_StartOfFrame( < YOUR VALUE HERE > );
            //    vCAN.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    vCAN.set_federateFilter( < YOUR VALUE HERE > );
            //    vCAN.set_originFed( < YOUR VALUE HERE > );
            //    vCAN.set_sourceFed( < YOUR VALUE HERE > );
            //    vCAN.sendInteraction(getLRC(), currentTime + getLookAhead());

            checkReceivedSubscriptions();
            int osd = (int)(currentTime) % 20;

      	   switch (osd)
      	   {

      	   case 0:  

   
      	   
             break;
      	   
      	   case 1:
      		   Control((int)(currentTime/20));
      		   
      	   	   Build_and_Send_CAN_Frame( VehicleControlparameter.VehicleControlPGN, Build_SPN());
//      		 log.info(" speed " + VehicleControlparameter.UCEFGateway_Motor_Speed+ " response " +   VehicleControlparameter.UCEFGateway_Motor_Torque_cmd );
                 break;
             
      	   case 6:
//      		 VehicleControl_Event_Status  (); 
//      	   Build_and_Send_CAN_Frame( VehicleControlparameter.VehicleControlPGN, Build_SPN());  
      	           
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

    private void handleInteractionClass(CAN interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    	String delims = "[ ]+";
    	String[] CSPNs = interaction.get_DataField().split(delims);
    switch (interaction.get_ID18B()) {
    case "EventInjection":  
   	 VehicleControlparameter.EventInjection_Obstacle_Presence_distance               = CSPNs[0];
 	   VehicleControlparameter.messageTime=interaction.getTime();
 	   
         break;        
    	
     case "UCEFGateway":  
    	 VehicleControlparameter.UCEFGateway_Motor_Torque_cmd             = CSPNs[0];
    	 VehicleControlparameter.UCEFGateway_Motor_Speed               = CSPNs[1];
  	   VehicleControlparameter.messageTime=interaction.getTime();
  	   
          break;      	
    		
    }
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser = new FederateConfigParser();
            VehicleControlConfig federateConfig = federateConfigParser.parseArgs(args, VehicleControlConfig.class);
            VehicleControl federate = new VehicleControl(federateConfig);
            federate.execute();
            log.info("Done.");
            System.exit(0);
        } catch (Exception e) {
            log.error(e);
            System.exit(1);
        }
    }
}

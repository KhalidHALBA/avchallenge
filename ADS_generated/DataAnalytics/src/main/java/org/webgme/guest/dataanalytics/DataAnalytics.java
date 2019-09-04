package org.webgme.guest.dataanalytics;

import org.webgme.guest.dataanalytics.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;




// Define the DataAnalytics type of federate for the federation.

public class DataAnalytics extends DataAnalyticsBase {
	
	ExecutorService myExecutor = Executors.newCachedThreadPool(); 
	
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    
 public DataAnalyticsConfig DataAnalyticsparameter = new DataAnalyticsConfig();
    
    CAN DataAnalyticsCAN = create_CAN();

    public DataAnalytics(DataAnalyticsConfig params) throws Exception {
        super(params);
        DataAnalyticsparameter.Engine_Speed = params.Engine_Speed ;
        DataAnalyticsparameter.Engine_Temperature = params.Engine_Temperature ;
        DataAnalyticsparameter.Motor_Power_Limits = params.Motor_Power_Limits ;
        DataAnalyticsparameter.Inverter_Temperature = params.Inverter_Temperature ;
        DataAnalyticsparameter.UCEFGateway_Torque_Commands = params.UCEFGateway_Torque_Commands ;
        DataAnalyticsparameter.Motor_Speed = params.Motor_Speed ;
        DataAnalyticsparameter.DataAnalyticsPGN = params.DataAnalyticsPGN ;
        DataAnalyticsparameter.DataAnalyticsSPNs = params.DataAnalyticsSPNs ;
        DataAnalyticsparameter.messageTime = params.messageTime ;
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

    
    
    
    public void Sense_speed()

    {
    	DataAnalyticsparameter.Engine_Speed       = Integer.toString(ThreadLocalRandom.current().nextInt(600, 1000 + 1)); 
    }

    
    public String Build_SPN()
    
    {  
    	return DataAnalyticsparameter.DataAnalyticsSPNs = DataAnalyticsparameter.Engine_Temperature + " " + DataAnalyticsparameter.Engine_Speed  + " " +DataAnalyticsparameter.Motor_Power_Limits  + " " +  DataAnalyticsparameter.Inverter_Temperature  ;
    	
    }
     
    
    public void AV_Log()
    
    {
    	
    String UCEFGateway_Torque_Message =  "UCEFGateway_Torque_Commands : " + DataAnalyticsparameter.UCEFGateway_Torque_Commands + " & current time is  " + currentTime + "  message time is " + DataAnalyticsparameter.messageTime +  " case " + (int)DataAnalyticsparameter.messageTime % 10 ;
    log.info("UCEFGateway_Torque_Commands "+UCEFGateway_Torque_Message);
    
    }
    
    
    
    public void Build_and_Send_CAN_Frame(String pgn,String spn)
    
    { 
    DataAnalyticsCAN.set_ID18B(DataAnalyticsparameter.DataAnalyticsPGN);
    DataAnalyticsCAN.set_DataField(DataAnalyticsparameter.DataAnalyticsSPNs);
    DataAnalyticsCAN.sendInteraction(getLRC(), currentTime + getLookAhead());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void update_chart(int ost , int speed, XYChart chart, SwingWrapper<XYChart> sw, double[] xData, ArrayList<Integer> yData) throws Exception
    
    {
    	  	 
	      final ArrayList<Integer> data = fill_array(ost,speed,xData, yData);

	      System.out.println();
	 
	      chart.getStyler().setYAxisMax((double) 60);
	      chart.getStyler().setYAxisMin(null, (double)0);
	      
	      
	      chart.getStyler().setXAxisMax((double) 120);
	      chart.getStyler().setXAxisMin((double)0);
	      
	      
	      System.out.println("Step Number : "+ost);

	      for(int i=0; i<data.size(); i++)
	      {
	    	  System.out.println("Speed Array : "+i+" = " +data.get(i));
	      }
    	 
      Thread.sleep(ost);
    	 
	      chart.updateXYSeries("Vehicle_Response", null, data, null);
	      sw.repaintChart();
    	
    	
    }
     
    
    
	  private static ArrayList<Integer> fill_array(int time, int speed, double[] xData, ArrayList<Integer> yData) throws Exception{
		    		  xData[time] =  time;
				      yData.add(time, speed);
		    return yData ;
		  }
	
	
    
    
    
    
    private void execute() throws Exception {
    	

	    ArrayList<Integer> initdata = new ArrayList<Integer>();
	    initdata.add(0);

	    final XYChart chart = QuickChart.getChart("IGNITE Vehicle Response", "Time", "Speed", "Vehicle_Response", null, initdata);

	    final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
	    sw.displayChart();
	 
	    
	    




    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
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
        
        
        
        
		   double[] xData = new double[120];
		   ArrayList<Integer> yData = new ArrayList<Integer>();
        
        

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

            
        	final int  speed = (int)(Double.parseDouble(DataAnalyticsparameter.Motor_Power_Limits)); 
            //     double something =  Double.parseDouble(MCUparameter.VCU_Torque_Commands);
             	
                 int osd = (int)(currentTime) % 20;
                 int time = (int)(currentTime/20);

          		 log.info("time   "+ time + "   speed  " +speed + "  current_time "  + currentTime);
         		   

      
                 
           	   switch (osd)
           	   {

           	   
           	   
           	   
           	   
           	   
           	   case 1:
           		 

           		   
           		    myExecutor.execute(new Runnable() {
            		        public void run() {
            		          try {
            		        	  
            		        	update_chart(time , speed, chart, sw, xData, yData);
         					} catch (Exception e) {
         						// TODO Auto-generated catch block
         						e.printStackTrace();
         					}
            		        }
            		    });
                     
           		   
           		   
           		
           		 break;
           		   
           	   case 4:
           
                   Sense_speed();
//                   Calculate_power_limits();
                   Build_and_Send_CAN_Frame( DataAnalyticsparameter.DataAnalyticsPGN, Build_SPN());
                   
                   break;
                   
                   
           	   case 9:
           		   
           		   checkReceivedSubscriptions();
           		   AV_Log();
           		
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
            case "UCEFGateway":
            	DataAnalyticsparameter.UCEFGateway_Torque_Commands      = CSPNs[1];
            	DataAnalyticsparameter.Motor_Power_Limits      = CSPNs[0];
            	DataAnalyticsparameter.messageTime=interaction.getTime();
       
                break;   
    
    	}
    	
    	
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser = new FederateConfigParser();
            DataAnalyticsConfig federateConfig = federateConfigParser.parseArgs(args, DataAnalyticsConfig.class);
            DataAnalytics federate = new DataAnalytics(federateConfig);
            federate.execute();
            log.info("Done.");
            System.exit(0);
        } catch (Exception e) {
            log.error(e);
            System.exit(1);
        }
    }
}

package org.webgme.guest.visualizeanalytics;

import org.webgme.guest.visualizeanalytics.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.awt.Font;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.cpswt.hla.base.ObjectReflector;


// Define the VisualizeAnalytics type of federate for the federation.

public class VisualizeAnalytics extends VisualizeAnalyticsBase {
	
	
	ExecutorService myExecutor = Executors.newCachedThreadPool();
	
	
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

	public VisualizeAnalyticsConfig VisualizeAnalyticsparameter = new VisualizeAnalyticsConfig();

	  
	
    
    
    
    
    public VisualizeAnalytics(VisualizeAnalyticsConfig params) throws Exception {
        super(params);
        VisualizeAnalyticsparameter = params;
    }

    private void checkReceivedSubscriptions() {

        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof VehiclePhysicsState) {
            	handleObjectClass((VehiclePhysicsState) object);
            }
            else if (object instanceof TorqueRequestsOp) {
//            if (object instanceof TorqueRequestsOp) {
                handleObjectClass1((TorqueRequestsOp) object);
            }
//            else if (object instanceof TripPlanMessage) {
//                handleObjectClass((TripPlanMessage) object);
//            }
            else {
                log.debug("unhandled object reflection: {}", object.getClassName());
            }
        }
    }

    
    
	public void update_chart(int ost, double speed, double speed1, double brake, XYChart chart,
			SwingWrapper<XYChart> sw, double[] xData, double[] xData1, double[] xData2, ArrayList<Double> yData,
			ArrayList<Double> yData1, ArrayList<Double> yData2) throws Exception

	{

		final ArrayList<Double> data1 = fill_array(ost, speed1, xData, yData1);
		final ArrayList<Double> data2 = fill_array(ost, brake, xData2, yData2);
		final ArrayList<Double> data = fill_array(ost, speed, xData, yData);

		chart.getStyler().setYAxisMax((double) 100);
		chart.getStyler().setYAxisMin(null, (double) 0);
		chart.getStyler().setXAxisMax((double) 3500);
		chart.getStyler().setXAxisMin((double) 0);
		chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideN);
		chart.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		chart.getStyler().setAxisTickLabelsFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		chart.getStyler().setLegendFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		Thread.sleep(ost);

		chart.updateXYSeries("Vehicle_Speed", null, data, null);
		chart.updateXYSeries("Speed_Request", null, data1, null).setMarker(SeriesMarkers.NONE)
				.setLineColor(XChartSeriesColors.RED).setLineStyle(SeriesLines.SOLID).setLineWidth(2);
		chart.updateXYSeries("Brake_demand", null, data2, null).setMarker(SeriesMarkers.NONE)
				.setLineColor(XChartSeriesColors.BLACK).setLineStyle(SeriesLines.DASH_DOT).setLineWidth(2);
		sw.repaintChart();

	}
    
	private static ArrayList<Double> fill_array(int time, double speed, double[] xData, ArrayList<Double> yData)
			throws Exception {
		xData[time] = time;
		yData.add(time, speed);
		return yData;
	}
    
	
	

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void execute() throws Exception {
    	
		ArrayList<Double> initdata = new ArrayList<Double>();
		initdata.add(0.0);

		XYChart chart = QuickChart.getChart("UCEF-IGNITE-Analytics", "Time [0.1 second]",
				"Speed [kph] / Brake_demand [0-100] ", "Vehicle_Speed", null, initdata);

		SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
		sw.displayChart().setSize(3000, 2000);

		ArrayList<Double> initdata1 = new ArrayList<Double>();
		initdata1.add(0.0);

		chart.addSeries("Speed_Request", initdata1);

		ArrayList<Double> initdata2 = new ArrayList<Double>();
		initdata2.add(0.0);

		chart.addSeries("Brake_demand", initdata2);
    	
    	
    	
        if(super.isLateJoiner()) {
            //log.info("turning off time regulation (late joiner)");
            currentTime = super.getLBTS() - super.getLookAhead();
            super.disableTimeRegulation();
        }

        /////////////////////////////////////////////
        // TODO perform basic initialization below //
        /////////////////////////////////////////////

        AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
        putAdvanceTimeRequest(atr);

        if(!super.isLateJoiner()) {
            //log.info("waiting on readyToPopulate...");
            readyToPopulate();
            //log.info("...synchronized on readyToPopulate");
        }

        ///////////////////////////////////////////////////////////////////////
        // TODO perform initialization that depends on other federates below //
        ///////////////////////////////////////////////////////////////////////

        if(!super.isLateJoiner()) {
            //log.info("waiting on readyToRun...");
            readyToRun();
            //log.info("...synchronized on readyToRun");
        }

        startAdvanceTimeThread();
        //log.info("started logical time progression");

        
        
		double[] xData = new double[3500];
		ArrayList<Double> yData = new ArrayList<Double>();
		double[] xData1 = new double[3500];
		ArrayList<Double> yData1 = new ArrayList<Double>();
		double[] xData2 = new double[3500];
		ArrayList<Double> yData2 = new ArrayList<Double>();

        
        
        while (!exitCondition) {
            atr.requestSyncStart();
            enteredTimeGrantedState();

            checkReceivedSubscriptions();

            
            
            
            final double speed = Double.parseDouble(VisualizeAnalyticsparameter.Motor_Power_Limits);
			final double brake = Double.parseDouble(VisualizeAnalyticsparameter.UCEFGateway_Torque_Commands);
			final double speed1 = Double.parseDouble(VisualizeAnalyticsparameter.Engine_Speed);

			int osd = (int) (currentTime) % 2;
			int time = (int) (currentTime / 2);

			//log.info("time" + time +  "  request " + speed1   + " response " +  speed  +  " brake " + brake );
			
			
			switch (osd) {


			case 0:
				myExecutor.execute(new Runnable() {
					public void run() {
						try {
							update_chart(time, speed, speed1, brake, chart, sw, xData, xData1, xData2, yData, yData1,
									yData2);

						} catch (Exception e) {

							e.printStackTrace();
						}
					}
				});
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

    }

    private void handleObjectClass(VehiclePhysicsState object) {

    	
        try {
        	        	
//       	 log.info(" vis in " + object.get_otherPhysicsSignals().toString());	
       	 
     	String delims = "[ ]+";
     	String[] CSPNs = object.get_otherPhysicsSignals().split(delims);
       	 
       	 
        VisualizeAnalyticsparameter.Motor_Power_Limits = CSPNs[0];
		VisualizeAnalyticsparameter.UCEFGateway_Torque_Commands = CSPNs[1] ; 


        }
        catch (Exception e) {
        log.info("wtf");	
 
        }

    }
    private void handleObjectClass1(TorqueRequestsOp object) {
    	
    try {
    	
    	
    	log.info("carries request" + object.get_Attribute());
    	
    	String delims = "[ ]+";
    	String[] CSPNs = object.get_Attribute().split(delims);
    	VisualizeAnalyticsparameter.Engine_Speed = CSPNs[0];   
    }
    catch (Exception e) {
    	
    	VisualizeAnalyticsparameter.Engine_Speed = "0";   
    }


    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            VisualizeAnalyticsConfig federateConfig =
                federateConfigParser.parseArgs(args, VisualizeAnalyticsConfig.class);
            VisualizeAnalytics federate =
                new VisualizeAnalytics(federateConfig);
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
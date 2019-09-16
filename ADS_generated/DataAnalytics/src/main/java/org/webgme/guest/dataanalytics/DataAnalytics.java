package org.webgme.guest.dataanalytics;

import org.webgme.guest.dataanalytics.rti.*;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class DataAnalytics extends DataAnalyticsBase {

	ExecutorService myExecutor = Executors.newCachedThreadPool();

	private final static Logger log = LogManager.getLogger();

	private double currentTime = 0;

	public DataAnalyticsConfig DataAnalyticsparameter = new DataAnalyticsConfig();

	CAN DataAnalyticsCAN = create_CAN();

	public DataAnalytics(DataAnalyticsConfig params) throws Exception {
		super(params);
		DataAnalyticsparameter.Engine_Speed = params.Engine_Speed;
		DataAnalyticsparameter.Engine_Temperature = params.Engine_Temperature;
		DataAnalyticsparameter.Motor_Power_Limits = params.Motor_Power_Limits;
		DataAnalyticsparameter.Inverter_Temperature = params.Inverter_Temperature;
		DataAnalyticsparameter.UCEFGateway_Torque_Commands = params.UCEFGateway_Torque_Commands;
		DataAnalyticsparameter.Vehicle_Speed_Response = params.Vehicle_Speed_Response;
		DataAnalyticsparameter.DataAnalyticsPGN = params.DataAnalyticsPGN;
		DataAnalyticsparameter.DataAnalyticsSPNs = params.DataAnalyticsSPNs;
		DataAnalyticsparameter.messageTime = params.messageTime;
	}

	private void checkReceivedSubscriptions() {
		InteractionRoot interaction = null;
		while ((interaction = getNextInteractionNoWait()) != null) {
			if (interaction instanceof CAN) {
				handleInteractionClass((CAN) interaction);
			} else {
				log.debug("unhandled interaction: {}", interaction.getClassName());
			}
		}
	}

	public void Build_and_Send_CAN_Frame(String pgn, String spn)

	{
		DataAnalyticsCAN.set_ID18B(DataAnalyticsparameter.DataAnalyticsPGN);
		DataAnalyticsCAN.set_DataField(DataAnalyticsparameter.DataAnalyticsSPNs);
		DataAnalyticsCAN.sendInteraction(getLRC(), currentTime + getLookAhead());
	}

	public void update_chart(int ost, int speed, int speed1, int brake, XYChart chart, SwingWrapper<XYChart> sw, double[] xData,
			double[] xData1, double[] xData2, ArrayList<Integer> yData, ArrayList<Integer> yData1 ,  ArrayList<Integer>  yData2) throws Exception

	{

		final ArrayList<Integer> data1 = fill_array(ost, speed1, xData, yData1);
		
		final ArrayList<Integer> data2 = fill_array(ost, brake, xData2, yData2);
		
		final ArrayList<Integer> data = fill_array(ost, speed, xData, yData);

		chart.getStyler().setYAxisMax((double) 100);
		chart.getStyler().setYAxisMin(null, (double) 0);

		chart.getStyler().setXAxisMax((double) 350);
		chart.getStyler().setXAxisMin((double) 0);

		Thread.sleep(ost);

		chart.updateXYSeries("Vehicle_Response", null, data, null);
		chart.updateXYSeries("Speed_Control", null, data1, null);
		chart.updateXYSeries("Brake_Pressure", null, data2, null);
		sw.repaintChart();

	}

	private static ArrayList<Integer> fill_array(int time, int speed, double[] xData, ArrayList<Integer> yData)
			throws Exception {
		xData[time] = time;
		yData.add(time, speed);
		return yData;
	}

	private void execute() throws Exception {

		ArrayList<Integer> initdata = new ArrayList<Integer>();
		initdata.add(0);

		XYChart chart = QuickChart.getChart("UCEF-IGNITE-Analytics", "Time", "Speed[kmph] / braking_demand[0-100] ", "Vehicle_Response", null,
				initdata);

		SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
		sw.displayChart();

		ArrayList<Integer> initdata1 = new ArrayList<Integer>();
		initdata1.add(0);

		chart.addSeries("Speed_Control", initdata1);

		ArrayList<Integer> initdata2 = new ArrayList<Integer>();
		initdata2.add(0);

		chart.addSeries("Brake_Pressure", initdata2);
		
		
		if (super.isLateJoiner()) {
			log.info("turning off time regulation (late joiner)");
			currentTime = super.getLBTS() - super.getLookAhead();
			super.disableTimeRegulation();
		}

		/////////////////////////////////////////////
		// TODO perform basic initialization below //
		/////////////////////////////////////////////

		AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
		putAdvanceTimeRequest(atr);

		if (!super.isLateJoiner()) {
			log.info("waiting on readyToPopulate...");
			readyToPopulate();
			log.info("...synchronized on readyToPopulate");
		}

		///////////////////////////////////////////////////////////////////////
		// TODO perform initialization that depends on other federates below //
		///////////////////////////////////////////////////////////////////////

		if (!super.isLateJoiner()) {
			log.info("waiting on readyToRun...");
			readyToRun();
			log.info("...synchronized on readyToRun");
		}

		startAdvanceTimeThread();
		log.info("started logical time progression");

		double[] xData = new double[350];
		ArrayList<Integer> yData = new ArrayList<Integer>();

		double[] xData1 = new double[350];
		ArrayList<Integer> yData1 = new ArrayList<Integer>();
		
		
		double[] xData2 = new double[350];
		ArrayList<Integer> yData2 = new ArrayList<Integer>();
		
		

		while (!exitCondition) {
			atr.requestSyncStart();
			enteredTimeGrantedState();

			////////////////////////////////////////////////////////////
			// TODO send interactions that must be sent every logical //
			// time step below //
			////////////////////////////////////////////////////////////

			// Set the interaction's parameters.
			//
			// CAN vCAN = create_CAN();
			// vCAN.set_ACKslot( < YOUR VALUE HERE > );
			// vCAN.set_CRC( < YOUR VALUE HERE > );
			// vCAN.set_DLC( < YOUR VALUE HERE > );
			// vCAN.set_DataField( < YOUR VALUE HERE > );
			// vCAN.set_EndOfFrame( < YOUR VALUE HERE > );
			// vCAN.set_ID11B( < YOUR VALUE HERE > );
			// vCAN.set_ID18B( < YOUR VALUE HERE > );
			// vCAN.set_IDE( < YOUR VALUE HERE > );
			// vCAN.set_Parameter( < YOUR VALUE HERE > );
			// vCAN.set_RTR( < YOUR VALUE HERE > );
			// vCAN.set_ReservedBit1( < YOUR VALUE HERE > );
			// vCAN.set_ReservedBit2( < YOUR VALUE HERE > );
			// vCAN.set_SRR( < YOUR VALUE HERE > );
			// vCAN.set_StartOfFrame( < YOUR VALUE HERE > );'
			// vCAN.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
			// vCAN.set_federateFilter( < YOUR VALUE HERE > );
			// vCAN.set_originFed( < YOUR VALUE HERE > );
			// vCAN.set_sourceFed( < YOUR VALUE HERE > );
			// vCAN.sendInteraction(getLRC(), currentTime + getLookAhead());

			checkReceivedSubscriptions();

			final int speed = (int) (Double.parseDouble(DataAnalyticsparameter.Motor_Power_Limits));
			final int brake = (int) (Double.parseDouble(DataAnalyticsparameter.UCEFGateway_Torque_Commands));
			final int speed1 = (int) (Double.parseDouble(DataAnalyticsparameter.Engine_Speed));


			int osd = (int) (currentTime) % 20;
			int time = (int) (currentTime / 20);

			System.out.println("time   " + time + "   control_speed  " + speed + " response_speed "  +  speed1 + " brake"  + brake + "  current_time " + currentTime);

			switch (osd) {

			case 1:
				myExecutor.execute(new Runnable() {
					public void run() {
						try {
							update_chart(time, speed, speed1, brake, chart, sw, xData, xData1, xData2, yData, yData1 , yData2);

						} catch (Exception e) {

							e.printStackTrace();
						}
					}
				});
				break;

			}
			////////////////////////////////////////////////////////////////////
			// TODO break here if ready to resign and break out of while loop //
			////////////////////////////////////////////////////////////////////

			if (!exitCondition) {
				currentTime += super.getStepSize();
				AdvanceTimeRequest newATR = new AdvanceTimeRequest(currentTime);
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
			DataAnalyticsparameter.UCEFGateway_Torque_Commands = CSPNs[1];
			DataAnalyticsparameter.Motor_Power_Limits = CSPNs[0];
			DataAnalyticsparameter.messageTime = interaction.getTime();

			break;
		case "VehicleControl":
			DataAnalyticsparameter.Engine_Speed = CSPNs[0];
			DataAnalyticsparameter.messageTime = interaction.getTime();

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

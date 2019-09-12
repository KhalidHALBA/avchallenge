package org.webgme.guest.ucefgateway;

import org.webgme.guest.ucefgateway.rti.*;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;
import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.*;
import java.util.Queue;


// Define the UCEFGateway type of federate for the federation.

public class UCEFGateway extends UCEFGatewayBase {
	private final static Logger log = LogManager.getLogger();

	private double currentTime = 0;

	String status = "0";

	public UCEFGatewayConfig UCEFGatewayparameter = new UCEFGatewayConfig();

	CAN UCEFGatewayCAN = create_CAN();

	Queue<String> s = new LinkedList<String>();
	Queue<Double> t = new LinkedList<Double>();

	Queue<String> s1 = new LinkedList<String>();
	Queue<Double> t1 = new LinkedList<Double>();

	public UCEFGateway(UCEFGatewayConfig params) throws Exception {
		super(params);

		UCEFGatewayparameter.UCEFGatewayPGN = params.UCEFGatewayPGN;

		UCEFGatewayparameter.UCEFGatewaySPNs = params.UCEFGatewaySPNs;

		UCEFGatewayparameter.Motor_Operating_Mode = params.Motor_Operating_Mode;

		UCEFGatewayparameter.Motor_Torque_cmd = params.Motor_Torque_cmd;

		UCEFGatewayparameter.Motor_Speed = params.Motor_Speed;

		UCEFGatewayparameter.Volt_Cmd = params.Volt_Cmd;

		UCEFGatewayparameter.Contactor_Override_Commands = params.Contactor_Override_Commands;

		UCEFGatewayparameter.Battery_Cooling_Heating_Commands = params.Battery_Cooling_Heating_Commands;

		UCEFGatewayparameter.Motor_Cooling_Heating_Commands = params.Motor_Cooling_Heating_Commands;

		UCEFGatewayparameter.Inverter_Cooling_Heating_Commands = params.Inverter_Cooling_Heating_Commands;

		UCEFGatewayparameter.DataAnalytics_Motor_Temperature = params.DataAnalytics_Motor_Temperature;

		UCEFGatewayparameter.DataAnalytics_Inverter_Temperature = params.DataAnalytics_Inverter_Temperature;

		UCEFGatewayparameter.DataAnalytics_Motor_Speed = params.DataAnalytics_Motor_Speed;

		UCEFGatewayparameter.DataAnalytics_Motor_Power_Limits = params.DataAnalytics_Motor_Power_Limits;

		UCEFGatewayparameter.EventInjection_Battery_Peak_Current = params.EventInjection_Battery_Peak_Current;

		UCEFGatewayparameter.EventInjection_Obstacle_Presence_distance = params.EventInjection_Obstacle_Presence_distance;

		UCEFGatewayparameter.EventInjection_Battery_Peak_Voltage = params.EventInjection_Battery_Peak_Voltage;

		UCEFGatewayparameter.EventInjection_Battery_State_of_charge_and_Health = params.EventInjection_Battery_State_of_charge_and_Health;

		UCEFGatewayparameter.EventInjection_Battery_Remaining_Capacity = params.EventInjection_Battery_Remaining_Capacity;

		UCEFGatewayparameter.EventInjection_Battery_Max_Min_Cell_Temperature = params.EventInjection_Battery_Max_Min_Cell_Temperature;

		UCEFGatewayparameter.EventInjection_Battery_Pack_Power_limit = params.EventInjection_Battery_Pack_Power_limit;

		UCEFGatewayparameter.VehicleControl_Wheel_Speed = params.VehicleControl_Wheel_Speed;

		UCEFGatewayparameter.VehicleControl_Vehicle_Speed = params.VehicleControl_Vehicle_Speed;

		UCEFGatewayparameter.VehicleControl_Event_Status = params.VehicleControl_Event_Status;

		UCEFGatewayparameter.Accel_Pedal_Position = params.Accel_Pedal_Position;

		UCEFGatewayparameter.Brake_Pressure = params.Brake_Pressure;

		UCEFGatewayparameter.messageTime = params.messageTime;

	}

	private void checkReceivedSubscriptions() {
		InteractionRoot interaction = null;
		if ((interaction = getNextInteractionNoWait()) != null) {
			if (interaction instanceof CAN) {
				handleInteractionClass((CAN) interaction);
			} else {
				log.debug("unhandled interaction: {}", interaction.getClassName());
			}
		}

		else {

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

	public void Send_Receive(byte[] receiveData ) {


		try {
			DatagramSocket clientSocket = new DatagramSocket();

			InetAddress IPAddress = InetAddress.getByName("192.168.78.1");
			byte[] sendData = new byte[1024];

//			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			sendData = (UCEFGatewayparameter.VehicleControl_Wheel_Speed).getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
			clientSocket.send(sendPacket);

			clientSocket.receive(receivePacket);
			String vehicleResponse = new String(receivePacket.getData());
			// String[] speeds = vehicleResponse.split(" ");

			try {
				float f = Float.valueOf(vehicleResponse.trim()).floatValue();
				System.out.println("IGNITE response float = " + f);

				UCEFGatewayparameter.Motor_Speed = Double.toString(f);
			} catch (NumberFormatException nfe) {

				UCEFGatewayparameter.Motor_Speed = "0";
				// System.out.println("IGNITE response floated = " + 100*f);
			}
			//
			//

			clientSocket.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String Build_SPN() {

		return UCEFGatewayparameter.UCEFGatewaySPNs = UCEFGatewayparameter.Motor_Speed + " "
				+ UCEFGatewayparameter.Motor_Torque_cmd;
	}

	public void Build_and_Send_CAN_Frame(String pgn, String spn) {
		UCEFGatewayCAN.set_ID18B(pgn);
		UCEFGatewayCAN.set_DataField(spn);
		UCEFGatewayCAN.sendInteraction(getLRC(), currentTime + getLookAhead());
	}

	private void execute() throws Exception {
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
			// vCAN.set_StartOfFrame( < YOUR VALUE HERE > );
			// vCAN.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
			// vCAN.set_federateFilter( < YOUR VALUE HERE > );
			// vCAN.set_originFed( < YOUR VALUE HERE > );
			// vCAN.set_sourceFed( < YOUR VALUE HERE > );
			// vCAN.sendInteraction(getLRC(), currentTime + getLookAhead());

			checkReceivedSubscriptions();
			
			////////////////////////////////////////////////////////////////////
			// TODO break here if ready to resign and break out of while loop //
			////////////////////////////////////////////////////////////////////

			int osd = (int) (currentTime) % 20;
			switch (osd) {

			case 2:
				byte[] receiveData = new byte[1024];
				Send_Receive(receiveData);
				break;
			case 3:
				Build_and_Send_CAN_Frame(UCEFGatewayparameter.UCEFGatewayPGN, Build_SPN());
				break;

			}

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

		String delims = "[ ]+";
		String[] CSPNs = interaction.get_DataField().split(delims);
		switch (interaction.get_ID18B()) {

		case "COA":
			UCEFGatewayparameter.COA_Message = interaction.get_DataField();
			UCEFGatewayparameter.messageTime = interaction.getTime();
			s.add(UCEFGatewayparameter.COA_Message);
			t.add(UCEFGatewayparameter.messageTime);

			break;

		case "VehicleControl":
			UCEFGatewayparameter.VehicleControl_Wheel_Speed = CSPNs[0];
			UCEFGatewayparameter.VehicleControl_Event_Status = CSPNs[2];
			UCEFGatewayparameter.messageTime = interaction.getTime();

			break;

		case "EventInjection":
			UCEFGatewayparameter.EventInjection_Obstacle_Presence_distance = CSPNs[0];
			UCEFGatewayparameter.messageTime = interaction.getTime();

			break;

		}

		///////////////////////////////////////////////////////////////
		// TODO implement how to handle reception of the interaction //
		///////////////////////////////////////////////////////////////
	}

	public static void main(String[] args) {
		try {
			FederateConfigParser federateConfigParser = new FederateConfigParser();
			UCEFGatewayConfig federateConfig = federateConfigParser.parseArgs(args, UCEFGatewayConfig.class);
			UCEFGateway federate = new UCEFGateway(federateConfig);
			federate.execute();
			log.info("Done.");
			System.exit(0);
		} catch (Exception e) {
			log.error(e);
			System.exit(1);
		}
	}
}

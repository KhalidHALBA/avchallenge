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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UCEFGateway extends UCEFGatewayBase {

	ExecutorService myExecutor = Executors.newCachedThreadPool();
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

		UCEFGatewayparameter = params;

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

	public void Send_Receive(byte[] receiveData) {

		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(UCEFGatewayparameter.IGNITE_IP);
			byte[] sendData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			sendData = ((int) (1000 * Double.parseDouble(UCEFGatewayparameter.UCEF_Vehicle_Speed_Control)) + " "
					+ (int) (1000 * Double.parseDouble(UCEFGatewayparameter.Speed_Control_Ahead_GW))).getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
			clientSocket.send(sendPacket);
			receivePacket.setLength(receiveData.length);
			clientSocket.receive(receivePacket);
			String vehicleResponse = new String(receivePacket.getData());
			String[] speeds = vehicleResponse.split(" ");
			float f = Float.valueOf(speeds[0].trim()).floatValue();
			float f1 = f / 100;
			UCEFGatewayparameter.Vehicle_Speed_Response = Double.toString(f1);
			float b = Float.valueOf(speeds[1].trim()).floatValue();
			float b1 = b / 100;
			UCEFGatewayparameter.Brake_Pressure = Double.toString(b1);
			float t = Float.valueOf(speeds[2].trim()).floatValue();
			float t1 = t / 100;
			UCEFGatewayparameter.IGNITE_Cycle_Time = Double.toString(t1);
			clientSocket.close();
		} catch (Exception e) {
			
			
			
			 System.out.println("THIS is an exception "+e.getMessage());

		}

	}

	public String Build_SPN() {

		return UCEFGatewayparameter.UCEFGatewaySPNs = UCEFGatewayparameter.Vehicle_Speed_Response + " "
				+ UCEFGatewayparameter.Brake_Pressure + " " + UCEFGatewayparameter.IGNITE_Cycle_Time;
	}

	public void Build_and_Send_CAN_Frame(String pgn, String spn) {
		UCEFGatewayCAN.set_ID18B(pgn);
		UCEFGatewayCAN.set_DataField(spn);
		UCEFGatewayCAN.sendInteraction(getLRC(), currentTime + getLookAhead());
	}

	private void execute() throws Exception {
		if (super.isLateJoiner()) {
			// // // // //log.info("turning off time regulation (late joiner)");
			currentTime = super.getLBTS() - super.getLookAhead();
			super.disableTimeRegulation();
		}

		/////////////////////////////////////////////
		// TODO perform basic initialization below //
		/////////////////////////////////////////////

		AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
		putAdvanceTimeRequest(atr);

		if (!super.isLateJoiner()) {
			// // // // //log.info("waiting on readyToPopulate...");
			readyToPopulate();
			// // // // //log.info("...synchronized on readyToPopulate");
		}

		///////////////////////////////////////////////////////////////////////
		// TODO perform initialization that depends on other federates below //
		///////////////////////////////////////////////////////////////////////

		if (!super.isLateJoiner()) {
			// // // // //log.info("waiting on readyToRun...");
			readyToRun();
			// // // // //log.info("...synchronized on readyToRun");
		}

		startAdvanceTimeThread();
		// // // // //log.info("started logical time progression");

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

			int osd = (int) (currentTime) % 2;
			switch (osd) {

			case 0:
				byte[] receiveData = new byte[1024];
				Send_Receive(receiveData);
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
			UCEFGatewayparameter.UCEF_Vehicle_Speed_Control = CSPNs[0];
			UCEFGatewayparameter.VehicleControl_Event_Status = CSPNs[2];
			UCEFGatewayparameter.Speed_Control_Ahead_GW = CSPNs[4];
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
			// // // // //log.info("Done.");
			System.exit(0);
		} catch (Exception e) {
			log.error(e);
			System.exit(1);
		}
	}
}
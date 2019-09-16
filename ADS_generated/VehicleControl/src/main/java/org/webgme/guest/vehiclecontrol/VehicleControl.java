package org.webgme.guest.vehiclecontrol;

import org.webgme.guest.vehiclecontrol.rti.*;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.RandomStringUtils;

public class VehicleControl extends VehicleControlBase {
	private final static Logger log = LogManager.getLogger();

	private double currentTime = 0;

	String Drive_Cycle_Speed = "0";

	String speed = "0";

	public VehicleControlConfig VehicleControlparameter = new VehicleControlConfig();
	CAN VehicleControlCAN = create_CAN();

	public VehicleControl(VehicleControlConfig params) throws Exception {
		super(params);

		VehicleControlparameter.UCEFGateway_Motor_Operating_Mode = params.UCEFGateway_Motor_Operating_Mode;

		VehicleControlparameter.UCEFGateway_Motor_Torque_cmd = params.UCEFGateway_Motor_Torque_cmd;

		VehicleControlparameter.UCEFGateway_Vehicle_Speed_Response = params.UCEFGateway_Vehicle_Speed_Response;

		VehicleControlparameter.UCEFGateway_Volt_Cmd = params.UCEFGateway_Volt_Cmd;

		VehicleControlparameter.messageTime = params.messageTime;
		VehicleControlparameter.Vehicle_Control_Speed = params.Vehicle_Control_Speed;
		VehicleControlparameter.VehicleControl_Event_Status = params.VehicleControl_Event_Status;
		VehicleControlparameter.Traction_Stability_Torque_Request = params.Traction_Stability_Torque_Request;
		VehicleControlparameter.UCEFGateway_PGN = params.UCEFGateway_PGN;
		VehicleControlparameter.UCEF_Control_Speed = params.UCEF_Control_Speed;
		VehicleControlparameter.Hydraulic_Valve_Commands = params.Hydraulic_Valve_Commands;
		VehicleControlparameter.VehicleControlPGN = params.VehicleControlPGN;
		VehicleControlparameter.VehicleControlSPNs = params.VehicleControlSPNs;
		VehicleControlparameter.Vehicle_Speed = params.Vehicle_Speed;
		VehicleControlparameter.EventInjection_Obstacle_Presence_distance = params.EventInjection_Obstacle_Presence_distance;
		VehicleControlparameter.Drive_Cycle = params.Drive_Cycle;

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

	public void VehicleControl_Event_Status()

	{

		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		VehicleControlparameter.VehicleControl_Event_Status = generatedString;

	}

	public void Control(int speedline)

	{

		try {

			if (speedline - 1 >= 0) {
				Drive_Cycle_Speed = Files
						.readAllLines(Paths
								.get(VehicleControlparameter.Drive_Cycle))
						.get(speedline);
			}

			log.info("Control Input :  Vehicle Response " + VehicleControlparameter.UCEFGateway_Motor_Torque_cmd
					+ " Obstacle Notification " + VehicleControlparameter.EventInjection_Obstacle_Presence_distance
					+ " DriveCycle Data " + Drive_Cycle_Speed);

			if (VehicleControlparameter.EventInjection_Obstacle_Presence_distance.equals("true")) {

			
				VehicleControlparameter.Vehicle_Control_Speed = "0";
				VehicleControlparameter.UCEF_Control_Speed = Drive_Cycle_Speed;
				System.out.println(
						" obstacle detected : speed " + VehicleControlparameter.Vehicle_Control_Speed + " time " + speedline);

			} else {

				VehicleControlparameter.UCEF_Control_Speed = Drive_Cycle_Speed;
				VehicleControlparameter.Vehicle_Control_Speed = Drive_Cycle_Speed;
				System.out.println("  obstacle NOT detected : speed " + VehicleControlparameter.Vehicle_Control_Speed + " time "
						+ speedline + " currentTime" + currentTime);

				// VehicleControlparameter.Vehicle_Control_Speed =
				// Double.toString(average);
				// replace with transmit frame
				// sendData = speed.getBytes();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String Build_SPN()

	{
		return VehicleControlparameter.VehicleControlSPNs = VehicleControlparameter.Vehicle_Control_Speed + " "
				+ VehicleControlparameter.UCEF_Control_Speed + " "
				+ VehicleControlparameter.VehicleControl_Event_Status + " "
				+ VehicleControlparameter.Traction_Stability_Torque_Request;
	}

	public void Build_and_Send_CAN_Frame(String pgn, String spn)

	{

		VehicleControlCAN.set_ID18B(pgn);
		VehicleControlCAN.set_DataField(spn);
		VehicleControlCAN.sendInteraction(getLRC(), currentTime + getLookAhead());

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
			int osd = (int) (currentTime) % 20;

			switch (osd) {

			case 1:
				Control((int) (currentTime / 20));

				Build_and_Send_CAN_Frame(VehicleControlparameter.VehicleControlPGN, Build_SPN());

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
		case "EventInjection":
			VehicleControlparameter.EventInjection_Obstacle_Presence_distance = CSPNs[0];
			VehicleControlparameter.messageTime = interaction.getTime();

			break;

		case "UCEFGateway":
			VehicleControlparameter.UCEFGateway_Motor_Torque_cmd = CSPNs[0];
			VehicleControlparameter.UCEFGateway_Vehicle_Speed_Response = CSPNs[1];
			VehicleControlparameter.messageTime = interaction.getTime();

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

package org.webgme.guest.vehiclecontrol;

import org.webgme.guest.vehiclecontrol.rti.*;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VehicleControl extends VehicleControlBase {
	private final static Logger log = LogManager.getLogger();

	private double currentTime = 0;
	int e = 0;
	String Drive_Cycle_Speed = "0";
	String Drive_Cycle_Speed_ahead = "0";
	String Drive_Cycle_Speed_ahead_1 = "0";
	double speed_ = 0;
	double speed_ahead_ = 0;
	double sampling_rate = 0.1;

	String speed = "0";

	public VehicleControlConfig VehicleControlparameter = new VehicleControlConfig();
	CAN VehicleControlCAN = create_CAN();

	public VehicleControl(VehicleControlConfig params) throws Exception {
		super(params);
		VehicleControlparameter = params;

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

	public void Control(double IGNITE_TIME__) {

		try {
			if ((int) (Double.parseDouble(VehicleControlparameter.IGNITE_TIME)) >= 0) {

				Drive_Cycle_Speed = Files.readAllLines(Paths.get(VehicleControlparameter.Drive_Cycle))
						.get((int) IGNITE_TIME__);
				Drive_Cycle_Speed_ahead = Files.readAllLines(Paths.get(VehicleControlparameter.Drive_Cycle))
						.get((int) IGNITE_TIME__ + Integer.parseInt(VehicleControlparameter.IGNITE_LOOKAHEAD));
				Drive_Cycle_Speed_ahead_1 = Files.readAllLines(Paths.get(VehicleControlparameter.Drive_Cycle))
						.get((int) IGNITE_TIME__ + 2);

			}
			if (VehicleControlparameter.EventInjection_Obstacle_Presence.equals("true")) {
				VehicleControlparameter.Vehicle_Control_Speed = "0";
			} else {

				int sampling_factor_ = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));

				double sampling_step_ = sampling_rate
						* (Double.parseDouble(Drive_Cycle_Speed_ahead) - Double.parseDouble(Drive_Cycle_Speed));

				DecimalFormat f = new DecimalFormat("##.000");
				speed_ = Double.parseDouble(
						f.format(Double.parseDouble(Drive_Cycle_Speed) + sampling_factor_ * sampling_step_));
				VehicleControlparameter.Vehicle_Control_Speed = Double.toString(speed_);

			}
			if (VehicleControlparameter.EventInjection_Obstacle_Presence_Ahead.equals("true")) {
				VehicleControlparameter.Speed_Control_Ahead_VC = "0";
			} else {

				int sampling_factor_ahead = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
				double sampling_step_ahead = sampling_rate
						* (Double.parseDouble(Drive_Cycle_Speed_ahead_1) - Double.parseDouble(Drive_Cycle_Speed_ahead));

				DecimalFormat f = new DecimalFormat("##.000");
				speed_ahead_ = Double.parseDouble(f.format(
						Double.parseDouble(Drive_Cycle_Speed_ahead) + sampling_factor_ahead * sampling_step_ahead));

				VehicleControlparameter.Speed_Control_Ahead_VC = Double.toString(speed_ahead_);

			}

			System.out.println("time " + IGNITE_TIME__ + " speed " + VehicleControlparameter.Vehicle_Control_Speed
					+ " speed_ahead " + VehicleControlparameter.Speed_Control_Ahead_VC);

		} catch (Exception e) {
			// System.out.println(e);
		}
	}

	public String Build_SPN()

	{
		return VehicleControlparameter.VehicleControlSPNs = VehicleControlparameter.Vehicle_Control_Speed + " "
				+ VehicleControlparameter.UCEF_Control_Speed + " " + VehicleControlparameter.VehicleControl_Event_Status
				+ " " + VehicleControlparameter.IGNITE_LOOKAHEAD + " " + VehicleControlparameter.Speed_Control_Ahead_VC;
	}

	public void Build_and_Send_CAN_Frame(String pgn, String spn)

	{

		VehicleControlCAN.set_ID18B(pgn);
		VehicleControlCAN.set_DataField(spn);
		VehicleControlCAN.sendInteraction(getLRC(), currentTime + getLookAhead());

	}

	private void execute() throws Exception {
		if (super.isLateJoiner()) {
			// log.info("turning off time regulation (late joiner)");
			currentTime = super.getLBTS() - super.getLookAhead();
			super.disableTimeRegulation();
		}

		/////////////////////////////////////////////
		// TODO perform basic initialization below //
		/////////////////////////////////////////////

		AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
		putAdvanceTimeRequest(atr);

		if (!super.isLateJoiner()) {
			// log.info("waiting on readyToPopulate...");
			readyToPopulate();
			// log.info("...synchronized on readyToPopulate");
		}

		///////////////////////////////////////////////////////////////////////
		// TODO perform initialization that depends on other federates below //
		///////////////////////////////////////////////////////////////////////

		if (!super.isLateJoiner()) {
			// log.info("waiting on readyToRun...");
			readyToRun();
			// log.info("...synchronized on readyToRun");
		}

		startAdvanceTimeThread();
		// log.info("started logical time progression");

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
			int osd = (int) (currentTime) % 2;

			double IGNITE_TIME_d = (Double.parseDouble(VehicleControlparameter.IGNITE_TIME));

			// int igtm = (int) (IGNITE_TIME_d) % 20;

			// log.info("ucef time " + (int) (currentTime / 3) + " ignite time
			// "+ IGNITE_TIME_d + " logical time " + currentTime );

			switch (osd) {

			case 0:

				Control(IGNITE_TIME_d);

				//
				// for ( e=0 ; e < 7; )
				//
				// {
				//
				//
				// e++;
				//
				// VehicleControlparameter.Speed_Control_Ahead_VC =
				// Double.toString(0.0);
				//
				// VehicleControlparameter.Vehicle_Control_Speed =
				// Double.toString(0.0);
				//
				// }
				//
				//
				// for ( ;e >= 7 ; )
				// {
				//
				Build_and_Send_CAN_Frame(VehicleControlparameter.VehicleControlPGN, Build_SPN());
				//
				// }

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
			VehicleControlparameter.EventInjection_Obstacle_Presence = CSPNs[0];
			VehicleControlparameter.EventInjection_Obstacle_Presence_Ahead = CSPNs[1];
			VehicleControlparameter.messageTime = interaction.getTime();

			break;

		case "UCEFGateway":

			VehicleControlparameter.UCEFGateway_Motor_Torque_cmd = CSPNs[0];
			VehicleControlparameter.UCEFGateway_Vehicle_Speed_Response = CSPNs[1];
			VehicleControlparameter.IGNITE_TIME = CSPNs[2];
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
			// log.info("Done.");
			System.exit(0);
		} catch (Exception e) {
			log.error(e);
			System.exit(1);
		}
	}
}

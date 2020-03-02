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
	
	int e,j,g = 0;
	String Drive_Cycle_Speed, Drive_Cycle_Speed_ahead,Drive_Cycle_Speed_ahead_1  = "0";
	double speed_, speed_ahead_ = 0;



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

				j++;

				if (j < 10) {

					Drive_Cycle_Speed_ahead = "0.2";
					int sampling_factor = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
					double sampling_step_ = VehicleControlparameter.sampling_rate
							* (Double.parseDouble(Drive_Cycle_Speed_ahead) - Double.parseDouble(Drive_Cycle_Speed));

					DecimalFormat f = new DecimalFormat("##.000");
					speed_ = Double.parseDouble(
							f.format(Double.parseDouble(Drive_Cycle_Speed) + sampling_factor * sampling_step_));

					VehicleControlparameter.Vehicle_Control_Speed = Double.toString(speed_);

				} else {

					VehicleControlparameter.Vehicle_Control_Speed = "0.2";
				}
			} else {

				j = 0;

				int sampling_factor_ = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
				double sampling_step_ = VehicleControlparameter.sampling_rate
						* (Double.parseDouble(Drive_Cycle_Speed_ahead) - Double.parseDouble(Drive_Cycle_Speed));
				DecimalFormat f = new DecimalFormat("##.000");
				speed_ = Double.parseDouble(
						f.format(Double.parseDouble(Drive_Cycle_Speed) + sampling_factor_ * sampling_step_));
				VehicleControlparameter.Vehicle_Control_Speed = Double.toString(speed_);
			}

			if (VehicleControlparameter.EventInjection_Obstacle_Presence_Ahead.equals("true")) {

			
				g++;

				if (g < 10) {

					Drive_Cycle_Speed_ahead_1 = "0.2";
					int sampling_factor_ahead = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
					double sampling_step_ahead = VehicleControlparameter.sampling_rate
							* (Double.parseDouble(Drive_Cycle_Speed_ahead_1)
									- Double.parseDouble(Drive_Cycle_Speed_ahead));

					DecimalFormat f = new DecimalFormat("##.000");
					speed_ahead_ = Double.parseDouble(f.format(
							Double.parseDouble(Drive_Cycle_Speed_ahead) + sampling_factor_ahead * sampling_step_ahead));

					VehicleControlparameter.Speed_Control_Ahead_VC = Double.toString(speed_ahead_);

				} else {
					VehicleControlparameter.Speed_Control_Ahead_VC = "0.2";
				}
			} else {
				g = 0;
				int sampling_factor_ahead = (int) (10 * (IGNITE_TIME__ - ((int) (IGNITE_TIME__))));
				double sampling_step_ahead = VehicleControlparameter.sampling_rate
						* (Double.parseDouble(Drive_Cycle_Speed_ahead_1) - Double.parseDouble(Drive_Cycle_Speed_ahead));

				DecimalFormat f = new DecimalFormat("##.000");
				speed_ahead_ = Double.parseDouble(f.format(
						Double.parseDouble(Drive_Cycle_Speed_ahead) + sampling_factor_ahead * sampling_step_ahead));

				VehicleControlparameter.Speed_Control_Ahead_VC = Double.toString(speed_ahead_);
			}

			log.info("time " + IGNITE_TIME__ + " speed " + VehicleControlparameter.Vehicle_Control_Speed
					+ " speed_ahead " + VehicleControlparameter.Speed_Control_Ahead_VC + " j " + j + " g " + g);

		} catch (Exception e) {

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

			currentTime = super.getLBTS() - super.getLookAhead();
			super.disableTimeRegulation();
		}

		/////////////////////////////////////////////
		// TODO perform basic initialization below //
		/////////////////////////////////////////////

		AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
		putAdvanceTimeRequest(atr);

		if (!super.isLateJoiner()) {

			readyToPopulate();

		}

		///////////////////////////////////////////////////////////////////////
		// TODO perform initialization that depends on other federates below //
		///////////////////////////////////////////////////////////////////////

		if (!super.isLateJoiner()) {

			readyToRun();

		}

		startAdvanceTimeThread();

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
			switch (osd) {
			case 0:
				
				Control(IGNITE_TIME_d);
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
			System.exit(0);
		} catch (Exception e) {
			log.error(e);
			System.exit(1);
		}
	}
}
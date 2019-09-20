package org.webgme.guest.eventinjection;

import org.webgme.guest.eventinjection.rti.*;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Define the EventInjection type of federate for the federation.

public class EventInjection extends EventInjectionBase {
	private final static Logger log = LogManager.getLogger();

	private double currentTime = 0;

	boolean d = false;
	public EventInjectionConfig EventInjectionparameter = new EventInjectionConfig();

	CAN EventInjectionCAN = create_CAN();

	public EventInjection(EventInjectionConfig params) throws Exception {
		super(params);
		EventInjectionparameter.Peak_Voltage = params.Peak_Voltage;
		EventInjectionparameter.Obstacle_Presence_distance = params.Obstacle_Presence_distance;
		EventInjectionparameter.Peak_Current = params.Peak_Current;
		EventInjectionparameter.State_Of_Charge = params.State_Of_Charge;
		EventInjectionparameter.State_Of_Health = params.State_Of_Health;
		EventInjectionparameter.Remaining_Capacity = params.Remaining_Capacity;
		EventInjectionparameter.Max_Temperature = params.Max_Temperature;
		EventInjectionparameter.Min_Temperature = params.Min_Temperature;
		EventInjectionparameter.Peak_Current_Limit = params.Peak_Current_Limit;
		EventInjectionparameter.EventInjectionPGN = params.EventInjectionPGN;
		EventInjectionparameter.EventInjectionSPNs = params.EventInjectionSPNs;
	

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

	public void Send_Obstacle_Notification()

	{

		if ((((currentTime / 3) > 2490) && ((currentTime / 3) < 2760)) || (((currentTime / 3) > 400) && ((currentTime / 3) < 500))   ) {
			d = true;

			EventInjectionparameter.Obstacle_Presence_distance = Boolean.toString(d);
			System.out.println("obstacle detected");
			log.info("obstacle distance " + Boolean.toString(d) + " currenttime " + Double.toString(currentTime));

			System.out.println("print string boolean " + EventInjectionparameter.Obstacle_Presence_distance);
		}

		else {
			d = false;
			EventInjectionparameter.Obstacle_Presence_distance = Boolean.toString(d);
			System.out.println("obstacle not detected currenttime " + Double.toString(currentTime));
			System.out.println("print string boolean " + EventInjectionparameter.Obstacle_Presence_distance);
		}

	}

	public String Build_SPN() {

		return EventInjectionparameter.EventInjectionSPNs = EventInjectionparameter.Obstacle_Presence_distance;
	}

	public void Build_and_Send_CAN_Frame(String pgn, String spn)

	{

		EventInjectionCAN.set_ID18B(pgn);
		EventInjectionCAN.set_DataField(spn);
		EventInjectionCAN.sendInteraction(getLRC(), currentTime + getLookAhead());

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
			int osd = (int) (currentTime) % 3;

			switch (osd) {

			case 0:
				Send_Obstacle_Notification();
				Build_and_Send_CAN_Frame(EventInjectionparameter.EventInjectionPGN, Build_SPN());
				log.info(" OSD " + Integer.toString(osd) + " currentime " + Double.toString(currentTime));
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

	}

	private void handleInteractionClass(CAN interaction) {

	}

	public static void main(String[] args) {
		try {
			FederateConfigParser federateConfigParser = new FederateConfigParser();
			EventInjectionConfig federateConfig = federateConfigParser.parseArgs(args, EventInjectionConfig.class);
			EventInjection federate = new EventInjection(federateConfig);
			federate.execute();
			log.info("Done.");
			System.exit(0);
		} catch (Exception e) {
			log.error(e);
			System.exit(1);
		}
	}

}

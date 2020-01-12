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
	boolean obstacle_presence = false;
	public EventInjectionConfig EventInjectionparameter = new EventInjectionConfig();
	CAN EventInjectionCAN = create_CAN();

	public EventInjection(EventInjectionConfig params) throws Exception {
		super(params);
		EventInjectionparameter = params;
	}

	private void checkReceivedSubscriptions() {
		InteractionRoot interaction = null;
		while ((interaction = getNextInteractionNoWait()) != null) {
			if (interaction instanceof CAN) {
				handleInteractionClass((CAN) interaction);
			} else {
				
			}
		}
	}

	public void Send_Obstacle_Notification(int ignite_t) {
		if ((ignite_t > Integer.parseInt(EventInjectionparameter.sts1))
				&& (ignite_t < Integer.parseInt(EventInjectionparameter.ste1))
				|| (ignite_t > Integer.parseInt(EventInjectionparameter.sts2))
						&& (ignite_t < Integer.parseInt(EventInjectionparameter.ste2))) {
			obstacle_presence = true;
			EventInjectionparameter.Obstacle_Presence_notification = Boolean.toString(obstacle_presence);
		} else {
			obstacle_presence = false;
			EventInjectionparameter.Obstacle_Presence_notification = Boolean.toString(obstacle_presence);
		}
		if ((ignite_t > Integer.parseInt(EventInjectionparameter.sts1)
				- Integer.parseInt(EventInjectionparameter.IGNITE_LOOKAHEAD))
				&& (ignite_t < Integer.parseInt(EventInjectionparameter.ste1)
						- Integer.parseInt(EventInjectionparameter.IGNITE_LOOKAHEAD))
				|| (ignite_t > Integer.parseInt(EventInjectionparameter.sts2)
						- Integer.parseInt(EventInjectionparameter.IGNITE_LOOKAHEAD))
						&& (ignite_t < Integer.parseInt(EventInjectionparameter.ste2)
								- Integer.parseInt(EventInjectionparameter.IGNITE_LOOKAHEAD))) {
			obstacle_presence = true;
			EventInjectionparameter.Obstacle_Presence_notification_ahead = Boolean.toString(obstacle_presence);
		} else {
			obstacle_presence = false;
			EventInjectionparameter.Obstacle_Presence_notification_ahead = Boolean.toString(obstacle_presence);
		}

		log.info( "event " + EventInjectionparameter.Obstacle_Presence_notification + "event_ahead " 
				+ EventInjectionparameter.Obstacle_Presence_notification_ahead ) ;
	}

	public String Build_SPN() {
		return EventInjectionparameter.EventInjectionSPNs = EventInjectionparameter.Obstacle_Presence_notification + " "
				+ EventInjectionparameter.Obstacle_Presence_notification_ahead;
	}

	public void Build_and_Send_CAN_Frame(String pgn, String spn) {
		EventInjectionCAN.set_ID18B(pgn);
		EventInjectionCAN.set_DataField(spn);
		EventInjectionCAN.sendInteraction(getLRC(), currentTime + getLookAhead());
	}

	private void execute() throws Exception {
		if (super.isLateJoiner()) {
			// //log.info("turning off time regulation (late joiner)");
			currentTime = super.getLBTS() - super.getLookAhead();
			super.disableTimeRegulation();
		}
		/////////////////////////////////////////////
		// TODO perform basic initialization below //
		/////////////////////////////////////////////
		AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
		putAdvanceTimeRequest(atr);
		if (!super.isLateJoiner()) {
			// //log.info("waiting on readyToPopulate...");
			readyToPopulate();
			// //log.info("...synchronized on readyToPopulate");
		}
		///////////////////////////////////////////////////////////////////////
		// TODO perform initialization that depends on other federates below //
		///////////////////////////////////////////////////////////////////////
		if (!super.isLateJoiner()) {
			// //log.info("waiting on readyToRun...");
			readyToRun();
			// //log.info("...synchronized on readyToRun");
		}
		startAdvanceTimeThread();
		// //log.info("started logical time progression");
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
			double ignite_time = Double.parseDouble(EventInjectionparameter.IGNITE_TIME_1);
			int osd = (int) (currentTime) % 2;
			switch (osd) {
			case 0:
				Send_Obstacle_Notification((int) ignite_time);
				Build_and_Send_CAN_Frame(EventInjectionparameter.EventInjectionPGN, Build_SPN());

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
		///////////////////////////////////////////////////////////////
		// TODO implement how to handle reception of the interaction //
		///////////////////////////////////////////////////////////////
		String delims = "[ ]+";
		String[] CSPNs = interaction.get_DataField().split(delims);
		switch (interaction.get_ID18B()) {
		case "UCEFGateway":
			EventInjectionparameter.IGNITE_TIME_1 = CSPNs[2];
			break;
		}
	}

	public static void main(String[] args) {
		try {
			FederateConfigParser federateConfigParser = new FederateConfigParser();
			EventInjectionConfig federateConfig = federateConfigParser.parseArgs(args, EventInjectionConfig.class);
			EventInjection federate = new EventInjection(federateConfig);
			federate.execute();
			// //log.info("Done.");
			System.exit(0);
		} catch (Exception e) {
			// log.error(e);
			System.exit(1);
		}
	}
}
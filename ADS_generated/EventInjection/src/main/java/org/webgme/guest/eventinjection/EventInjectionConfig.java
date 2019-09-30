package org.webgme.guest.eventinjection;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateParameter;

/**
 * An example of how to implement custom configuration options for a federate.
 * 
 * A custom configuration file requires the definition of a class that extends
 * from FederateConfig. Each configuration option must be declared as a public
 * member variable annotated with the FederateParameter annotation.
 * 
 * See {@link InputSource#main(String[])} and
 * {@link InputSource#InputSource(InputSourceConfig)} for how to use the
 * configuration class to initialize a federate.
 */
public class EventInjectionConfig extends FederateConfig {
	/**
	 * ABS Measured Wheel Speed.
	 */

	// J1931-71 compliant local Messages
	@FederateParameter
	public String sts1; // pgn ? SPN ?
	@FederateParameter
	public String Obstacle_Presence_notification;
	@FederateParameter
	public String ste1; // pgn ? SPN ?
	@FederateParameter
	public String sts2; // pgn ? SPN ?
	@FederateParameter
	public String ste2; // pgn ? SPN ?
	@FederateParameter
	public String IGNITE_TIME_1; // pgn ? SPN ?
	@FederateParameter
	public String Obstacle_Presence_notification_ahead;
	@FederateParameter
	public String IGNITE_LOOKAHEAD;
	@FederateParameter
	public String ste1_Limit;


	// J1931-71 compliant external Messages
	// Messages sent/received to/from actuators/sensors

	// PGN : multiple PGNs per Federate are possible. we use the Federate name
	// as a placeholder for now.

	@FederateParameter
	public String EventInjectionPGN;

	// SPNs
	@FederateParameter
	public String EventInjectionSPNs;

	// String BMSSPNs = sts1 + ste1 + sts2 +
	// ste2 + IGNITE_TIME_1 + Obstacle_Presence_notification_ahead +IGNITE_LOOKAHEAD +
	// ste1_Limit ;

}

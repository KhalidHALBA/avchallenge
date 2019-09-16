package org.webgme.guest.ucefgateway;

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
public class UCEFGatewayConfig extends FederateConfig {
	/**
	 * The number of operands to generate for the adder.
	 */

	@FederateParameter
	public String UCEFGatewayPGN;
	@FederateParameter
	public String UCEFGatewaySPNs;
	@FederateParameter
	public String Motor_Operating_Mode;
	@FederateParameter
	public String Motor_Torque_cmd;
	@FederateParameter
	public String Vehicle_Speed_Response;
	@FederateParameter
	public String Volt_Cmd;
	@FederateParameter
	public String Contactor_Override_Commands;
	@FederateParameter
	public String Battery_Cooling_Heating_Commands;
	@FederateParameter
	public String Motor_Cooling_Heating_Commands;
	@FederateParameter
	public String Inverter_Cooling_Heating_Commands;
	@FederateParameter
	public String DataAnalytics_Motor_Temperature;
	@FederateParameter
	public String DataAnalytics_Inverter_Temperature;
	@FederateParameter
	public String DataAnalytics_Vehicle_Speed_Response;
	@FederateParameter
	public String DataAnalytics_Motor_Power_Limits;
	@FederateParameter
	public String EventInjection_Battery_Peak_Current;
	@FederateParameter
	public String EventInjection_Battery_Peak_Voltage;
	@FederateParameter
	public String EventInjection_Battery_State_of_charge_and_Health;
	@FederateParameter
	public String EventInjection_Battery_Remaining_Capacity;
	@FederateParameter
	public String EventInjection_Battery_Max_Min_Cell_Temperature;
	@FederateParameter
	public String EventInjection_Battery_Pack_Power_limit;
	@FederateParameter
	public String EventInjection_Obstacle_Presence_distance;
	@FederateParameter
	public String UCEF_Vehicle_Speed_Control;
	@FederateParameter
	public String VehicleControl_Vehicle_Speed;
	@FederateParameter
	public String VehicleControl_Event_Status;
	@FederateParameter
	public String Accel_Pedal_Position;
	@FederateParameter
	public String Brake_Pressure;
	@FederateParameter
	public double messageTime;
	@FederateParameter
	public String COA_Message;
	@FederateParameter
	public String IGNITE_IP;

}

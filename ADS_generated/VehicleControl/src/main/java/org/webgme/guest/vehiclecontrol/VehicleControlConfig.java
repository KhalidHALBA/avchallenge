package org.webgme.guest.vehiclecontrol;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateParameter;

/**
 * An example of how to implement custom configuration options for a federate.
 * 
 * A custom configuration file requires the definition of a class that extends from FederateConfig. Each configuration
 * option must be declared as a public member variable annotated with the FederateParameter annotation.
 * 
 * See {@link InputSource#main(String[])} and {@link InputSource#InputSource(InputSourceConfig)} for how to use the
 * configuration class to initialize a federate.
 */
public class VehicleControlConfig extends FederateConfig {
    /**
     * ABS Measured Wheel Speed.
     */

    @FederateParameter
    public double messageTime;
    @FederateParameter
    public String Wheel_Speed;
    @FederateParameter
    public String VehicleControl_Event_Status;   // pgn ?    SPN ?
    // J1931-71 NON-compliant local Messages
    @FederateParameter
    public String Traction_Stability_Torque_Request;   
    // J1931-71 compliant external Messages : (ExtrenalEntity_Message)
//    @FederateParameter
//    public String UCEFGateway_Motor_Torque; 
    @FederateParameter
    public String UCEFGateway_PGN ;
   // Messages sent/received to/from actuators/sensors
    @FederateParameter
    public String Wheel_Speed_Sensors; // see graph i`n slide 3
    @FederateParameter
    public String Hydraulic_Valve_Commands; // see graph in slide 3
   // PGN : multiple PGNs per Federate are possible. we use the Federate name as a placeholder for now.
    @FederateParameter
    public  String VehicleControlPGN;
   // SPNs 
    @FederateParameter
    public String VehicleControlSPNs; 
    @FederateParameter
    public String Vehicle_Speed;
    @FederateParameter
    public String UCEFGateway_Motor_Operating_Mode;
    @FederateParameter
    public String UCEFGateway_Motor_Torque_cmd;
    @FederateParameter
    public String UCEFGateway_Motor_Speed;
    @FederateParameter
    public String UCEFGateway_Volt_Cmd;
    @FederateParameter
    public String EventInjection_Obstacle_Presence_distance;
    
}

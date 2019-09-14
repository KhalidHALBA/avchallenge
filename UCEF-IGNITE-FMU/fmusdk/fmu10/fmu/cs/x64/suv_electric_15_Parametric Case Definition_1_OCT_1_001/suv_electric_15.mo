//-------------------------------------------------------------
// IGNITE model 'suv_electric_15' translation to Modelica
//-------------------------------------------------------------

model IModel
    // --- parameters ---
    parameter Real      A = 2.73; // units=m^2
    parameter Real      Vnom = 375.0; // units=V
    parameter Real      Coeff_RR = 0.008;
    parameter Real      T_radius = 0.3814; // units=m
    parameter Real      Pm = 235000.0; // units=W
    parameter Real      J_wheel = 2.0; // units=kg.m^2
    parameter Real      Mass = 2134.0; // units=kg
    parameter Real      C_bat = 136.0; // units=A.hr
    parameter Real      Cd = 0.24;
    parameter Real      Paux = 200.0; // units=W
    parameter Real      FDR = 9.73;
    parameter Real      Tm = 440.0; // units=N.m
    parameter Real      Trac_Eff = 0.85;
    parameter Real      soc_init = 0.3;

    // --- model objects ---
    __advancedVehicle1 advancedVehicle1(
        mass = Mass,
        brake_max_force = 10000.0,
        cog_height = 0.7,
        air_resistance_height = 0.7,
        air_resistance_coef = Cd,
        air_density = 1.293,
        frontal_area = A,
        drawbar_height = 0.7,
        axleA_position = 1.61,
        axleB_position = -1.61,
        suspensionAK = 100000.0,
        suspensionBK = 100000.0,
        use_init_velocity = false,
        use_tau_suspension = true,
        use_terrain = true
    );

    IPowertrain.Gearsets.BasicDifferential basicDifferential3(
        ratio = FDR
    );

    IPowertrain.Breakouts.Plant.BasicVehicleBreakout basicVehicleBreakout1(
    );

    IPowertrain.Breakouts.Plant.BatteryBreakout batteryBreakout1(
    );

    IPowertrain.HybridElectric.Battery_2Pin battery_2Pin1(
        electrical_model = IPowertrain.HybridElectric.Types.BatteryModel.scalar,
        c_max = (C_bat * 3600.0),
        Voc_nom = Vnom,
        eff_co = 1.0,
        r_ch = 0.1,
        r_di = 0.05,
        init_soc = soc_init
    );

    Modelica.Electrical.Analog.Sources.ConstantCurrent constantCurrent1(
        I = Paux/Vnom
    );

    IPowertrain.Controllers.CycleDriver cycleDriver1(
        model_a = 0.2,
        model_r = 0.01,
        driver_lookahead_dt = 1.0,
        accn_f = 0.2,
        accn_p = 1.0,
        accn_i = 0.002,
        brake_f = 0.5,
        brake_p = 0.5
    );

    IPowertrain.Breakouts.Controller.CycleDriverBreakout cycleDriverBreakout1(
    );

    IPowertrain.Controllers.ElectricVehicleController electricVehicleController1(
        data_file = "vehicle_data.m",
        rb_max_brake_force = 15000.0,
        rb_speed_low = 2.0,
        gbx_discrete = true
    );

    IPowertrain.Breakouts.Controller.ElectricVehicleControllerDiscreteBreakout electricVehicleControllerDiscreteBreakout1(
    );

    Modelica.Electrical.Analog.Basic.Ground ground1(
    );

    Modelica.Mechanics.Rotational.Components.Inertia inertia1(
        J = J_wheel
    );

    Modelica.Mechanics.Rotational.Components.Inertia inertia2(
        J = J_wheel
    );

    Modelica.Mechanics.Rotational.Components.Inertia inertia3(
        J = J_wheel
    );

    Modelica.Mechanics.Rotational.Components.Inertia inertia4(
        J = J_wheel
    );

    Modelica.Blocks.Sources.IntegerConstant integerConstant1(
    );

    IPowertrain.Breakouts.Plant.MotorGeneratorBreakout motorGeneratorBreakout1(
    );

    IPowertrain.Tyres.SimpleTyre simpleTyre1(
        radius = T_radius,
        rolling_resistance_coeff = Coeff_RR
    );

    IPowertrain.Tyres.SimpleTyre simpleTyre2(
        radius = T_radius,
        rolling_resistance_coeff = Coeff_RR
    );

    IPowertrain.Tyres.SimpleTyre simpleTyre3(
        radius = T_radius,
        rolling_resistance_coeff = Coeff_RR
    );

    IPowertrain.Tyres.SimpleTyre simpleTyre4(
        radius = T_radius,
        rolling_resistance_coeff = Coeff_RR
    );

    // <rdesk.rmodel.Element {bf5348fb-5436-466b-b9cf-1203d95ba70e} name=Bus type=Standard:Bus label=b0>
    IPowertrain.Interfaces.BusComponent Bus;

    // <rdesk.rmodel.Element {2c3139b5-0933-486c-b32f-ab9105da08d1} name=Bus_1 type=Standard:Bus label=b1>
    IPowertrain.Interfaces.BusComponent Bus_1;

    // <rdesk.rmodel.Element {ca180138-b298-429d-97a8-a2fd19f4b8c7} name=Bus_2 type=Standard:Bus label=b2>
    IPowertrain.Interfaces.BusComponent Bus_2;

    // <rdesk.rmodel.Element {b25327a8-fdd3-440f-935a-73d28d9c7549} name=Bus_3 type=Standard:Bus label=b3>
    IPowertrain.Interfaces.BusComponent Bus_3;

    // <rdesk.rmodel.Element {4f8e2874-e2c1-4187-b3eb-a26b791b3633} name=Bus_4 type=Standard:Bus label=b4>
    IPowertrain.Interfaces.BusComponent Bus_4;


      model klass_UserBlockExp_1
        extends IStandard.UserDefined.UserBlockExp;
          // User code
          
              parameter IStandard.Parameters.RealParameter soc_init;
              parameter IStandard.Parameters.RealParameter Vnom;
              parameter IStandard.Parameters.RealParameter C_bat;
              parameter Real E_bat = Vnom * C_bat; //usable energy in the battery
              parameter Real eff_charging = 0.93; //Charging efficiency for MPGe calculation  
              parameter Real E_gallon = 33705; //W.hr/1 gallon
              
              Real delta_soc;
              Real soc;
              Real distance;
              Real E_cycle;
              Real E_cycle_mile;
              Real raw_mpge;
              Real mpge_label;
              
              IStandard.Blocks.Math.ProtectedDivision div(max_y = 1000);
              
          equation
                ur[1] = soc;
                distance = ur[2] / 1609.34; //meters > miles
                delta_soc = soc_init - soc;
                E_cycle = E_bat * delta_soc;
                
                //protected division when the distance is zero at the begining of the cycle
                div.u1 = E_cycle;
                div.u2 = distance;
                div.y = E_cycle_mile;
                
                raw_mpge = eff_charging * E_gallon / E_cycle_mile;
                mpge_label = 0.7 * raw_mpge; 
                
                // This is simplified to calculation example for a single cycle, 
                // 
                // EPA standard dictates the ouput over 5 cycles
                // or the regression 0.7 over CAFE (FTP72 and HWFET)
                     
      end klass_UserBlockExp_1;
        
      klass_UserBlockExp_1 UserBlockExp_1 (
      
        use_outputs_quant = {0,0,0}
        ,use_inputs_quant = {2,0,0}
        
        ,Vnom = Vnom
        ,soc_init = soc_init
        ,C_bat = C_bat
         
      );
          IPowertrain.HybridElectric.MotorGenerator_2Pin MotorGenerator_2Pin(
        eff_mot = Trac_Eff,
        max_pwr_mtr = Pm,
        max_pwr_gen = Pm,
        max_trq_mtr = Tm,
        max_trq_gen = Tm,
        current_limit_spec = IPowertrain.HybridElectric.Types.MotorCurrentLimit.Scalars,
        max_cur_mtr = Pm*1.02/Vnom,
        max_cur_gen = Pm*1.02/Vnom,
        trq_mtr_table_name = "mech_gen_profile",
        cur_mtr_table_name = "mech_gen_profile",
        gen_torque_separated = false,
        gen_current_separated = false,
        trq_limit_spec = IPowertrain.HybridElectric.Types.MotorCurrentLimit.Scalars
    );

    TCPLib.TCPDriveCycle TCPDriveCycle(
    );

    drivecycle.drivecycle.drivecycle drivecycle(
    );


    // --- reference objects ---

    // --- instance classes ---
    model __advancedVehicle1
        extends IPowertrain.Vehicles.AdvancedVehicle;
    end __advancedVehicle1;

    // --- interfaces ---
equation
    connect(basicVehicleBreakout1.vehicle_velocity, cycleDriverBreakout1.vehicle_velocity);

    connect(simpleTyre1.axle, advancedVehicle1.axleALTyre);

    connect(simpleTyre2.axle, advancedVehicle1.axleARTyre);

    connect(advancedVehicle1.axleBRTyre, simpleTyre3.axle);

    connect(advancedVehicle1.axleBLTyre, simpleTyre4.axle);

    connect(inertia1.flange_b, simpleTyre2.flange);

    connect(inertia2.flange_b, simpleTyre1.flange);

    connect(simpleTyre4.flange, inertia4.flange_a);

    connect(simpleTyre3.flange, inertia3.flange_a);

    connect(inertia3.flange_b, basicDifferential3.flange_b2);

    connect(inertia4.flange_b, basicDifferential3.flange_b1);

    connect(constantCurrent1.p, battery_2Pin1.pin_p);

    connect(constantCurrent1.n, battery_2Pin1.pin_n);

    connect(integerConstant1.y, electricVehicleControllerDiscreteBreakout1.shift_target_gear);

    connect(basicVehicleBreakout1.vehicle_velocity, electricVehicleControllerDiscreteBreakout1.vehicle_velocity);

    connect(batteryBreakout1.battery_soc, electricVehicleControllerDiscreteBreakout1.battery_soc);

    connect(electricVehicleControllerDiscreteBreakout1.brake_demand, basicVehicleBreakout1.brake_demand);

    connect(cycleDriverBreakout1.driver_accn_demand, electricVehicleControllerDiscreteBreakout1.driver_accn_demand);

    connect(motorGeneratorBreakout1.motor_gen_speed, electricVehicleControllerDiscreteBreakout1.motor_gen_speed);

    connect(cycleDriverBreakout1.driver_brake_demand, electricVehicleControllerDiscreteBreakout1.driver_brake_demand);

    connect(electricVehicleControllerDiscreteBreakout1.motor_gen_demand, motorGeneratorBreakout1.motor_gen_demand);

    connect(ground1.p, battery_2Pin1.pin_n);

    connect(cycleDriver1.bus, Bus.Bus);

    connect(cycleDriverBreakout1.bus, Bus.Bus);

    connect(electricVehicleControllerDiscreteBreakout1.bus, Bus_1.Bus);

    connect(electricVehicleController1.bus, Bus_1.Bus);

    connect(batteryBreakout1.bus, Bus_2.Bus);

    connect(battery_2Pin1.bus, Bus_2.Bus);

    connect(motorGeneratorBreakout1.bus, Bus_3.Bus);

    connect(basicVehicleBreakout1.bus, Bus_4.Bus);

    connect(advancedVehicle1.bus, Bus_4.Bus);

    connect(basicVehicleBreakout1.vehicle_position, UserBlockExp_1.ur[2]);

    connect(batteryBreakout1.battery_soc, UserBlockExp_1.ur[1]);

    connect(Bus_3.Bus, MotorGenerator_2Pin.bus);

    connect(battery_2Pin1.pin_p, MotorGenerator_2Pin.pin_p);

    connect(MotorGenerator_2Pin.flange, basicDifferential3.flange_a);

    connect(battery_2Pin1.pin_n, MotorGenerator_2Pin.pin_n);

    connect(TCPDriveCycle.driveCycle_a, cycleDriver1.cycle);

    connect(basicVehicleBreakout1.vehicle_velocity, drivecycle.vehicle_velocity);

    connect(drivecycle.driver_brake_demand, cycleDriverBreakout1.driver_brake_demand);

    connect(TCPDriveCycle.cycle_time, drivecycle.cycle_time);

    connect(TCPDriveCycle.cycle_time_ahead, drivecycle.cycle_time_ahead);

    connect(TCPDriveCycle.cycle_speed_kmph, drivecycle.cycle_speed_kmph);

    connect(TCPDriveCycle.cycle_speed_ahead_kmph, drivecycle.cycle_speed_ahead_kmph);


annotation (uses(Modelica(version="3.2.2")),
Diagram(coordinateSystem(preserveAspectRatio=false)), 
Icon(cordinateSystem(preserveAspectRatio=false), coordinateSystem(extent={{-100,-100},{100,100}}),graphics={Rectangle(
  extent={{-100,100},{100,-100}},
  lineColor={28,108,200},
  fillColor={255,255,255},
  fillPattern=FillPattern.Solid), Text(
  extent={{-88,42},{80,-50}},
  lineColor={28,108,200},
  fillColor={255,255,255},
  fillPattern=FillPattern.None,
 textString="Model")}));
end IModel;


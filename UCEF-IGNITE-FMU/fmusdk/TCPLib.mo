within ;
package TCPLib
    model TCPDriveCycle
    IPowertrain.Interfaces.DriveCycle_a driveCycle_a
      annotation (Placement(transformation(extent={{90,-10},{110,10}})));
    Modelica.Blocks.Interfaces.RealInput cycle_speed_ahead_kmph
      annotation (Placement(transformation(extent={{-140,60},{-100,100}})));
    Modelica.Blocks.Interfaces.RealOutput cycle_time
      annotation (Placement(transformation(extent={{-100,-70},{-120,-90}})));
    Modelica.Blocks.Interfaces.RealOutput cycle_time_ahead
      annotation (Placement(transformation(extent={{-100,-30},{-120,-50}})));
    Modelica.Blocks.Interfaces.RealInput cycle_speed_kmph
      annotation (Placement(transformation(extent={{-140,0},{-100,40}})));
    equation
    cycle_time = time;
    cycle_time_ahead = time + driveCycle_a.cycle_lookahead_dt;
    cycle_speed_kmph = driveCycle_a.cycle_speed_kmph;
    cycle_speed_ahead_kmph = driveCycle_a.cycle_lookahead_speed_kmph;
    end TCPDriveCycle;
    annotation (uses(IPowertrain(version="1.0.1"), Modelica(version="3.2.2")));
end TCPLib;
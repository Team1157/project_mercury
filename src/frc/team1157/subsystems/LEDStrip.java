package frc.team1157.subsystems;


import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1157.RobotMap;
import frc.team1157.commands.controlLED;

public class LEDStrip extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Victor redLED = new Victor(RobotMap.red);
    public Victor greenLED = new Victor(RobotMap.green);
    public Victor blueLED = new Victor(RobotMap.blue);



    public void setStrip(double red, double green, double blue) {
        redLED.set(red / 255.0);
        greenLED.set(green / 255.0);
        blueLED.set(blue / 255.0);
    }

    public void initDefaultCommand() {

        setDefaultCommand(new controlLED());
    }


}


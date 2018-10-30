package frc.team1157.subsystems;


import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1157.Robot;
import frc.team1157.RobotMap;
import frc.team1157.commands.JoystickCamera;

public class CameraMount extends Subsystem {

    public Servo pitchMotor = new Servo(RobotMap.cameraPitch);
    public Servo yawMotor = new Servo(RobotMap.cameraYaw);


    public void initDefaultCommand() {
        pitchMotor.set(.5);
        yawMotor.set(.5);
        Robot.camera0.setExposureAuto();
        Robot.camera0.setWhiteBalanceAuto();
        setDefaultCommand(new JoystickCamera());
    }

    public void stop() {
        pitchMotor.set(.5);
        yawMotor.set(.5);
    }
}


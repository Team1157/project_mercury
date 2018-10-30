package frc.team1157.subsystems;


import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.team1157.RobotMap;
import frc.team1157.commands.JoystickMecanum;


public class DriveTrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Victor frontRightVictor = new Victor(RobotMap.frontRightMotor);
    public Victor frontLeftVictor = new Victor(RobotMap.frontLeftMotor);
    public Victor backRightVictor = new Victor(RobotMap.backRightMotor);
    public Victor backLeftVictor = new Victor(RobotMap.backLeftMotor);
    public MecanumDrive mecanumDrive = new MecanumDrive(frontLeftVictor, backLeftVictor, frontRightVictor,
            backRightVictor);

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
        frontRightVictor.setExpiration(.1);
        frontLeftVictor.setExpiration(.1);
        backLeftVictor.setExpiration(.1);
        backRightVictor.setExpiration(.1);
        super.setDefaultCommand(new JoystickMecanum());
    }

    public void DriveMech(double x, double y, double rotation, double gyro) {
        mecanumDrive.driveCartesian(-x, y, rotation, gyro);
    }

    public void stop() {
        frontRightVictor.set(0);
        frontLeftVictor.set(0);
        backRightVictor.set(0);
        backLeftVictor.set(0);
    }
}


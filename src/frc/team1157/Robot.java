/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team1157;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1157.commands.DualJoystickTank;
import frc.team1157.commands.JoystickMecanum;
import frc.team1157.subsystems.CameraMount;
import frc.team1157.subsystems.DriveTrain;
import frc.team1157.subsystems.LEDStrip;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
// If you rename or move this class, update the build.properties file in the project root
public class Robot extends TimedRobot {

    public static final DriveTrain driveTrain = new DriveTrain();
    public static final LEDStrip ledStrip = new LEDStrip();
    public static final CameraMount cameraMount = new CameraMount();
    public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    public static final CameraServer cameraServer = CameraServer.getInstance();
    public static final UsbCamera camera0 = cameraServer.startAutomaticCapture();
    public static final UsbCamera camera1 = cameraServer.startAutomaticCapture();
    public static final UsbCamera camera2 = cameraServer.startAutomaticCapture();
    public static final UsbCamera camera3 = cameraServer.startAutomaticCapture();

    public static OI oi;
    Accelerometer a = new BuiltInAccelerometer();
    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();
    private SendableChooser<Command> teleopMode = new SendableChooser<>();
    private long lastTime = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();
        // chooser.addObject("My Auto", new MyAutoCommand());

        SmartDashboard.putString("Test", "working");
        LiveWindow.addSensor("Gyro", 0, gyro);
    }

    @Override
    public void robotPeriodic() {
        long time = System.currentTimeMillis();
        SmartDashboard.putNumber("UPS", 1000 / (time - lastTime));
        lastTime = time;
        SmartDashboard.putNumber("Accel X", a.getX());
        SmartDashboard.putNumber("Accel Y", a.getY());
        SmartDashboard.putNumber("Accel Z", a.getZ());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (autonomousCommand != null) {
            autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        double gyroAngle = gyro.getAngle();
        SmartDashboard.putNumber("Gyro", gyroAngle);
        //SmartDashboard.putNumber("gyroAngle",gyroAngle);
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }
}

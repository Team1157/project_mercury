package frc.team1157.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1157.OI;
import frc.team1157.Robot;


public class JoystickMecanum extends Command {

    double twistDamp = 6.0;
    double speedDamp = 1.0;
    double dir = -1;

    public JoystickMecanum(double d) {
        dir;
        requires(Robot.driveTrain);
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {

    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        speedDamp = (OI.stick0.getThrottle() - 1) / -2;
        twistDamp = speedDamp * .6;
        SmartDashboard.putNumber("X", OI.stick0.getX());
        SmartDashboard.putNumber("Y", OI.stick0.getY());
        SmartDashboard.putNumber("Z", OI.stick0.getTwist());
        SmartDashboard.putNumber("T", OI.stick0.getThrottle());
        SmartDashboard.putNumber("sd", speedDamp);
        SmartDashboard.putNumber("td", twistDamp);
        SmartDashboard.putNumber("gyroAngle", Robot.gyro.getAngle());
        SmartDashboard.putString("Direction", (dir>0)?"Backwards":"Forwards");
        if (Math.abs(OI.stick0.getTwist()) > 0.15 || Math.abs(OI.stick0.getX()) > 0.15 || Math.abs(
                OI.stick0.getY()) > 0.15) {
            double dx = dir * OI.stick0.getX() * speedDamp;
            double dy = dir * OI.stick0.getY() * speedDamp;
            double twist = OI.stick0.getTwist() * twistDamp;
            dx *= (OI.stick0.getTrigger()) ? 0 : 1;
            Robot.driveTrain.DriveMech(dx, dy, twist, Robot.gyro.getAngle());
        }
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    @Override
    protected void interrupted() {
        Robot.driveTrain.stop();
    }
}

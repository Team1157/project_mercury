package frc.team1157.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1157.Robot;
import org.opencv.core.Mat;

import java.util.ArrayList;


public class controlLED extends Command {
    public ArrayList<double[]> singleQueue = new ArrayList();
    public ArrayList<double[]> repeatQueue = new ArrayList();
    double[] currentLED;

    public controlLED() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.ledStrip);
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
        if (singleQueue.size() != 0) {
            currentLED = singleQueue.remove(0);
            Robot.ledStrip.setStrip(currentLED[0], currentLED[1], currentLED[2]);
        }else if (repeatQueue.size()  != 0) {
            currentLED = repeatQueue.remove(0);
            repeatQueue.add(currentLED);
            Robot.ledStrip.setStrip(currentLED[0], currentLED[1], currentLED[2]);
        }else {
            Robot.ledStrip.setStrip(0,0,0);
        }
    }

    protected void fadeEffect(boolean repeating, boolean replace, double[] color1, double[] color2, int steps) {
        if (replace) {
            if (repeating) {
                repeatQueue.clear();
            } else {
                singleQueue.clear();
            }
        }
        double redDiff = color1[0]-color2[0];
        double greenDiff = color1[1]-color2[1];
        double blueDiff = color1[2]-color2[2];
        for(int i = 0; i < steps; i++) {
            double redValue = ((redDiff / steps) * i) + color1[0];
            double greenValue = ((greenDiff / steps) * i) + color1[1];
            double blueValue = ((blueDiff / steps) * i) + color1[2];
            if (repeating) {
                repeatQueue.add(new double[] {redValue, greenValue, blueValue});
            } else {
                singleQueue.add(new double[] {redValue, greenValue, blueValue});
            }
        }
    }

    protected void fadeSwitchEffect(boolean repeating, boolean replace, double[] color1, double[] color2, int steps) {
        fadeEffect(repeating, replace, color1, color2, steps);
        fadeEffect(repeating, false, color2, color1, steps);
    }

    protected void flashEffect(boolean repeating, boolean replace, double[] color1, double[] color2, int amount) {
        if (replace) {
            if (repeating) {
                repeatQueue.clear();
            } else {
                singleQueue.clear();
            }
        }
        for (int i = 0; i < amount; i++) {
            if (repeating) {
                repeatQueue.add(color1);
                repeatQueue.add(color2);
            } else {
                singleQueue.add(color1);
                singleQueue.add(color2);
            }
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
        Robot.ledStrip.setStrip(0,0,0);
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
        super.interrupted();
    }
}

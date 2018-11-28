/**
 * AngryBee AngryMe
 * This is my first Basic 2D Android Game app way back year 2013.
 * Using a java game development framework called LibGDX - https://libgdx.badlogicgames.com/
 *
 * @author        Christopher M. Natan
 * @version       1.0
 */
package cmnworks.com.angrybee;

public class Game {
    public final String START      = "Start";
    public final String RUNNING    = "Running";
    public final String FINISHED   = "Finished";
    public final String OVER       = "Over";
    public String setState         = START;

    public Game() {}
    public boolean isStart() {
        if (this.setState == this.START) return true;
        return false;
    }
    public boolean isRunning() {
        if (this.setState == this.RUNNING) return true;
        return false;
    }
    public boolean isFinished() {
        if (this.setState == this.FINISHED) return true;
        return false;
    }
    public boolean isOver() {
        if (this.setState == this.OVER) return true;
        return false;
    }
}

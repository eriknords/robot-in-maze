package robot2_0;

/**
 * A robot interface which defines the structure for robot subclasses.
 *
 * @author Erik Nordström (tfy15enm)
 */

public interface Robot {

    /**
     * Moves the object
     */
    void move();

    /**
     * Returns this {@code Position}
     * @return Position
     */
    Position getPosition();

    /**
     * Sets this {@code Position} to the new position if it is not blocked by anything
     * or outside the boundaries defined by the constants NR_OF_COLUMNS, NR_OF_ROWS and x=y=0.
     * @param newPosition The new position
     */
    void setPosition(Position newPosition);

    /**
     * Return true if the RandomRobot has reached the goal
     * @return boolean
     */
    boolean hasReachedGoal();


}
package robot2_0;

/**
 * This class represents a direction class that keeps track of direction by using the north,
 * east, south and west points of the compass.
 *
 * @author Erik Nordström (tfy15enm)
 */

public class Direction {

    public static final int NORTH_DIRECTION = 0;
    public static final int EAST_DIRECTION = 1;
    public static final int SOUTH_DIRECTION = 2;
    public static final int WEST_DIRECTION = 3;

    private int direction;

    public Direction() {
        direction = 0;
    }

    /**
     * Returns an int representation of the direction
     *
     * @return int
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Turn 180 degrees
     */
    public void turnBack() {
        this.direction = (this.direction + 2) % 4;
    }
    /**
     * Calculates direction of the current position with regards to the previous position
     * @param previousPos the previous position
     * @param currentPos the current position
     */
    public void updateDirection(Position previousPos, Position currentPos) {
        Position diff = new Position(currentPos.getX() - previousPos.getX(),
                currentPos.getY() - previousPos.getY());

        if (diff.getY() == 0 && diff.getX() == 1) {
            this.direction = EAST_DIRECTION;
        } else if (diff.getY() == 0 && diff.getX() == -1) {
            this.direction = WEST_DIRECTION;
        } else if (diff.getY() == -1 && diff.getX() == 0) {
            this.direction = NORTH_DIRECTION;
        } else if (diff.getY() == 1 && diff.getX() == 0) {
            this.direction = SOUTH_DIRECTION;
        } else if (diff.getY() != 0 && diff.getX() != 0) {
            throw new RuntimeException("INVALID POSITION: Current position is not adjacent to previous.");
        }
    }
}
package robot2_0;

/**
 * This class represents an Abstract robot class that moves to new adjacent positions inside a maze.
 *
 * @author Erik Nordström (tfy15enm)
 */
public abstract class AbstractRobot implements Robot {

    Position position;
    Position previousPosition;
    Maze maze;
    Direction direction = new Direction();

    public AbstractRobot(Maze inputMaze) {
        maze = inputMaze;
        position = maze.getStart();
        previousPosition = maze.getStart();
    }

    /**
     * Moves the robot
     */
    @Override
    public abstract void move();

    /**
     * @return Position
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Sets this {@code Position} to the new position if it is movable and adjacent.
     * @param newPosition the position we want to move to
     * @throws RuntimeException the new position is not movable/adjacent
     */
    @Override
    public void setPosition(Position newPosition) {
        if (canSetPosition(newPosition)) {
            previousPosition = position;
            position = newPosition;
            return;
        }
        throw new RuntimeException("POSITION ERROR: Robot cam not move to the new position.");
    }

    /**
     * Sets this {@code Position} to the new position if it is movable and adjacent.
     * @param newPosition the position we want to move to
     * @throws RuntimeException the new position is not movable/adjacent
     */
    @Override
    public boolean canSetPosition(Position newPosition) {
        return position.isAdjacent(newPosition) && maze.isMovable(newPosition);
    }

    /**
     * Return true if the robot has reached the goal
     * @return boolean
     */
    @Override
    public boolean hasReachedGoal() {
        return maze.isGoal(position);
    }
}

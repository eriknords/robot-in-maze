package robot2_0;

/**
 * This class represents a LeftHandRuleRobot that extends the AbstractRobotClass.
 * The robot moves around in the maze, always keeping its left robotic hand against the wall.
 * It uses the direction object.
 *
 * @author Erik Nordström (tfy15enm)
 */
public class LeftHandRuleRobot extends AbstractRobot {

    /**
     * Initializes objects needed for the LeftHandRuleRobot.
     * @param inputMaze the maze
     */
    public LeftHandRuleRobot(Maze inputMaze) {
        super(inputMaze);
    }

    /**
     * Moves the robot either north if no walls are nearby, or forward while still
     * having its robotic hand against the wall.
     */
    @Override
    public void move() {
        if (noNearbyWalls()) {
            setPosition(position.getPosToNorth());
            return;
        }
        if (!moveInDirection() && !outerCorner() && !innerCorner()) {
            previousPosition = position;
            // Turn 180 degrees
            direction.turnBack();
        }
        direction.updateDirection(previousPosition, position);
    }

    /**
     * Checks if the robot has any adjacent walls.
     * @return boolean
     */
    private boolean noNearbyWalls() {
        return maze.isMovable(position.getPosToNorth())
                && maze.isMovable(position.getPosToNorth().getPosToEast())
                && maze.isMovable(position.getPosToEast())
                && maze.isMovable(position.getPosToEast().getPosToSouth())
                && maze.isMovable(position.getPosToSouth())
                && maze.isMovable(position.getPosToSouth().getPosToWest())
                && maze.isMovable(position.getPosToWest())
                && maze.isMovable(position.getPosToWest().getPosToNorth());
    }

    /**
     * Tries to move the robot in its current direction while still having its hand on the left wall.
     * @return boolean
     */
    private boolean moveInDirection() {
        if (direction.getDirection() == Direction.NORTH_DIRECTION
                && !maze.isMovable(position.getPosToWest())
                && canSetPosition(position.getPosToNorth())) {
            setPosition(position.getPosToNorth());
            return true;
        } else if (direction.getDirection() == Direction.EAST_DIRECTION
                && !maze.isMovable(position.getPosToNorth())
                && canSetPosition(position.getPosToEast())) {
            setPosition(position.getPosToEast());

            return true;
        } else if (direction.getDirection() == Direction.SOUTH_DIRECTION
                && !maze.isMovable(position.getPosToEast())
                && canSetPosition(position.getPosToSouth())) {
            setPosition(position.getPosToSouth());
            return true;
        } else if (direction.getDirection() == Direction.WEST_DIRECTION
                && !maze.isMovable(position.getPosToSouth())
                && canSetPosition(position.getPosToWest())) {
            setPosition(position.getPosToWest());
            return true;
        }
        return false;
    }

    /**
     * checks for an outer corner of a wall inside the maze (not the boundary of the maze). If one is
     * found, the robot move pasts the outer corner while still keeping the hand on the wall.
     * @return boolean
     */
    private boolean outerCorner() {
        if (direction.getDirection() == Direction.NORTH_DIRECTION
                && !maze.isMovable(position.getPosToWest().getPosToSouth())
                && canSetPosition(position.getPosToWest())) {
            setPosition(position.getPosToWest());
            return true;
        } else if (direction.getDirection() == Direction.EAST_DIRECTION
                && !maze.isMovable(position.getPosToNorth().getPosToWest())
                && canSetPosition(position.getPosToNorth())) {
            setPosition(position.getPosToNorth());
            return true;
        } else if (direction.getDirection() == Direction.SOUTH_DIRECTION
                && !maze.isMovable(position.getPosToEast().getPosToNorth())
                && canSetPosition(position.getPosToEast())) {
            setPosition(position.getPosToEast());
            return true;
        } else if (direction.getDirection() == Direction.WEST_DIRECTION
                && !maze.isMovable(position.getPosToSouth().getPosToEast())
                && canSetPosition(position.getPosToSouth())) {
            setPosition(position.getPosToSouth());
            return true;
        }
        return false;
    }

    /**
     * checks for an inner corner of a wall inside the maze. If one is found, the robot move pasts
     * the inner corner while still keeping the hand on the wall.
     * @return boolean
     */
    private boolean innerCorner() {
        if (direction.getDirection() == Direction.NORTH_DIRECTION
                && !maze.isMovable(position.getPosToWest())
                && !maze.isMovable(position.getPosToNorth())
                && canSetPosition(position.getPosToEast())) {
            setPosition(position.getPosToEast());
            return true;
        } else if (direction.getDirection() == Direction.EAST_DIRECTION
                && !maze.isMovable(position.getPosToNorth())
                && !maze.isMovable(position.getPosToEast())
                && canSetPosition(position.getPosToSouth())) {
            setPosition(position.getPosToSouth());
            return true;
        } else if (direction.getDirection() == Direction.SOUTH_DIRECTION
                && !maze.isMovable(position.getPosToEast())
                && !maze.isMovable(position.getPosToSouth())
                && canSetPosition(position.getPosToWest())) {
            setPosition(position.getPosToWest());
            return true;
        } else if (direction.getDirection() == Direction.WEST_DIRECTION
                && !maze.isMovable(position.getPosToSouth())
                && !maze.isMovable(position.getPosToWest())
                && canSetPosition(position.getPosToNorth())) {
            setPosition(position.getPosToNorth());
            return true;
        }
        return false;
    }
}

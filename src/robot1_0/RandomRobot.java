package robot1_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a RandomRobot that moves to new adjacent positions randomly inside a maze.
 * It only moves back to previous position if there are no other adjacent movable positions.
 *
 * @author Erik Nordström (tfy15enm)
 */
public class RandomRobot {

    private static final Random RANDOM_GENERATOR = new Random();

    private Position position;
    private Position previousPosition;
    private final Maze maze;

    /**
     * Initializes objects needed for the RandomRobot.
     * @param inputMaze the maze
     */
    public RandomRobot(Maze inputMaze) {
        maze = inputMaze;
        position = maze.getStart();
        previousPosition = maze.getStart();
    }

    /**
     * Moves the RandomRobot to random an adjacent position that is movable. If none exists, the
     * RandomRobot will move back to the precious position.
     */
    public void move() {
        List<Position> movablePositions = new ArrayList<>();
        List<Position> adjacentPositions = List.of(position.getPosToSouth(), position.getPosToNorth(),
                position.getPosToWest(), position.getPosToEast());

        // Add to list all adjacent positions that is movable and not the previous position
        for (Position position : adjacentPositions) {
            if (!previousPosition.equals(position) && maze.isMovable(position)) {
                movablePositions.add(position);
            }
        }
        // If no positions were added to the list
        if (movablePositions.isEmpty()) {
            if (position.equals(previousPosition)) {
                throw new RuntimeException("INVALID MAZE: Robot1.0 can not move from start position.");
            }
            // Move back to previous position
            setPosition(previousPosition);
        } else { // Move in any random available position from list
            int random = RANDOM_GENERATOR.nextInt(movablePositions.size());
            setPosition(movablePositions.get(random));
        }
    }

    /**
     * Return this {@code Position}
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets this {@code Position} to the new position.
     * @param newPosition the position we want to move to
     */
    private void setPosition(Position newPosition) {
        if (position.isAdjacent(newPosition) && maze.isMovable(newPosition)) {
            previousPosition = position;
            position = newPosition;
            return;
        }
        throw new RuntimeException("POSITION ERROR: Robot1.0 cam not move to the new position.");
    }

    /**
     * Return true if the RandomRobot has reached the goal
     * @return boolean
     */
    public boolean hasReachedGoal() {
        return maze.isGoal(position);
    }
}
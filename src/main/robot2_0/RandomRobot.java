package robot2_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a RandomRobot that moves to new adjacent positions randomly inside a maze.
 * It only moves back to previous position if there are no other adjacent movable positions.
 *
 * @author Erik Nordström (tfy15enm)
 */
public class RandomRobot extends AbstractRobot {

    private static final Random RANDOM_GENERATOR = new Random();

    /**
     * Initializes objects needed for the RandomRobot.
     * @param inputMaze the maze
     */
    public RandomRobot(Maze inputMaze) {
        super(inputMaze);
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
}
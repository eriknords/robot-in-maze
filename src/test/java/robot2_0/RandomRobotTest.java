package robot2_0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Objects;

/**
 * A test program to test that the RandomRobot is moving around in an arbitrary maze until it reaches the goal.
 * @author Erik Nordström (tfy15enm)
 */
public class RandomRobotTest {

    private static final int MAX_MOVES = 500;

    private int moves = 0;

    @Test
    public void testRandomRobot() throws FileNotFoundException {
        // GIVEN a maze and the random robot
        String file = Objects.requireNonNull(MemoryRobotTest.class.getClassLoader()
                .getResource("maze.txt")).getFile();
        Reader mazeReader = new FileReader(file);

        Maze maze = new Maze(mazeReader);
        RandomRobot robot = new RandomRobot(maze);

        // WHEN moving the robot until goal is reached (or very high number of max moves for complex mazes)
        while (!robot.hasReachedGoal() && moves < MAX_MOVES) {
            robot.move();
            moves++;
        }
        // THEN verify that the goal has been reached
        Assertions.assertTrue(robot.hasReachedGoal());
    }

    @Test
    public void testRandomRobotStuckInStart() {
        // GIVEN a maze with no movable position adjacent to the start position and the random robot
        Maze invalidMaze = new Maze(new StringReader("*S***G*"));
        RandomRobot robot = new RandomRobot(invalidMaze);

        // WHEN trying to move the robot
        try {
            robot.move();
            // THEN a RunTimeException is thrown
        } catch (RuntimeException re) {
            Assertions.assertEquals("INVALID MAZE: RandomRobot can not move from start position.",
                    re.getMessage());
        }
        // THEN verify that the goal has not been reached
        Assertions.assertFalse(robot.hasReachedGoal());
    }
}
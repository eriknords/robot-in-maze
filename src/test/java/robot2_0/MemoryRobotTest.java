package robot2_0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;

/**
 * A test program to test that the MemoryRobot is moving around in an arbitrary maze until it reaches the goal.
 * @author Erik Nordström (tfy15enm)
 */
public class MemoryRobotTest {

    private static final int MAX_MOVES = 200;
    private static MemoryRobot robot;

    @BeforeEach
    public void beforeClass() throws FileNotFoundException {
        // Hopefully this will work since maze.txt is located in test resources
        String file = Objects.requireNonNull(MemoryRobotTest.class.getClassLoader()
                .getResource("maze.txt")).getFile();
        Reader mazeReader = new FileReader(file);

        Maze maze = new Maze(mazeReader);
        robot = new MemoryRobot(maze);
    }

    @Test
    public void testMemoryRobot() {
        // GIVEN a maze and the memory robot
        int moves = 0;

        // WHEN moving the robot until goal is reached (or very high number of max moves for complex mazes)
        while (!robot.hasReachedGoal() && moves < MAX_MOVES) {
            robot.move();
            moves++;
        }
        // THEN verify that the goal has been reached
        Assertions.assertTrue(robot.hasReachedGoal());
    }
}

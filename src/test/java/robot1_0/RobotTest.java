package robot1_0;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


/**
 * A test program to test that the RandomRobot is moving around in an arbitrary maze until it reaches the goal.
 * @author Erik Nordström (tfy15enm)
 */
public class RobotTest {

    /**
     * Command line test program of the RandomRobot.
     * The first argument should be the maze file name.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        int moves = 0;

        try (Reader input = new FileReader(args[0])) {
            // GIVEN a valid maze
            Maze maze = new Maze(input);
            RandomRobot robot = new RandomRobot(maze);

            // WHEN moving the robot unit goal is reached (or very high number of max moves for complex mazes)
            while (!robot.hasReachedGoal() && moves < 500) {
                robot.move();
                moves++;

                System.out.printf("(%d,%d)\n", robot.getPosition().getX(), robot.getPosition().getY());
            }
            // THEN verify that the goal has been reached (can't use JUnit when compiling in terminal)
            if (robot.hasReachedGoal()) {
                System.out.printf("SUCCESS: Robot1.0 reached the goal after %d moves\n", moves);
            } else {
                System.out.printf("FAIL: Robot1.0 did not manage to reach the goal after %d moves\n", moves);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }
}
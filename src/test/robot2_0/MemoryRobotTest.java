package robot2_0;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MemoryRobotTest {

    /**
     * Command line test program of the RandomRobot.
     * The first argument should be the maze file name.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        int moves = 0;

        try (Reader input = new FileReader("/home/erik/projects/robot-in-maze/src/main/robot2_0/labyrint.txt")) {
            // GIVEN a valid maze
            Maze maze = new Maze(input);
            MemoryRobot robot = new MemoryRobot(maze);

            // WHEN moving the robot unit goal is reached (or very high number of max moves for complex mazes)
            while (!robot.hasReachedGoal() && moves < 100) {
                robot.move();
                moves++;

                System.out.printf("(%d,%d)\n", robot.getPosition().getX(), robot.getPosition().getY());
            }
            // THEN verify that the goal has been reached (can't use JUnit when compiling in terminal)
            if (robot.hasReachedGoal()) {
                System.out.printf("SUCCESS: LeftHandRuleRobot reached the goal after %d moves\n", moves);
            } else {
                System.out.printf("FAIL: LeftHandRuleRobot did not manage to reach the goal after %d moves\n", moves);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }
}

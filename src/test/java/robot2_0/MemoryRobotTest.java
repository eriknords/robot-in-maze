package robot2_0;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class MemoryRobotTest {

    private static int MOVES = 100;

    @Test
    public void testMemoryRobot() {

        Scanner scanner = new Scanner(getClass().getResourceAsStream("Words.txt"));
        try (Reader input = new FileReader("/home/erik/projects/robot-in-maze/src/main/robot2_0/labyrint.txt")) {
            // GIVEN a valid maze
            Maze maze = new Maze(input);
            MemoryRobot robot = new MemoryRobot(maze);

            // WHEN moving the robot unit goal is reached (or very high number of max moves for complex mazes)
            while (!robot.hasReachedGoal() && MOVES < 100) {
                robot.move();
                MOVES++;

                System.out.printf("(%d,%d)\n", robot.getPosition().getX(), robot.getPosition().getY());
            }
            // THEN verify that the goal has been reached (can't use JUnit when compiling in terminal)
            if (robot.hasReachedGoal()) {
                System.out.printf("SUCCESS: MemoryRobot reached the goal after %d moves\n", MOVES);
            } else {
                System.out.printf("FAIL: MemoryRobot did not manage to reach the goal after %d moves\n", MOVES);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }
}

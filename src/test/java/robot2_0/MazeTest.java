package robot2_0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * A test program to test functionality of the Maze class.
 * @author Erik Nordström (tfy15enm)
 */
class MazeTest {

    @Test
    public void testMazeSunnyDay() {
        // GIVEN a maze string
        StringReader mazeString = new StringReader("*S G*");

        // WHEN generating the maze
        Maze maze = new Maze(mazeString);

        // THEN
        Assertions.assertEquals(maze.getNumRows(), 1);
        Assertions.assertEquals(maze.getNumColumns(), 5);

        Assertions.assertEquals(maze.getStart(), new Position(1,0));
        Assertions.assertTrue(maze.isMovable(new Position(2,0)));
        Assertions.assertFalse(maze.isMovable(new Position(-1,0)));
        Assertions.assertTrue(maze.isGoal(new Position(3,0)));
    }

    @Test
    public void testMazeNoStart() {
        // GIVEN a maze string with no start
        StringReader mazeString = new StringReader("* G*");

        // WHEN generating the maze
        try {
            new Maze(mazeString);
            // THEN a RunTimeException is thrown
        } catch (RuntimeException re) {
            Assertions.assertEquals(re.getMessage(), "MAZE ERROR: Start position of maze does not exist.");
        }
    }

    @Test
    public void testMazeMultipleStart() {
        // GIVEN a maze string with no start
        StringReader mazeString = new StringReader("*SS G*");

        // WHEN generating the maze
        try {
            new Maze(mazeString);
            // THEN a RunTimeException is thrown
        } catch (RuntimeException re) {
            Assertions.assertEquals(re.getMessage(), "MAZE ERROR: Multiple start positions is not allowed.");
        }
    }

    @Test
    public void testMazeNoGoal() {
        // GIVEN a maze string with no start
        StringReader mazeString = new StringReader("*S *");

        // WHEN generating the maze
        try {
            new Maze(mazeString);
            // THEN a RunTimeException is thrown
        } catch (RuntimeException re) {
            Assertions.assertEquals(re.getMessage(), "MAZE ERROR: Goal of maze does not exist.");
        }
    }
}

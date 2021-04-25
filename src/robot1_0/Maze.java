package robot1_0;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a Maze that reads a maze from a file into an array list. The maze
 * keeps track of its size, start position and can check if a {@code Position} is movable or even a goal position.
 *
 * @author Erik Nordström (tfy15enm)
 */
public class Maze {

    private final ArrayList<String> maze;

    private int NR_OF_ROWS;
    private int NR_OF_COLUMNS;
    private Position start;

    /**
     * Generating the maze from file reader
     *
     * @param input the file reader to read from
     */
    public Maze(Reader input) {
        boolean goalExists = false;
        NR_OF_ROWS = 0;
        NR_OF_COLUMNS = 0;

        maze = new ArrayList<>();
        Scanner scanner = new Scanner(input);

        while (scanner.hasNext()) {
            String currentLine = scanner.nextLine();

            for (int i = 0; i < currentLine.length(); i++) {
                char currentChar = currentLine.charAt(i);
                if (start == null && currentChar == 'S') {
                    start = new Position(i, NR_OF_ROWS);
                } else if (start != null && currentChar == 'S') {
                    throw new RuntimeException("MAZE ERROR: Multiple start positions is not allowed.");
                }
                if (currentChar == 'G') {
                    goalExists = true;
                }
            }
            if (NR_OF_COLUMNS < currentLine.length()) {
                NR_OF_COLUMNS = currentLine.length();
            }
            maze.add(currentLine);
            NR_OF_ROWS++;
        }

        if (!goalExists) {
            throw new RuntimeException("MAZE ERROR: Goal of maze does not exist.");
        }
        if (start == null) {
            throw new RuntimeException("MAZE ERROR: Start position of maze does not exist.");
        }
    }

    /**
     * Return true if the position is movable to from this {@code Position}.
     * @param position the position we want to check if movable
     * @return boolean
     */
    public boolean isMovable(Position position) {
        if (position.getX() < 0 || position.getX() >= NR_OF_COLUMNS ||
                position.getY() < 0 || position.getY() >= NR_OF_ROWS) {
            return false;
        }
        char newChar = getChar(position);
        return newChar == ' ' || newChar == 'G' || newChar == 'S';
    }

    /**
     * Return true if the position is a goal of this {@code Maze}.
     * @param position the position we want to check if goal
     * @return boolean
     */
    public boolean isGoal(Position position) {
        char newChar = getChar(position);
        return newChar == 'G';
    }

    /**
     * Returns the start position of this {@code Maze}
     * @return Position
     */
    public Position getStart() {
        return start;
    }

    /**
     * Returns the maximum number of columns of this {@code Maze}
     * @return int
     */
    public int getNumColumns() {
        return NR_OF_COLUMNS;
    }

    /**
     * Returns the maximum number of rows of this {@code Maze}
     * @return int
     */
    public int getNumRows() {
        return NR_OF_ROWS;
    }

    /**
     * Helper method
     * @param position the position where we want to check the current char
     * @return char
     */
    private char getChar(Position position) {
        String row = maze.get(position.getY());
        return row.charAt(position.getX());
    }
}
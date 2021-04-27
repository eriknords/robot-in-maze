package robot1_0;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents a location (position) in {@code (x, y)} coordinate space, specified in integer precision.
 * This class is used for navigation within the maze or storing information of positions.
 *
 * @author Erik Nordström (tfy15enm)
 */
public class Position {

    private final int x;
    private final int y;

    /**
     * Constructs and initializes a point at the specified
     * {@code (x, y)} location in the coordinate space.
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of this {@code Position}
     * in integer precision.
     * @return the x-coordinate of this {@code Position}
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this {@code Position}
     * in integer precision.
     * @return the y-coordinate of this {@code Position}
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the position south of this {@code Position}
     * @return Position
     */
    public Position getPosToSouth() {
        return new Position(x, y + 1);
    }

    /**
     * Returns the position north of this {@code Position}
     * @return Position
     */
    public Position getPosToNorth() {
        return new Position(x, y - 1);
    }

    /**
     * Returns the position west of this {@code Position}
     * @return Position
     */
    public Position getPosToWest() {
        return new Position(x - 1, y);
    }

    /**
     * Returns the position east of this {@code Position}
     * @return Position
     */
    public Position getPosToEast() {
        return new Position(x + 1, y);
    }


    /**
     * Return true if the position is adjacent to this {@code Position}.
     * @param newPosition the position we want to check if adjacent
     * @return boolean
     */
    public boolean isAdjacent(Position newPosition) {
        if (!this.equals(newPosition)) {
            return getAllAdjacent().contains(newPosition);
        }
        return false;
    }

    /**
     * Return a list of all positions
     * @return List<Position>
     */
    private List<Position> getAllAdjacent() {
        return Arrays.asList(
                new Position(x, y + 1),
                new Position(x, y - 1),
                new Position(x - 1, y),
                new Position(x + 1, y)
        );
    }

    /**
     * Return true if the position in the argument is the same as this {@code Position}
     *
     * @param p the position object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object p) {
        if (p instanceof Position) {
            Position pos = (Position) p;
            return pos.x == x && pos.y == y;
        }
        return false;
    }

    /**
     * Return the hash code of the {@code (x, y)} coordinates
     *
     * @return int.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return (31 * hash + getX()) + (31 * hash + getY());
    }
}
package robot2_0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test program to test functionality of the Direction class.
 * @author Erik Nordström (tfy15enm)
 */
public class DirectionTest {

    private static Direction direction;

    @BeforeEach
    public void beforeMethod()   {
       direction = new Direction();
    }

    @Test
    public void testTurnBack() {
        // GIVEN an initial north direction
        Assertions.assertEquals(direction.getDirection(), Direction.NORTH_DIRECTION);

        // WHEN turning back
        direction.turnBack();

        // THEN the new direction is south
        Assertions.assertEquals(direction.getDirection(), Direction.SOUTH_DIRECTION);
    }

    @Test
    void testUpdateDirectionNorth() {
        // GIVEN a previous and current position
        Position previous = new Position(0, 1);
        Position current = new Position(0, 0);

        // WHEN updating the direction
        direction.updateDirection(previous, current);

        // THEN the updated direction should be south
        Assertions.assertEquals(direction.getDirection(), Direction.NORTH_DIRECTION);
    }

    @Test
    void testUpdateDirectionEast() {
        // GIVEN a previous and current position
        Position previous = new Position(0, 0);
        Position current = new Position(1, 0);

        // WHEN updating the direction
        direction.updateDirection(previous, current);

        // THEN the updated direction should be south
        Assertions.assertEquals(direction.getDirection(), Direction.EAST_DIRECTION);
    }

    @Test
    void testUpdateDirectionSouth() {
        // GIVEN a previous and current position
        Position previous = new Position(0, 0);
        Position current = new Position(0, 1);

        // WHEN updating the direction
        direction.updateDirection(previous, current);

        // THEN the updated direction should be south
        Assertions.assertEquals(direction.getDirection(), Direction.SOUTH_DIRECTION);
    }

    @Test
    void testUpdateDirectionWest() {
        // GIVEN a previous and current position
        Position previous = new Position(1, 0);
        Position current = new Position(0, 0);

        // WHEN updating the direction
        direction.updateDirection(previous, current);

        // THEN the updated direction should be south
        Assertions.assertEquals(direction.getDirection(), Direction.WEST_DIRECTION);
    }

    @Test
    void testUpdateDirectionInvalidPositions() {
        // GIVEN a previous and current position that is not adjacent to each other
        Position previous = new Position(0, 0);
        Position current = new Position(0, 10);

        // WHEN updating the direction
        try {
            direction.updateDirection(previous, current);
            // THEN a RunTimeException is thrown
        } catch (RuntimeException re) {
            Assertions.assertEquals(re.getMessage(),
                    "INVALID POSITION: Current position is not adjacent to previous.");
        }
    }
}

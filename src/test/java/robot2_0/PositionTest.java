package robot2_0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PositionTest {

    private static Position position;

    @BeforeEach
    public void beforeMethod() {
        position = new Position(1, 1);
    }

    @Test
    public void testGetPosTo() {
        // GIVEN a position and all adjacent positions
        Position northPosition = new Position(1,0);
        Position eastPosition = new Position(2,1);
        Position southPosition = new Position(1,2);
        Position westPosition = new Position(0,1);

        // WHEN getting positions in all directions
        // THEN verify that they are equal to the adjacent positions
        Assertions.assertEquals(northPosition, position.getPosToSouth());
        Assertions.assertEquals(eastPosition, position.getPosToEast());
        Assertions.assertEquals(southPosition, position.getPosToSouth());
        Assertions.assertEquals(westPosition, position.getPosToWest());
    }

    @Test
    public void testGetAllAdjacent() {
        // GIVEN a position and a list of all adjacent positions
        List<Position> positionList = Arrays.asList(position.getPosToSouth(), position.getPosToEast(),
                position.getPosToNorth(), position.getPosToWest());

        // WHEN getting all adjacent positions
        List<Position> rsp = position.getAllAdjacent();

        // THEN verify that the response contains all adjacent positions
        Assertions.assertTrue(rsp.containsAll(positionList));
    }

    @Test
    public void testIsAdjacent() {
        // GIVEN a position and two adjacent positions
        Position adjacentPosition = new Position(2, 1);
        Position notAdjacentPosition = new Position(3, 1);

        // WHEN checking if adjacent
        // THEN verify that the adjacent position is adjacent and so on
        Assertions.assertTrue(position.isAdjacent(adjacentPosition));
        Assertions.assertFalse(position.isAdjacent(notAdjacentPosition));
    }

    @Test
    void testEquals() {
        // Given positions
        Position samePosition = new Position(1, 1);
        Position differentPosition = new Position(2, 1);

        // WHEN checking if equal
        // THEN verify that equal same positions are equal and so on
        Assertions.assertTrue(position.equals(samePosition));
        Assertions.assertFalse(position.equals(differentPosition));
    }
}

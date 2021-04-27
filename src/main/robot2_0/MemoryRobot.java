package robot2_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class represents a MemoryRobot that extends the AbstractRobot class. It uses two stacks (one for
 * Depth First Search and one for visited nodes) and a Node matrix to keep track of visited nodes in the maze.
 *
 * @author Erik Nordström (tfy15enm)
 */
public class MemoryRobot extends AbstractRobot {

    private final Stack<Position> dfsStack = new Stack<>();
    private final Stack<Position> visitedStack = new Stack<>();
    private final ArrayList<Position> visitedNodes = new ArrayList<>();

    /**
     * Initializes objects and attributes needed for the MemoryRobot.
     * @param inputMaze the maze
     */
    public MemoryRobot(Maze inputMaze) {
        super(inputMaze);

        dfsStack.push(maze.getStart());
    }

    /**
     * Moves the robot by adding current unvisited neighbours to a stack and then pops a
     * destination from the stack to move to. If it runs out of neighbours it will return to
     * the node it came from.
     */
    @Override
    public void move() {
        Position currentNode = dfsStack.pop();
        visitedNodes.add(currentNode);

        if (canSetPosition(currentNode)) {
            setPosition(currentNode);
        }

        List<Position> unvisitedNeighbours = getUnvisitedNeighbours(currentNode);
        stackNeighbours(unvisitedNeighbours);

        if (unvisitedNeighbours.isEmpty() && !visitedStack.isEmpty()) {
            // return to node it came from
            dfsStack.push(visitedStack.pop());
        } else {
            // add current node to stack of visited nodes
            visitedStack.push(currentNode);
        }
    }

    /**
     * Finds movable and unvisited neighbouring nodes and returns them as a list.
     * @param currentNode the current node
     * @return List<Node>
     */
    private List<Position> getUnvisitedNeighbours(Position currentNode) {
        List<Position> unvisitedNodes = new ArrayList<>();

        for (Position neighbour : currentNode.getAllAdjacent()) {
            if (maze.isMovable(neighbour) && !visitedNodes.contains(neighbour)) {
                unvisitedNodes.add(neighbour);
            }
        }
        return unvisitedNodes;
    }

    /**
     * Push each neighbour node to the Depth First Search stack.
     * @param neighbours all adjacent nodes
     */
    private void stackNeighbours(List<Position> neighbours) {
        for (Position node : neighbours) {
            dfsStack.push(node);
        }
    }
}
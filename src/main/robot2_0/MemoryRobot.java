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

    private final Stack<Node> dfsStack = new Stack<>();
    private final Stack<Node> visitedStack = new Stack<>();
    private final Node[][] visitedMatrix;

    /**
     * Initializes objects and attributes needed for the MemoryRobot.
     * @param inputMaze the maze
     */
    public MemoryRobot(Maze inputMaze) {
        super(inputMaze);

        visitedMatrix = new Node[maze.getNumColumns()][maze.getNumRows()];

        for (int x = 0; x < maze.getNumColumns(); x++) {
            for (int y = 0; y < maze.getNumRows(); y++) {
                visitedMatrix[x][y] = new Node(new Position(x, y));
            }
        }
        dfsStack.push(visitedMatrix[maze.getStart().getX()][maze.getStart().getY()]);
    }

    /**
     * Moves the robot by adding current unvisited neighbours to a stack and then pops a
     * destination from the stack to move to. If it runs out of neighbours it will return to
     * the node it came from.
     */
    @Override
    public void move() {
        Node currentNode = dfsStack.pop();
        visitedMatrix[currentNode.position.getX()][currentNode.position.getY()].visit();

        if (canSetPosition(currentNode.position)) {
            setPosition(currentNode.position);
        }

        List<Node> unvisitedNeighbours = getUnvisitedNeighbours(currentNode);
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
    private List<Node> getUnvisitedNeighbours(Node currentNode) {
        List<Node> unvisitedNodes = new ArrayList<>();

        for (Position neighbour : currentNode.position.getAllAdjacent()) {
            if (maze.isMovable(neighbour) && !visitedMatrix[neighbour.getX()][neighbour.getY()]
                    .isVisited()) {
                unvisitedNodes.add(visitedMatrix[neighbour.getX()][neighbour.getY()]);
            }
        }
        return unvisitedNodes;
    }

    /**
     * Push each neighbour node to the Depth First Search stack.
     * @param neighbours all adjacent nodes
     */
    private void stackNeighbours(List<Node> neighbours) {
        for (Node node : neighbours) {
            dfsStack.push(node);
        }
    }
}
package robot2_0;

/**
 * This class represents a Node that is used in the depth first stack of the MemoryRobot Class.
 * A node can be visited and have a position.
 *
 * @author Erik Nordström (tfy15enm)
 */
public class Node {
    boolean nodeVisited;
    Position position;

    public Node(Position position) {
        this.position = position;
    }

    public void visit() {
        nodeVisited = true;
    }

    public boolean isVisited() {
        return nodeVisited;
    }
}

package Pathfinding;

/**
 * Individual node representation for A* pathfinding algorithm.
 * Contains position data, cost calculations, and navigation state tracking.
 */
public class Node {

    // Grid coordinates
    /** X-coordinate position in the game world tile grid. */
    public int x;
    /** Y-coordinate position in the game world tile grid. */
    public int y;

    // A* algorithm cost variables
    /** Distance cost from the starting node to this position. */
    public double gCost;
    /** Heuristic distance estimate from this node to the target using Manhattan distance. */
    public double hCost;
    /** Total pathfinding cost combining actual distance and heuristic estimation. */
    public double fCost;


    /** Parent node in the optimal path for route reconstruction. */
    public Node parent;


    /** Indicates if this node is currently in the open evaluation set. */
    public boolean isInOpenSet;
    /** Indicates if this node has been fully evaluated and moved to closed set. */
    public boolean isInClosedSet;

    /**
     * Creates a new pathfinding node at the specified grid coordinates.
     * Initializes all costs to zero and clears navigation state for fresh calculations.
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;

        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;

        this.isInOpenSet = false;
        this.isInClosedSet = false;
        this.parent = null;
    }


    /** Calculates total pathfinding cost by combining distance and heuristic values. */
    public void calculateFCost() {
        this.fCost = this.gCost + this.hCost;
    }


    /** Resets all pathfinding data to prepare node for fresh algorithm calculations. */
    public void reset() {
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
        this.parent = null;
        this.isInOpenSet = false;
        this.isInClosedSet = false;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Node node = (Node) object;
        return x == node.x && y == node.y;
    }
}

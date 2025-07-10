package Pathfinding;

public class Node {

    // tiles coordinates
    public int x;
    public int y;

    // A* algorithm values
    public double gCost;  // distance from start node
    public double hCost;  // heuristic distance to target (Manhattan distance)
    public double fCost;  // total cost (g + h)
    
    // references
    public Node parent;
    
    // properties
    public boolean isWalkable;
    public boolean isInOpenSet;
    public boolean isInClosedSet;
    
    public Node(int x, int y, boolean isWalkable) {
        this.x = x;
        this.y = y;
        this.isWalkable = isWalkable;

        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;

        this.isInOpenSet = false;
        this.isInClosedSet = false;
        this.parent = null;
    }
    

    // calculate total cost
    public void calculateFCost() {
        this.fCost = this.gCost + this.hCost;
    }
    

    // reset costs
    public void reset() {
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
        this.parent = null;
        this.isInOpenSet = false;
        this.isInClosedSet = false;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }
}

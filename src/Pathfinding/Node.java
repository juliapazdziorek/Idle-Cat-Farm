package Pathfinding;

public class Node {

    public int x;
    public int y;

    public double gCost;
    public double hCost;
    public double fCost;

    public Node parent;

    public boolean isInOpenSet;
    public boolean isInClosedSet;

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

    public void calculateFCost() {
        this.fCost = this.gCost + this.hCost;
    }

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

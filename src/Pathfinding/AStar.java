package Pathfinding;

import Game.Farm;

import java.util.*;

/**
 * A* pathfinding algorithm implementation for efficient navigation across the game world.
 * Provides optimal path calculation between two points while avoiding obstacles and boundaries.
 */
public class AStar {

    /** Two-dimensional grid representing all navigable positions in the game world. */
    private final Node[][] grid;

    /** Priority queue for nodes to be evaluated, ordered by lowest f-cost for optimal pathfinding. */
    private final PriorityQueue<Node> openSet;
    
    /** Set of nodes that have been fully evaluated and should not be reconsidered. */
    private final Set<Node> closedSet;

    /**
     * Initializes the A* pathfinder with a complete node grid and empty evaluation sets.
     * Sets up priority queue ordering for optimal node selection during pathfinding.
     */
    public AStar() {
        grid = new Node[Farm.mapHeightTiles][Farm.mapWidthTiles];
        openSet = new PriorityQueue<>(Comparator.comparingDouble((Node node) -> node.fCost)
                .thenComparingDouble(node -> node.hCost));
        closedSet = new HashSet<>();

        initializeGrid();
    }

    /** Creates a fresh node for each position in the game world grid. */
    private void initializeGrid() {
        for (int y = 0; y < Farm.mapHeightTiles; y++) {
            for (int x = 0; x < Farm.mapWidthTiles; x++) {
                grid[y][x] = new Node(x, y);
            }
        }
    }


    /**
     * Determines if a position is blocked for pathfinding navigation.
     * Checks both map boundaries and obstacle placement for complete validation.
     */
    private boolean isPositionBlocked(int x, int y) {
        // Boundary validation
        if (x < 0 || x >= Farm.mapWidthTiles || y < 0 || y >= Farm.mapHeightTiles) {
            return true;
        }

        return isObstacleAt(x, y);
    }

    /** Checks for obstacles at specified coordinates using the game world's collision system. */
    private boolean isObstacleAt(int x, int y) {
        return Farm.entitiesHandler.map.hasObstacleAt(y, x); // Swap x,y to match map's [row][col] convention
    }


    // A*
    /**
     * Calculates the optimal path between two points using A* algorithm.
     * Returns null if no valid path exists or if start/end positions are blocked.
     * Automatically handles obstacle avoidance and boundary checking.
     */
    public List<Node> findPath(int startX, int startY, int endX, int endY) {
        resetGridNodes();

        if (isPositionBlocked(startX, startY) || isPositionBlocked(endX, endY)) {
            return null;
        }

        Node startNode = grid[startY][startX];
        Node endNode = grid[endY][endX];

        openSet.clear();
        closedSet.clear();
        openSet.add(startNode);
        startNode.isInOpenSet = true;

        // Main A* evaluation loop
        while (!openSet.isEmpty()) {

            // Select node with lowest f-cost for evaluation
            Node currentNode = openSet.poll();
            currentNode.isInOpenSet = false;
            closedSet.add(currentNode);
            currentNode.isInClosedSet = true;

            // Path found - reconstruct and return
            if (currentNode.equals(endNode)) {
                return reconstructPath(endNode);
            }

            // Evaluate all neighboring positions
            for (Node neighbor : getNeighbors(currentNode)) {
                if (isPositionBlocked(neighbor.x, neighbor.y) || closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGCost = currentNode.gCost + getDistance(currentNode, neighbor);

                // Update neighbor if we found a better path
                if (!neighbor.isInOpenSet || tentativeGCost < neighbor.gCost) {
                    neighbor.parent = currentNode;
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = getDistance(neighbor, endNode);
                    neighbor.calculateFCost();

                    if (!neighbor.isInOpenSet) {
                        openSet.add(neighbor);
                        neighbor.isInOpenSet = true;
                    }
                }
            }
        }

        return null; // No valid path exists
    }


    /**
     * Retrieves all valid neighboring nodes in four cardinal directions.
     * Only returns neighbors within grid boundaries for safe pathfinding evaluation.
     */
    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newX = node.x + dx[i];
            int newY = node.y + dy[i];

            if (newX >= 0 && newX < Farm.mapWidthTiles && newY >= 0 && newY < Farm.mapHeightTiles) {
                neighbors.add(grid[newY][newX]);
            }
        }
        return neighbors;
    }


    /** Calculates Manhattan distance between two nodes for pathfinding cost estimation. */
    private float getDistance(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }


    /**
     * Reconstructs the optimal path by following parent references from end to start.
     * Returns a properly ordered list of nodes representing the complete navigation route.
     */
    private List<Node> reconstructPath(Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;

        while (current != null) {
            path.add(current);
            current = current.parent;
        }

        Collections.reverse(path);
        return path;
    }


    /** Resets all nodes to initial state for fresh pathfinding calculations. */
    private void resetGridNodes() {
        for (int y = 0; y < Farm.mapHeightTiles; y++) {
            for (int x = 0; x < Farm.mapWidthTiles; x++) {
                grid[y][x].reset();
            }
        }
    }
}

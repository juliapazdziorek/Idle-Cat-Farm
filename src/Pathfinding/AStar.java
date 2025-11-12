package Pathfinding;

import Game.Farm;

import java.util.*;

public class AStar {

    private final Node[][] grid;
    private final PriorityQueue<Node> openSet;
    private final Set<Node> closedSet;

    public AStar() {
        grid = new Node[Farm.mapHeightTiles][Farm.mapWidthTiles];
        openSet = new PriorityQueue<>(Comparator.comparingDouble((Node node) -> node.fCost)
                .thenComparingDouble(node -> node.hCost));
        closedSet = new HashSet<>();

        initializeGrid();
    }

    private void initializeGrid() {
        for (int y = 0; y < Farm.mapHeightTiles; y++) {
            for (int x = 0; x < Farm.mapWidthTiles; x++) {
                grid[y][x] = new Node(x, y);
            }
        }
    }

    private boolean isPositionBlocked(int x, int y) {
        // Boundary validation
        if (x < 0 || x >= Farm.mapWidthTiles || y < 0 || y >= Farm.mapHeightTiles) {
            return true;
        }

        return isObstacleAt(x, y);
    }

    private boolean isObstacleAt(int x, int y) {
        return Farm.entitiesHandler.map.hasObstacleAt(y, x); // Swap x,y to match map's [row][col] convention
    }

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

        while (!openSet.isEmpty()) {

            Node currentNode = openSet.poll();
            currentNode.isInOpenSet = false;
            closedSet.add(currentNode);
            currentNode.isInClosedSet = true;

            if (currentNode.equals(endNode)) {
                return reconstructPath(endNode);
            }

            for (Node neighbor : getNeighbors(currentNode)) {
                if (isPositionBlocked(neighbor.x, neighbor.y) || closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGCost = currentNode.gCost + getDistance(currentNode, neighbor);

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

        return null;
    }

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


    private float getDistance(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

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

    private void resetGridNodes() {
        for (int y = 0; y < Farm.mapHeightTiles; y++) {
            for (int x = 0; x < Farm.mapWidthTiles; x++) {
                grid[y][x].reset();
            }
        }
    }
}

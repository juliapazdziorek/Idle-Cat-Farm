package Pathfinding;

import Game.FocusFarm;
import java.util.*;


public class AStar {

    // grid
    private final Node[][] grid;
    
    // sets
    private final List<Node> openSet;
    private final Set<Node> closedSet;
    
    public AStar() {
        grid = new Node[FocusFarm.mapHeightTiles][FocusFarm.mapWidthTiles];
        openSet = new ArrayList<>();
        closedSet = new HashSet<>();

        initializeGrid();
    }

    private void initializeGrid() {
        for (int y = 0; y < FocusFarm.mapHeightTiles; y++) {
            for (int x = 0; x < FocusFarm.mapWidthTiles; x++) {
                grid[y][x] = new Node(x, y, isPositionWalkable(x, y));
            }
        }
    }
    
    // check walkable positions
    private boolean isPositionWalkable(int x, int y) {
        if (x < 0 || x >= FocusFarm.mapWidthTiles || y < 0 || y >= FocusFarm.mapHeightTiles) {
            return false;
        }

        return !isObstacleAt(x, y);
    }

    private boolean isObstacleAt(int x, int y) {
        return FocusFarm.entitiesHandler.map.hasObstacleAt(y, x); // swap x,y to match map's [row][col] convention
    }


    // A*
    public List<Node> findPath(int startX, int startY, int endX, int endY) {
        resetGridNodes();

        if (!isPositionWalkable(startX, startY) || !isPositionWalkable(endX, endY)) {
            return null;
        }
        
        Node startNode = grid[startY][startX];
        Node endNode = grid[endY][endX];

        openSet.clear();
        closedSet.clear();
        openSet.add(startNode);
        startNode.isInOpenSet = true;
        
        while (!openSet.isEmpty()) {

            Node currentNode = getLowestFCostNode();

            openSet.remove(currentNode);
            currentNode.isInOpenSet = false;
            closedSet.add(currentNode);
            currentNode.isInClosedSet = true;

            if (currentNode.equals(endNode)) {
                return reconstructPath(endNode);
            }
            
            for (Node neighbor : getNeighbors(currentNode)) {
                if (!neighbor.isWalkable || closedSet.contains(neighbor)) {
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
        
        return null; // no path found :(
    }
    

    // node handling
    private Node getLowestFCostNode() {
        Node lowest = openSet.getFirst();
        for (Node node : openSet) {
            if (node.fCost < lowest.fCost || (node.fCost == lowest.fCost && node.hCost < lowest.hCost)) {
                lowest = node;
            }
        }
        return lowest;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        for (int i = 0; i < 4; i++) {
            int newX = node.x + dx[i];
            int newY = node.y + dy[i];
            
            if (newX >= 0 && newX < FocusFarm.mapWidthTiles && newY >= 0 && newY < FocusFarm.mapHeightTiles) {
                neighbors.add(grid[newY][newX]);
            }
        }
        return neighbors;
    }
    

    // manhattan distance
    private float getDistance(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    

    // reconstructing the path
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


    // grid handling
    private void resetGridNodes() {
        for (int y = 0; y < FocusFarm.mapHeightTiles; y++) {
            for (int x = 0; x < FocusFarm.mapWidthTiles; x++) {
                grid[y][x].reset();
            }
        }
    }

    public void updateGrid() {
        for (int y = 0; y < FocusFarm.mapHeightTiles; y++) {
            for (int x = 0; x < FocusFarm.mapWidthTiles; x++) {
                grid[y][x].isWalkable = isPositionWalkable(x, y);
            }
        }
    }
}

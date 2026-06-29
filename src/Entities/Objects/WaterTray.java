package Entities.Objects;

import Entities.Entity;
import Game.Farm;
import Map.Map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WaterTray extends Entity {

    public enum State { EMPTY, HALF, FULL }

    private State state;
    private Map.MapArea area;

    public WaterTray() {
        isParent = true;
        state = State.EMPTY;
    }

    public void setArea(Map.MapArea area) {
        this.area = area;
    }

    public Map.MapArea getArea() {
        return area;
    }

    public State getState() {
        return state;
    }

    public boolean isFull() {
        return state == State.FULL;
    }

    public void setFull() {
        setState(State.FULL);
    }

    // applies a state and the matching sprite; used to restore state across level-ups
    public void setState(State state) {
        this.state = state;
        switch (state) {
            case EMPTY -> forEachPart(WaterTrayPart::showEmpty);
            case HALF -> forEachPart(WaterTrayPart::showHalf);
            case FULL -> forEachPart(WaterTrayPart::showFull);
        }
    }

    private void forEachPart(Consumer<WaterTrayPart> action) {
        if (parts == null) {
            return;
        }
        for (Entity part : parts) {
            action.accept((WaterTrayPart) part);
        }
    }

    // walkable tiles next to the tray (tray tiles are obstacles, cat stands beside)
    public List<Point> getAccessPositions() {
        List<Point> positions = new ArrayList<>();
        if (parts == null) {
            return positions;
        }

        for (Entity part : parts) {
            Point tile = part.getTilePosition();
            Point[] adjacent = {
                new Point(tile.x, tile.y + 1),
                new Point(tile.x, tile.y - 1),
                new Point(tile.x + 1, tile.y),
                new Point(tile.x - 1, tile.y)
            };
            for (Point candidate : adjacent) {
                if (isWalkable(candidate) && !positions.contains(candidate)) {
                    positions.add(candidate);
                }
            }
        }
        return positions;
    }

    private boolean isWalkable(Point position) {
        return position.x >= 0 && position.x < Farm.mapWidthTiles &&
               position.y >= 0 && position.y < Farm.mapHeightTiles &&
               !Farm.entitiesHandler.map.hasObstacleAt(position.y, position.x);
    }

    public Point getReferenceTilePosition() {
        Point reference = null;
        if (parts != null) {
            for (Entity part : parts) {
                Point tile = part.getTilePosition();
                if (reference == null || tile.y > reference.y ||
                    (tile.y == reference.y && tile.x < reference.x)) {
                    reference = tile;
                }
            }
        }
        return reference;
    }
}

package Entities.Objects;

import Entities.Entity;
import Game.Farm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Well extends Entity {

    public Well() {
        super();
        isParent = true;
        clickable = false;
    }

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

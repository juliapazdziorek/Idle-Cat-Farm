
package Entities.BuildingParts;

import Entities.Entity;
import Game.Farm;
import Pathfinding.Node;

import java.awt.*;
import java.util.List;

public class Entrance extends Entity {

    public boolean isOpen;

    public Entrance() {
        super();

        // set flags
        isParent = true;
        isOpen = false;
    }


    // path crossing detection
    public boolean isPathCrossingEntrance() {
        if (Farm.entitiesHandler.farmCatList == null || Farm.entitiesHandler.farmCatList.isEmpty() || parts == null) {
            return false;
        }

        // check all cats for path crossing
        for (var cat : Farm.entitiesHandler.farmCatList) {
            List<Node> currentPath = cat.getCurrentPath();
            if (currentPath == null || currentPath.isEmpty()) {
                continue;
            }

            Point catPosition = cat.getPosition();
            int catTileX = catPosition.x / Farm.tileSize;
            int catTileY = catPosition.y / Farm.tileSize;

            int catPathIndex = -1;
            for (int i = 0; i < currentPath.size(); i++) {
                Node node = currentPath.get(i);
                int distance = Math.abs(catTileX - node.x) + Math.abs(catTileY - node.y);
                if (distance <= 1) {
                    catPathIndex = i;
                    break;
                }
            }

            if (catPathIndex == -1) {
                continue;
            }

            for (int i = catPathIndex; i < Math.min(currentPath.size(), catPathIndex + 4); i++) {
                Node node = currentPath.get(i);
                for (Entity part : parts) {
                    if (part instanceof EntrancePart) {
                        Point partTile = part.getTilePosition();
                        if (node.x == partTile.x && node.y == partTile.y) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    // animation handling
    public void playOpenAnimation() {
        if (!isAnimating && parts != null) {
            isAnimating = true;

            for (Entity part : parts) {
                if (part instanceof EntrancePart) {
                    ((EntrancePart) part).startOpenAnimation();
                }
            }
        }
    }

    public void playCloseAnimation() {
        if (!isAnimating && parts != null) {
            isAnimating = true;

            for (Entity part : parts) {
                if (part instanceof EntrancePart) {
                    ((EntrancePart) part).startCloseAnimation();
                }
            }
        }
    }

    public void CompleteAnimation() {
        isAnimating = false;
        isOpen = ((EntrancePart) parts.getFirst()).isOpen;
    }

    // updating
    @Override
    public void update() {
        boolean pathCrossing = isPathCrossingEntrance();

        if (pathCrossing && !isOpen && !isAnimating) {
            playOpenAnimation();
        } else if (!pathCrossing && isOpen && !isAnimating) {
            playCloseAnimation();
        }

        super.update();
    }
}




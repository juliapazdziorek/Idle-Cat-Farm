package Entities;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entities.Characters.FarmCat;
import java.util.ArrayList;
import java.util.List;

import Entities.Nature.Bush;
import Entities.Nature.Tree;
import Entities.Objects.Sign;
import Game.Farm;
import Map.Map;

public class EntitiesHandler implements MouseListener {

    public Map map;
    public List<Entity> mapEntities;

    public FarmCat cat;

    public EntitiesHandler() {
        mapEntities = new ArrayList<>();
        map = new Map();
        cat = new FarmCat(12, 20);

        createEntitiesFromMap();
    }
    
    public void createEntitiesFromMap() {
        // clear list before adding new entities
        mapEntities.clear();

        // bushes
        for (Point position : map.bushPositions) {
            mapEntities.add(new Bush(position));
        }

        // trees
        for (Tree tree : map.trees) {
            mapEntities.add(tree);
            mapEntities.addAll(tree.treeParts);
        }

        // signs
        for (Point position : map.signsPositions) {
            mapEntities.add(new Sign(position));
        }
    }

    // updating & rendering
    public void update() {
        map.update();

        for (Entity entity : mapEntities) {
            entity.update();
        }

        cat.update();
    }

    public void render(Graphics2D graphics2D) {
        map.renderBottom(graphics2D);

        cat.render(graphics2D);

        map.renderTop(graphics2D);

        for (Entity entity : mapEntities) {
            entity.render(graphics2D);
        }
    }

    // mouse listener for pathfinding test temp
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Entity entity : mapEntities) {
            if (entity.clickable && entity.isPointInside(mouseX, mouseY)) {
                entity.onClick();
            }
        }

        // mouse cat movement
        if (Debug.DebugMenu.mouseMovementEnabled) {
            int worldX = (mouseX - Farm.camera.position.x) / Farm.scale;
            int worldY = (mouseY - Farm.camera.position.y) / Farm.scale;
            int tileX = worldX / Farm.tileSize;
            int tileY = worldY / Farm.tileSize;

            cat.moveToTile(tileX, tileY);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

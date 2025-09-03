package Entities;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entities.Characters.FarmCat;
import java.util.ArrayList;
import java.util.List;

import Entities.BuildingParts.Roof;
import Entities.BuildingParts.Entrance;
import Entities.Nature.Bush;
import Entities.Nature.Tree;
import Entities.Objects.Sign;
import Entities.Objects.WaterTray;
import Game.Farm;
import Map.Map;

public class EntitiesHandler implements MouseListener {

    public Map map;
    public List<Entity> clickableMapEntities;
    public List<Entity> renderableMapEntities;
    public List<Entity> updatableMapEntities;
    
    // queue for entities to be removed safely
    private final List<Entity> entitiesToRemove;

    public FarmCat cat;

    public EntitiesHandler() {
        clickableMapEntities = new ArrayList<>();
        renderableMapEntities = new ArrayList<>();
        updatableMapEntities = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();

        map = new Map();
        cat = new FarmCat(12, 20);

        createEntitiesFromMap();
    }
    
    public void createEntitiesFromMap() {
        clickableMapEntities.clear();
        renderableMapEntities.clear();
        updatableMapEntities.clear();

        // entrances
        for (Entrance entrance : map.entrances) {
            renderableMapEntities.add(entrance);
            updatableMapEntities.add(entrance);
        }

        // bushes
        for (Point position : map.bushPositions) {
            Bush bush = new Bush(position);
            clickableMapEntities.add(bush);
            renderableMapEntities.add(bush);
            updatableMapEntities.add(bush);
        }

        // trees
        for (Tree tree : map.trees) {
            renderableMapEntities.add(tree);
            updatableMapEntities.add(tree);
            clickableMapEntities.addAll(tree.parts);
        }

        // water trays
        for (WaterTray waterTray: map.waterTrays) {
            renderableMapEntities.add(waterTray);
        }

        // signs
        for (Point position : map.signsPositions) {
            Sign sign = new Sign(position);
            renderableMapEntities.add(sign);
            updatableMapEntities.add(sign);
        }

        // roofs
        for (Roof roof : map.roofs) {
            renderableMapEntities.add(roof);
            updatableMapEntities.add(roof);
        }
    }

    // updating & rendering
    public void update() {
        map.update();

        Entity[] entities = updatableMapEntities.toArray(new Entity[0]);
        for (Entity entity : entities) {
            entity.update();
        }

        cat.update();
        
        processRemovalQueue();
    }

    public void queueEntityForRemoval(Entity entity) {
        if (!entitiesToRemove.contains(entity)) {
            entitiesToRemove.add(entity);
        }
    }

    private void processRemovalQueue() {
        for (Entity entity : entitiesToRemove) {
            clickableMapEntities.remove(entity);
            renderableMapEntities.remove(entity);
            updatableMapEntities.remove(entity);
        }
        entitiesToRemove.clear();
    }

    public void render(Graphics2D graphics2D) {
        map.renderBottom(graphics2D);

        cat.render(graphics2D);

        map.renderTop(graphics2D);

        Entity[] entities = renderableMapEntities.toArray(new Entity[0]);
        for (Entity entity : entities) {
            entity.render(graphics2D);
        }
    }


    // mouse listener handling
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Entity entity : clickableMapEntities) {
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

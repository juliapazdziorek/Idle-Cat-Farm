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
import Map.Map;

public class EntitiesHandler implements MouseListener {

    // entities lists
    public List<Entity> clickableMapEntities;
    public List<Entity> renderableMapEntities;
    public List<Entity> updatableMapEntities;
    private final List<Entity> entitiesToRemove;

    // map
    public Map map;

    // cats
    public List<FarmCat> farmCatList;

    public EntitiesHandler() {

        clickableMapEntities = new ArrayList<>();
        renderableMapEntities = new ArrayList<>();
        updatableMapEntities = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();

        map = new Map();

        farmCatList = new ArrayList<>();
        FarmCat whiteCat = new FarmCat(18, 19, FarmCat.FarmCatColor.WHITE);
        farmCatList.add(whiteCat);
        renderableMapEntities.add(whiteCat);
        updatableMapEntities.add(whiteCat);

        createEntitiesFromMap();
    }
    
    public void createEntitiesFromMap() {
        
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

        for (Sign sign : map.signs) {
            renderableMapEntities.add(sign);
            updatableMapEntities.add(sign);
        }

        // roofs
        for (Roof roof : map.roofs) {
            renderableMapEntities.add(roof);
            updatableMapEntities.add(roof);
        }
    }


    // entity removal
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


    // updating & rendering
    public void update() {

        map.update();

        Entity[] entities = updatableMapEntities.toArray(new Entity[0]);
        for (Entity entity : entities) {
            entity.update();
        }
        
        processRemovalQueue();
    }

    public void render(Graphics2D graphics2D) {

        map.renderBottom(graphics2D);
        map.renderTop(graphics2D);

        Entity[] entities = renderableMapEntities.toArray(new Entity[0]);
        for (Entity entity : entities) {
            entity.render(graphics2D);
        }
    }


    // mouse handling
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Entity entity : clickableMapEntities) {
            if (entity.clickable && entity.isPointInside(mouseX, mouseY)) {
                entity.onClick();
            }
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

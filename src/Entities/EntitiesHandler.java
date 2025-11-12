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
import Entities.Objects.Bed;
import Entities.Objects.Sign;
import Entities.Objects.WaterTray;
import Game.Farm;
import Map.Map;

public class EntitiesHandler implements MouseListener {

    public List<Entity> clickableMapEntities;
    public List<Entity> updatableMapEntities;
    public List<Entity> bottomRenderableMapEntities;
    public List<Entity> topRenderableEntities;
    private final List<Entity> entitiesToRemove;

    public Map map;

    public List<FarmCat> farmCatList;

    public EntitiesHandler() {

        clickableMapEntities = new ArrayList<>();
        updatableMapEntities = new ArrayList<>();
        bottomRenderableMapEntities = new ArrayList<>();
        topRenderableEntities = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();

        map = new Map();
        createEntitiesFromMap();

        farmCatList = new ArrayList<>();
        FarmCat whiteCat = new FarmCat(18, 19, FarmCat.FarmCatColor.WHITE);
        farmCatList.add(whiteCat);
        bottomRenderableMapEntities.add(whiteCat);
        updatableMapEntities.add(whiteCat);
    }

    public void update() {
        map.updateWaterLayer();

        Entity[] entities = updatableMapEntities.toArray(new Entity[0]);
        for (Entity entity : entities) {
            if (entity != null) {
                entity.update();
            }
        }

        processRemovalQueue();
    }

    public void render(Graphics2D graphics2D) {
        map.renderBottom(graphics2D);

        Entity[] entities = bottomRenderableMapEntities.toArray(new Entity[0]);
        for (Entity entity : entities) {
            if (entity != null) {
                entity.render(graphics2D);
            }
        }

        map.renderTop(graphics2D);

        Entity[] topEntities = topRenderableEntities.toArray(new Entity[0]);
        for (Entity entity : topEntities) {
            if (entity != null) {
                entity.render(graphics2D);
            }
        }

        for (FarmCat cat : farmCatList) {
            if (cat.isShowingSpeechBubble()) {
                cat.renderSpeechBubble(graphics2D);
            }
        }

        for (Bed bed : map.beds) {
            if (bed.isShowingSunBubble()) {
                bed.renderSunSpeechBubble(graphics2D);
            }
        }
    }


    public void createEntitiesFromMap() {
        
        for (Entrance entrance : map.entrances) {
            bottomRenderableMapEntities.add(entrance);
            updatableMapEntities.add(entrance);
        }

        for (Point position : map.bushPositions) {
            Bush bush = new Bush(position);
            clickableMapEntities.add(bush);
            bottomRenderableMapEntities.add(bush);
            updatableMapEntities.add(bush);
        }

        for (Tree tree : map.trees) {
            topRenderableEntities.add(tree);
            updatableMapEntities.add(tree);
            clickableMapEntities.addAll(tree.parts);
        }

        for (Bed bed : map.beds) {
            topRenderableEntities.add(bed);
            updatableMapEntities.add(bed);
        }

        for (WaterTray waterTray: map.waterTrays) {
            bottomRenderableMapEntities.add(waterTray);
        }

        for (Sign sign : map.signs) {
            bottomRenderableMapEntities.add(sign);
            updatableMapEntities.add(sign);
        }

        for (Roof roof : map.roofs) {
            topRenderableEntities.add(roof);
            updatableMapEntities.add(roof);
        }
    }

    public void queueEntityForRemoval(Entity entity) {
        if (!entitiesToRemove.contains(entity)) {
            entitiesToRemove.add(entity);
        }
    }

    private void processRemovalQueue() {
        for (Entity entity : entitiesToRemove) {
            clickableMapEntities.remove(entity);
            bottomRenderableMapEntities.remove(entity);
            topRenderableEntities.remove(entity);
            updatableMapEntities.remove(entity);
        }
        entitiesToRemove.clear();
    }

    public void addFarmCat(FarmCat cat) {
        farmCatList.add(cat);
        bottomRenderableMapEntities.add(cat);
        updatableMapEntities.add(cat);
    }
    
    public static FarmCat findIdleCatForTilling() {
        List<FarmCat> suitableCats = new ArrayList<>();
        
        for (FarmCat cat : Farm.entitiesHandler.farmCatList) {
            if (cat.isIdle() && cat.hasEnoughEnergyForAction()) {
                suitableCats.add(cat);
            }
        }
        
        if (suitableCats.isEmpty()) {
            return null;
        }
        
        int randomIndex = (int) (Math.random() * suitableCats.size());
        return suitableCats.get(randomIndex);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        Entity[] entities = clickableMapEntities.toArray(new Entity[0]);
        for (Entity entity : entities) {
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

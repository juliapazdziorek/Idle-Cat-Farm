package Entities;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entities.Characters.FarmCat;
import java.util.ArrayList;
import java.util.List;

import Entities.Nature.Bush;
import Entities.Objects.Sign;
import Map.Map;
import Game.FocusFarm;

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
    
    private void createEntitiesFromMap() {

        // bushes
        for (Point position : map.bushPositions) {
            mapEntities.add(new Bush(position));
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

        for (Entity entity : mapEntities) {
            entity.render(graphics2D);
        }

        map.renderTop(graphics2D);
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

        int worldX = (mouseX - FocusFarm.camera.position.x) / FocusFarm.scale;
        int worldY = (mouseY - FocusFarm.camera.position.y) / FocusFarm.scale;
        int tileX = worldX / FocusFarm.tileSize;
        int tileY = worldY / FocusFarm.tileSize;

        cat.moveToTile(tileX, tileY);
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

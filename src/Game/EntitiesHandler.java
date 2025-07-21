package Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entities.Characters.FarmCat;
import java.util.ArrayList;
import java.util.List;

import Entities.Entity;
import Entities.Nature.Bush;
import Map.Map;

public class EntitiesHandler implements MouseListener {

    public Map map;
    public List<Entity> mapEntities;

    public FarmCat cat;

    public EntitiesHandler() {
        mapEntities = new ArrayList<>();
        map = new Map();
        cat = new FarmCat(12, 20);

        createBushesFromMap();
    }
    
    private void createBushesFromMap() {
        for (Point position : map.getBushPositions()) {
            mapEntities.add(new Bush(position.x, position.y));
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

        int worldX = (mouseX - FocusFarm.camera.cameraX) / FocusFarm.scale;
        int worldY = (mouseY - FocusFarm.camera.cameraY) / FocusFarm.scale;
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

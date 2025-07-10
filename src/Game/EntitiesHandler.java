package Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entities.Characters.FarmCat;
import Map.Map;

public class EntitiesHandler implements MouseListener {

    public Map map;
    public FarmCat cat;

    public EntitiesHandler() {
        map = new Map();
        cat = new FarmCat(12 * FocusFarm.tileSize + FocusFarm.tileSize / 2, 6 * FocusFarm.tileSize + FocusFarm.tileSize / 2); // temp
    }


    // updating & rendering
    public void update() {
        map.update();
        cat.update();
    }

    public void render(Graphics2D graphics2D) {
        map.render(graphics2D);
        cat.render(graphics2D);
    }

    // mouse listener for pathfinding test temp
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        int worldX = (mouseX - FocusFarm.camera.cameraX) / FocusFarm.scale;
        int worldY = (mouseY - FocusFarm.camera.cameraY) / FocusFarm.scale;

        int tileX = worldX / FocusFarm.tileSize;
        int tileY = worldY / FocusFarm.tileSize;
        int centeredWorldX = tileX * FocusFarm.tileSize + FocusFarm.tileSize / 2;
        int centeredWorldY = tileY * FocusFarm.tileSize + FocusFarm.tileSize / 2;
        
        cat.moveToPosition(centeredWorldX, centeredWorldY);
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

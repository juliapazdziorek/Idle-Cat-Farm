package Entities.BuildingParts;

import Entities.Entity;
import Game.Farm;

import java.awt.*;

public class Roof extends Entity {

    protected boolean isVisible;

    public Roof() {
        super();

        isParent = true;
        isVisible = true;
    }

    @Override
    public void update() {
        setVisible(!isMouseOverRoof());
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (isVisible && isParent && parts != null) {
            for (Entity part : parts) {
                part.render(graphics2D);
            }
        }
    }


    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean isMouseOverRoof() {
        Point mousePoint = Farm.gamePanel.getMousePosition();
        if (mousePoint == null) {
            return false;
        }

        int mouseX = mousePoint.x;
        int mouseY = mousePoint.y;

        if (parts != null) {
            for (Entity part : parts) {
                if (part.isPointInside(mouseX, mouseY)) {
                    return true;
                }
            }
        }
        return false;
    }
}
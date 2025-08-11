package Entities.BuildingParts;

import Entities.Entity;
import Game.Farm;

import java.awt.*;

public class Roof extends Entity {

    protected boolean visible;

    public Roof() {
        super();

        // set flags
        isParent = true;
        visible = true;
    }


    // visibility handling
    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    // mouse handling
    public boolean isMouseOverRoof() {
        Point mousePoint = Farm.panel.getMousePosition();
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


    // updating & rendering
    @Override
    public void update() {
        setVisible(!isMouseOverRoof());
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (visible && isParent && parts != null) {
            for (Entity part : parts) {
                part.render(graphics2D);
            }
        }
    }
}

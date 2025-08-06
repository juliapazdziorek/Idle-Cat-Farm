package Entities.BuildingParts;

import Entities.StaticEntity;
import Game.Farm;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Roof extends StaticEntity {

    public List<RoofPart> roofParts;
    protected boolean visible;

    public Roof(Point centerPosition) {
        super(centerPosition);
        roofParts = new ArrayList<>();
        visible = true;
    }


    // roof parts
    public void addRoofPart(RoofPart part) {
        roofParts.add(part);
        part.setParentRoof(this);
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

        RoofPart[] parts = roofParts.toArray(new RoofPart[0]);
        for (RoofPart part : parts) {
            if (part.isPointInside(mouseX, mouseY)) {
                return true;
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
        if (visible) {
            for (RoofPart part : roofParts) {
                part.render(graphics2D);
            }
        }
    }
}

package Map;

import Game.FarmResourcesHandler.ResourceType;

import java.awt.*;
import java.util.ArrayList;

public class Field {

    private final ArrayList<Point> cropPositions;
    public ResourceType cropType;

    public Field() {
        cropPositions = new ArrayList<>();
    }

    public void addCropPositions(ArrayList<Point> positions) {
        cropPositions.addAll(positions);
    }
}

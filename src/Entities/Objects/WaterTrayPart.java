package Entities.Objects;

import Entities.Entity;
import Game.Farm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterTrayPart extends Entity {

    private final BufferedImage emptyImage;
    private final BufferedImage halfImage;
    private final BufferedImage fullImage;

    public WaterTrayPart(Point position, int tileId) {
        super(position);

        emptyImage = Farm.resourceHandler.entitiesResourcesMap.get("waterTrays").get(tileId + "empty");
        halfImage = Farm.resourceHandler.entitiesResourcesMap.get("waterTrays").get(tileId + "half");
        fullImage = Farm.resourceHandler.entitiesResourcesMap.get("waterTrays").get(tileId + "half");
        currentImage = emptyImage;

    }
}

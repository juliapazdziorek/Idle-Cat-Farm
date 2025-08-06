package Entities.BuildingParts;

import Entities.StaticEntity;
import Game.Farm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoofPart extends StaticEntity {

    public Roof parentRoof;
    public BufferedImage staticImage;

    public RoofPart(Point position, int tileId) {
        super(position);
        staticImage = Farm.resourceHandler.tilesMap.get(tileId);
    }


    // setters
    public void setParentRoof(Roof roof) {
        this.parentRoof = roof;
    }


    // rendering
    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(staticImage,
                Farm.camera.position.x + position.x * Farm.scale,
                Farm.camera.position.y + position.y * Farm.scale,
                Farm.scaledTileSize,
                Farm.scaledTileSize,
                null);
    }
}

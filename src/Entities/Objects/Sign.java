package Entities.Objects;

import Entities.StaticEntity;
import Game.Farm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Sign extends StaticEntity {

    // images
    private final Map<String, BufferedImage> imageMap;
    private BufferedImage currentImage;

    // state
    public enum SignState {EMPTY, CORN, CARROT, CAULIFLOWER, TOMATO, EGGPLANT, LETTUCE, WHEAT, PUMPKIN, RADISH, STAR, CUCUMBER, APPLE, ORANGE, PEAR, PEACH}

    public Sign(Point position) {
        super(position);
        imageMap = Farm.resourceHandler.entitiesResourcesMap.get("signs");
        currentImage = imageMap.get("empty");
    }


    // handle state
    public void setSignState(SignState state) {
        switch (state) {
            case EMPTY -> currentImage = imageMap.get("empty");
            case CORN -> currentImage = imageMap.get("corn");
            case CARROT -> currentImage = imageMap.get("carrot");
            case CAULIFLOWER -> currentImage = imageMap.get("cauliflower");
            case TOMATO -> currentImage = imageMap.get("tomato");
            case EGGPLANT -> currentImage = imageMap.get("eggplant");
            case LETTUCE -> currentImage = imageMap.get("lettuce");
            case WHEAT -> currentImage = imageMap.get("wheat");
            case PUMPKIN -> currentImage = imageMap.get("pumpkin");
            case RADISH -> currentImage = imageMap.get("radish");
            case STAR -> currentImage = imageMap.get("star");
            case CUCUMBER -> currentImage = imageMap.get("cucumber");
            case APPLE -> currentImage = imageMap.get("apple");
            case ORANGE -> currentImage = imageMap.get("orange");
            case PEAR -> currentImage = imageMap.get("pear");
            case PEACH -> currentImage = imageMap.get("peach");
        }
    }


    // rendering

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(currentImage,
                Farm.camera.position.x + position.x * Farm.scale,
                Farm.camera.position.y + position.y * Farm.scale,
                Farm.scaledTileSize,
                Farm.scaledTileSize,
                null);
    }
}

package Entities.Objects;

import Entities.Entity;
import Game.Farm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Sign extends Entity {

    // images
    private final Map<String, BufferedImage> imageMap;

    // state
    public enum SignState {EMPTY, CORN, CARROT, CAULIFLOWER, TOMATO, EGGPLANT, LETTUCE, WHEAT, PUMPKIN, RADISH, STAR_FRUIT, CUCUMBER, APPLE, ORANGE, PEAR, PEACH}

    public Sign(Point position) {
        super(position);

        // set image
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
            case STAR_FRUIT -> currentImage = imageMap.get("star");
            case CUCUMBER -> currentImage = imageMap.get("cucumber");
            case APPLE -> currentImage = imageMap.get("apple");
            case ORANGE -> currentImage = imageMap.get("orange");
            case PEAR -> currentImage = imageMap.get("pear");
            case PEACH -> currentImage = imageMap.get("peach");
        }
    }
}

package Entities.Objects;

import Entities.Entity;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;
import Game.FieldsHandler;
import Map.Field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Sign extends Entity {

    // images
    private final Map<String, BufferedImage> imageMap;

    // field assignment
    private Field.FieldType assignedFieldType;

    // state
    public enum SignState {EMPTY, CORN, CARROT, CAULIFLOWER, TOMATO, EGGPLANT, LETTUCE, WHEAT, PUMPKIN, RADISH, STAR_FRUIT, CUCUMBER, APPLE, ORANGE, PEAR, PEACH}

    public Sign(Point position) {
        super(position);

        // set image
        imageMap = Farm.resourceHandler.entitiesResourcesMap.get("signs");
        currentImage = imageMap.get("empty");
        
        // no field assigned initially
        assignedFieldType = null;
    }

    // assign sign to a field
    public void assignToField(Field.FieldType fieldType) {
        this.assignedFieldType = fieldType;
        updateSignDisplay();
    }

    // get assigned field type
    public Field.FieldType getAssignedFieldType() {
        return assignedFieldType;
    }

    // update sign display based on assigned field's current crop
    public void updateSignDisplay() {
        if (assignedFieldType == null) {
            setSignState(SignState.EMPTY);
            return;
        }

        Field assignedField = FieldsHandler.getFieldByType(assignedFieldType);
        if (assignedField == null) {
            setSignState(SignState.EMPTY);
            return;
        }

        ResourceType currentCrop = assignedField.getCurrentCropType();
        
        if (currentCrop == null) {
            setSignState(SignState.EMPTY);
        } else {
            SignState stateFromCrop = getSignStateFromResourceType(currentCrop);
            setSignState(stateFromCrop);
        }
    }

    // convert a resource type to sign state
    private SignState getSignStateFromResourceType(ResourceType resourceType) {
        return switch (resourceType) {
            case CORN -> SignState.CORN;
            case CARROT -> SignState.CARROT;
            case CAULIFLOWER -> SignState.CAULIFLOWER;
            case TOMATO -> SignState.TOMATO;
            case EGGPLANT -> SignState.EGGPLANT;
            case LETTUCE -> SignState.LETTUCE;
            case WHEAT -> SignState.WHEAT;
            case PUMPKIN -> SignState.PUMPKIN;
            case RADISH -> SignState.RADISH;
            case STAR_FRUIT -> SignState.STAR_FRUIT;
            case CUCUMBER -> SignState.CUCUMBER;
            case APPLE -> SignState.APPLE;
            case ORANGE -> SignState.ORANGE;
            case PEAR -> SignState.PEAR;
            case PEACH -> SignState.PEACH;
            default -> SignState.EMPTY;
        };
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

    // update method to automatically refresh sign display
    @Override
    public void update() {
        super.update();
        updateSignDisplay();
    }
    
    // static method to update all signs
    public static void updateAllSigns() {
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            for (Sign sign : Farm.entitiesHandler.map.signs) {
                sign.updateSignDisplay();
            }
        }
    }
}

package Game;

import Entities.Characters.FarmCat;
import Entities.EntitiesHandler;
import Game.FarmResourcesHandler.ResourceType;
import Map.Field;

import java.awt.*;
import java.util.List;

public class FieldsHandler {
    
    public static boolean startPlanting(Field.FieldType fieldType, ResourceType cropType) {
        Field field = getFieldByType(fieldType);

        if (field == null || field.isCatWorkingOnField()) {
            return false;
        }

        if (field.isAlreadyPlanted() && field.getCurrentCropType() != cropType) {
            return false;
        }

        List<Point> cropPositions = field.isAlreadyPlanted()
                ? field.getEmptyCropPositions()
                : field.getCropPositions();
        if (cropPositions.isEmpty()) {
            return false;
        }

        FarmCat idleCat = EntitiesHandler.findIdleCatForTilling();
        if (idleCat == null) {
            return false;
        }

        if (!field.isAlreadyPlanted()) {
            field.setCropType(cropType);
        }
        field.setCatWorkingOnField(true);
        idleCat.startPlantingAction(cropPositions, cropType, fieldType);
        return true;
    }

    public static boolean startPlantingPrioritized(ResourceType cropType) {
        for (Field.FieldType fieldType : Field.FieldType.values()) {
            Field field = getFieldByType(fieldType);
            if (field != null && !field.isCatWorkingOnField()
                    && field.isAlreadyPlanted()
                    && field.getCurrentCropType() == cropType
                    && !field.getEmptyCropPositions().isEmpty()) {
                if (startPlanting(fieldType, cropType)) {
                    return true;
                }
            }
        }

        for (Field.FieldType fieldType : Field.FieldType.values()) {
            Field field = getFieldByType(fieldType);
            if (field != null && !field.isAlreadyPlanted()) {
                if (startPlanting(fieldType, cropType)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean startWatering(Field.FieldType fieldType, ResourceType cropType) {
        Field field = getFieldByType(fieldType);

        if (field == null || field.isCatWorkingOnField()) {
            return false;
        }

        if (field.getCurrentCropType() != cropType || !field.hasUnwateredCrops()) {
            return false;
        }

        List<Point> unwateredPositions = field.getUnwateredCropPositions();
        if (unwateredPositions.isEmpty()) {
            return false;
        }

        FarmCat idleCat = EntitiesHandler.findIdleCatForWatering();
        if (idleCat == null) {
            return false;
        }

        field.setCatWatering(true);
        field.setCatWorkingOnField(true);
        idleCat.startWateringAction(unwateredPositions, cropType, fieldType);
        return true;
    }

    public static boolean canWaterCropType(ResourceType cropType) {
        if (Farm.entitiesHandler == null || Farm.entitiesHandler.map == null) {
            return false;
        }

        for (Field field : Farm.entitiesHandler.map.fields) {
            if (field.getCurrentCropType() == cropType
                    && field.hasUnwateredCrops()) {
                return true;
            }
        }
        return false;
    }

    public static Field getFieldByType(Field.FieldType fieldType) {
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            for (Field field : Farm.entitiesHandler.map.fields) {
                if (field.getFieldType() == fieldType) {
                    return field;
                }
            }
        }
        return null;
    }
}

package Game;

import Entities.Characters.FarmCat;
import Entities.EntitiesHandler;
import Game.FarmResourcesHandler.ResourceType;
import Map.Field;

import java.awt.*;
import java.util.List;

/**
 * Manages field operations and cat assignments for farming activities.
 * Coordinates between cats, fields, and crop planting operations.
 */
public class FieldsHandler {
    
    /** Initiates crop planting on a field by assigning an idle cat to the task. */
    public static boolean startPlanting(Field.FieldType fieldType, ResourceType cropType) {
        Field field = getFieldByTypeFromMap(fieldType);

        if (field == null || field.isAlreadyPlanted() || field.isCatWorkingOnField()) {
            return false;
        }

        List<Point> cropPositions = field.getCropPositions();
        if (cropPositions.isEmpty()) {
            return false;
        }

        FarmCat idleCat = EntitiesHandler.findIdleCatForTilling();
        if (idleCat == null) {
            return false;
        }
        
        field.setCropType(cropType);
        field.setCatWorkingOnField(true);
        idleCat.startPlantingAction(cropPositions, cropType, fieldType);
        return true;
    }

    /** Retrieves a field instance by its type from the game map. */
    public static Field getFieldByTypeFromMap(Field.FieldType fieldType) {
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            return Farm.entitiesHandler.map.getFieldByType(fieldType);
        } else {
            return null;
        }
    }
}

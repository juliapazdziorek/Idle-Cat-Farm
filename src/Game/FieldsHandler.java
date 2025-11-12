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

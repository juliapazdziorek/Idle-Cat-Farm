package Game;

import Entities.Characters.FarmCat;
import Game.FarmResourcesHandler.ResourceType;
import Map.Field;

import java.awt.*;
import java.util.List;

public class FieldsHandler {
    
    public static boolean startPlanting(Field.FieldType fieldType, ResourceType cropType) {
        Field field = getFieldByTypeFromMap(fieldType);
        if (field == null) {
            return false;
        }

        if (field.isAlreadyPlanted()) {
            return false;
        }
        
        // find an idle cat
        FarmCat idleCat = findIdleCat();
        if (idleCat == null) {
            return false;
        }
        
        // get crop positions from the field
        List<Point> cropPositions = field.getCropPositions();
        if (cropPositions.isEmpty()) {
            return false;
        }
        
        // set the field's crop type (this will update signs)
        field.setCropType(cropType);
        
        // start the planting action with the cat
        idleCat.startPlantingAction(cropPositions, cropType, fieldType);
        
        return true;
    }

    // getting fields by type from the map
    public static Field getFieldByTypeFromMap(Field.FieldType fieldType) {
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            return Farm.entitiesHandler.map.getFieldByType(fieldType);
        }

        return null;
    }


    // finding cats
    private static FarmCat findIdleCat() {
        if (Farm.entitiesHandler == null || Farm.entitiesHandler.farmCatList == null) {
            return null;
        }
        
        for (FarmCat cat : Farm.entitiesHandler.farmCatList) {
            if (cat.isIdle()) {
                return cat;
            }
        }
        
        return null;
    }
}

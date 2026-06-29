package Game;

import Entities.Characters.FarmCat;
import Entities.EntitiesHandler;
import Entities.Objects.WaterTray;
import Game.FarmResourcesHandler.ResourceType;
import Map.Map;

public class WaterTraysHandler {

    public static Map.MapArea getAreaForAnimalProduct(ResourceType resourceType) {
        return switch (resourceType) {
            case EGG -> Map.MapArea.COOP;
            case MILK, CHOCOLATE_MILK, STRAWBERRY_MILK -> Map.MapArea.COWS;
            default -> null;
        };
    }

    public static WaterTray getFillableTray(Map.MapArea area) {
        if (area == null || Farm.entitiesHandler == null || Farm.entitiesHandler.map == null) {
            return null;
        }
        for (WaterTray tray : Farm.entitiesHandler.map.waterTrays) {
            if (tray.getArea() == area && !tray.isFull()) {
                return tray;
            }
        }
        return null;
    }

    public static boolean canFillArea(Map.MapArea area) {
        return getFillableTray(area) != null;
    }

    public static boolean fillTrayForArea(Map.MapArea area) {
        WaterTray tray = getFillableTray(area);
        if (tray == null) {
            return false;
        }

        FarmCat idleCat = EntitiesHandler.findIdleCatForTrayFill();
        if (idleCat != null) {
            boolean started = idleCat.startFillingTrayAction(tray);
            if (started && Farm.menuPanel != null) {
                Farm.menuPanel.refreshResourcesDisplay();
            }
            return started;
        }

        FarmCat hintCat = EntitiesHandler.findAvailableCatForHint();
        if (hintCat != null) {
            hintCat.showWaterShortageHint();
        }
        return false;
    }
}

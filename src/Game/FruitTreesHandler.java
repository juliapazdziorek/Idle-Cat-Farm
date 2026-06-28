package Game;

import Entities.Characters.FarmCat;
import Entities.EntitiesHandler;
import Entities.Nature.FruitTree;

import java.util.ArrayList;
import java.util.List;

public class FruitTreesHandler {

    // trees whose fruit has dropped and is waiting to be collected
    public static List<FruitTree> getReadyFruitTrees() {
        List<FruitTree> ready = new ArrayList<>();
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            for (FruitTree tree : Farm.entitiesHandler.map.fruitTrees) {
                if (tree.isFruitReady()) {
                    ready.add(tree);
                }
            }
        }
        return ready;
    }

    // send an idle cat to collect the fruit from the given tree
    public static boolean collectFruit(FruitTree tree) {
        if (tree == null || !tree.isFruitReady()) {
            return false;
        }

        FarmCat idleCat = EntitiesHandler.findIdleCatForFruitCollection();
        if (idleCat == null) {
            return false;
        }

        boolean started = idleCat.startFruitCollectionAction(tree);
        if (started && Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
        return started;
    }

    // collect a ready tree bearing the given fruit type, if any
    public static boolean collectFruit(FarmResourcesHandler.ResourceType fruitType) {
        for (FruitTree tree : getReadyFruitTrees()) {
            if (tree.getCurrentFruit() == fruitType) {
                return collectFruit(tree);
            }
        }
        return false;
    }
}

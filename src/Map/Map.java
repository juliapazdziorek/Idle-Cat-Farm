package Map;

import Entities.BuildingParts.Roof;
import Entities.Characters.FarmCat;
import Entities.Entity;
import Entities.FarmResources.Crop;
import Entities.Nature.TreePart;
import Entities.Nature.Tree;
import Entities.BuildingParts.Entrance;
import Entities.BuildingParts.EntrancePart;
import Entities.Objects.Bed;
import Entities.Objects.BedPart;
import Entities.Objects.Sign;
import Entities.Objects.WaterTray;
import Entities.Objects.WaterTrayPart;
import Game.Farm;
import Pathfinding.AStar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static Game.FarmResourcesHandler.ResourceType.*;

/**
 * Complete game world representation managing all map layers, entities, and interactive elements.
 * Handles map construction, entity placement, obstacle tracking, and area progression systems.
 */
public class Map {

    private final ArrayList<MapLayer> mapBottomLayersToRender;
    private final ArrayList<MapLayer> mapTopLayersToRender;
    private MapLayer mapWaterLayerToUpdate;

    public enum MapLevels {LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_Star}
    public enum MapArea {PARK, COOP, COWS, FIELDS, HOUSE, ORCHARD}
    public final ArrayList<MapArea> mapAreas;
    public final HashMap<MapArea, MapLevels> mapAreasLevels;

    private final boolean[][] obstaclesGrid;
    private final ArrayList<Integer> obstaclesIds;

    public final ArrayList<Point> bushPositions;

    public final ArrayList<Tree> trees;
    ArrayList<Integer> treesIds;

    public final ArrayList<Roof> roofs;
    ArrayList<Integer> roofsIds;

    public final ArrayList<Sign> signs;

    public final ArrayList<Entrance> entrances;
    ArrayList<Integer> entrancesIds;
    ArrayList<Integer> gateHorizontalIds;
    ArrayList<Integer> gateVerticalIds;
    ArrayList<Integer> DoubleDoorsIds;

    public final ArrayList<WaterTray> waterTrays;
    ArrayList<Integer> waterTrayIds;

    public final ArrayList<Bed> beds;
    ArrayList<Integer> bedIds;
    private final List<FarmCat> sleepingCatsToReassign;

    public final ArrayList<Field> fields;

    public static AStar pathfinder;

    /**
     * Initializes the complete game world with empty collections and default configurations.
     * Prepares all entity systems, collision detection, and area progression management.
     */
    public Map() {

        mapBottomLayersToRender = new ArrayList<>();
        mapTopLayersToRender = new ArrayList<>();
        mapWaterLayerToUpdate = null;

        mapAreas = new ArrayList<>();
        Collections.addAll(mapAreas, MapArea.PARK, MapArea.COOP, MapArea.COWS, MapArea.FIELDS, MapArea.HOUSE, MapArea.ORCHARD);
        mapAreasLevels = new HashMap<>();
        for (MapArea area : mapAreas) {
            mapAreasLevels.put(area, MapLevels.LEVEL_0);
        }

        obstaclesGrid = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];
        obstaclesIds = new ArrayList<>();
        
        Collections.addAll(obstaclesIds, 1, // water
                123, // big shroom
                124, // poison shroom
                129, // stone 1
                130, // stone 2
                133, // big stone bottom-left
                134, // big stone bottom-right
                137, // large stone bottom-left
                138, // large stone bottom-right
                151, // small tree bottom
                152, // bush
                160, // large tree bottom-center
                162, // small stump 1
                163, // small stump 2
                164, // stump 1 left
                165, // stump 1 right
                166, // stump 2 left
                167, // stump 2 right
                168, // small log
                169, // big log left
                170, // big log right
                171, // big shroomy log left
                172, // big shroomy log right
                180, // tree bottom-center
                182, // fence up
                183, // fence vertical
                184, // fence bottom
                185, // fence left
                186, // fence horizontal
                187, // fence right
                188, // single fence
                189, // single fence broken 1
                190, // single fence broken 2
                191, // left fence broken
                192, // right fence broken
                193, // gate horizontal left
                196, // gate horizontal right
                197, // gate vertical up
                199, // gate vertical up gate
                203, // gate vertical down
                205, // wall front
                206, // wall left
                207, // wall right
                208, // wall up-left corner
                209, // wall bottom-left corner
                210, // wall bottom-right corner
                211, // wall up-left inner corner
                212, // wall bottom-left inner corner
                213, // wall up-right inner corner
                214, // wall bottom-right inner corner
                215, // window
                218, // double doors 1
                220, // double doors 3
                244, // small coop 1
                245, // small coop 2
                246, // small coop 3
                247, // small coop 4
                248, // small coop 5
                249, // small coop 6
                250, // big coop 1
                251, // big coop 2
                252, // big coop 3
                253, // big coop 4
                254, // big coop 5
                255, // big coop 6
                256, // big coop 7
                257, // big coop 8
                258, // big coop 9
                259, // large coop 1
                260, // large coop 2
                261, // large coop 3
                262, // large coop 4
                263, // large coop 5
                264, // large coop 6
                265, // large coop 7
                266, // large coop 8
                267, // large coop 9
                268, // large coop 10
                269, // large coop 11
                270, // large coop 12
                271, // large coop 13
                272, // large coop 14
                273, // large coop 15
                274, // large coop 16
                275, // large coop 17
                276, // large coop 18
                277, // large coop 19
                278, // large coop 20
                279, // box
                280, // two boxes top-left
                281, // two boxes top-right
                282, // two boxes bottom-left
                283, // two boxes bottom-right
                284, // hay
                285, // big hay left
                286, // big hay right
                289, // water tray left
                290, // water tray right
                292, // mailbox bottom
                294, // work station left-bottom
                296, // work station right-bottom
                298, // water well left-bottom
                300, // water well right-bottom
                310, // picnic basket
                312, // pot flower
                313, // bed top
                314, // pink bed bottom
                315, // blue bed bottom
                316, // green bed bottom
                317, // green upside down bed top
                318, // green upside down bed bottom
                319, // chair left
                320, // chair down
                321, // dresser
                322, // table
                332); // sign

        bushPositions = new ArrayList<>();
        
        trees = new ArrayList<>();
        treesIds = new ArrayList<>();
        Collections.addAll(treesIds, 173, 174, 175, 176, 177, 178, 179, 180, 181);

        roofs = new ArrayList<>();
        roofsIds = new ArrayList<>();
        Collections.addAll(roofsIds, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243);

        signs = new ArrayList<>();

        entrances = new ArrayList<>();
        entrancesIds = new ArrayList<>();
        gateHorizontalIds = new ArrayList<>();
        gateVerticalIds = new ArrayList<>();
        DoubleDoorsIds = new ArrayList<>();
        Collections.addAll(entrancesIds, 194, 195, 197, 198, 199, 200, 201, 202, 203, 204, 217, 218, 219, 220);
        Collections.addAll(gateHorizontalIds, 194, 195);
        Collections.addAll(gateVerticalIds, 197, 198, 199, 200, 201, 202, 203, 204);
        Collections.addAll(DoubleDoorsIds, 218, 219, 220);

        waterTrays = new ArrayList<>();
        waterTrayIds = new ArrayList<>();
        Collections.addAll(waterTrayIds, 289, 290);

        beds = new ArrayList<>();
        bedIds = new ArrayList<>();
        Collections.addAll(bedIds, 313, 317); // bed top tiles
        sleepingCatsToReassign = new ArrayList<>();

        fields = new ArrayList<>();

        initializeFields();
        createMapLayers();
        createObstaclesGrid();
    }

    /** Initializes the pathfinding system for navigation across the game world. */
    public static void initializePathfinder() {
        if (pathfinder == null) {
            pathfinder = new AStar();
        }
    }


    /**
     * Creates and organizes all map rendering layers from ground tiles to area-specific content.
     * Builds the complete visual foundation for the game world with proper layering hierarchy.
     */
    private void createMapLayers() {

        // Base terrain layers
        MapLayer waterLayer = createLayer("src/Map/TileMaps/Ground/ground_water.txt"); // Animated water
        mapBottomLayersToRender.add(waterLayer);
        mapWaterLayerToUpdate = waterLayer;

        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_soil.txt")); // Farmable soil
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_grass.txt")); // Standard grass
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_darkGrass.txt")); // Shaded areas
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_bridges.txt")); // Walkable bridges
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_groundDecor.txt")); // Decorative elements

        createAreasLayersFiles();
        addAreaLayersForRender();
    }

    /**
     * Generates layer files for all active farm areas based on their current upgrade levels.
     * Merges area templates with base layers to create the complete game world layout.
     */
    private void createAreasLayersFiles() {
        MapFileUtils.prepareEmptyLayerFiles();

        for (MapArea area : mapAreas) {
            MapLevels level = mapAreasLevels.get(area);
            String levelPath = getLevelPath(level);
            String areaPath = getAreaPath(area);

            MapFileUtils.copyAreaLayer(areaPath, levelPath, "floor_first.txt");
            MapFileUtils.copyAreaLayer(areaPath, levelPath, "floor_second.txt");
            MapFileUtils.copyAreaLayer(areaPath, levelPath, "layer_first.txt");
            MapFileUtils.copyAreaLayer(areaPath, levelPath, "layer_second.txt");
            MapFileUtils.copyAreaLayer(areaPath, levelPath, "layer_third.txt");
            MapFileUtils.copyAreaLayer(areaPath, levelPath, "roof.txt");
        }
    }

    /**
     * Registers all area-specific layers with the rendering system in correct depth order.
     * Separates layers into background and foreground collections for proper visual hierarchy.
     */
    private void addAreaLayersForRender() {

        // Background rendering layers
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/floor_first.txt")); // Primary floor tiles
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/floor_second.txt")); // Secondary floor details

        // Foreground rendering layers
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/layer_first.txt")); // Base structures
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/layer_second.txt")); // Mid-level details
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/layer_third.txt")); // Upper decorations
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/roof.txt")); // Roof coverage
    }


    // Area management system
    /**
     * Updates all area layers when farm areas are upgraded or modified.
     * Handles complete regeneration of map content and entity placement for level changes.
     */
    private void updateAreasLayers() {
        clearMapEntities();
        createAreasLayersFiles();
        refreshLayersRenderLists();
        Farm.entitiesHandler.createEntitiesFromMap();
        refreshObstaclesGrid();
        calculateBedPositions();
        reassignSleepingCats();
    }

    /**
     * Removes all map-based entities while preserving essential game state like crops and cats.
     * Prepares the map for complete reconstruction during area level updates.
     */
    private void clearMapEntities() {
        bushPositions.clear();
        trees.clear();
        roofs.clear();
        entrances.clear();
        signs.clear();
        waterTrays.clear();

        List<Entity> existingCrops = new ArrayList<>();
        List<Entity> existingCats = new ArrayList<>();
        
        sleepingCatsToReassign.clear();
        for (Bed bed : beds) {
            if (bed.getBedState() == Bed.BedState.OCCUPIED && bed.getOccupyingCat() != null) {
                sleepingCatsToReassign.add(bed.getOccupyingCat());
            }
        }
        
        beds.clear();
        
        for (Entity entity : new ArrayList<>(Farm.entitiesHandler.topRenderableEntities)) {
            if (entity instanceof Crop) {
                existingCrops.add(entity);
            }
        }
        
        for (Entity entity : new ArrayList<>(Farm.entitiesHandler.bottomRenderableMapEntities)) {
            if (entity instanceof FarmCat) {
                existingCats.add(entity);
            }
        }
        
        Farm.entitiesHandler.clickableMapEntities.clear();
        Farm.entitiesHandler.bottomRenderableMapEntities.clear();
        Farm.entitiesHandler.topRenderableEntities.clear();
        Farm.entitiesHandler.updatableMapEntities.clear();
        
        for (Entity cat : existingCats) {
            if (cat != null) {
                if (cat instanceof FarmCat farmCat && sleepingCatsToReassign.contains(farmCat)) {
                    farmCat.setVisible(true);
                }

                Farm.entitiesHandler.bottomRenderableMapEntities.add(cat);
                Farm.entitiesHandler.updatableMapEntities.add(cat);
            }
        }

        for (Entity crop : existingCrops) {
            if (crop != null) {
                Farm.entitiesHandler.clickableMapEntities.add(crop);
                Farm.entitiesHandler.topRenderableEntities.add(crop);
                Farm.entitiesHandler.updatableMapEntities.add(crop);
                
                Point tilePos = crop.getTilePosition();
                addObstacleToTile(tilePos.x, tilePos.y);
            }
        }
    }

    /**
     * Refreshes the rendering layer collections after area level changes.
     * Preserves base ground layers while rebuilding area-specific content layers.
     */
    private void refreshLayersRenderLists() {
        // Keep ground layers (first 6 layers in mapBottomLayersToRender)
        while (mapBottomLayersToRender.size() > 6) {
            mapBottomLayersToRender.removeLast();
        }
        mapTopLayersToRender.clear();

        addAreaLayersForRender();
    }

    /** Updates a specific farm area to a new upgrade level and rebuilds the map accordingly. */
    public void setAreaLevel(MapArea area, MapLevels newLevel) {
        mapAreasLevels.put(area, newLevel);
        updateAreasLayers();
    }

    /**
     * Reassigns displaced sleeping cats to available beds after map reconstruction.
     * Handles bed availability checking and cat state management for seamless transitions.
     */
    private void reassignSleepingCats() {
        if (sleepingCatsToReassign == null || sleepingCatsToReassign.isEmpty()) {
            return;
        }

        ArrayList<Bed> availableBeds = new ArrayList<>();
        for (Bed bed : beds) {
            if (bed.isAvailable() && bed.hasValidPosition()) {
                availableBeds.add(bed);
            }
        }

        for (FarmCat cat : sleepingCatsToReassign) {
            if (!availableBeds.isEmpty()) {
                Bed chosenBed = availableBeds.removeFirst();
                if (chosenBed.reserveBed(cat)) {
                    cat.setActionState(FarmCat.CatActionState.SLEEPING);
                    cat.setTargetBed(chosenBed);
                    chosenBed.setCatSleeping(cat);
                    cat.setVisible(false);
                }
            } else {

                cat.setActionState(FarmCat.CatActionState.IDLE);
                cat.setTargetBed(null);
                cat.setVisible(true);
            }
        }

        sleepingCatsToReassign.clear();
    }

    /** Recalculates optimal positioning for all bed entities in the current map layout. */
    public void calculateBedPositions() {
        for (Bed bed : beds) {
            bed.calculateToBedPosition();
        }
    }

    /** Returns the current upgrade level for the specified farm area. */
    public MapLevels getAreaLevel(MapArea area) {
        return mapAreasLevels.get(area);
    }


    /**
     * Creates a complete map layer from tile data file with automatic entity generation.
     * Processes multi-tile entities like roofs, entrances, and beds with collision tracking.
     */
    private MapLayer createLayer(String path) {
        int[][] tilesIds = MapFileUtils.readFileToGrid(path);
        MapLayer layer = new MapLayer(Farm.mapHeightTiles, Farm.mapWidthTiles);

        boolean[][] processedRoofTiles = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];
        boolean[][] processedEntranceTiles = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];
        boolean[][] processedWaterTrayTiles = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];
        boolean[][] processedBedTiles = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];

        for (int i = 0; i < Farm.mapHeightTiles; i++) {
            for (int j = 0; j < Farm.mapWidthTiles; j++) {

                if (tilesIds[i][j] != 0) {

                    // Water
                    if (tilesIds[i][j] == 1) {
                        layer.tiles[i][j] = new Entities.Entity(new Point(j * Farm.tileSize, i * Farm.tileSize), Farm.resourceHandler.animationFactory.createWaterAnimation());
                        continue;
                    }

                    // Bushes
                    if (tilesIds[i][j] == 152) {
                        bushPositions.add(new Point(j * Farm.tileSize, i * Farm.tileSize));
                        continue;
                    }

                    // Trees
                    if (treesIds.contains(tilesIds[i][j])) {
                        if (tilesIds[i][j] == 177) {
                            createTree(tilesIds, i, j);
                        }
                        continue;
                    }

                    // Roof
                    if (roofsIds.contains(tilesIds[i][j])) {
                        if (!processedRoofTiles[i][j]) {
                            createRoof(tilesIds, i, j, processedRoofTiles);
                        }
                        continue;
                    }

                    // Entrances
                    if (entrancesIds.contains(tilesIds[i][j])) {
                        if (!processedEntranceTiles[i][j]) {
                            createEntrance(tilesIds, i, j, processedEntranceTiles);
                        }
                        continue;
                    }

                    // Water tray
                    if (waterTrayIds.contains(tilesIds[i][j])) {
                        if (!processedWaterTrayTiles[i][j]) {
                            createWaterTray(tilesIds, i, j, processedWaterTrayTiles);
                        }
                        continue;
                    }

                    // Beds
                    if (bedIds.contains(tilesIds[i][j])) {
                        if (!processedBedTiles[i][j]) {
                            createBed(tilesIds, i, j, processedBedTiles);
                        }
                        continue;
                    }

                    // Signs
                    if (tilesIds[i][j] == 332) {
                        Point signPosition = new Point(j * Farm.tileSize, i * Farm.tileSize);
                        Sign sign = new Sign(signPosition);
                        
                        if (j == 26 && i == 24) {
                            sign.assignToField(Field.FieldType.EAST);
                        } else if ((j == 11 && i == 24) || (j == 12 && i == 24)) {
                            sign.assignToField(Field.FieldType.WEST);
                        }
                        
                        signs.add(sign);
                        continue;
                    }

                    // Standard static tile entities
                    layer.tiles[i][j] = new Entities.Entity(new Point(j * Farm.tileSize, i * Farm.tileSize), tilesIds[i][j]);
                }
            }
        }
        return layer;
    }


    /**
     * Constructs a complete tree entity from surrounding tree part tiles.
     * Scans a 3x3 grid around the center tile to collect all connected tree components.
     */
    private void createTree(int[][] tilesIds, int i, int j) {
        Tree tree = new Tree();

        for (int deltaI = -1; deltaI <= 1; deltaI++) {
            for (int deltaJ = -1; deltaJ <= 1; deltaJ++) {
                int newI = i + deltaI;
                int newJ = j + deltaJ;

                if (newI >= 0 && newI < Farm.mapHeightTiles && newJ >= 0 && newJ < Farm.mapWidthTiles && treesIds.contains(tilesIds[newI][newJ])) {
                    Point partPosition = new Point(newJ * Farm.tileSize, newI * Farm.tileSize);
                    tree.addPart(new TreePart(partPosition, tilesIds[newI][newJ]));
                }
            }
        }

        trees.add(tree);
    }

    /**
     * Creates a building roof entity by collecting all connected roof tile positions.
     * Uses flood-fill algorithm to identify complete roof structures automatically.
     */
    private void createRoof(int[][] tilesIds, int i, int j, boolean[][] processedRoofTiles) {
        Roof roof = new Roof();
        ArrayList<Point> positions = findEntityPositions(tilesIds, i, j, processedRoofTiles, roofsIds);
        for (Point position : positions) {
            int tileY = position.y / Farm.tileSize;
            int tileX = position.x / Farm.tileSize;
            roof.addPart(new Entity(position, tilesIds[tileY][tileX]));
        }

        roofs.add(roof);
    }

    /**
     * Constructs entrance entities with proper type detection and multi-tile processing.
     * Handles single doors, gates, and double doors with appropriate part assignment.
     */
    private void createEntrance(int[][] tilesIds, int i, int j, boolean[][] processedEntranceTiles) {
        Entrance entrance = new Entrance();

        if (tilesIds[i][j] == 217) {
            Point position = new Point(j * Farm.tileSize, i * Farm.tileSize);
            entrance.addPart(new EntrancePart(position, tilesIds[i][j], entrance));
            processedEntranceTiles[i][j] = true;
        }
        else {
            ArrayList<Integer> relevantIds = getEntranceTypeIds(tilesIds[i][j]);
            ArrayList<Point> positions = findEntityPositions(tilesIds, i, j, processedEntranceTiles, relevantIds);
            for (Point position : positions) {
                int tileY = position.y / Farm.tileSize;
                int tileX = position.x / Farm.tileSize;
                entrance.addPart(new EntrancePart(position, tilesIds[tileY][tileX], entrance));
            }
        }
        
        entrances.add(entrance);
    }
    
    /** Determines the appropriate tile ID collection based on entrance type classification. */
    private ArrayList<Integer> getEntranceTypeIds(int tileId) {
        if (gateHorizontalIds.contains(tileId)) {
            return gateHorizontalIds;
        } else if (gateVerticalIds.contains(tileId)) {
            return gateVerticalIds;
        } else if (DoubleDoorsIds.contains(tileId)) {
            return DoubleDoorsIds;
        }
        return null;
    }

    /**
     * Creates water tray entities for animal hydration with multi-tile support.
     * Processes connected water tray components into unified interactive structures.
     */
    private void createWaterTray(int[][] tilesIds, int i, int j, boolean[][] processedEntranceTiles) {
        WaterTray waterTray = new WaterTray();

        ArrayList<Integer> relevantIds = waterTrayIds;
        ArrayList<Point> positions = findEntityPositions(tilesIds, i, j, processedEntranceTiles, relevantIds);
        for (Point position : positions) {
            int tileY = position.y / Farm.tileSize;
            int tileX = position.x / Farm.tileSize;
            waterTray.addPart(new WaterTrayPart(position, tilesIds[tileY][tileX]));
        }
        waterTrays.add(waterTray);
    }

    /**
     * Constructs bed entities for cat rest areas with automatic top-bottom part detection.
     * Handles vertical bed structures by linking top and bottom tile components.
     */
    private void createBed(int[][] tilesIds, int i, int j, boolean[][] processedBedTiles) {
        int topTileId = tilesIds[i][j];
        Bed bed = new Bed(topTileId);
        processedBedTiles[i][j] = true;

        Point topPosition = new Point(j * Farm.tileSize, i * Farm.tileSize);
        bed.addPart(new BedPart(topPosition, topTileId, true));

        int bottomRow = i + 1;
        if (bottomRow < Farm.mapHeightTiles) {
            int bottomTileId = tilesIds[bottomRow][j];
            if (bottomTileId != 0) { // Tile exists below
                processedBedTiles[bottomRow][j] = true;
                Point bottomPosition = new Point(j * Farm.tileSize, bottomRow * Farm.tileSize);
                bed.addPart(new BedPart(bottomPosition, bottomTileId, false));
            }
        }

        beds.add(bed);
    }


    /**
     * Identifies all connected tile positions belonging to a multi-tile entity structure.
     * Initiates flood-fill algorithm to discover complete entity boundaries automatically.
     */
    private ArrayList<Point> findEntityPositions(int[][] tilesIds, int i, int j, boolean[][] processedEntityTiles, ArrayList<Integer> idList) {
        boolean[][] visited = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];
        ArrayList<Point> foundPositions = new ArrayList<>();
        return findEntityPositionsRecursive(tilesIds, i, j, processedEntityTiles, idList, visited, foundPositions);
    }

    /**
     * Recursive flood-fill implementation for connected entity component discovery.
     * Explores all adjacent tiles matching the entity type to build complete structures.
     */
    private ArrayList<Point> findEntityPositionsRecursive(int[][] tilesIds, int i, int j, boolean[][] processedEntityTiles, ArrayList<Integer> idList, boolean[][] visited, ArrayList<Point> foundPositions) {
        if (i < 0 || i >= Farm.mapHeightTiles || j < 0 || j >= Farm.mapWidthTiles || visited[i][j]) {
            return foundPositions;
        }

        if (!idList.contains(tilesIds[i][j])) {
            return foundPositions;
        }

        visited[i][j] = true;
        processedEntityTiles[i][j] = true;
        Point partPosition = new Point(j * Farm.tileSize, i * Farm.tileSize);
        foundPositions.add(partPosition);

        for (int deltaI = -1; deltaI <= 1; deltaI++) {
            for (int deltaJ = -1; deltaJ <= 1; deltaJ++) {
                if (deltaI != 0 || deltaJ != 0) {
                    findEntityPositionsRecursive(tilesIds, i + deltaI, j + deltaJ, processedEntityTiles, idList, visited, foundPositions);
                }
            }
        }
        return foundPositions;
    }


    /**
     * Builds the complete obstacle grid for pathfinding and collision detection.
     * Combines ground-based obstacles with entity-placed barriers for navigation.
     */
    private void createObstaclesGrid() {
        addObstaclesFromGround();
        addObstaclesFromCurrentAreas();
    }

    /** Rebuilds the entire obstacle grid after map changes or area level updates. */
    public void refreshObstaclesGrid() {
        clearObstaclesGrid();
        addObstaclesFromGround();
        addObstaclesFromCurrentAreas();
    }

    /** Processes ground-level terrain obstacles including water and decorative elements. */
    private void addObstaclesFromGround() {
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_water.txt", true);
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_bridges.txt", false);
        addObstaclesFromIdList("src/Map/TileMaps/Ground/ground_groundDecor.txt");
    }

    /** Integrates obstacles from all current area-specific layer files. */
    private void addObstaclesFromCurrentAreas() {
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/floor_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/floor_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/layer_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/layer_third.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/roof.txt");
    }

    /** Resets all obstacle grid positions to passable state for complete reconstruction. */
    private void clearObstaclesGrid() {
        // Reset obstacles grid to false
        for (int i = 0; i < Farm.mapHeightTiles; i++) {
            for (int j = 0; j < Farm.mapWidthTiles; j++) {
                obstaclesGrid[i][j] = false;
            }
        }
    }

    /**
     * Sets obstacle status for all non-zero tiles in a layer to the specified value.
     * Used for layer-wide obstacle assignment like water or bridge passability.
     */
    private void setObstacleValueByLayer(String path, boolean value) {
        int[][] tilesIds = MapFileUtils.readFileToGrid(path);
        for (int i = 0; i < Farm.mapHeightTiles; i++) {
            for (int j = 0; j < Farm.mapWidthTiles; j++) {
                if (tilesIds[i][j] != 0) {
                    obstaclesGrid[i][j] = value;
                }
            }
        }
    }

    /**
     * Marks tiles as obstacles based on their tile ID matching the predefined obstacle list.
     * Processes layer files to identify and register collision-causing terrain elements.
     */
    private void addObstaclesFromIdList(String path) {
        int[][] tilesIds = MapFileUtils.readFileToGrid(path);
        for (int i = 0; i < Farm.mapHeightTiles; i++) {
            for (int j = 0; j < Farm.mapWidthTiles; j++) {
                if (obstaclesIds.contains(tilesIds[i][j])) {
                    obstaclesGrid[i][j] = true;
                }
            }
        }
    }

    /** Checks if the specified grid position contains an obstacle for pathfinding validation. */
    public boolean hasObstacleAt(int i, int j) {
        if (i < 0 || i >= Farm.mapHeightTiles || j < 0 || j >= Farm.mapWidthTiles) {
            return true;
        }

        return obstaclesGrid[i][j];
    }
    
    // Dynamic obstacle management
    /** Adds a collision obstacle at the specified tile coordinates for pathfinding avoidance. */
    public void addObstacleToTile(int tileX, int tileY) {
        if (tileY >= 0 && tileY < Farm.mapHeightTiles && tileX >= 0 && tileX < Farm.mapWidthTiles) {
            obstaclesGrid[tileY][tileX] = true;
        }
    }
    
    /** Removes a collision obstacle from the specified tile coordinates to restore passability. */
    public void removeObstacleFromTile(int tileX, int tileY) {
        if (tileY >= 0 && tileY < Farm.mapHeightTiles && tileX >= 0 && tileX < Farm.mapWidthTiles) {
            obstaclesGrid[tileY][tileX] = false;
        }
    }


    /**
     * Creates and configures all farm fields with their designated planting positions.
     * Establishes east and west field areas with predefined crop coordinate layouts.
     */
    public void initializeFields() {
        Field field = new Field(Field.FieldType.EAST);
        ArrayList<Point> cropPositions = new ArrayList<>();
        Collections.addAll(cropPositions,
                new Point(23, 26),
                new Point(24, 25),
                new Point(25, 26),
                new Point(26, 25),
                new Point(28, 25)
        );

        field.addCropPositions(cropPositions);
        fields.add(field);
    }

    /** Manages agricultural field area leveling. */
    public void levelUpFieldsArea() {
        switch (mapAreasLevels.get(MapArea.FIELDS)) {
            case LEVEL_0 -> {
                Field field = fields.getFirst();
                ArrayList<Point> cropPositions = new ArrayList<>();
                Collections.addAll(cropPositions,
                        new Point(29, 26),
                        new Point(25, 28),
                        new Point(26, 27),
                        new Point(27, 28),
                        new Point(28, 27)
                );
                field.addCropPositions(cropPositions);

                Farm.farmResourcesHandler.unlockResource(CORN);
                Farm.farmResourcesHandler.unlockResource(CARROT);

                setAreaLevel(MapArea.FIELDS, MapLevels.LEVEL_1);
            }

            case LEVEL_1 -> {
                Field field = new Field(Field.FieldType.WEST);
                ArrayList<Point> cropPositions = new ArrayList<>();
                Collections.addAll(cropPositions,
                        new Point(10, 25),
                        new Point(9, 26),
                        new Point(11, 26),
                        new Point(12, 25),
                        new Point(13, 26)
                );
                field.addCropPositions(cropPositions);
                fields.add(field);

                Farm.farmResourcesHandler.unlockResource(WHEAT);
                Farm.farmResourcesHandler.unlockResource(CUCUMBER);
                Farm.farmResourcesHandler.unlockResource(RADISH);

                setAreaLevel(MapArea.FIELDS, MapLevels.LEVEL_2);
            }

            case LEVEL_2 -> {
                Field field = fields.getLast();
                ArrayList<Point> cropPositions = new ArrayList<>();
                Collections.addAll(cropPositions,
                        new Point(8, 29),
                        new Point(9, 28),
                        new Point(11, 28),
                        new Point(12, 29),
                        new Point(13, 28)
                );
                field.addCropPositions(cropPositions);

                Farm.farmResourcesHandler.unlockResource(CAULIFLOWER);
                Farm.farmResourcesHandler.unlockResource(EGGPLANT);
                Farm.farmResourcesHandler.unlockResource(PUMPKIN);

                setAreaLevel(MapArea.FIELDS, MapLevels.LEVEL_3);
            }

            case LEVEL_3 -> {
                Farm.farmResourcesHandler.unlockResource(STAR_FRUIT);

                setAreaLevel(MapArea.FIELDS, MapLevels.LEVEL_Star);
            }

            case LEVEL_Star -> {
                // max level reached
            }
        }
    }

    /** Handles house area leveling */
    public void levelUpHouseArea() {
        switch (mapAreasLevels.get(MapArea.HOUSE)) {
            case LEVEL_0 -> {
                // House construction completion

                setAreaLevel(MapArea.HOUSE, MapLevels.LEVEL_1);
            }

            case LEVEL_1 -> {
                FarmCat greyCat = new FarmCat(13, 20, FarmCat.FarmCatColor.GREY);
                Farm.entitiesHandler.addFarmCat(greyCat);
                Farm.catsCount++;

                setAreaLevel(MapArea.HOUSE, MapLevels.LEVEL_2);
            }

            case LEVEL_2 -> {
                FarmCat gingerCat = new FarmCat(22, 21, FarmCat.FarmCatColor.GINGER);
                Farm.entitiesHandler.addFarmCat(gingerCat);
                Farm.catsCount++;

                setAreaLevel(MapArea.HOUSE, MapLevels.LEVEL_3);
            }

            case LEVEL_3 -> {
                FarmCat tricolorCat = new FarmCat(18, 21, FarmCat.FarmCatColor.TRICOLOR);
                Farm.entitiesHandler.addFarmCat(tricolorCat);
                Farm.catsCount++;

                setAreaLevel(MapArea.HOUSE, MapLevels.LEVEL_Star);
            }

            case LEVEL_Star -> {
                // Maximum level reached
            }
        }
    }

    /** Manages chicken coop area leveling */
    public void levelUpCoopArea() {
        switch (mapAreasLevels.get(MapArea.COOP)) {
            case LEVEL_0 -> {
                // TODO: add 1 chicken

                Farm.farmResourcesHandler.unlockResource(EGG);

                setAreaLevel(MapArea.COOP, MapLevels.LEVEL_1);
            }

            case LEVEL_1 -> {
                // TODO: add 1 chicken
                setAreaLevel(MapArea.COOP, MapLevels.LEVEL_2);
            }

            case LEVEL_2 -> {
                // TODO: add 1 chicken
                setAreaLevel(MapArea.COOP, MapLevels.LEVEL_3);
            }

            case LEVEL_3 -> {
                // TODO: add 2 chickens -> total 5
                setAreaLevel(MapArea.COOP, MapLevels.LEVEL_Star);
            }

            case LEVEL_Star -> {
                // Maximum level reached
            }
        }
    }

    /** Handles cattle area leveling. */
    public void levelUpCowsArea() {
        switch (mapAreasLevels.get(MapArea.COWS)) {
            case LEVEL_0 -> {
                // TODO: add 1 cow

                Farm.farmResourcesHandler.unlockResource(MILK);

                setAreaLevel(MapArea.COWS, MapLevels.LEVEL_1);
            }

            case LEVEL_1 -> {
                // TODO: add 1 brown cow

                Farm.farmResourcesHandler.unlockResource(CHOCOLATE_MILK);

                setAreaLevel(MapArea.COWS, MapLevels.LEVEL_2);
            }

            case LEVEL_2 -> {
                // Barn building upgrade
                setAreaLevel(MapArea.COWS, MapLevels.LEVEL_3);
            }

            case LEVEL_3 -> {
                // TODO: add 1 pink cow -> total 3

                Farm.farmResourcesHandler.unlockResource(STRAWBERRY_MILK);

                setAreaLevel(MapArea.COWS, MapLevels.LEVEL_Star);
            }

            case LEVEL_Star -> {
                // Maximum level reached
            }
        }
    }

    /** Manages orchard area leveling. */
    public void levelUpOrchardArea() {
        switch (mapAreasLevels.get(MapArea.ORCHARD)) {
            case LEVEL_0 -> {
                // TODO: 2 trees

                Farm.farmResourcesHandler.unlockResource(APPLE);

                setAreaLevel(MapArea.ORCHARD, MapLevels.LEVEL_1);
            }

            case LEVEL_1 -> {
                // TODO: add 2 trees

                Farm.farmResourcesHandler.unlockResource(PEAR);

                setAreaLevel(MapArea.ORCHARD, MapLevels.LEVEL_2);
            }

            case LEVEL_2 -> {
                // TODO: add 2 trees

                Farm.farmResourcesHandler.unlockResource(PEACH);

                setAreaLevel(MapArea.ORCHARD, MapLevels.LEVEL_3);
            }

            case LEVEL_3 -> {
                // TODO: add 2 trees

                Farm.farmResourcesHandler.unlockResource(ORANGE);

                setAreaLevel(MapArea.ORCHARD, MapLevels.LEVEL_Star);
            }

            case LEVEL_Star -> {
                // Maximum level reached
            }
        }
    }

    /** Handles park area leveling. */
    public void levelUpParkArea() { // TODO: implement bush fruits
        switch (mapAreasLevels.get(MapArea.PARK)) {
            case LEVEL_0 -> {
                // TODO: Add park level up logic
                setAreaLevel(MapArea.PARK, MapLevels.LEVEL_1);
            }
            case LEVEL_1 -> {
                // TODO: Add park level up logic
                setAreaLevel(MapArea.PARK, MapLevels.LEVEL_2);
            }
            case LEVEL_2 -> {
                // TODO: Add park level up logic
                setAreaLevel(MapArea.PARK, MapLevels.LEVEL_3);
            }
            case LEVEL_3 -> {
                // TODO: Add park level up logic
                setAreaLevel(MapArea.PARK, MapLevels.LEVEL_Star);
            }
            case LEVEL_Star -> {
                // Maximum level reached
            }
        }
    }


    /** Converts map level enum to corresponding file system directory path string. */
    private String getLevelPath(MapLevels level) {
        return switch (level) {
            case LEVEL_0 -> "Lvl0";
            case LEVEL_1 -> "Lvl1";
            case LEVEL_2 -> "Lvl2";
            case LEVEL_3 -> "Lvl3";
            case LEVEL_Star -> "LvlStar";
        };
    }

    /** Converts map area enum to corresponding file system directory path string. */
    private String getAreaPath(MapArea area) {
        return switch (area) {
            case PARK -> "Park";
            case COOP -> "Coop";
            case COWS -> "Cows";
            case FIELDS -> "Fields";
            case HOUSE -> "House";
            case ORCHARD -> "Orchard";
        };
    }

    /** Updates animated water layer tiles for continuous visual effects. */
    public void updateWaterLayer() {
        mapWaterLayerToUpdate.update();
    }


    /** Renders all background map layers beneath entities in proper depth order. */
    public void renderBottom(Graphics2D graphics2D) {
        mapBottomLayersToRender.forEach(layer -> layer.render(graphics2D));
    }

    /** Renders all foreground map layers above entities for overlay effects. */
    public void renderTop(Graphics2D graphics2D) {
        mapTopLayersToRender.forEach(layer -> layer.render(graphics2D));
    }
}
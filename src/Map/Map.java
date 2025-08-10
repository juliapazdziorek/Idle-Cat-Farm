package Map;

import Entities.AnimatedEntity;
import Entities.StaticEntity;
import Entities.Nature.TreePart;
import Entities.Nature.Tree;
import Entities.BuildingParts.Roof;
import Entities.BuildingParts.RoofPart;
import Game.Farm;
import Resources.Animation;
import Pathfinding.AStar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Map {

    // layers lists
    private final ArrayList<MapLayer> mapBottomLayersToRender;
    private final ArrayList<MapLayer> mapTopLayersToRender;
    private final ArrayList<MapLayer> mapLayersToUpdate;

    // map areas
    public enum MapLevels {LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_Star}
    public enum MapArea {PARK, COOP, COWS, FIELDS, HOUSE, ORCHARD}
    public final ArrayList<MapArea> mapAreas;
    public final HashMap<MapArea, MapLevels> mapAreasLevels;

    // obstacles
    private final boolean[][] obstaclesGrid;
    private final ArrayList<Integer> obstaclesIds;

    // map entities
    public final ArrayList<Point> bushPositions;
    public final ArrayList<Point> signsPositions;

    // trees
    public final ArrayList<Tree> trees;
    ArrayList<Integer> treesIds;

    // roofs
    public final ArrayList<Roof> roofs;
    ArrayList<Integer> roofsIds;

    // pathfinder
    public static AStar pathfinder;

    public Map() {

        // layer lists
        mapBottomLayersToRender = new ArrayList<>();
        mapTopLayersToRender = new ArrayList<>();
        mapLayersToUpdate = new ArrayList<>();

        // map areas
        mapAreas = new ArrayList<>();
        Collections.addAll(mapAreas, MapArea.PARK, MapArea.COOP, MapArea.COWS, MapArea.FIELDS, MapArea.HOUSE, MapArea.ORCHARD);
        mapAreasLevels = new HashMap<>();
        for (MapArea area : mapAreas) {
            mapAreasLevels.put(area, MapLevels.LEVEL_0);
        }

        // obstacles
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

        // map entities
        bushPositions = new ArrayList<>();
        signsPositions = new ArrayList<>();
        trees = new ArrayList<>();
        treesIds = new ArrayList<>();
        Collections.addAll(treesIds, 173, 174, 175, 176, 177, 178, 179, 180, 181);

        roofs = new ArrayList<>();
        roofsIds = new ArrayList<>();
        Collections.addAll(roofsIds, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243);

        // initialize layers
        createMapLayers();

        // create obstacles grid
        createObstaclesGrid();
    }

    public static void initializePathfinder() {
        if (pathfinder == null) {
            pathfinder = new AStar();
        }
    }


    // handling map layers
    private void createMapLayers() {

        // ground layers
        MapLayer waterLayer = createLayer("src/Map/TileMaps/Ground/ground_water.txt"); // water
        mapBottomLayersToRender.add(waterLayer);
        mapLayersToUpdate.add(waterLayer);

        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_soil.txt")); // soil
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_grass.txt")); // grass
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_darkGrass.txt")); // dark grass
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_bridges.txt")); // bridges
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_groundDecor.txt")); // ground decor

        // create area layers
        createAreasLayersFiles();

        // add area layers to render list
        addAreaLayersForRender();
    }

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

    private void addAreaLayersForRender() {

        // bottom layers for current tile maps
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/floor_first.txt")); // first floor
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/floor_second.txt")); // second floor

        // top layers for current tile maps
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/layer_first.txt")); // first layer
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/layer_second.txt")); // second layer
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/layer_third.txt")); // third layer
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/InGameTileMaps/roof.txt")); // roof
    }


    // managing areas
    private void updateAreasLayers() {

        // clear map entities from previous levels
        clearMapEntities();

        // recreate the current tile maps with updated levels
        createAreasLayersFiles();

        // refresh the rendered layers to reflect the changes
        refreshLayersRenderLists();
        Farm.entitiesHandler.createEntitiesFromMap();

        // refresh obstacles to match the new area levels
        refreshObstaclesGrid();

        // update pathfinding grid after obstacle changes
        pathfinder.updateGrid();
    }

    private void clearMapEntities() {
        bushPositions.clear();
        signsPositions.clear();
        trees.clear();
        roofs.clear();

        Farm.entitiesHandler.clickableMapEntities.clear();
        Farm.entitiesHandler.renderableMapEntities.clear();
        Farm.entitiesHandler.updatableMapEntities.clear();
    }

    private void refreshLayersRenderLists() {

        // remove the existing area layers from rendering lists
        // keep ground layers (first 6 layers in mapBottomLayersToRender)
        while (mapBottomLayersToRender.size() > 6) {
            mapBottomLayersToRender.removeLast();
        }
        mapTopLayersToRender.clear();

        // recreate and add area layers for render
        addAreaLayersForRender();
    }

    public void setAreaLevel(MapArea area, MapLevels newLevel) {
        mapAreasLevels.put(area, newLevel);
        updateAreasLayers();
    }

    public MapLevels getAreaLevel(MapArea area) {
        return mapAreasLevels.get(area);
    }


    // creating map layer from a file
    private MapLayer createLayer(String path) {
        int[][] tilesIds = MapFileUtils.readFileToGrid(path);
        MapLayer layer = new MapLayer(Farm.mapHeightTiles, Farm.mapWidthTiles);
        
        // track processed roof tiles
        boolean[][] processedRoofTiles = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];

        for (int i = 0; i < Farm.mapHeightTiles; i++) {
            for (int j = 0; j < Farm.mapWidthTiles; j++) {

                if (tilesIds[i][j] != 0) {

                    // water
                    if (tilesIds[i][j] == 1) {
                        layer.tiles[i][j] = new AnimatedEntity(new Point(j * Farm.tileSize, i * Farm.tileSize), Farm.resourceHandler.animationFactory.createWaterAnimation());
                        continue;
                    }

                    // bush positions
                    if (tilesIds[i][j] == 152) {
                        bushPositions.add(new Point(j * Farm.tileSize, i * Farm.tileSize));
                        continue;
                    }

                    // trees
                    if (treesIds.contains(tilesIds[i][j])) {
                        if (tilesIds[i][j] == 177) {
                            createTree(tilesIds, i, j);
                        }
                        continue;
                    }

                    // roofs
                    if (roofsIds.contains(tilesIds[i][j])) {
                        if (!processedRoofTiles[i][j]) {
                            createRoof(tilesIds, i, j, processedRoofTiles);
                        }
                        continue;
                    }

                    // signs positions
                    if (tilesIds[i][j] == 332) {
                        signsPositions.add(new Point(j * Farm.tileSize, i * Farm.tileSize));
                        continue;
                    }

                    // static tiles
                    layer.tiles[i][j] = new StaticEntity(new Point(j * Farm.tileSize, i * Farm.tileSize), Farm.resourceHandler.tilesMap.get(tilesIds[i][j]));
                }
            }
        }
        return layer;
    }

    // create a tree
    private void createTree(int[][] tilesIds, int i, int j) {
        Point centerPosition = new Point(j * Farm.tileSize, i * Farm.tileSize);
        Tree tree = new Tree(centerPosition);

        for (int deltaI = -1; deltaI <= 1; deltaI++) {
            for (int deltaJ = -1; deltaJ <= 1; deltaJ++) {
                int newI = i + deltaI;
                int newJ = j + deltaJ;

                if (newI >= 0 && newI < Farm.mapHeightTiles &&
                        newJ >= 0 && newJ < Farm.mapWidthTiles &&
                        treesIds.contains(tilesIds[newI][newJ])) {

                    Point partPosition = new Point(newJ * Farm.tileSize, newI * Farm.tileSize);
                    TreePart part = new TreePart(partPosition, tilesIds[newI][newJ]);
                    tree.addTreePart(part);
                }
            }
        }

        trees.add(tree);
    }

    // create a roof
    private void createRoof(int[][] tilesIds, int i, int j, boolean[][] processedRoofTiles) {
        Point firstPosition = new Point(j * Farm.tileSize, i * Farm.tileSize);
        boolean[][] visited = new boolean[Farm.mapHeightTiles][Farm.mapWidthTiles];
        Roof roof = new Roof(firstPosition);

        // flood fill algorithm to find all connected roof parts
        findRoofParts(tilesIds, visited, roof, i, j, processedRoofTiles);

        roofs.add(roof);
    }

    private void findRoofParts(int[][] tilesIds, boolean[][] visited, Roof roof, int i, int j, boolean[][] processedRoofTiles) {
        if (i < 0 || i >= Farm.mapHeightTiles ||
            j < 0 || j >= Farm.mapWidthTiles || 
            visited[i][j]) {
            return;
        }

        if (!roofsIds.contains(tilesIds[i][j])) {
            return;
        }

        visited[i][j] = true;
        processedRoofTiles[i][j] = true;
        Point partPosition = new Point(j * Farm.tileSize, i * Farm.tileSize);
        RoofPart part = new RoofPart(partPosition, tilesIds[i][j]);
        roof.addRoofPart(part);

        for (int deltaI = -1; deltaI <= 1; deltaI++) {
            for (int deltaJ = -1; deltaJ <= 1; deltaJ++) {
                if (deltaI != 0 || deltaJ != 0) {
                    findRoofParts(tilesIds, visited, roof, i + deltaI, j + deltaJ, processedRoofTiles);
                }
            }
        }
    }


    // handling obstacles
    private void createObstaclesGrid() {
        // obstacles from ground layers
        addObstaclesFromGround();

        // add obstacles from current area levels
        addObstaclesFromCurrentAreas();
    }

    public void refreshObstaclesGrid() {
        // clear current obstacles grid
        clearObstaclesGrid();

        // re-add obstacles
        addObstaclesFromGround();
        addObstaclesFromCurrentAreas();
    }

    private void addObstaclesFromGround() {
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_water.txt", true);
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_bridges.txt", false);
        addObstaclesFromIdList("src/Map/TileMaps/Ground/ground_groundDecor.txt");
    }

    private void addObstaclesFromCurrentAreas() {
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/floor_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/floor_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/layer_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/layer_third.txt");
        addObstaclesFromIdList("src/Map/TileMaps/InGameTileMaps/roof.txt");
    }

    private void clearObstaclesGrid() {
        // reset obstacles grid to false
        for (int i = 0; i < Farm.mapHeightTiles; i++) {
            for (int j = 0; j < Farm.mapWidthTiles; j++) {
                obstaclesGrid[i][j] = false;
            }
        }
    }

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

    public boolean hasObstacleAt(int i, int j) {
        if (i < 0 || i >= Farm.mapHeightTiles || j < 0 || j >= Farm.mapWidthTiles) {
            return true;
        }

        return obstaclesGrid[i][j];
    }


    // enums utility methods
    private String getLevelPath(MapLevels level) {
        return switch (level) {
            case LEVEL_0 -> "Lvl0";
            case LEVEL_1 -> "Lvl1";
            case LEVEL_2 -> "Lvl2";
            case LEVEL_3 -> "Lvl3";
            case LEVEL_Star -> "LvlStar";
        };
    }

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


    // updating & rendering
    public void update() {
        mapLayersToUpdate.forEach(MapLayer::update);
    }

    public void renderBottom(Graphics2D graphics2D) {
        mapBottomLayersToRender.forEach(layer -> layer.render(graphics2D));
    }

    public void renderTop(Graphics2D graphics2D) {
        mapTopLayersToRender.forEach(layer -> layer.render(graphics2D));
    }
}
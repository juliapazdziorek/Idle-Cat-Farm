package Map;

import Entities.AnimatedEntity;
import Entities.StaticEntity;
import Entities.Nature.TreePart;
import Entities.Nature.Tree;
import Game.FocusFarm;
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
    public enum MapLevels {LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_Star;}
    public enum MapArea {PARK, COOP, COWS, FIELDS, HOUSE, ORCHARD;}
    public final ArrayList<MapArea> mapAreas;
    public final HashMap<MapArea, MapLevels> mapAreasLevels;

    // obstacles
    private final boolean[][] obstaclesGrid;
    private ArrayList<Integer> obstaclesIds = new ArrayList<>();

    // map entities
    public final ArrayList<Point> bushPositions;
    public final ArrayList<Point> signsPositions;

    // trees
    public final ArrayList<Tree> trees;
    ArrayList<Integer> treesIds;

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
        obstaclesGrid = new boolean[FocusFarm.mapHeightTiles][FocusFarm.mapWidthTiles];
        obstaclesIds = new ArrayList<>();
        Collections.addAll(obstaclesIds, 123, // big shroom
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
                189, // single fence broken small
                190, // single fence broken
                191, // left fence broken
                192, // right fence broken
                193, // gate horizontal left
                196, // gate horizontal right
                197, // gate vertical up
                200, // gate vertical down
                201, // wall front
                202, // wall left
                203, // wall right
                204, // wall up-left corner
                205, // wall bottom-left corner
                206, // wall bottom-right corner
                207, // wall up-left big corner
                208, // wall bottom-left big corner
                209, // wall up-right big corner
                210, // wall bottom-right big corner
                211, // window
                239, // small coop 1
                240, // small coop 2
                241, // small coop 3
                242, // small coop 4
                243, // small coop 5
                244, // small coop 6
                245, // big coop 1
                246, // big coop 2
                247, // big coop 3
                248, // big coop 4
                249, // big coop 5
                250, // big coop 6
                251, // big coop 7
                252, // big coop 8
                253, // big coop 9
                254, // large coop 1
                255, // large coop 2
                256, // large coop 3
                257, // large coop 4
                258, // large coop 5
                259, // large coop 6
                260, // large coop 7
                261, // large coop 8
                262, // large coop 9
                263, // large coop 10
                264, // large coop 11
                265, // large coop 12
                266, // large coop 13
                267, // large coop 14
                268, // large coop 15
                269, // large coop 16
                270, // large coop 17
                271, // large coop 18
                272, // large coop 19
                273, // large coop 20
                274, // box
                275, // two boxes top-left
                276, // two boxes top-right
                277, // two boxes bottom-left
                278, // two boxes bottom-right
                279, // hay
                280, // big hay left
                281, // big hay right
                284, // water tray left
                285, // water tray right
                287, // mailbox bottom
                289, // work station left-bottom
                291, // work station right-bottom
                293, // water well left-bottom
                295, // water well right-bottom
                305, // picnic basket
                307, // flower pot
                309, // pink bed bottom
                310, // blue bed bottom
                311, // green bed bottom
                313, // green upside down bed bottom
                314, // chair left
                315, // chair down
                316, // dresser
                317, // table
                327); // sign

        // map entities
        bushPositions = new ArrayList<>();
        signsPositions = new ArrayList<>();
        trees = new ArrayList<>();
        treesIds = new ArrayList<>();
        Collections.addAll(treesIds, 173, 174, 175, 176, 177, 178, 179, 180, 181);

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
        FocusFarm.entitiesHandler.createEntitiesFromMap();

        // refresh obstacles to match the new area levels
        refreshObstaclesGrid();

        // update pathfinding grid after obstacle changes
        pathfinder.updateGrid();
    }

    private void clearMapEntities() {
        bushPositions.clear();
        signsPositions.clear();
        trees.clear();
        FocusFarm.entitiesHandler.mapEntities.clear();
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
        MapLayer layer = new MapLayer(FocusFarm.mapHeightTiles, FocusFarm.mapWidthTiles);

        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {

                if (tilesIds[i][j] != 0) {

                    // map entities

                    // bush positions
                    if (tilesIds[i][j] == 152) {
                        bushPositions.add(new Point(j * FocusFarm.tileSize, i * FocusFarm.tileSize));
                        continue;
                    }

                    // trees
                    if (tilesIds[i][j] == 177) {
                        createTree(tilesIds, i, j);
                        continue;
                    }

                    // signs positions
                    if (tilesIds[i][j] == 327) {
                        signsPositions.add(new Point(j * FocusFarm.tileSize, i * FocusFarm.tileSize));
                        continue;
                    }


                    // animated tiles
                    if (FocusFarm.resourceHandler.animationsMap.containsKey(tilesIds[i][j])) {
                        Animation animation = FocusFarm.resourceHandler.animationsMap.get(tilesIds[i][j]).get();
                        layer.tiles[i][j] = new AnimatedEntity(new Point(j * FocusFarm.tileSize, i * FocusFarm.tileSize), animation);

                        // static tiles
                    } else if (FocusFarm.resourceHandler.tilesMap.containsKey(tilesIds[i][j])) {
                        layer.tiles[i][j] = new StaticEntity(new Point(j * FocusFarm.tileSize, i * FocusFarm.tileSize), FocusFarm.resourceHandler.tilesMap.get(tilesIds[i][j]));
                    }
                }
            }
        }
        return layer;
    }

    // create a tree
    private void createTree(int[][] tilesIds, int i, int j) {
        Point centerPosition = new Point(j * FocusFarm.tileSize, i * FocusFarm.tileSize);
        Tree tree = new Tree(centerPosition);

        for (int deltaI = -1; deltaI <= 1; deltaI++) {
            for (int deltaJ = -1; deltaJ <= 1; deltaJ++) {
                int newI = i + deltaI;
                int newJ = j + deltaJ;

                // Check bounds and if the tile ID is a tree part
                if (newI >= 0 && newI < FocusFarm.mapHeightTiles &&
                        newJ >= 0 && newJ < FocusFarm.mapWidthTiles &&
                        treesIds.contains(tilesIds[newI][newJ])) {

                    Point partPosition = new Point(newJ * FocusFarm.tileSize, newI * FocusFarm.tileSize);
                    TreePart part = new TreePart(partPosition, tilesIds[newI][newJ]);
                    tree.addTreePart(part);
                }
            }
        }

        trees.add(tree);
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
        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {
                obstaclesGrid[i][j] = false;
            }
        }
    }

    private void setObstacleValueByLayer(String path, boolean value) {
        int[][] tilesIds = MapFileUtils.readFileToGrid(path);
        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {
                if (tilesIds[i][j] != 0) {
                    obstaclesGrid[i][j] = value;
                }
            }
        }
    }

    private void addObstaclesFromIdList(String path) {
        int[][] tilesIds = MapFileUtils.readFileToGrid(path);
        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {
                if (obstaclesIds.contains(tilesIds[i][j])) {
                    obstaclesGrid[i][j] = true;
                }
            }
        }
    }

    public boolean hasObstacleAt(int i, int j) {
        if (i < 0 || i >= FocusFarm.mapHeightTiles || j < 0 || j >= FocusFarm.mapWidthTiles) {
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
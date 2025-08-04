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
    public static final ArrayList<Integer> obstaclesIds = new ArrayList<>();
    private final boolean[][] obstaclesGrid;

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
        mapBottomLayersToRender.add(createLayer("src/Map/CurrentTileMaps/floor_first.txt")); // first floor
        mapBottomLayersToRender.add(createLayer("src/Map/CurrentTileMaps/floor_second.txt")); // second floor

        // top layers for current tile maps
        mapTopLayersToRender.add(createLayer("src/Map/CurrentTileMaps/layer_first.txt")); // first layer
        mapTopLayersToRender.add(createLayer("src/Map/CurrentTileMaps/layer_second.txt")); // second layer
        mapTopLayersToRender.add(createLayer("src/Map/CurrentTileMaps/layer_third.txt")); // third layer
        mapTopLayersToRender.add(createLayer("src/Map/CurrentTileMaps/roof.txt")); // roof
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
                    if (tilesIds[i][j] == 325) {
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

        // initialize obstacles ids
        MapFileUtils.loadObstaclesIds();

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
        addObstaclesFromIdList("src/Map/CurrentTileMaps/floor_first.txt");
        addObstaclesFromIdList("src/Map/CurrentTileMaps/floor_second.txt");
        addObstaclesFromIdList("src/Map/CurrentTileMaps/layer_first.txt");
        addObstaclesFromIdList("src/Map/CurrentTileMaps/layer_second.txt");
        addObstaclesFromIdList("src/Map/CurrentTileMaps/layer_third.txt");
        addObstaclesFromIdList("src/Map/CurrentTileMaps/roof.txt");
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
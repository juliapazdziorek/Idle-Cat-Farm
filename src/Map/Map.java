package Map;

import Entities.AnimatedEntity;
import Entities.StaticEntity;
import Game.FocusFarm;
import Resources.Animation;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Map {

    // layers
    private final ArrayList<MapLayer> mapLayersToRender;
    private final ArrayList<MapLayer> mapLayersToUpdate;

    // obstacles
    private final boolean[][] obstaclesGrid;
    private final ArrayList<Integer> obstaclesIds;


    public Map() {
        mapLayersToRender = new ArrayList<>();
        mapLayersToUpdate = new ArrayList<>();

        obstaclesGrid = new boolean[FocusFarm.mapHeightTiles][FocusFarm.mapWidthTiles];
        obstaclesIds = new ArrayList<>();

        createMapLayers();
        createObstaclesGrid();
    }

    private void createMapLayers() {

        // ground

        // water layer
        MapLayer waterLayer = createWaterLayer();
        mapLayersToRender.add(waterLayer);
        mapLayersToUpdate.add(waterLayer);

        // static ground layers
        mapLayersToRender.add(createStaticLayer("src/Map/TileMaps/Ground/ground_soil.txt")); // soil
        mapLayersToRender.add(createStaticLayer("src/Map/TileMaps/Ground/ground_grass.txt")); // grass
        mapLayersToRender.add(createStaticLayer("src/Map/TileMaps/Ground/ground_darkGrass.txt")); // dark grass
        mapLayersToRender.add(createStaticLayer("src/Map/TileMaps/Ground/ground_bridges.txt")); // bridges
        mapLayersToRender.add(createStaticLayer("src/Map/TileMaps/Ground/ground_groundDecor.txt")); // ground decor

    }

    private MapLayer createWaterLayer() {
        int[][] tilesIds = readFileToGrid("src/Map/TileMaps/Ground/ground_water.txt");
        MapLayer waterLayer = new MapLayer(FocusFarm.mapHeightTiles, FocusFarm.mapWidthTiles);

        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {

                // id 1 for water animated tiles
                if (tilesIds[i][j] == 1) {
                    Animation waterAnimation = FocusFarm.resourceHandler.createWaterAnimation();
                    waterLayer.setTile(i, j, new AnimatedEntity(j * FocusFarm.tileSize, i * FocusFarm.tileSize, waterAnimation));
                }
            }
        }
        return waterLayer;
    }

    private MapLayer createStaticLayer(String path) {
        int[][] tilesIds = readFileToGrid(path);
        MapLayer staticLayer = new MapLayer(FocusFarm.mapHeightTiles, FocusFarm.mapWidthTiles);

        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {

                if (tilesIds[i][j] != 0) {
                    staticLayer.tiles[i][j] = new StaticEntity(j * FocusFarm.tileSize, i * FocusFarm.tileSize, FocusFarm.resourceHandler.mapTilesMap.get(tilesIds[i][j]));
                }
            }
        }
        return staticLayer;
    }


    // handling obstacles
    private void createObstaclesGrid() {

        // initialize obstacles ids
        initializeObstaclesIds();

        // obstacles from ground layers
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_water.txt", true);
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_bridges.txt", false);
        addObstaclesFromIdList("src/Map/TileMaps/Ground/ground_groundDecor.txt");

    }

    private void initializeObstaclesIds() {
        
        // grass decor, shrooms, stones & flowers
        Collections.addAll(obstaclesIds,
            123, // big shroom
            124, // poison shroom
            129, // stone 1
            130, // stone 2
            133, // big stone bottom-left
            134, // big stone bottom-right
            137, // large stone bottom-left
            138, // large stone bottom-right
            147, // yellow flower 4 bottom
            154, // pink flower 4
            158  // blue flower 4
        );

        // trees, bushes stumps
        Collections.addAll(obstaclesIds,
                165 // small tree bottom
        );
    }

    private void setObstacleValueByLayer(String path, boolean value) {
        int[][] tilesIds = readFileToGrid(path);
        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {
                if (tilesIds[i][j] != 0) {
                    obstaclesGrid[i][j] = value;
                }
            }
        }
    }

    private void addObstaclesFromIdList(String path) {
        int[][] tilesIds = readFileToGrid(path);
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


    // file reading
    private int[][] readFileToGrid(String path) {
        ArrayList<int[]> rows = new ArrayList<>();
        String line;
        String[] tokens;

        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                tokens = line.split("\\s+");
                int[] row = new int[FocusFarm.mapWidthTiles];
                for (int i = 0; i < tokens.length && i < FocusFarm.mapWidthTiles; i++) {
                    row[i] = Integer.parseInt(tokens[i]);
                }
                rows.add(row);
            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("Problem with reading tiles ids from file: " + path + "\n" + exception.getMessage());
        }

        return rows.toArray(new int[rows.size()][]);
    }


    // updating & rendering
    public void update() {
        mapLayersToUpdate.forEach(MapLayer::update);
    }

    public void render(Graphics2D graphics2D) {
        mapLayersToRender.forEach(layer -> layer.render(graphics2D));
    }
}
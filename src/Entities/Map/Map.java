package Entities.Map;

import Entities.AnimatedEntity;
import Entities.StaticEntity;
import Resources.ResourceHandler;
import Resources.Animation;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    ArrayList<MapLayer> mapLayersToRender;
    ArrayList<MapLayer> mapLayersToUpdate;
    private final int mapHeightTiles;
    private final int mapWidthTiles;
    private final int tileSize;
    private final ResourceHandler resourceHandler;

    public Map(int mapHeightTiles, int mapWidthTiles, int tileSize, ResourceHandler resourceHandler) {
        this.mapHeightTiles = mapHeightTiles;
        this.mapWidthTiles = mapWidthTiles;
        this.tileSize = tileSize;
        this.resourceHandler = resourceHandler;
        mapLayersToRender = new ArrayList<>();
        mapLayersToUpdate = new ArrayList<>();
        createMapLayers();
    }

    private void createMapLayers() {

        // water layer
        MapLayer waterLayer = createWaterLayer();
        mapLayersToRender.add(waterLayer);
        mapLayersToUpdate.add(waterLayer);
    }


    // creating layers
    private MapLayer createWaterLayer() {
        int[][] idFromFile = readFileToIntInt("src/Entities/Map/TextMapLayers/water.txt");
        MapLayer waterLayer = new MapLayer(mapHeightTiles, mapWidthTiles);

        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                if (idFromFile[i][j] == 1) {
                    Animation waterAnimation = resourceHandler.getWaterAnimation();
                    if (waterAnimation != null) {
                        waterLayer.setTile(i, j, new AnimatedEntity(j * tileSize, i * tileSize, waterAnimation));
                    }
                }
            }
        }

        return waterLayer;
    }


    // file reading
    private int[][] readFileToIntInt(String filePath) {
        ArrayList<int[]> rows = new ArrayList<>();
        String line;
        String[] tokens;

        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                tokens = line.split("\\s+");
                int[] row = new int[mapWidthTiles];
                for (int i = 0; i < tokens.length && i < mapWidthTiles; i++) {
                    row[i] = Integer.parseInt(tokens[i]);
                }
                rows.add(row);
            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("Problem with reading tiles ids from file: " + filePath + "\n" + exception.getMessage());
        }

        return rows.toArray(new int[rows.size()][]);
    }


    // updating & rendering
    public void update() {
        mapLayersToUpdate.forEach(MapLayer::update);
    }

    public void render(Graphics2D graphics2D, int cameraX, int cameraY, int scale, int scaledTileSize) {
        mapLayersToRender.forEach(layer -> layer.render(graphics2D, cameraX, cameraY, scale, scaledTileSize));
    }
}
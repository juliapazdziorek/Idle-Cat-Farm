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
        mapLayersToRender = new ArrayList<>();
        mapLayersToUpdate = new ArrayList<>();

        this.mapHeightTiles = mapHeightTiles;
        this.mapWidthTiles = mapWidthTiles;
        this.tileSize = tileSize;
        this.resourceHandler = resourceHandler;

        createMapLayers();
    }

    private void createMapLayers() {

        // water layer
        MapLayer waterLayer = createWaterLayer();
        mapLayersToRender.add(waterLayer);
        mapLayersToUpdate.add(waterLayer);

        // soil & grass
        mapLayersToRender.add(createSoilLayer());
        mapLayersToRender.add(createGrassLayer());
        mapLayersToRender.add(createDarkGrassLayer());
        mapLayersToRender.add(createBridgesLayer());
        mapLayersToRender.add(createGroundDecorLayer());
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

    private MapLayer createSoilLayer() {
        int[][] idFromFile = readFileToIntInt("src/Entities/Map/TextMapLayers/soil.txt");
        MapLayer soilLayer = new MapLayer(mapHeightTiles, mapWidthTiles);

        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                switch (idFromFile[i][j]) {
                    case 1 -> soilLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("plainSoil"));
                    case 2 -> soilLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeWhiteStones"));
                    case 3 -> soilLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkSand"));
                    case 4 -> soilLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeDarkStones"));
                    case 5 -> soilLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("whiteSand"));
                    case 6 -> soilLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("twoWhiteStones"));
                    case 7 -> soilLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("twoDarkStones"));
                }
            }
        }
        return soilLayer;
    }

    private MapLayer createGrassLayer() {
        int[][] idFromFile = readFileToIntInt("src/Entities/Map/TextMapLayers/grass.txt");
        MapLayer grassLayer = new MapLayer(mapHeightTiles, mapWidthTiles);

        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                switch (idFromFile[i][j]) {
                    case 1 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("upGrass"));
                    case 2 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("leftUpCornerWaterGrass"));
                    case 3 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("leftBottomCornerWaterGrass"));
                    case 4 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("rightUpCornerWaterGrass"));
                    case 5 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("rightBottomCornerWaterGrass"));
                    case 6 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("leftWaterGrass"));
                    case 7 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("rightWaterGrass"));
                    case 8 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("bottomWaterGrass"));
                    case 9 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("leftUpInnerWaterGrass"));
                    case 10 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("leftBottomInnerWaterGrass"));
                    case 11 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("rightUpInnerWaterGrass"));
                    case 12 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("rightBottomInnerWaterGrass"));
                    case 13 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassTile"));
                    case 14 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdWaterGrass1"));
                    case 15 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdWaterGrass2"));
                    case 16 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdWaterGrass3"));
                    case 17 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdWaterGrass4"));
                    case 18 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdWaterGrass5"));
                    case 19 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdWaterGrass6"));
                    case 20 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdWaterGrass7"));
                    case 21 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("bigGrassGreen"));
                    case 22 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("smallerGrassGreen"));
                    case 23 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("smallGrassGreen"));
                    case 24 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("twoDarkRocksGrass"));
                    case 25 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeDarkRocksGrass"));
                    case 26 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeFlowersGrass"));
                    case 27 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("bigGrassLight"));
                    case 28 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("smallerGrassLight"));
                    case 29 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("smallGrassLight"));
                    case 30 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("twoLightRocksGrass"));
                    case 31 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeLightRocksGrass"));
                    case 32 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("twoFlowersGrass"));
                    case 33 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerBottom"));
                    case 34 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerLeft"));
                    case 35 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerRight"));
                    case 36 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerUpLeftCorner"));
                    case 37 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerBottomLeftCorner"));
                    case 38 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerUpRightCorner"));
                    case 39 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerBottomRightCorner"));
                    case 40 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerUpLeftInner"));
                    case 41 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerBottomLeftInner"));
                    case 42 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerUpRightInner"));
                    case 43 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassLayerBottomRightInner"));
                    case 44 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdGrassLayer1"));
                    case 45 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdGrassLayer2"));
                    case 46 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdGrassLayer3"));
                    case 47 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdGrassLayer4"));
                    case 48 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdGrassLayer5"));
                    case 49 -> grassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdGrassLayer6"));
                }
            }
        }
        return grassLayer;
    }

    private MapLayer createDarkGrassLayer() {
        int[][] idFromFile = readFileToIntInt("src/Entities/Map/TextMapLayers/darkGrass.txt");
        MapLayer darkGrassLayer = new MapLayer(mapHeightTiles, mapWidthTiles);

        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                switch (idFromFile[i][j]) {
                    case 1 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassTile"));
                    case 2 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassUp"));
                    case 3 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassLeft"));
                    case 4 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassBottom"));
                    case 5 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassRight"));
                    case 6 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassLeftUpCorner"));
                    case 7 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassLeftBottomCorner"));
                    case 8 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassRightUpCorner"));
                    case 9 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassRightBottomCorner"));
                    case 10 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassUpLeftInner"));
                    case 11 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassBottomLeftInner"));
                    case 12 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassUpRightInner"));
                    case 13 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("darkGrassBottomRightInner"));
                    case 14 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass1"));
                    case 15 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass2"));
                    case 16 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass3"));
                    case 17 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass4"));
                    case 18 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass5"));
                    case 19 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass6"));
                    case 20 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass7"));
                    case 21 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass8"));
                    case 22 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass9"));
                    case 23 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass10"));
                    case 24 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass11"));
                    case 25 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass12"));
                    case 26 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass13"));
                    case 27 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass14"));
                    case 28 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass15"));
                    case 29 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass16"));
                    case 30 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass17"));
                    case 31 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass18"));
                    case 32 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass19"));
                    case 33 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass20"));
                    case 34 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("weirdDarkGrass21"));
                    case 35 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeDarkStonesDarkGrass"));
                    case 36 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("bigGrassDarkGrass"));
                    case 37 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeGrassDarkGrass"));
                    case 38 -> darkGrassLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("twoDarkStonesDarkGrass"));
                }
            }
        }
        return darkGrassLayer;
    }

    private MapLayer createBridgesLayer() {
        int[][] idFromFile = readFileToIntInt("src/Entities/Map/TextMapLayers/bridges.txt");
        MapLayer bridgesLayer = new MapLayer(mapHeightTiles, mapWidthTiles);

        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                switch (idFromFile[i][j]) {
                    case 1 -> bridgesLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("leftBridge"));
                    case 2 -> bridgesLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("bridge"));
                    case 3 -> bridgesLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("rightBridge"));
                    case 4 -> bridgesLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterLeftBridge"));

                }
            }
        }
        return bridgesLayer;
    }

    private MapLayer createGroundDecorLayer() {
        int[][] idFromFile = readFileToIntInt("src/Entities/Map/TextMapLayers/groundDecor.txt");
        MapLayer groundDecorLayer = new MapLayer(mapHeightTiles, mapWidthTiles);

        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                switch (idFromFile[i][j]) {

                    // water decor
                    case 1 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterStone1"));
                    case 2 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterStone2"));
                    case 3 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterStone3"));
                    case 4 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterStone4"));
                    case 5 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("bigWaterStone1"));
                    case 6 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("bigWaterStone2"));
                    case 7 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("threeWaterSticks"));
                    case 8 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("fourWaterSticks"));
                    case 9 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterLily1"));
                    case 10 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterLily2"));
                    case 11 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterLily3"));
                    case 12 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("waterLily4"));

                    // grass decor
                    case 13 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("stone1"));
                    case 14 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("stone2"));
                    case 15 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("stone3"));
                    case 16 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("stone4"));
                    case 17 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("stone5"));
                    case 18 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("stone6"));
                    case 19 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassDecor1"));
                    case 20 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassDecor2"));
                    case 21 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassDecor3"));
                    case 22 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("grassDecor4"));

                    // paths
                    case 23 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathUp"));
                    case 24 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathVertical"));
                    case 25 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathBottom"));
                    case 26 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathLeft"));
                    case 27 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathHorizontal"));
                    case 28 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathRight"));
                    case 29 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathArc1"));
                    case 30 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathArc2"));
                    case 31 -> groundDecorLayer.tiles[i][j] = new StaticEntity(j * tileSize, i * tileSize, resourceHandler.mapTilesMap.get("pathArc3"));

                }
            }
        }
        return groundDecorLayer;
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
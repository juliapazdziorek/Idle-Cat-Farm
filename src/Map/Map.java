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
    private final ArrayList<MapLayer> mapBottomLayersToRender;
    private final ArrayList<MapLayer> mapTopLayersToRender;
    private final ArrayList<MapLayer> mapLayersToUpdate;

    // obstacles
    private final boolean[][] obstaclesGrid;
    private final ArrayList<Integer> obstaclesIds;
    
    // entities positions
    private final ArrayList<Point> bushPositions;

    public Map() {
        mapBottomLayersToRender = new ArrayList<>();
        mapTopLayersToRender = new ArrayList<>();
        mapLayersToUpdate = new ArrayList<>();
        bushPositions = new ArrayList<>();

        obstaclesGrid = new boolean[FocusFarm.mapHeightTiles][FocusFarm.mapWidthTiles];
        obstaclesIds = new ArrayList<>();

        createMapLayers();
        createObstaclesGrid();
    }
    
    public ArrayList<Point> getBushPositions() {
        return bushPositions;
    }

    private void createMapLayers() {

        // water layer
        MapLayer waterLayer = createLayer("src/Map/TileMaps/Ground/ground_water.txt");
        mapBottomLayersToRender.add(waterLayer);
        mapLayersToUpdate.add(waterLayer);

        // static ground layers
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_soil.txt")); // soil
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_grass.txt")); // grass
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_darkGrass.txt")); // dark grass
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_bridges.txt")); // bridges
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Ground/ground_groundDecor.txt")); // ground decor

        // lvl 0 temp
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Park/layer_first.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Park/layer_second.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Coop/layer_first.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Coop/layer_second.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Coop/layer_third.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Cows/layer_first.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Cows/layer_second.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Fields/layer_first.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Fields/layer_second.txt"));
        mapBottomLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/House/floor_first.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/House/layer_first.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/House/layer_second.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Orchard/layer_first.txt"));
        mapTopLayersToRender.add(createLayer("src/Map/TileMaps/Lvl0/Orchard/layer_second.txt"));


    }

    private MapLayer createLayer(String path) {
        int[][] tilesIds = readFileToGrid(path);
        MapLayer layer = new MapLayer(FocusFarm.mapHeightTiles, FocusFarm.mapWidthTiles);

        for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
            for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {

                if (tilesIds[i][j] != 0) {

                    // map entities

                    // bush positions
                    if (tilesIds[i][j] == 152) {
                        bushPositions.add(new Point(j * FocusFarm.tileSize, i * FocusFarm.tileSize));
                    }


                    // animated tiles
                    else if (FocusFarm.resourceHandler.animationsMap.containsKey(tilesIds[i][j])) {
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


    // handling obstacles
    private void createObstaclesGrid() {

        // initialize obstacles ids
        initializeObstaclesIds();

        // obstacles from ground layers
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_water.txt", true);
        setObstacleValueByLayer("src/Map/TileMaps/Ground/ground_bridges.txt", false);
        addObstaclesFromIdList("src/Map/TileMaps/Ground/ground_groundDecor.txt");

        // obstacles lvl 0 temp
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Park/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Park/layer_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Coop/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Coop/layer_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Coop/layer_third.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Cows/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Cows/layer_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Fields/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Fields/layer_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/House/floor_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/House/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/House/layer_second.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Orchard/layer_first.txt");
        addObstaclesFromIdList("src/Map/TileMaps/Lvl0/Orchard/layer_second.txt");

    }

    private void initializeObstaclesIds() {
        Collections.addAll(obstaclesIds,
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
            159, // large tree bottom-left
            160, // large tree bottom-center
            161, // large tree bottom-right
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
            172, // big shroomy log rig
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
            204, // wall bottom-left corner
            205, // wall up-left big corner
            206, // wall bottom-left big corner
            207, // wall up-right big corner
            208, // wall bottom-right big corner
            209, // window
            237, // small coop 1
            238, // small coop 2
            239, // small coop 3
            240, // small coop 4
            241, // small coop 5
            242, // small coop 6
            243, // big coop 1
            244, // big coop 2
            245, // big coop 3
            246, // big coop 4
            247, // big coop 5
            248, // big coop 6
            249, // big coop 7
            250, // big coop 8
            251, // big coop 9
            252, // large coop 1
            253, // large coop 2
            254, // large coop 3
            255, // large coop 4
            256, // large coop 5
            257, // large coop 6
            258, // large coop 7
            259, // large coop 8
            260, // large coop 9
            261, // large coop 10
            262, // large coop 11
            263, // large coop 12
            264, // large coop 13
            265, // large coop 14
            266, // large coop 15
            267, // large coop 16
            268, // large coop 17
            269, // large coop 18
            270, // large coop 19
            271, // large coop 20
            272, // box
            273, // two boxes top-left
            274, // two boxes top-right
            275, // two boxes bottom-left
            276, // two boxes bottom-right
            277, // hay
            278, // big hay left
            279, // big hay right
            282, // water tray left
            283, // water tray right
            285, // mailbox bottom
            287, // work station left-bottom
            289, // work station right-bottom
            291, // water well left-bottom
            293, // water well right-bottom
            303, // piknik basket
            305, // flower pot
            307, // pink bed bottom
            308, // blue bed bottom
            309, // green bed bottom
            311, // green upside down bed bottom
            312, // chair left
            313, // chair down
            314, // dresser
            315  // table
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
        //mapLayersToUpdate.forEach(MapLayer::update);
        mapTopLayersToRender.forEach(MapLayer::update);
    }

    public void renderBottom(Graphics2D graphics2D) {
        // rendering ground and floor layers
        mapBottomLayersToRender.forEach(layer -> layer.render(graphics2D));
    }

    public void renderTop(Graphics2D graphics2D) {
        // rendering top layers
        mapTopLayersToRender.forEach(layer -> layer.render(graphics2D));
    }
}
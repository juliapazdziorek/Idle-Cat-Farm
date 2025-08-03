package Map;

import Game.FocusFarm;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;

public class MapFileUtils {

    // read grid from a file
    public static int[][] readFileToGrid(String filePath) {
        int[][] grid = new int[FocusFarm.mapHeightTiles][FocusFarm.mapWidthTiles];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    String[] values = line.split("\\s+");

                    for (int j = 0; j < Math.min(values.length, FocusFarm.mapWidthTiles); j++) {
                        grid[i][j] = Integer.parseInt(values[j]);
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            return createEmptyGrid();
        } catch (Exception exception) {
            throw new RuntimeException("Error reading grid from file " + filePath + ": " + exception.getMessage());
        }

        return grid;
    }


    // write grid to file
    public static void writeGridToFile(int[][] grid, String filePath) {
        try {

            Path parentDir = Paths.get(filePath).getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
                    StringBuilder line = new StringBuilder();
                    for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {
                        line.append(grid[i][j]);
                        if (j < FocusFarm.mapWidthTiles - 1) {
                            line.append(" ");
                        }
                    }
                    writer.println(line);
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("Error writing grid to file " + filePath + ": " + exception.getMessage());
        }
    }


    // create empty layer current layers files (filled with zeros)
    public static void prepareEmptyLayerFiles() {
        writeGridToFile(createEmptyGrid(), "src/Map/CurrentTileMaps/floor_first.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/CurrentTileMaps/floor_second.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/CurrentTileMaps/layer_first.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/CurrentTileMaps/layer_second.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/CurrentTileMaps/layer_third.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/CurrentTileMaps/roof.txt");
    }

    private static int[][] createEmptyGrid() {
        return new int[FocusFarm.mapHeightTiles][FocusFarm.mapWidthTiles];
    }


    // copy non-zero values from source area/level to the current tile map file
    public static void copyAreaLayer(String areaPath, String levelPath, String layerFileName) {
        String sourcePath = "src/Map/TileMaps/" + levelPath + "/" + areaPath + "/" + layerFileName;
        String targetPath = "src/Map/CurrentTileMaps/" + layerFileName;

        try {
            int[][] sourceGrid = readFileToGrid(sourcePath);
            int[][] targetGrid = readFileToGrid(targetPath);
            for (int i = 0; i < FocusFarm.mapHeightTiles; i++) {
                for (int j = 0; j < FocusFarm.mapWidthTiles; j++) {
                    if (sourceGrid[i][j] != 0) {
                        targetGrid[i][j] = sourceGrid[i][j];
                    }
                }
            }

            writeGridToFile(targetGrid, targetPath);

        } catch (Exception exception) {
            if (!new File(sourcePath).exists()) {
                return;
            }

            throw new RuntimeException("Error copying area layer from " + sourcePath + " to " + targetPath + ": " + exception.getMessage());
        }
    }


    // load obstacle IDs
    public static void loadObstaclesIds() {
        String filePath = "src/Map/Obstacles/obstacle_ids.txt";
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                if (line.contains("//")) {
                    String[] parts = line.split("//", 2);
                    int obstacleId = Integer.parseInt(parts[0].trim());
                    Map.obstaclesIds.add(obstacleId);
                } else {
                    int obstacleId = Integer.parseInt(line);
                    Map.obstaclesIds.add(obstacleId);
                }
            }
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("Error loading obstacles form file " + filePath + ": " + exception.getMessage());
        }
    }
}

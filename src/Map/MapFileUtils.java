package Map;

import Game.Farm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MapFileUtils {

    // read grid from a file
    public static int[][] readFileToGrid(String filePath) {
        int[][] grid = new int[Farm.mapHeightTiles][Farm.mapWidthTiles];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            for (int i = 0; i < Farm.mapHeightTiles; i++) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    String[] values = line.split("\\s+");

                    for (int j = 0; j < Math.min(values.length, Farm.mapWidthTiles); j++) {
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
                for (int i = 0; i < Farm.mapHeightTiles; i++) {
                    StringBuilder line = new StringBuilder();
                    for (int j = 0; j < Farm.mapWidthTiles; j++) {
                        line.append(grid[i][j]);
                        if (j < Farm.mapWidthTiles - 1) {
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
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/floor_first.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/floor_second.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/layer_first.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/layer_second.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/layer_third.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/roof.txt");
    }

    private static int[][] createEmptyGrid() {
        return new int[Farm.mapHeightTiles][Farm.mapWidthTiles];
    }


    // copy non-zero values from source area/level to the current tile map file
    public static void copyAreaLayer(String areaPath, String levelPath, String layerFileName) {
        String sourcePath = "src/Map/TileMaps/AreasTemplates/" + levelPath + "/" + areaPath + "/" + layerFileName;
        String targetPath = "src/Map/TileMaps/InGameTileMaps/" + layerFileName;

        try {
            int[][] sourceGrid = readFileToGrid(sourcePath);
            int[][] targetGrid = readFileToGrid(targetPath);
            for (int i = 0; i < Farm.mapHeightTiles; i++) {
                for (int j = 0; j < Farm.mapWidthTiles; j++) {
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
}

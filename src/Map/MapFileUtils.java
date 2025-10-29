package Map;

import Game.Farm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * File I/O utilities for map data persistence and template management.
 * Handles reading, writing, and copying of tile grid configurations between files.
 */
public class MapFileUtils {

    /**
     * Loads a tile grid configuration from a text file into a two-dimensional array.
     * Creates an empty grid if the file is not found, throws exceptions for other errors.
     */
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

    /**
     * Saves a tile grid configuration to a text file with automatic directory creation.
     * Formats grid data with space-separated values for easy parsing and readability.
     */
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

    /**
     * Creates empty tile map files for all current game layers filled with zeros.
     * Prepares a clean slate for map construction and area template application.
     */
    public static void prepareEmptyLayerFiles() {
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/floor_first.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/floor_second.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/layer_first.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/layer_second.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/layer_third.txt");
        writeGridToFile(createEmptyGrid(), "src/Map/TileMaps/InGameTileMaps/roof.txt");
    }

    /** Creates a new empty grid filled with zeros using current map dimensions. */
    private static int[][] createEmptyGrid() {
        return new int[Farm.mapHeightTiles][Farm.mapWidthTiles];
    }

    /**
     * Copies non-zero tile values from area template files to current game layer files.
     * Merges area-specific layouts into the active map without overwriting existing tiles.
     */
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

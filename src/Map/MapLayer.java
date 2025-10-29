package Map;

import Entities.Entity;

import java.awt.*;

/**
 * Individual rendering layer for the game world tile system.
 * Manages entity placement, updates, and rendering for a specific map depth level.
 */
public class MapLayer {

    public Entity[][] tiles;
    private final int mapHeightTiles;
    private final int mapWidthTiles;

    /**
     * Creates a new map layer with specified dimensions and initializes the tile grid.
     * Prepares an empty layer ready for entity placement and rendering operations.
     */
    public MapLayer(int mapHeightTiles, int mapWidthTiles) {
        this.mapHeightTiles = mapHeightTiles;
        this.mapWidthTiles = mapWidthTiles;
        tiles = new Entity[mapHeightTiles][mapWidthTiles];
    }

    /** Updates all entities within this layer by calling their individual update methods. */
    public void update() {
        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                if (tiles[i][j] != null ) {
                    tiles[i][j].update();
                }
            }
        }
    }

    /** Renders all entities in this layer to the graphics context in proper tile order. */
    public void render(Graphics2D graphics2D) {
        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {

                if (tiles[i][j] != null) {
                    tiles[i][j].render(graphics2D);
                }
            }
        }
    }
}

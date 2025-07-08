package Map;

import Entities.Entity;

import java.awt.*;

public class MapLayer {

    public Entity[][] tiles;
    private final int mapHeightTiles;
    private final int mapWidthTiles;

    public MapLayer(int mapHeightTiles, int mapWidthTiles) {
        this.mapHeightTiles = mapHeightTiles;
        this.mapWidthTiles = mapWidthTiles;
        tiles = new Entity[mapHeightTiles][mapWidthTiles];
    }


    // tiles handling
    public void setTile(int row, int col, Entity entity) {
        if (row >= 0 && row < mapHeightTiles && col >= 0 && col < mapWidthTiles) {
            tiles[row][col] = entity;
        }
    }

    public Entity getTile(int row, int col) {
        if (row >= 0 && row < mapHeightTiles && col >= 0 && col < mapWidthTiles) {
            return tiles[row][col];
        }
        return null;
    }


    // updating & rendering
    public void update() {
        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                if (tiles[i][j] != null ) {
                    tiles[i][j].update();
                }
            }
        }
    }

    public void render(Graphics2D graphics2D, int cameraX, int cameraY, int scale, int scaledTileSize) {
        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {

                if (tiles[i][j] != null) {
                    tiles[i][j].render(graphics2D, cameraX, cameraY, scale, scaledTileSize);
                }
            }
        }
    }
}

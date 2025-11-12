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

    public void update() {
        for (int i = 0; i < mapHeightTiles; i++) {
            for (int j = 0; j < mapWidthTiles; j++) {
                if (tiles[i][j] != null ) {
                    tiles[i][j].update();
                }
            }
        }
    }

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

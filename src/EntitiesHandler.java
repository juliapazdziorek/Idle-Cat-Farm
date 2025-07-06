import java.awt.*;
import Entities.Map.Map;

public class EntitiesHandler {

    public Map map;

    public EntitiesHandler() {
        map = new Map(FocusFarm.mapHeightTiles, FocusFarm.mapWidthTiles, FocusFarm.tileSize, FocusFarm.resourceHandler);
    }


    // updating & rendering
    public void update() {
        map.update();
    }

    public void render(Graphics2D graphics2D) {
        map.render(graphics2D, FocusFarm.camera.cameraX, FocusFarm.camera.cameraY, FocusFarm.scale, FocusFarm.scaledTileSize);
    }
}

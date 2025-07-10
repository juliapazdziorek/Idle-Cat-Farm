package Game;

import java.awt.*;

import Map.Map;

public class EntitiesHandler {

    public Map map;

    public EntitiesHandler() {
        map = new Map();
    }


    // updating & rendering
    public void update() {
        map.update();
    }

    public void render(Graphics2D graphics2D) {
        map.render(graphics2D);
    }
}

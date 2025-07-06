package Resources;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class ResourceHandler {

    private final Map<String, BufferedImage> imageMap;

    public ResourceHandler() {
        imageMap = new HashMap<>();
        loadResources();
    }

    private void loadResources() {
        addToMap(imageMap, "src/Resources/SproutLands/water.png", "water");
        addToMap(imageMap, "src/Resources/SproutLands/soil.png", "soil");
        addToMap(imageMap, "src/Resources/SproutLands/grassWater.png", "grassWater");
        addToMap(imageMap, "src/Resources/SproutLands/grass.png", "grass");
        addToMap(imageMap, "src/Resources/SproutLands/darkGrass.png", "darkGrass");
        addToMap(imageMap, "src/Resources/SproutLands/bridges.png", "bridges");
        addToMap(imageMap, "src/Resources/SproutLands/waterDecor.png", "waterDecor");
        addToMap(imageMap, "src/Resources/SproutLands/grassDecor.png", "grassDecor");
        addToMap(imageMap, "src/Resources/SproutLands/paths.png", "paths");
    }


    // creating animations
    public Animation createWaterAnimation() {
        return new Animation(imageMap.get("water"), 4, 1, 0, 4, 20);
    }


    // maps handling
    private void addToMap(Map<String, BufferedImage> map, String path, String key) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            map.put(key, image);

        } catch (IOException exception) {
            throw new RuntimeException("Problem with creating BufferedImage from file: " + path + "\n" + exception.getMessage());
        }
    }


    // getters
    public BufferedImage getImage(String key) {
        return imageMap.get(key);
    }

    public Animation getWaterAnimation() {
        return createWaterAnimation();
    }
}

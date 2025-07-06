package Resources;

import Entities.AnimatedEntity;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class ResourceHandler {

    private Map<String, BufferedImage> imageMap;

    public ResourceHandler() {
        imageMap = new HashMap<>();
        loadResources();
    }

    private void loadResources() {}

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
}

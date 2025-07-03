import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.awt.image.BufferedImage;

public class ResourceHandler {

    public ResourceHandler() {}

    private void addToMap(Map<String, BufferedImage> map, String path, String key) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            map.put(key, image);

        } catch (IOException exception) {
            throw new RuntimeException("Problem with creating BufferImage from file: " + path + "\n" + exception.getMessage());
        }
    }
}

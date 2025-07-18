package Resources;

import Game.FocusFarm;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class ResourceHandler {

    // maps
    private final Map<String, BufferedImage> imageMap;
    public final Map<Integer, BufferedImage> mapTilesMap;


    public ResourceHandler() {
        imageMap = new HashMap<>();
        mapTilesMap = new HashMap<>();

        loadResources();
        initializeMapTilesMap();
    }


    // loading images from files
    private void loadResources() {

        // tiles
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/water.png", "water");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/soil.png", "soil");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/grass_water.png", "grassWater");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/grass_layer.png", "grass");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/dark_grass.png", "darkGrass");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/bridges.png", "bridges");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/paths.png", "paths");

        // decors & plants
        loadImageToMap(imageMap, "src/Resources/SproutLands/Nature/water_decor.png", "waterDecor");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Nature/grass_decor.png", "grassDecor");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Nature/trees.png", "trees");

        // farm cat
        loadImageToMap(imageMap, "src/Resources/SproutLands/Characters/farm_cat.png", "farmCat");
    }

    private void loadImageToMap(Map<String, BufferedImage> map, String path, String key) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            map.put(key, image);

        } catch (IOException exception) {
            throw new RuntimeException("Problem with creating BufferedImage from file: " + path + "\n" + exception.getMessage());
        }
    }


    private void initializeMapTilesMap() {

        // soil
        BufferedImage soilSheet = imageMap.get("soil");
        mapTilesMap.put(2, soilSheet.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // plain soil
        mapTilesMap.put(3, soilSheet.getSubimage(2 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // dark sand soil
        mapTilesMap.put(4, soilSheet.getSubimage(4 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two dark stones soil
        mapTilesMap.put(5, soilSheet.getSubimage(3 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three dark stones soil
        mapTilesMap.put(6, soilSheet.getSubimage(2 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light sand soil
        mapTilesMap.put(7, soilSheet.getSubimage(3 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two light stones soil
        mapTilesMap.put(8, soilSheet.getSubimage(4 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three light stones soil

        // grass water
        BufferedImage grassWaterSheet = imageMap.get("grassWater");
        mapTilesMap.put(9, grassWaterSheet.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // plain grass
        mapTilesMap.put(10, grassWaterSheet.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // up grass water
        mapTilesMap.put(11, grassWaterSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left grass water
        mapTilesMap.put(12, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right grass water
        mapTilesMap.put(13, grassWaterSheet.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom grass water
        mapTilesMap.put(14, grassWaterSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up corner grass water
        mapTilesMap.put(15, grassWaterSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom corner grass water
        mapTilesMap.put(16, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up corner grass water
        mapTilesMap.put(17, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom corner grass water
        mapTilesMap.put(18, grassWaterSheet.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up inner grass water
        mapTilesMap.put(19, grassWaterSheet.getSubimage(6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom inner grass water
        mapTilesMap.put(20, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up inner grass water
        mapTilesMap.put(21, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom inner grass water
        mapTilesMap.put(22, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 1
        mapTilesMap.put(23, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 2
        mapTilesMap.put(24, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 3
        mapTilesMap.put(25, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 4
        mapTilesMap.put(26, grassWaterSheet.getSubimage(9 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 5
        mapTilesMap.put(27, grassWaterSheet.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 6
        mapTilesMap.put(28, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 7
        mapTilesMap.put(29, grassWaterSheet.getSubimage(0, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green decor grass
        mapTilesMap.put(30, grassWaterSheet.getSubimage(FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green two flowers grass
        mapTilesMap.put(31, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green three flowers grass
        mapTilesMap.put(32, grassWaterSheet.getSubimage(0, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light decor grass
        mapTilesMap.put(33, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light decor one flower grass
        mapTilesMap.put(34, grassWaterSheet.getSubimage(FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light decor two flowers grass
        mapTilesMap.put(35, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two dark rocks grass
        mapTilesMap.put(36, grassWaterSheet.getSubimage(4 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three dark rocks grass
        mapTilesMap.put(37, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two light rocks grass
        mapTilesMap.put(38, grassWaterSheet.getSubimage(4 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three light rocks grass
        mapTilesMap.put(39, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three flowers grass
        mapTilesMap.put(40, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two flowers grass

        // grass layer
        BufferedImage grassSheet = imageMap.get("grass");
        mapTilesMap.put(41, grassSheet.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom grass layer
        mapTilesMap.put(42, grassSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left grass layer
        mapTilesMap.put(43, grassSheet.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right grass layer
        mapTilesMap.put(44, grassSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up corner grass layer
        mapTilesMap.put(45, grassSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom corner grass layer
        mapTilesMap.put(46, grassSheet.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up corner grass layer
        mapTilesMap.put(47, grassSheet.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom corner grass layer
        mapTilesMap.put(48, grassSheet.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up inner grass layer
        mapTilesMap.put(49, grassSheet.getSubimage(6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom inner grass layer
        mapTilesMap.put(50, grassSheet.getSubimage(5 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up inner grass layer
        mapTilesMap.put(51, grassSheet.getSubimage(5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom inner grass layer
        mapTilesMap.put(52, grassSheet.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 1
        mapTilesMap.put(53, grassSheet.getSubimage(4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 2
        mapTilesMap.put(54, grassSheet.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 3
        mapTilesMap.put(55, grassSheet.getSubimage(7 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 4
        mapTilesMap.put(56, grassSheet.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 5
        mapTilesMap.put(57, grassSheet.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 6

        // dark grass
        BufferedImage darkGrassSheet = imageMap.get("darkGrass");
        mapTilesMap.put(58, darkGrassSheet.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // plain dark grass
        mapTilesMap.put(59, darkGrassSheet.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // up dark grass
        mapTilesMap.put(60, darkGrassSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left dark grass
        mapTilesMap.put(61, darkGrassSheet.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom dark grass
        mapTilesMap.put(62, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right dark grass
        mapTilesMap.put(63, darkGrassSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up corner dark grass
        mapTilesMap.put(64, darkGrassSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom corner dark grass
        mapTilesMap.put(65, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up corner dark grass
        mapTilesMap.put(66, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom corner dark grass
        mapTilesMap.put(67, darkGrassSheet.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // up-left inner dark grass
        mapTilesMap.put(68, darkGrassSheet.getSubimage(6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom-left inner dark grass
        mapTilesMap.put(69, darkGrassSheet.getSubimage(5 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // up-right inner dark grass
        mapTilesMap.put(70, darkGrassSheet.getSubimage(5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom-right inner dark grass
        mapTilesMap.put(71, darkGrassSheet.getSubimage(5 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 1
        mapTilesMap.put(72, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 2
        mapTilesMap.put(73, darkGrassSheet.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 3
        mapTilesMap.put(74, darkGrassSheet.getSubimage(6 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 4
        mapTilesMap.put(75, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 5
        mapTilesMap.put(76, darkGrassSheet.getSubimage(7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 6
        mapTilesMap.put(77, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 7
        mapTilesMap.put(78, darkGrassSheet.getSubimage(7 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 8
        mapTilesMap.put(79, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 9
        mapTilesMap.put(80, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 10
        mapTilesMap.put(81, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 11
        mapTilesMap.put(82, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 12
        mapTilesMap.put(83, darkGrassSheet.getSubimage(10 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 13
        mapTilesMap.put(84, darkGrassSheet.getSubimage(FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 14
        mapTilesMap.put(85, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 15
        mapTilesMap.put(86, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 16
        mapTilesMap.put(87, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 17
        mapTilesMap.put(88, darkGrassSheet.getSubimage(10 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 18
        mapTilesMap.put(89, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 19
        mapTilesMap.put(90, darkGrassSheet.getSubimage(7 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 20
        mapTilesMap.put(91, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 21
        mapTilesMap.put(92, darkGrassSheet.getSubimage(0, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green decor dark grass
        mapTilesMap.put(93, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green three flowers dark grass
        mapTilesMap.put(94, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three dark stones dark grass
        mapTilesMap.put(95, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two dark stones dark grass

        // bridges
        BufferedImage bridgesSheet = imageMap.get("bridges");
        mapTilesMap.put(96, bridgesSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left bridge
        mapTilesMap.put(97, bridgesSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // horizontal bridge
        mapTilesMap.put(98, bridgesSheet.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right bridge
        mapTilesMap.put(99, bridgesSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // water left bridge

        // paths
        BufferedImage paths = imageMap.get("paths");
        mapTilesMap.put(100, paths.getSubimage( 0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // path up
        mapTilesMap.put(101, paths.getSubimage( 0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path vertical
        mapTilesMap.put(102, paths.getSubimage( 0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path bottom
        mapTilesMap.put(103, paths.getSubimage( FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path left
        mapTilesMap.put(104, paths.getSubimage( 2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path horizontal
        mapTilesMap.put(105, paths.getSubimage( 3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path right
        mapTilesMap.put(106, paths.getSubimage( FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path arc 1
        mapTilesMap.put(107, paths.getSubimage( 2 * FocusFarm.tileSize, 2 *FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path arc 2
        mapTilesMap.put(108, paths.getSubimage( FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path arc 3

        // water decor
        BufferedImage waterDecor = imageMap.get("waterDecor");
        mapTilesMap.put(109, waterDecor.getSubimage( 0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 1
        mapTilesMap.put(110, waterDecor.getSubimage( FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 2
        mapTilesMap.put(111, waterDecor.getSubimage( 2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 3
        mapTilesMap.put(112, waterDecor.getSubimage( 3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 4
        mapTilesMap.put(113, waterDecor.getSubimage( 4 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // big water stone 1
        mapTilesMap.put(114, waterDecor.getSubimage( 5 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // big water stone 2
        mapTilesMap.put(115, waterDecor.getSubimage( 6 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // three water sticks
        mapTilesMap.put(116, waterDecor.getSubimage( 7 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // four water sticks
        mapTilesMap.put(117, waterDecor.getSubimage( 8 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 1
        mapTilesMap.put(118, waterDecor.getSubimage( 9 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 2
        mapTilesMap.put(119, waterDecor.getSubimage( 10 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 3
        mapTilesMap.put(120, waterDecor.getSubimage( 11 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 4

        // grass decor, shrooms & flowers
        BufferedImage grassDecor = imageMap.get("grassDecor");
        mapTilesMap.put(121, grassDecor.getSubimage( 3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // small shroom
        mapTilesMap.put(122, grassDecor.getSubimage( 4 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // two small shrooms
        mapTilesMap.put(123, grassDecor.getSubimage( 5 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // big shroom
        mapTilesMap.put(124, grassDecor.getSubimage( 6 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // poison shroom
        mapTilesMap.put(125, grassDecor.getSubimage( 0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 1
        mapTilesMap.put(126, grassDecor.getSubimage( FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 2
        mapTilesMap.put(127, grassDecor.getSubimage( 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 3
        mapTilesMap.put(128, grassDecor.getSubimage( 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 4
        mapTilesMap.put(129, grassDecor.getSubimage( 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stone 1
        mapTilesMap.put(130, grassDecor.getSubimage( 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stone 2
        mapTilesMap.put(131, grassDecor.getSubimage( 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone top-left
        mapTilesMap.put(132, grassDecor.getSubimage( 7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone top-right
        mapTilesMap.put(133, grassDecor.getSubimage( 6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone bottom-left
        mapTilesMap.put(134, grassDecor.getSubimage( 7 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone bottom-right
        mapTilesMap.put(135, grassDecor.getSubimage( 8 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone top-left
        mapTilesMap.put(136, grassDecor.getSubimage( 9 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone top-right
        mapTilesMap.put(137, grassDecor.getSubimage( 8 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone bottom-left
        mapTilesMap.put(138, grassDecor.getSubimage( 9 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone bottom-right
        mapTilesMap.put(139, grassDecor.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 1
        mapTilesMap.put(140, grassDecor.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 2
        mapTilesMap.put(141, grassDecor.getSubimage( 2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 3
        mapTilesMap.put(142, grassDecor.getSubimage( 3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 4
        mapTilesMap.put(143, grassDecor.getSubimage( 0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // yellow flower 1
        mapTilesMap.put(144, grassDecor.getSubimage( FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // yellow flower 2
        mapTilesMap.put(145, grassDecor.getSubimage( 2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // yellow flower 3
        mapTilesMap.put(146, grassDecor.getSubimage( 3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // yellow flower 4 top
        mapTilesMap.put(147, grassDecor.getSubimage( 3 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // yellow flower 4 bottom
        mapTilesMap.put(148, grassDecor.getSubimage( 0, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // violet flower 1
        mapTilesMap.put(149, grassDecor.getSubimage( FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // violet flower 2
        mapTilesMap.put(150, grassDecor.getSubimage( 2 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // violet flower 2
        mapTilesMap.put(151, grassDecor.getSubimage( 4 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink flower 1
        mapTilesMap.put(152, grassDecor.getSubimage( 5 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink flower 2
        mapTilesMap.put(153, grassDecor.getSubimage( 6 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink flower 3
        mapTilesMap.put(154, grassDecor.getSubimage( 7 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink flower 4
        mapTilesMap.put(155, grassDecor.getSubimage( 4 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue flower 1
        mapTilesMap.put(156, grassDecor.getSubimage( 5 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue flower 2
        mapTilesMap.put(157, grassDecor.getSubimage( 6 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue flower 3
        mapTilesMap.put(158, grassDecor.getSubimage( 7 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue flower 4
        mapTilesMap.put(159, grassDecor.getSubimage( 9 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // white flower 1
        mapTilesMap.put(160, grassDecor.getSubimage( 10 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // white flower 2
        mapTilesMap.put(161, grassDecor.getSubimage( 11 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // white flower 3
        mapTilesMap.put(162, grassDecor.getSubimage( 10 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // dark blue flower 1
        mapTilesMap.put(163, grassDecor.getSubimage( 11 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // dark blue flower 2

        // trees, bushes & stumps
        BufferedImage trees = imageMap.get("trees");
        mapTilesMap.put(164, trees.getSubimage( 0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // small tree top
        mapTilesMap.put(165, trees.getSubimage( 0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small tree bottom
    }


    // animation factories
    public Animation createWaterAnimation() {
        return new Animation(imageMap.get("water"), 4, 1, 0, 4, 20);
    }

    public HashMap<String, Animation> createCatAnimationMap() {

        HashMap<String, Animation> catAnimationMap = new HashMap<>();

        // standing
        catAnimationMap.put("farmCatStandingDown", new Animation(imageMap.get("farmCat"), 8, 24, 0, 8, 10));
        catAnimationMap.put("farmCatStandingUp", new Animation(imageMap.get("farmCat"), 8, 24, 1, 8, 10));
        catAnimationMap.put("farmCatStandingRight", new Animation(imageMap.get("farmCat"), 8, 24, 2, 8, 10));
        catAnimationMap.put("farmCatStandingLeft", new Animation(imageMap.get("farmCat"), 8, 24, 3, 8, 10));

        // walking
        catAnimationMap.put("farmCatWalkingDown", new Animation(imageMap.get("farmCat"), 8, 24, 4, 8, 10));
        catAnimationMap.put("farmCatWalkingUp", new Animation(imageMap.get("farmCat"), 8, 24, 5, 8, 10));
        catAnimationMap.put("farmCatWalkingRight", new Animation(imageMap.get("farmCat"), 8, 24, 6, 8, 10));
        catAnimationMap.put("farmCatWalkingLeft", new Animation(imageMap.get("farmCat"), 8, 24, 7, 8, 10));

        // running
        catAnimationMap.put("farmCatRunningDown", new Animation(imageMap.get("farmCat"), 8, 24, 8, 8, 10));
        catAnimationMap.put("farmCatRunningUp", new Animation(imageMap.get("farmCat"), 8, 24, 9, 8, 10));
        catAnimationMap.put("farmCatRunningRight", new Animation(imageMap.get("farmCat"), 8, 24, 10, 8, 10));
        catAnimationMap.put("farmCatRunningLeft", new Animation(imageMap.get("farmCat"), 8, 24, 11, 8, 10));

        // tilling
        catAnimationMap.put("farmCatTillingDown", new Animation(imageMap.get("farmCat"), 8, 24, 12, 8, 10));
        catAnimationMap.put("farmCatTillingUp", new Animation(imageMap.get("farmCat"), 8, 24, 13, 8, 10));
        catAnimationMap.put("farmCatTillingRight", new Animation(imageMap.get("farmCat"), 8, 24, 14, 8, 10));
        catAnimationMap.put("farmCatTillingLeft", new Animation(imageMap.get("farmCat"), 8, 24, 15, 8, 10));

        // chopping
        catAnimationMap.put("farmCatChoppingDown", new Animation(imageMap.get("farmCat"), 8, 24, 16, 8, 10));
        catAnimationMap.put("farmCatChoppingUp", new Animation(imageMap.get("farmCat"), 8, 24, 17, 8, 10));
        catAnimationMap.put("farmCatChoppingRight", new Animation(imageMap.get("farmCat"), 8, 24, 18, 8, 10));
        catAnimationMap.put("farmCatChoppingLeft", new Animation(imageMap.get("farmCat"), 8, 24, 19, 8, 10));

        // watering
        catAnimationMap.put("farmCatWateringDown", new Animation(imageMap.get("farmCat"), 8, 24, 20, 8, 10));
        catAnimationMap.put("farmCatWateringUp", new Animation(imageMap.get("farmCat"), 8, 24, 21, 8, 10));
        catAnimationMap.put("farmCatWateringRight", new Animation(imageMap.get("farmCat"), 8, 24, 22, 8, 10));
        catAnimationMap.put("farmCatWateringLeft", new Animation(imageMap.get("farmCat"), 8, 24, 23, 8, 10));

        return catAnimationMap;
    }
}

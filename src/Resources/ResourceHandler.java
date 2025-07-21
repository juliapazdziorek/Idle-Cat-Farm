package Resources;

import Game.FocusFarm;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.util.function.Supplier;

public class ResourceHandler {

    // maps
    private final Map<String, BufferedImage> imageMap;
    public final Map<Integer, BufferedImage> tilesMap;
    public final Map<Integer, Supplier<Animation>> animationsMap;
    public final Map<String, List<BufferedImage>> entitiesResourcesMap;

    public ResourceHandler() {
        imageMap = new HashMap<>();
        tilesMap = new HashMap<>();
        animationsMap = new HashMap<>();
        entitiesResourcesMap = new HashMap<>();

        loadResources();
        initializeTilesMap();
        initializeAnimationMap();
        initializeEntitiesResourcesMap();
    }


    // loading images from files
    private void loadResources() {

        // map tiles
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/water.png", "water");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/soil.png", "soil");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/grass_water.png", "grassWater");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/grass_layer.png", "grass");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/dark_grass.png", "darkGrass");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/bridges.png", "bridges");
        loadImageToMap(imageMap, "src/Resources/SproutLands/MapTiles/paths.png", "paths");

        // nature
        loadImageToMap(imageMap, "src/Resources/SproutLands/Nature/water_decor.png", "waterDecor");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Nature/grass_decor.png", "grassDecor");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Nature/trees.png", "trees");

        // building parts
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/fences.png", "fences");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/gates.png", "gates");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/walls.png", "walls");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/coop.png", "coop");

        // objects
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/barn_structures.png", "barnStructures");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/work_station.png", "workStation");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/water_well.png", "waterWell");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/piknik_blanket.png", "piknikBlanket");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/piknik_basket.png", "piknikBasket");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/furniture.png", "furniture");

        // characters
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


    // static tiles
    private void initializeTilesMap() {

        // map tiles

        // soil
        BufferedImage soilSheet = imageMap.get("soil");
        tilesMap.put(2, soilSheet.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // plain soil
        tilesMap.put(3, soilSheet.getSubimage(2 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // dark sand soil
        tilesMap.put(4, soilSheet.getSubimage(4 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two dark stones soil
        tilesMap.put(5, soilSheet.getSubimage(3 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three dark stones soil
        tilesMap.put(6, soilSheet.getSubimage(2 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light sand soil
        tilesMap.put(7, soilSheet.getSubimage(3 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two light stones soil
        tilesMap.put(8, soilSheet.getSubimage(4 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three light stones soil

        // grass water
        BufferedImage grassWaterSheet = imageMap.get("grassWater");
        tilesMap.put(9, grassWaterSheet.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // plain grass
        tilesMap.put(10, grassWaterSheet.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // up grass water
        tilesMap.put(11, grassWaterSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left grass water
        tilesMap.put(12, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right grass water
        tilesMap.put(13, grassWaterSheet.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom grass water
        tilesMap.put(14, grassWaterSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up corner grass water
        tilesMap.put(15, grassWaterSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom corner grass water
        tilesMap.put(16, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up corner grass water
        tilesMap.put(17, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom corner grass water
        tilesMap.put(18, grassWaterSheet.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up inner grass water
        tilesMap.put(19, grassWaterSheet.getSubimage(6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom inner grass water
        tilesMap.put(20, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up inner grass water
        tilesMap.put(21, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom inner grass water
        tilesMap.put(22, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 1
        tilesMap.put(23, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 2
        tilesMap.put(24, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 3
        tilesMap.put(25, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 4
        tilesMap.put(26, grassWaterSheet.getSubimage(9 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 5
        tilesMap.put(27, grassWaterSheet.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 6
        tilesMap.put(28, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass water 7
        tilesMap.put(29, grassWaterSheet.getSubimage(0, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green decor grass
        tilesMap.put(30, grassWaterSheet.getSubimage(FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green two flowers grass
        tilesMap.put(31, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green three flowers grass
        tilesMap.put(32, grassWaterSheet.getSubimage(0, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light decor grass
        tilesMap.put(33, grassWaterSheet.getSubimage(2 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light decor one flower grass
        tilesMap.put(34, grassWaterSheet.getSubimage(FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // light decor two flowers grass
        tilesMap.put(35, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two dark rocks grass
        tilesMap.put(36, grassWaterSheet.getSubimage(4 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three dark rocks grass
        tilesMap.put(37, grassWaterSheet.getSubimage(3 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two light rocks grass
        tilesMap.put(38, grassWaterSheet.getSubimage(4 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three light rocks grass
        tilesMap.put(39, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three flowers grass
        tilesMap.put(40, grassWaterSheet.getSubimage(5 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two flowers grass

        // grass layer
        BufferedImage grassSheet = imageMap.get("grass");
        tilesMap.put(41, grassSheet.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom grass layer
        tilesMap.put(42, grassSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left grass layer
        tilesMap.put(43, grassSheet.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right grass layer
        tilesMap.put(44, grassSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up corner grass layer
        tilesMap.put(45, grassSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom corner grass layer
        tilesMap.put(46, grassSheet.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up corner grass layer
        tilesMap.put(47, grassSheet.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom corner grass layer
        tilesMap.put(48, grassSheet.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up inner grass layer
        tilesMap.put(49, grassSheet.getSubimage(6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom inner grass layer
        tilesMap.put(50, grassSheet.getSubimage(5 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up inner grass layer
        tilesMap.put(51, grassSheet.getSubimage(5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom inner grass layer
        tilesMap.put(52, grassSheet.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 1
        tilesMap.put(53, grassSheet.getSubimage(4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 2
        tilesMap.put(54, grassSheet.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 3
        tilesMap.put(55, grassSheet.getSubimage(7 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 4
        tilesMap.put(56, grassSheet.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 5
        tilesMap.put(57, grassSheet.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition grass layer 6

        // dark grass
        BufferedImage darkGrassSheet = imageMap.get("darkGrass");
        tilesMap.put(58, darkGrassSheet.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // plain dark grass
        tilesMap.put(59, darkGrassSheet.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // up dark grass
        tilesMap.put(60, darkGrassSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left dark grass
        tilesMap.put(61, darkGrassSheet.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom dark grass
        tilesMap.put(62, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right dark grass
        tilesMap.put(63, darkGrassSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left-up corner dark grass
        tilesMap.put(64, darkGrassSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left-bottom corner dark grass
        tilesMap.put(65, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right-up corner dark grass
        tilesMap.put(66, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right-bottom corner dark grass
        tilesMap.put(67, darkGrassSheet.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // up-left inner dark grass
        tilesMap.put(68, darkGrassSheet.getSubimage(6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom-left inner dark grass
        tilesMap.put(69, darkGrassSheet.getSubimage(5 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // up-right inner dark grass
        tilesMap.put(70, darkGrassSheet.getSubimage(5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bottom-right inner dark grass
        tilesMap.put(71, darkGrassSheet.getSubimage(5 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 1
        tilesMap.put(72, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 2
        tilesMap.put(73, darkGrassSheet.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 3
        tilesMap.put(74, darkGrassSheet.getSubimage(6 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 4
        tilesMap.put(75, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 5
        tilesMap.put(76, darkGrassSheet.getSubimage(7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 6
        tilesMap.put(77, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 7
        tilesMap.put(78, darkGrassSheet.getSubimage(7 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 8
        tilesMap.put(79, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 9
        tilesMap.put(80, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 10
        tilesMap.put(81, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 11
        tilesMap.put(82, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 12
        tilesMap.put(83, darkGrassSheet.getSubimage(10 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 13
        tilesMap.put(84, darkGrassSheet.getSubimage(FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 14
        tilesMap.put(85, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 15
        tilesMap.put(86, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 16
        tilesMap.put(87, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 17
        tilesMap.put(88, darkGrassSheet.getSubimage(10 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 18
        tilesMap.put(89, darkGrassSheet.getSubimage(9 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 19
        tilesMap.put(90, darkGrassSheet.getSubimage(7 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 20
        tilesMap.put(91, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // special transition dark grass 21
        tilesMap.put(92, darkGrassSheet.getSubimage(0, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green decor dark grass
        tilesMap.put(93, darkGrassSheet.getSubimage(2 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green three flowers dark grass
        tilesMap.put(94, darkGrassSheet.getSubimage(4 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // three dark stones dark grass
        tilesMap.put(95, darkGrassSheet.getSubimage(3 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two dark stones dark grass

        // bridges
        BufferedImage bridgesSheet = imageMap.get("bridges");
        tilesMap.put(96, bridgesSheet.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // left bridge
        tilesMap.put(97, bridgesSheet.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // horizontal bridge
        tilesMap.put(98, bridgesSheet.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // right bridge
        tilesMap.put(99, bridgesSheet.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // water left bridge

        // paths
        BufferedImage paths = imageMap.get("paths");
        tilesMap.put(100, paths.getSubimage( 0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // path up
        tilesMap.put(101, paths.getSubimage( 0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path vertical
        tilesMap.put(102, paths.getSubimage( 0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path bottom
        tilesMap.put(103, paths.getSubimage( FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path left
        tilesMap.put(104, paths.getSubimage( 2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path horizontal
        tilesMap.put(105, paths.getSubimage( 3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path right
        tilesMap.put(106, paths.getSubimage( FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path arc 1
        tilesMap.put(107, paths.getSubimage( 2 * FocusFarm.tileSize, 2 *FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path arc 2
        tilesMap.put(108, paths.getSubimage( FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // path arc 3

        // nature

        // water decor
        BufferedImage waterDecor = imageMap.get("waterDecor");
        tilesMap.put(109, waterDecor.getSubimage( 0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 1
        tilesMap.put(110, waterDecor.getSubimage( FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 2
        tilesMap.put(111, waterDecor.getSubimage( 2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 3
        tilesMap.put(112, waterDecor.getSubimage( 3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water stone 4
        tilesMap.put(113, waterDecor.getSubimage( 4 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // big water stone 1
        tilesMap.put(114, waterDecor.getSubimage( 5 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // big water stone 2
        tilesMap.put(115, waterDecor.getSubimage( 6 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // three water sticks
        tilesMap.put(116, waterDecor.getSubimage( 7 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // four water sticks
        tilesMap.put(117, waterDecor.getSubimage( 8 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 1
        tilesMap.put(118, waterDecor.getSubimage( 9 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 2
        tilesMap.put(119, waterDecor.getSubimage( 10 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 3
        tilesMap.put(120, waterDecor.getSubimage( 11 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water lily 4

        // grass decor
        BufferedImage grassDecor = imageMap.get("grassDecor");
        tilesMap.put(121, grassDecor.getSubimage( 3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // small shroom
        tilesMap.put(122, grassDecor.getSubimage( 4 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // two small shrooms
        tilesMap.put(123, grassDecor.getSubimage( 5 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // big shroom
        tilesMap.put(124, grassDecor.getSubimage( 6 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // poison shroom
        tilesMap.put(125, grassDecor.getSubimage( 0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 1
        tilesMap.put(126, grassDecor.getSubimage( FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 2
        tilesMap.put(127, grassDecor.getSubimage( 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 3
        tilesMap.put(128, grassDecor.getSubimage( 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stone 4
        tilesMap.put(129, grassDecor.getSubimage( 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stone 1
        tilesMap.put(130, grassDecor.getSubimage( 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stone 2
        tilesMap.put(131, grassDecor.getSubimage( 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone top-left
        tilesMap.put(132, grassDecor.getSubimage( 7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone top-right
        tilesMap.put(133, grassDecor.getSubimage( 6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone bottom-left
        tilesMap.put(134, grassDecor.getSubimage( 7 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big stone bottom-right
        tilesMap.put(135, grassDecor.getSubimage( 8 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone top-left
        tilesMap.put(136, grassDecor.getSubimage( 9 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone top-right
        tilesMap.put(137, grassDecor.getSubimage( 8 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone bottom-left
        tilesMap.put(138, grassDecor.getSubimage( 9 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large stone bottom-right
        tilesMap.put(139, grassDecor.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 1
        tilesMap.put(140, grassDecor.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 2
        tilesMap.put(141, grassDecor.getSubimage( 2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 3
        tilesMap.put(142, grassDecor.getSubimage( 3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small bush 4

        // trees
        BufferedImage trees = imageMap.get("trees");
        tilesMap.put(150, trees.getSubimage( 0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // small tree top
        tilesMap.put(151, trees.getSubimage( 0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small tree bottom
        tilesMap.put(153, trees.getSubimage( 9 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree top-left
        tilesMap.put(154, trees.getSubimage( 10 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree top-center
        tilesMap.put(155, trees.getSubimage( 11 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree top-right
        tilesMap.put(156, trees.getSubimage( 9 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree middle-left
        tilesMap.put(157, trees.getSubimage( 10 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree middle-center
        tilesMap.put(158, trees.getSubimage( 11 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree middle-right
        tilesMap.put(159, trees.getSubimage( 9 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree bottom-left
        tilesMap.put(160, trees.getSubimage( 10 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree bottom-center
        tilesMap.put(161, trees.getSubimage( 11 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large tree bottom-right
        tilesMap.put(162, trees.getSubimage( 0, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stump 1
        tilesMap.put(163, trees.getSubimage( FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small stump 2
        tilesMap.put(164, trees.getSubimage( 2 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stump 1 left
        tilesMap.put(165, trees.getSubimage( 3 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stump 1 right
        tilesMap.put(166, trees.getSubimage( 4 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stump 2 left
        tilesMap.put(167, trees.getSubimage( 5 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // stump 2 right
        tilesMap.put(168, trees.getSubimage( 6 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small log
        tilesMap.put(169, trees.getSubimage( 7 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big log left
        tilesMap.put(170, trees.getSubimage( 8 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big log right
        tilesMap.put(171, trees.getSubimage( 9 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big shroomy log left
        tilesMap.put(172, trees.getSubimage( 10 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big shroomy log right

        // building parts

        // fences
        BufferedImage fences = imageMap.get("fences");
        tilesMap.put(182, fences.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // fence up
        tilesMap.put(183, fences.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // fence vertical
        tilesMap.put(184, fences.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // fence bottom
        tilesMap.put(185, fences.getSubimage(FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // fence left
        tilesMap.put(186, fences.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // fence horizontal
        tilesMap.put(187, fences.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // fence right
        tilesMap.put(188, fences.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // single fence
        tilesMap.put(189, fences.getSubimage(6 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // single fence broken small
        tilesMap.put(190, fences.getSubimage(5 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // single fence broken
        tilesMap.put(191, fences.getSubimage(5 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // left fence broken
        tilesMap.put(192, fences.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // right fence broken

        // gates
        BufferedImage gates = imageMap.get("gates");
        tilesMap.put(193, gates.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // gate horizontal left
        tilesMap.put(196, gates.getSubimage(3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // gate horizontal right
        tilesMap.put(197, gates.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // gate vertical up
        tilesMap.put(200, gates.getSubimage(0, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // gate vertical down

        // walls
        BufferedImage walls = imageMap.get("walls");
        tilesMap.put(201, walls.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // wall front
        tilesMap.put(202, walls.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // wall left
        tilesMap.put(203, walls.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // wall right
        tilesMap.put(204, walls.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // wall bottom-left corner
        tilesMap.put(205, walls.getSubimage(3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // wall up-left big corner
        tilesMap.put(206, walls.getSubimage(3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // wall bottom-left big corner
        tilesMap.put(207, walls.getSubimage(4 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // wall up-right big corner
        tilesMap.put(208, walls.getSubimage(4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // wall bottom-right big corner
        tilesMap.put(209, walls.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // window
        tilesMap.put(210, walls.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // floor

        // coop
        BufferedImage coop = imageMap.get("coop");
        tilesMap.put(237, coop.getSubimage(10 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small coop 1
        tilesMap.put(238, coop.getSubimage(11 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small coop 2
        tilesMap.put(239, coop.getSubimage(10 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small coop 3
        tilesMap.put(240, coop.getSubimage(11 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small coop 4
        tilesMap.put(241, coop.getSubimage(10 * FocusFarm.tileSize, 7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small coop 5
        tilesMap.put(242, coop.getSubimage(11 * FocusFarm.tileSize, 7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // small coop 6
        tilesMap.put(243, coop.getSubimage(0, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 1
        tilesMap.put(244, coop.getSubimage(FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 2
        tilesMap.put(245, coop.getSubimage(2 * FocusFarm.tileSize, 5* FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 3
        tilesMap.put(246, coop.getSubimage(0, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 4
        tilesMap.put(247, coop.getSubimage(FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 5
        tilesMap.put(248, coop.getSubimage(2 * FocusFarm.tileSize, 6 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 6
        tilesMap.put(249, coop.getSubimage(0, 7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 7
        tilesMap.put(250, coop.getSubimage(FocusFarm.tileSize, 7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 8
        tilesMap.put(251, coop.getSubimage(2 * FocusFarm.tileSize, 7 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big coop 9
        tilesMap.put(252, coop.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 1
        tilesMap.put(253, coop.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 2
        tilesMap.put(254, coop.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 3
        tilesMap.put(255, coop.getSubimage(3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 4
        tilesMap.put(256, coop.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 5
        tilesMap.put(257, coop.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 6
        tilesMap.put(258, coop.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 7
        tilesMap.put(259, coop.getSubimage(3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 8
        tilesMap.put(260, coop.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 9
        tilesMap.put(261, coop.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 10
        tilesMap.put(262, coop.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 11
        tilesMap.put(263, coop.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 12
        tilesMap.put(264, coop.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 13
        tilesMap.put(265, coop.getSubimage(FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 14
        tilesMap.put(266, coop.getSubimage(2 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 15
        tilesMap.put(267, coop.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 16
        tilesMap.put(268, coop.getSubimage(0, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 17
        tilesMap.put(269, coop.getSubimage(FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 18
        tilesMap.put(270, coop.getSubimage(2 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 19
        tilesMap.put(271, coop.getSubimage(3 * FocusFarm.tileSize, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // large coop 20

        // objects

        // barn structures
        BufferedImage barnStructures = imageMap.get("barnStructures");
        tilesMap.put(272, barnStructures.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // box
        tilesMap.put(273, barnStructures.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // two boxes top-left
        tilesMap.put(274, barnStructures.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // two boxes top-right
        tilesMap.put(275, barnStructures.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two boxes bottom-left
        tilesMap.put(276, barnStructures.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // two boxes bottom-right
        tilesMap.put(277, barnStructures.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // hay
        tilesMap.put(278, barnStructures.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big hay left
        tilesMap.put(279, barnStructures.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big hay right
        tilesMap.put(280, barnStructures.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // patch of hay
        tilesMap.put(281, barnStructures.getSubimage(FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // big patch of hay

        // work station
        BufferedImage workStation = imageMap.get("workStation");
        tilesMap.put(286, workStation.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // work station left-up
        tilesMap.put(287, workStation.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // work station left-bottom
        tilesMap.put(288, workStation.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // work station right-up
        tilesMap.put(289, workStation.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // work station right-bottom

        // water well
        BufferedImage waterWell = imageMap.get("waterWell");
        tilesMap.put(290, waterWell.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water well left-up
        tilesMap.put(291, waterWell.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // water well left-bottom
        tilesMap.put(292, waterWell.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // water well right-up
        tilesMap.put(293, waterWell.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // water well right-bottom

        // piknik blanket
        BufferedImage piknikBlanket = imageMap.get("piknikBlanket");
        tilesMap.put(294, piknikBlanket.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 1
        tilesMap.put(295, piknikBlanket.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 2
        tilesMap.put(296, piknikBlanket.getSubimage(2 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 3
        tilesMap.put(297, piknikBlanket.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 4
        tilesMap.put(298, piknikBlanket.getSubimage(FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 5
        tilesMap.put(299, piknikBlanket.getSubimage(2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 6
        tilesMap.put(300, piknikBlanket.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 7
        tilesMap.put(301, piknikBlanket.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 8
        tilesMap.put(302, piknikBlanket.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik blanket 9

        // piknik basket
        BufferedImage piknikBasket = imageMap.get("piknikBasket");
        tilesMap.put(303, piknikBasket.getSubimage(0, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // piknik basket

        // furniture
        BufferedImage furniture = imageMap.get("furniture");
        tilesMap.put(304, furniture.getSubimage(FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // painting
        tilesMap.put(305, furniture.getSubimage(3 * FocusFarm.tileSize, 0, FocusFarm.tileSize, FocusFarm.tileSize)); // flower pot
        tilesMap.put(306, furniture.getSubimage(0, FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bed top
        tilesMap.put(307, furniture.getSubimage(2 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink bed bottom
        tilesMap.put(308, furniture.getSubimage(FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue bed bottom
        tilesMap.put(309, furniture.getSubimage(0, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green bed bottom
        tilesMap.put(310, furniture.getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green upside down bed top
        tilesMap.put(311, furniture.getSubimage(0, 4 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green upside down bed bottom
        tilesMap.put(312, furniture.getSubimage(4 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // chair left
        tilesMap.put(313, furniture.getSubimage(6 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // chair down
        tilesMap.put(314, furniture.getSubimage(3 * FocusFarm.tileSize, 2 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // dresser
        tilesMap.put(315, furniture.getSubimage(3 * FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // table
        tilesMap.put(316, furniture.getSubimage(5 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink big carpet left
        tilesMap.put(317, furniture.getSubimage(6 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink big carpet right
        tilesMap.put(319, furniture.getSubimage(7 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue big carpet right
        tilesMap.put(318, furniture.getSubimage(8 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue big carpet left
        tilesMap.put(320, furniture.getSubimage(3 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green big carpet left
        tilesMap.put(321, furniture.getSubimage(4 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green big carpet right
        tilesMap.put(322, furniture.getSubimage(FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // pink small carpet
        tilesMap.put(323, furniture.getSubimage(2 * FocusFarm.tileSize, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // blue small carpet
        tilesMap.put(324, furniture.getSubimage(0, 5 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // green small carpet
    }


    // animations
    private void initializeAnimationMap() {
        animationsMap.put(1, this::createWaterAnimation); // water
    }

    // animation factories
    private Animation createWaterAnimation() {
        return new Animation(imageMap.get("water"), 4, 1, 0, 4, 20);
    }

//    private Animation createBushAnimation() {
//        return new Animation(imageMap.get("trees"), 12, 7, 3, 2, 25 * ((int)(Math.random() * 6) + 5));
//    }

//    private Animation createTreeTopLeftAnimation() {
//        return new Animation(imageMap.get("tree"), 12, 7, 0, 2, 100 * ((int)(Math.random() * 6) + 5));
//    }

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


    // entities resources
    private void initializeEntitiesResourcesMap() {

        // bush
        ArrayList<BufferedImage> bushList = new ArrayList<>();
        bushList.add(imageMap.get("trees").getSubimage(FocusFarm.tileSize, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bush grown
        bushList.add(imageMap.get("trees").getSubimage(0, 3 * FocusFarm.tileSize, FocusFarm.tileSize, FocusFarm.tileSize)); // bush grown
        entitiesResourcesMap.put("bush", bushList);

    }

}

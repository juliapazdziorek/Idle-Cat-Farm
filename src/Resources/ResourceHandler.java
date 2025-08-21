package Resources;

import Game.Farm;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.image.BufferedImage;

public class ResourceHandler {

    // maps
    private final Map<String, BufferedImage> imageMap;
    public final Map<Integer, BufferedImage> tilesMap;
    public final Map<String, Map<String, BufferedImage>> entitiesResourcesMap;
    public final Map<String, BufferedImage> iconsMap;

    // animation factory
    public final AnimationFactory animationFactory;

    public ResourceHandler() {
        imageMap = new HashMap<>();
        tilesMap = new HashMap<>();
        entitiesResourcesMap = new HashMap<>();
        iconsMap = new HashMap<>();

        loadResources();
        initializeTilesMap();
        initializeEntitiesResourcesMap();
        initializeIconsMap();

        // animations
        animationFactory = new AnimationFactory(imageMap);
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
        loadImageToMap(imageMap, "src/Resources/SproutLands/Nature/tree.png", "tree");

        // building parts
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/fences.png", "fences");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/gates.png", "gates");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/walls.png", "walls");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/door.png", "doors");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/roof.png", "roof");
        loadImageToMap(imageMap, "src/Resources/SproutLands/BuildingParts/coop.png", "coop");

        // objects
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/barn_structures.png", "barnStructures");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/water_tray.png", "waterTrays");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/work_station.png", "workStation");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/water_well.png", "waterWell");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/piknik_blanket.png", "piknikBlanket");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/piknik_basket.png", "piknikBasket");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/furniture.png", "furniture");
        loadImageToMap(imageMap, "src/Resources/SproutLands/Objects/signs.png", "signs");

        // characters
        loadImageToMap(imageMap, "src/Resources/SproutLands/Characters/farm_cat.png", "farmCat");

        // icons
        loadImageToMap(imageMap, "src/Resources/SproutLands/UI/items.png", "items");
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
        tilesMap.put(2, soilSheet.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // plain soil
        tilesMap.put(3, soilSheet.getSubimage(2 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // dark sand soil
        tilesMap.put(4, soilSheet.getSubimage(4 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two dark stones soil
        tilesMap.put(5, soilSheet.getSubimage(3 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // three dark stones soil
        tilesMap.put(6, soilSheet.getSubimage(2 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // light sand soil
        tilesMap.put(7, soilSheet.getSubimage(3 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two light stones soil
        tilesMap.put(8, soilSheet.getSubimage(4 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // three light stones soil

        // grass water
        BufferedImage grassWaterSheet = imageMap.get("grassWater");
        tilesMap.put(9, grassWaterSheet.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // plain grass
        tilesMap.put(10, grassWaterSheet.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // up grass water
        tilesMap.put(11, grassWaterSheet.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left grass water
        tilesMap.put(12, grassWaterSheet.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right grass water
        tilesMap.put(13, grassWaterSheet.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bottom grass water
        tilesMap.put(14, grassWaterSheet.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // left-up corner grass water
        tilesMap.put(15, grassWaterSheet.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left-bottom corner grass water
        tilesMap.put(16, grassWaterSheet.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // right-up corner grass water
        tilesMap.put(17, grassWaterSheet.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right-bottom corner grass water
        tilesMap.put(18, grassWaterSheet.getSubimage(6 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left-up inner grass water
        tilesMap.put(19, grassWaterSheet.getSubimage(6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left-bottom inner grass water
        tilesMap.put(20, grassWaterSheet.getSubimage(5 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right-up inner grass water
        tilesMap.put(21, grassWaterSheet.getSubimage(5 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right-bottom inner grass water
        tilesMap.put(22, grassWaterSheet.getSubimage(5 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass water 1
        tilesMap.put(23, grassWaterSheet.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass water 2
        tilesMap.put(24, grassWaterSheet.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass water 3
        tilesMap.put(25, grassWaterSheet.getSubimage(5 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass water 4
        tilesMap.put(26, grassWaterSheet.getSubimage(9 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass water 5
        tilesMap.put(27, grassWaterSheet.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass water 6
        tilesMap.put(28, grassWaterSheet.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass water 7
        tilesMap.put(29, grassWaterSheet.getSubimage(0, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green decor grass
        tilesMap.put(30, grassWaterSheet.getSubimage(Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green two flowers grass
        tilesMap.put(31, grassWaterSheet.getSubimage(2 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green three flowers grass
        tilesMap.put(32, grassWaterSheet.getSubimage(0, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // light decor grass
        tilesMap.put(33, grassWaterSheet.getSubimage(2 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // light decor one flower grass
        tilesMap.put(34, grassWaterSheet.getSubimage(Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // light decor two flowers grass
        tilesMap.put(35, grassWaterSheet.getSubimage(3 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two dark rocks grass
        tilesMap.put(36, grassWaterSheet.getSubimage(4 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // three dark rocks grass
        tilesMap.put(37, grassWaterSheet.getSubimage(3 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two light rocks grass
        tilesMap.put(38, grassWaterSheet.getSubimage(4 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // three light rocks grass
        tilesMap.put(39, grassWaterSheet.getSubimage(5 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // three flowers grass
        tilesMap.put(40, grassWaterSheet.getSubimage(5 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two flowers grass

        // grass layer
        BufferedImage grassSheet = imageMap.get("grass");
        tilesMap.put(41, grassSheet.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bottom grass layer
        tilesMap.put(42, grassSheet.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left grass layer
        tilesMap.put(43, grassSheet.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right grass layer
        tilesMap.put(44, grassSheet.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // left-up corner grass layer
        tilesMap.put(45, grassSheet.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left-bottom corner grass layer
        tilesMap.put(46, grassSheet.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // right-up corner grass layer
        tilesMap.put(47, grassSheet.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right-bottom corner grass layer
        tilesMap.put(48, grassSheet.getSubimage(6 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left-up inner grass layer
        tilesMap.put(49, grassSheet.getSubimage(6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left-bottom inner grass layer
        tilesMap.put(50, grassSheet.getSubimage(5 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right-up inner grass layer
        tilesMap.put(51, grassSheet.getSubimage(5 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right-bottom inner grass layer
        tilesMap.put(52, grassSheet.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass layer 1
        tilesMap.put(53, grassSheet.getSubimage(4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass layer 2
        tilesMap.put(54, grassSheet.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass layer 3
        tilesMap.put(55, grassSheet.getSubimage(7 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // special transition grass layer 4
        tilesMap.put(56, grassSheet.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass layer 5
        tilesMap.put(57, grassSheet.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition grass layer 6

        // dark grass
        BufferedImage darkGrassSheet = imageMap.get("darkGrass");
        tilesMap.put(58, darkGrassSheet.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // plain dark grass
        tilesMap.put(59, darkGrassSheet.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // up dark grass
        tilesMap.put(60, darkGrassSheet.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left dark grass
        tilesMap.put(61, darkGrassSheet.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bottom dark grass
        tilesMap.put(62, darkGrassSheet.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right dark grass
        tilesMap.put(63, darkGrassSheet.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // left-up corner dark grass
        tilesMap.put(64, darkGrassSheet.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left-bottom corner dark grass
        tilesMap.put(65, darkGrassSheet.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // right-up corner dark grass
        tilesMap.put(66, darkGrassSheet.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right-bottom corner dark grass
        tilesMap.put(67, darkGrassSheet.getSubimage(6 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // up-left inner dark grass
        tilesMap.put(68, darkGrassSheet.getSubimage(6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bottom-left inner dark grass
        tilesMap.put(69, darkGrassSheet.getSubimage(5 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // up-right inner dark grass
        tilesMap.put(70, darkGrassSheet.getSubimage(5 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bottom-right inner dark grass
        tilesMap.put(71, darkGrassSheet.getSubimage(5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // special transition dark grass 1
        tilesMap.put(72, darkGrassSheet.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 2
        tilesMap.put(73, darkGrassSheet.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 3
        tilesMap.put(74, darkGrassSheet.getSubimage(6 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // special transition dark grass 4
        tilesMap.put(75, darkGrassSheet.getSubimage(9 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // special transition dark grass 5
        tilesMap.put(76, darkGrassSheet.getSubimage(7 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 6
        tilesMap.put(77, darkGrassSheet.getSubimage(4 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 7
        tilesMap.put(78, darkGrassSheet.getSubimage(7 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // special transition dark grass 8
        tilesMap.put(79, darkGrassSheet.getSubimage(4 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 9
        tilesMap.put(80, darkGrassSheet.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 10
        tilesMap.put(81, darkGrassSheet.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 11
        tilesMap.put(82, darkGrassSheet.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // special transition dark grass 12
        tilesMap.put(83, darkGrassSheet.getSubimage(10 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 13
        tilesMap.put(84, darkGrassSheet.getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 14
        tilesMap.put(85, darkGrassSheet.getSubimage(9 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 15
        tilesMap.put(86, darkGrassSheet.getSubimage(9 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 16
        tilesMap.put(87, darkGrassSheet.getSubimage(4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // special transition dark grass 17
        tilesMap.put(88, darkGrassSheet.getSubimage(10 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 18
        tilesMap.put(89, darkGrassSheet.getSubimage(9 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 19
        tilesMap.put(90, darkGrassSheet.getSubimage(7 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 20
        tilesMap.put(91, darkGrassSheet.getSubimage(3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // special transition dark grass 21
        tilesMap.put(92, darkGrassSheet.getSubimage(0, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green decor dark grass
        tilesMap.put(93, darkGrassSheet.getSubimage(2 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green three flowers dark grass
        tilesMap.put(94, darkGrassSheet.getSubimage(4 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // three dark stones dark grass
        tilesMap.put(95, darkGrassSheet.getSubimage(3 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two dark stones dark grass

        // bridges
        BufferedImage bridgesSheet = imageMap.get("bridges");
        tilesMap.put(96, bridgesSheet.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // left bridge
        tilesMap.put(97, bridgesSheet.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // horizontal bridge
        tilesMap.put(98, bridgesSheet.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // right bridge
        tilesMap.put(99, bridgesSheet.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // water left bridge

        // paths
        BufferedImage paths = imageMap.get("paths");
        tilesMap.put(100, paths.getSubimage( 0, 0, Farm.tileSize, Farm.tileSize)); // path up
        tilesMap.put(101, paths.getSubimage( 0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path vertical
        tilesMap.put(102, paths.getSubimage( 0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path bottom
        tilesMap.put(103, paths.getSubimage( Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path left
        tilesMap.put(104, paths.getSubimage( 2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path horizontal
        tilesMap.put(105, paths.getSubimage( 3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path right
        tilesMap.put(106, paths.getSubimage( Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path arc 1
        tilesMap.put(107, paths.getSubimage( 2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path arc 2
        tilesMap.put(108, paths.getSubimage( Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // path arc 3

        // nature

        // water decor
        BufferedImage waterDecor = imageMap.get("waterDecor");
        tilesMap.put(109, waterDecor.getSubimage( 0, 0, Farm.tileSize, Farm.tileSize)); // water stone 1
        tilesMap.put(110, waterDecor.getSubimage( Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water stone 2
        tilesMap.put(111, waterDecor.getSubimage( 2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water stone 3
        tilesMap.put(112, waterDecor.getSubimage( 3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water stone 4
        tilesMap.put(113, waterDecor.getSubimage( 4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // big water stone 1
        tilesMap.put(114, waterDecor.getSubimage( 5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // big water stone 2
        tilesMap.put(115, waterDecor.getSubimage( 6 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // three water sticks
        tilesMap.put(116, waterDecor.getSubimage( 7 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // four water sticks
        tilesMap.put(117, waterDecor.getSubimage( 8 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water lily 1
        tilesMap.put(118, waterDecor.getSubimage( 9 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water lily 2
        tilesMap.put(119, waterDecor.getSubimage( 10 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water lily 3
        tilesMap.put(120, waterDecor.getSubimage( 11 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water lily 4

        // grass decor
        BufferedImage grassDecor = imageMap.get("grassDecor");
        tilesMap.put(121, grassDecor.getSubimage( 3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // small shroom
        tilesMap.put(122, grassDecor.getSubimage( 4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // two small shrooms
        tilesMap.put(123, grassDecor.getSubimage( 5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // big shroom
        tilesMap.put(124, grassDecor.getSubimage( 6 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // poison shroom
        tilesMap.put(125, grassDecor.getSubimage( 0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small stone 1
        tilesMap.put(126, grassDecor.getSubimage( Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small stone 2
        tilesMap.put(127, grassDecor.getSubimage( 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small stone 3
        tilesMap.put(128, grassDecor.getSubimage( 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small stone 4
        tilesMap.put(129, grassDecor.getSubimage( 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // stone 1
        tilesMap.put(130, grassDecor.getSubimage( 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // stone 2
        tilesMap.put(131, grassDecor.getSubimage( 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big stone top-left
        tilesMap.put(132, grassDecor.getSubimage( 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big stone top-right
        tilesMap.put(133, grassDecor.getSubimage( 6 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big stone bottom-left
        tilesMap.put(134, grassDecor.getSubimage( 7 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big stone bottom-right
        tilesMap.put(135, grassDecor.getSubimage( 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large stone top-left
        tilesMap.put(136, grassDecor.getSubimage( 9 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large stone top-right
        tilesMap.put(137, grassDecor.getSubimage( 8 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large stone bottom-left
        tilesMap.put(138, grassDecor.getSubimage( 9 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large stone bottom-right
        tilesMap.put(139, grassDecor.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small bush 1
        tilesMap.put(140, grassDecor.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small bush 2
        tilesMap.put(141, grassDecor.getSubimage( 2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small bush 3
        tilesMap.put(142, grassDecor.getSubimage( 3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small bush 4

        // trees
        BufferedImage trees = imageMap.get("trees");
        tilesMap.put(150, trees.getSubimage( 0, 0, Farm.tileSize, Farm.tileSize)); // small tree top
        tilesMap.put(151, trees.getSubimage( 0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small tree bottom
        tilesMap.put(153, trees.getSubimage( 9 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree top-left
        tilesMap.put(154, trees.getSubimage( 10 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree top-center
        tilesMap.put(155, trees.getSubimage( 11 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree top-right
        tilesMap.put(156, trees.getSubimage( 9 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree middle-left
        tilesMap.put(157, trees.getSubimage( 10 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree middle-center
        tilesMap.put(158, trees.getSubimage( 11 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree middle-right
        tilesMap.put(159, trees.getSubimage( 9 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree bottom-left
        tilesMap.put(160, trees.getSubimage( 10 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree bottom-center
        tilesMap.put(161, trees.getSubimage( 11 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large tree bottom-right
        tilesMap.put(162, trees.getSubimage( 0, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small stump 1
        tilesMap.put(163, trees.getSubimage( Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small stump 2
        tilesMap.put(164, trees.getSubimage( 2 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // stump 1 left
        tilesMap.put(165, trees.getSubimage( 3 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // stump 1 right
        tilesMap.put(166, trees.getSubimage( 4 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // stump 2 left
        tilesMap.put(167, trees.getSubimage( 5 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // stump 2 right
        tilesMap.put(168, trees.getSubimage( 6 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small log
        tilesMap.put(169, trees.getSubimage( 7 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big log left
        tilesMap.put(170, trees.getSubimage( 8 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big log right
        tilesMap.put(171, trees.getSubimage( 9 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big shroomy log left
        tilesMap.put(172, trees.getSubimage( 10 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big shroomy log right

        // building parts

        // fences
        BufferedImage fences = imageMap.get("fences");
        tilesMap.put(182, fences.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // fence up
        tilesMap.put(183, fences.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // fence vertical
        tilesMap.put(184, fences.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // fence bottom
        tilesMap.put(185, fences.getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // fence left
        tilesMap.put(186, fences.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // fence horizontal
        tilesMap.put(187, fences.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // fence right
        tilesMap.put(188, fences.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // single fence
        tilesMap.put(189, fences.getSubimage(6 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // single fence broken small
        tilesMap.put(190, fences.getSubimage(5 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // single fence broken
        tilesMap.put(191, fences.getSubimage(5 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // left fence broken
        tilesMap.put(192, fences.getSubimage(6 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // right fence broken

        // gates
        BufferedImage gates = imageMap.get("gates");
        tilesMap.put(193, gates.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // gate horizontal left
        tilesMap.put(196, gates.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // gate horizontal right

        // walls
        BufferedImage walls = imageMap.get("walls");
        tilesMap.put(205, walls.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // wall front
        tilesMap.put(206, walls.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // wall left
        tilesMap.put(207, walls.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // wall right
        tilesMap.put(208, walls.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // wall up-left corner
        tilesMap.put(209, walls.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // wall bottom-left corner
        tilesMap.put(210, walls.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // wall bottom-right corner
        tilesMap.put(211, walls.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // wall up-left big corner
        tilesMap.put(212, walls.getSubimage(3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // wall bottom-left big corner
        tilesMap.put(213, walls.getSubimage(4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // wall up-right big corner
        tilesMap.put(214, walls.getSubimage(4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // wall bottom-right big corner
        tilesMap.put(215, walls.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // window
        tilesMap.put(216, walls.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // floor

        // roof
        BufferedImage roof = imageMap.get("roof");
        tilesMap.put(221, roof.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // roof edge corner up-left
        tilesMap.put(222, roof.getSubimage( Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // roof edge up
        tilesMap.put(223, roof.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // roof edge corner up-right
        tilesMap.put(224, roof.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge up left
        tilesMap.put(225, roof.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle up
        tilesMap.put(226, roof.getSubimage(4 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle up light decor
        tilesMap.put(227, roof.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle up dark decor
        tilesMap.put(228, roof.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge up right
        tilesMap.put(229, roof.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle part edge left
        tilesMap.put(230, roof.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle part
        tilesMap.put(231, roof.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle part edge right
        tilesMap.put(232, roof.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge down left
        tilesMap.put(233, roof.getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle down
        tilesMap.put(234, roof.getSubimage(3 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle down light decor
        tilesMap.put(235, roof.getSubimage(4 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof middle down dark decor
        tilesMap.put(236, roof.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge down right
        tilesMap.put(237, roof.getSubimage(0, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge corner bottom-left
        tilesMap.put(238, roof.getSubimage(Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge down
        tilesMap.put(239, roof.getSubimage(2 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge corner bottom-right
        tilesMap.put(240, roof.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // roof edge inner corner up-left
        tilesMap.put(241, roof.getSubimage(4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // roof edge inner corner up-right
        tilesMap.put(242, roof.getSubimage(4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // roof edge inner corner down-right
        tilesMap.put(243, roof.getSubimage(5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // roof chimney

        // coop
        BufferedImage coop = imageMap.get("coop");
        tilesMap.put(244, coop.getSubimage(10 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small coop 1
        tilesMap.put(245, coop.getSubimage(11 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small coop 2
        tilesMap.put(246, coop.getSubimage(10 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small coop 3
        tilesMap.put(247, coop.getSubimage(11 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small coop 4
        tilesMap.put(248, coop.getSubimage(10 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small coop 5
        tilesMap.put(249, coop.getSubimage(11 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // small coop 6
        tilesMap.put(250, coop.getSubimage(0, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 1
        tilesMap.put(251, coop.getSubimage(Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 2
        tilesMap.put(252, coop.getSubimage(2 * Farm.tileSize, 5* Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 3
        tilesMap.put(253, coop.getSubimage(0, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 4
        tilesMap.put(254, coop.getSubimage(Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 5
        tilesMap.put(255, coop.getSubimage(2 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 6
        tilesMap.put(256, coop.getSubimage(0, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 7
        tilesMap.put(257, coop.getSubimage(Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 8
        tilesMap.put(258, coop.getSubimage(2 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big coop 9
        tilesMap.put(259, coop.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // large coop 1
        tilesMap.put(260, coop.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // large coop 2
        tilesMap.put(261, coop.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // large coop 3
        tilesMap.put(262, coop.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // large coop 4
        tilesMap.put(263, coop.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 5
        tilesMap.put(264, coop.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 6
        tilesMap.put(265, coop.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 7
        tilesMap.put(266, coop.getSubimage(3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 8
        tilesMap.put(267, coop.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 9
        tilesMap.put(268, coop.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 10
        tilesMap.put(269, coop.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 11
        tilesMap.put(270, coop.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 12
        tilesMap.put(271, coop.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 13
        tilesMap.put(272, coop.getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 14
        tilesMap.put(273, coop.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 15
        tilesMap.put(274, coop.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 16
        tilesMap.put(275, coop.getSubimage(0, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 17
        tilesMap.put(276, coop.getSubimage(Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 18
        tilesMap.put(277, coop.getSubimage(2 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 19
        tilesMap.put(278, coop.getSubimage(3 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // large coop 20

        // objects

        // barn structures
        BufferedImage barnStructures = imageMap.get("barnStructures");
        tilesMap.put(279, barnStructures.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // box
        tilesMap.put(280, barnStructures.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // two boxes top-left
        tilesMap.put(281, barnStructures.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // two boxes top-right
        tilesMap.put(282, barnStructures.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two boxes bottom-left
        tilesMap.put(283, barnStructures.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // two boxes bottom-right
        tilesMap.put(284, barnStructures.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // hay
        tilesMap.put(285, barnStructures.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big hay left
        tilesMap.put(286, barnStructures.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big hay right
        tilesMap.put(287, barnStructures.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // patch of hay
        tilesMap.put(288, barnStructures.getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // big patch of hay

        // work station
        BufferedImage workStation = imageMap.get("workStation");
        tilesMap.put(293, workStation.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // work station left-up
        tilesMap.put(294, workStation.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // work station left-bottom
        tilesMap.put(295, workStation.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // work station right-up
        tilesMap.put(296, workStation.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // work station right-bottom

        // water well
        BufferedImage waterWell = imageMap.get("waterWell");
        tilesMap.put(297, waterWell.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // water well left-up
        tilesMap.put(298, waterWell.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // water well left-bottom
        tilesMap.put(299, waterWell.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water well right-up
        tilesMap.put(300, waterWell.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // water well right-bottom

        // piknik blanket
        BufferedImage piknikBlanket = imageMap.get("piknikBlanket");
        tilesMap.put(301, piknikBlanket.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // piknik blanket 1
        tilesMap.put(302, piknikBlanket.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // piknik blanket 2
        tilesMap.put(303, piknikBlanket.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // piknik blanket 3
        tilesMap.put(304, piknikBlanket.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // piknik blanket 4
        tilesMap.put(305, piknikBlanket.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // piknik blanket 5
        tilesMap.put(306, piknikBlanket.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // piknik blanket 6
        tilesMap.put(307, piknikBlanket.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // piknik blanket 7
        tilesMap.put(308, piknikBlanket.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // piknik blanket 8
        tilesMap.put(309, piknikBlanket.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // piknik blanket 9

        // piknik basket
        BufferedImage piknikBasket = imageMap.get("piknikBasket");
        tilesMap.put(310, piknikBasket.getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // piknik basket

        // furniture
        BufferedImage furniture = imageMap.get("furniture");
        tilesMap.put(311, furniture.getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // painting
        tilesMap.put(312, furniture.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // flower pot
        tilesMap.put(313, furniture.getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bed top
        tilesMap.put(314, furniture.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // pink bed bottom
        tilesMap.put(315, furniture.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // blue bed bottom
        tilesMap.put(316, furniture.getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green bed bottom
        tilesMap.put(317, furniture.getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green upside down bed top
        tilesMap.put(318, furniture.getSubimage(0, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green upside down bed bottom
        tilesMap.put(319, furniture.getSubimage(4 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // chair left
        tilesMap.put(320, furniture.getSubimage(6 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // chair down
        tilesMap.put(321, furniture.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // dresser
        tilesMap.put(322, furniture.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // table
        tilesMap.put(323, furniture.getSubimage(5 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // pink big carpet left
        tilesMap.put(324, furniture.getSubimage(6 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // pink big carpet right
        tilesMap.put(325, furniture.getSubimage(7 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // blue big carpet left
        tilesMap.put(326, furniture.getSubimage(8 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // blue big carpet right
        tilesMap.put(327, furniture.getSubimage(3 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green big carpet left
        tilesMap.put(328, furniture.getSubimage(4 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green big carpet right
        tilesMap.put(329, furniture.getSubimage(Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // pink small carpet
        tilesMap.put(330, furniture.getSubimage(2 * Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // blue small carpet
        tilesMap.put(331, furniture.getSubimage(0, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // green small carpet
    }


    // entities resources
    private void initializeEntitiesResourcesMap() {

        // bush
        HashMap<String, BufferedImage> bushMap = new HashMap<>();
        bushMap.put("bush grown", imageMap.get("trees").getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bush grown
        bushMap.put("bush shrunken", imageMap.get("trees").getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // bush shrunken
        entitiesResourcesMap.put("bush", bushMap);

        // tree
        HashMap<String, BufferedImage> treeMap = new HashMap<>();
        treeMap.put("173", imageMap.get("tree").getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // tree top-left
        treeMap.put("174", imageMap.get("tree").getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // tree top-center
        treeMap.put("175", imageMap.get("tree").getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // tree top-right
        treeMap.put("176", imageMap.get("tree").getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // tree middle-left
        treeMap.put("177", imageMap.get("tree").getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // tree middle-center
        treeMap.put("178", imageMap.get("tree").getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // tree middle-right
        treeMap.put("179", imageMap.get("tree").getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // tree bottom-left
        treeMap.put("180", imageMap.get("tree").getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // tree bottom-center
        treeMap.put("181", imageMap.get("tree").getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // tree bottom-right
        entitiesResourcesMap.put("tree", treeMap);

        // entrances
        HashMap<String, BufferedImage> entrancesMap = new HashMap<>();
        entrancesMap.put("194open", imageMap.get("gates").getSubimage(17 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // gate horizontal left gate
        entrancesMap.put("195open", imageMap.get("gates").getSubimage(18 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // gate horizontal right gate
        entrancesMap.put("197open", imageMap.get("gates").getSubimage(8 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up
        entrancesMap.put("198open", imageMap.get("gates").getSubimage(9 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up front
        entrancesMap.put("199open", imageMap.get("gates").getSubimage(8 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up gate
        entrancesMap.put("200open", imageMap.get("gates").getSubimage(9 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up gate front
        entrancesMap.put("201open", imageMap.get("gates").getSubimage(8 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down gate
        entrancesMap.put("202open", imageMap.get("gates").getSubimage(9 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down gate front
        entrancesMap.put("203open", imageMap.get("gates").getSubimage(8 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down
        entrancesMap.put("204open", imageMap.get("gates").getSubimage(9 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down front
        entrancesMap.put("194closed", imageMap.get("gates").getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // gate horizontal left gate
        entrancesMap.put("195closed", imageMap.get("gates").getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // gate horizontal right gate
        entrancesMap.put("197closed", imageMap.get("gates").getSubimage(0, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up
        entrancesMap.put("198closed", imageMap.get("gates").getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up front
        entrancesMap.put("199closed", imageMap.get("gates").getSubimage(0, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up gate
        entrancesMap.put("200closed", imageMap.get("gates").getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical up gate front
        entrancesMap.put("201closed", imageMap.get("gates").getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down gate
        entrancesMap.put("202closed", imageMap.get("gates").getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down gate front
        entrancesMap.put("203closed", imageMap.get("gates").getSubimage(0, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down
        entrancesMap.put("204closed", imageMap.get("gates").getSubimage(Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // gate vertical down front
        entrancesMap.put("217open", imageMap.get("doors").getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // single door
        entrancesMap.put("218open", imageMap.get("doors").getSubimage(3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // double doors 1
        entrancesMap.put("219open", imageMap.get("doors").getSubimage(4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // double doors 2
        entrancesMap.put("220open", imageMap.get("doors").getSubimage(5 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // double doors 3
        entrancesMap.put("217closed", imageMap.get("doors").getSubimage(5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // single door
        entrancesMap.put("218closed", imageMap.get("doors").getSubimage(15 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // double doors 1
        entrancesMap.put("219closed", imageMap.get("doors").getSubimage(16 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // double doors 2
        entrancesMap.put("220closed", imageMap.get("doors").getSubimage(17 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // double doors 3
        entitiesResourcesMap.put("entrances", entrancesMap);

        // water trays
        HashMap<String, BufferedImage> waterTraysMap = new HashMap<>();
        waterTraysMap.put("289empty", imageMap.get("waterTrays").getSubimage(4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water tray left
        waterTraysMap.put("290empty", imageMap.get("waterTrays").getSubimage(5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water tray right
        waterTraysMap.put("289half", imageMap.get("waterTrays").getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water tray left
        waterTraysMap.put("290half", imageMap.get("waterTrays").getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water tray right
        waterTraysMap.put("289full", imageMap.get("waterTrays").getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // water tray left
        waterTraysMap.put("290full", imageMap.get("waterTrays").getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // water tray right
        entitiesResourcesMap.put("waterTrays", waterTraysMap);

        // signs
        HashMap<String, BufferedImage> signsMap = new HashMap<>();
        signsMap.put("empty", imageMap.get("signs").getSubimage(0, 0, Farm.tileSize, Farm.tileSize)); // empty sign
        signsMap.put("corn", imageMap.get("signs").getSubimage(Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // corn sign
        signsMap.put("carrot", imageMap.get("signs").getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // carrot sign
        signsMap.put("cauliflower", imageMap.get("signs").getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // cauliflower sign
        signsMap.put("tomato", imageMap.get("signs").getSubimage(4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // tomato sign
        signsMap.put("eggplant", imageMap.get("signs").getSubimage(5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize)); // eggplant sign
        signsMap.put("lettuce", imageMap.get("signs").getSubimage(Farm.tileSize,  Farm.tileSize, Farm.tileSize, Farm.tileSize)); // lettuce sign
        signsMap.put("wheat", imageMap.get("signs").getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // wheat sign
        signsMap.put("pumpkin", imageMap.get("signs").getSubimage(3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // pumpkin sign
        signsMap.put("radish", imageMap.get("signs").getSubimage(4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize)); // radish sign
        signsMap.put("star", imageMap.get("signs").getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // star sign
        signsMap.put("cucumber", imageMap.get("signs").getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // cucumber sign
        signsMap.put("apple", imageMap.get("signs").getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // apple sign
        signsMap.put("orange", imageMap.get("signs").getSubimage(4 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // orange sign
        signsMap.put("pear", imageMap.get("signs").getSubimage(5 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // pear sign
        signsMap.put("peach", imageMap.get("signs").getSubimage(0, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize)); // peach sign
        entitiesResourcesMap.put("signs", signsMap);
    }


    // icons
    private void initializeIconsMap() {

        BufferedImage icons = imageMap.get("items");
        iconsMap.put("corn", icons.getSubimage(Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("carrot", icons.getSubimage(Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("cauliflower", icons.getSubimage(Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("tomato", icons.getSubimage(Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("eggplant", icons.getSubimage(Farm.tileSize, 5 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("lettuce", icons.getSubimage(Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("wheat", icons.getSubimage(Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("pumpkin", icons.getSubimage(Farm.tileSize, 9 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("radish", icons.getSubimage(Farm.tileSize, 10 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("star", icons.getSubimage(Farm.tileSize, 13 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("cucumber", icons.getSubimage(Farm.tileSize, 14 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("apple", icons.getSubimage(2 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("orange", icons.getSubimage(2 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("pear", icons.getSubimage(2 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("peach", icons.getSubimage(2 * Farm.tileSize, 9 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("egg", icons.getSubimage(3 * Farm.tileSize, 11 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("milk", icons.getSubimage(7 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("chockMilk", icons.getSubimage(7 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        iconsMap.put("berryMilk", icons.getSubimage(7 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
    }
}

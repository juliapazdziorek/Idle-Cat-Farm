package Resources;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class ResourceHandler {

    // maps
    private final Map<String, BufferedImage> imageMap;
    public final Map<String, BufferedImage> mapTilesMap;

    // tiles properties
    private final int tileSize;

    public ResourceHandler(int tileSize) {
        this.tileSize = tileSize;
        imageMap = new HashMap<>();
        mapTilesMap = new HashMap<>();
        loadResources();
        initializeMapTilesMap();
    }

    private void loadResources() {

        // tiles
        loadImageToMap(imageMap, "src/Resources/SproutLands/water.png", "water");
        loadImageToMap(imageMap, "src/Resources/SproutLands/soil.png", "soil");
        loadImageToMap(imageMap, "src/Resources/SproutLands/grassWater.png", "grassWater");
        loadImageToMap(imageMap, "src/Resources/SproutLands/grass.png", "grass");
        loadImageToMap(imageMap, "src/Resources/SproutLands/darkGrass.png", "darkGrass");
        loadImageToMap(imageMap, "src/Resources/SproutLands/bridges.png", "bridges");
        loadImageToMap(imageMap, "src/Resources/SproutLands/waterDecor.png", "waterDecor");
        loadImageToMap(imageMap, "src/Resources/SproutLands/grassDecor.png", "grassDecor");
        loadImageToMap(imageMap, "src/Resources/SproutLands/paths.png", "paths");

        // farm cat
        loadImageToMap(imageMap, "src/Resources/SproutLands/farmCat.png", "farmCat");
    }

    private void initializeMapTilesMap() {

        // soil tiles
        BufferedImage soilSheet = imageMap.get("soil");
        mapTilesMap.put("plainSoil", soilSheet.getSubimage(tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("darkSand", soilSheet.getSubimage(2 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("threeDarkStones", soilSheet.getSubimage(3 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("twoDarkStones", soilSheet.getSubimage(4 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("whiteSand", soilSheet.getSubimage(2 * tileSize, 6 * tileSize, tileSize, tileSize));
        mapTilesMap.put("threeWhiteStones", soilSheet.getSubimage(3 * tileSize, 6 * tileSize, tileSize, tileSize));
        mapTilesMap.put("twoWhiteStones", soilSheet.getSubimage(4 * tileSize, 6 * tileSize, tileSize, tileSize));

        // water grass
        BufferedImage grassWaterSheet = imageMap.get("grassWater");
        mapTilesMap.put("upGrass", grassWaterSheet.getSubimage(tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("leftUpCornerWaterGrass", grassWaterSheet.getSubimage(0, 0, tileSize, tileSize));
        mapTilesMap.put("leftBottomCornerWaterGrass", grassWaterSheet.getSubimage(0, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("rightUpCornerWaterGrass", grassWaterSheet.getSubimage(2 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("rightBottomCornerWaterGrass", grassWaterSheet.getSubimage(2 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("leftWaterGrass", grassWaterSheet.getSubimage(0, tileSize, tileSize, tileSize));
        mapTilesMap.put("rightWaterGrass", grassWaterSheet.getSubimage(2 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("bottomWaterGrass", grassWaterSheet.getSubimage(tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("leftUpInnerWaterGrass", grassWaterSheet.getSubimage(6 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("leftBottomInnerWaterGrass", grassWaterSheet.getSubimage(6 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("rightUpInnerWaterGrass", grassWaterSheet.getSubimage(5 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("rightBottomInnerWaterGrass", grassWaterSheet.getSubimage(5 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("grassTile", grassWaterSheet.getSubimage(tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdWaterGrass1", grassWaterSheet.getSubimage(5 * tileSize, 4 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdWaterGrass2", grassWaterSheet.getSubimage(2 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdWaterGrass3", grassWaterSheet.getSubimage(3 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdWaterGrass4", grassWaterSheet.getSubimage(5 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdWaterGrass5", grassWaterSheet.getSubimage(9 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdWaterGrass6", grassWaterSheet.getSubimage(0, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdWaterGrass7", grassWaterSheet.getSubimage(3 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("bigGrassGreen", grassWaterSheet.getSubimage(0, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("smallerGrassGreen", grassWaterSheet.getSubimage(tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("smallGrassGreen", grassWaterSheet.getSubimage(2 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("twoDarkRocksGrass", grassWaterSheet.getSubimage(3 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("threeDarkRocksGrass", grassWaterSheet.getSubimage(4 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("threeFlowersGrass", grassWaterSheet.getSubimage(5 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("bigGrassLight", grassWaterSheet.getSubimage(0, 6 * tileSize, tileSize, tileSize));
        mapTilesMap.put("smallerGrassLight", grassWaterSheet.getSubimage(2 * tileSize, 6 * tileSize, tileSize, tileSize));
        mapTilesMap.put("smallGrassLight", grassWaterSheet.getSubimage(tileSize, 6 * tileSize, tileSize, tileSize));
        mapTilesMap.put("twoLightRocksGrass", grassWaterSheet.getSubimage(3 * tileSize, 6 * tileSize, tileSize, tileSize));
        mapTilesMap.put("threeLightRocksGrass", grassWaterSheet.getSubimage(4 * tileSize, 6 * tileSize, tileSize, tileSize));
        mapTilesMap.put("twoFlowersGrass", grassWaterSheet.getSubimage(5 * tileSize, 6 * tileSize, tileSize, tileSize));

        // grass
        BufferedImage grassSheet = imageMap.get("grass");
        mapTilesMap.put("grassLayerBottom", grassSheet.getSubimage(tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerLeft", grassSheet.getSubimage(0, tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerRight", grassSheet.getSubimage(2 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerUpLeftCorner", grassSheet.getSubimage(0, 0, tileSize, tileSize));
        mapTilesMap.put("grassLayerBottomLeftCorner", grassSheet.getSubimage(0, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerUpRightCorner", grassSheet.getSubimage(2 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("grassLayerBottomRightCorner", grassSheet.getSubimage(2 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerUpLeftInner", grassSheet.getSubimage(6 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerBottomLeftInner", grassSheet.getSubimage(6 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerUpRightInner", grassSheet.getSubimage(5 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassLayerBottomRightInner", grassSheet.getSubimage(5 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdGrassLayer1", grassSheet.getSubimage(3 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdGrassLayer2", grassSheet.getSubimage(4 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdGrassLayer3", grassSheet.getSubimage(3 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdGrassLayer4", grassSheet.getSubimage(7 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("weirdGrassLayer5", grassSheet.getSubimage(0, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdGrassLayer6", grassSheet.getSubimage(2 * tileSize, 3 * tileSize, tileSize, tileSize));

        // dark grass
        BufferedImage darkGrassSheet = imageMap.get("darkGrass");
        mapTilesMap.put("darkGrassTile", darkGrassSheet.getSubimage(tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassUp", darkGrassSheet.getSubimage(tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("darkGrassLeft", darkGrassSheet.getSubimage(0, tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassBottom", darkGrassSheet.getSubimage(tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassRight", darkGrassSheet.getSubimage(2 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassLeftUpCorner", darkGrassSheet.getSubimage(0, 0, tileSize, tileSize));
        mapTilesMap.put("darkGrassLeftBottomCorner", darkGrassSheet.getSubimage(0, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassRightUpCorner", darkGrassSheet.getSubimage(2 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("darkGrassRightBottomCorner", darkGrassSheet.getSubimage(2 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassUpLeftInner", darkGrassSheet.getSubimage(6 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassBottomLeftInner", darkGrassSheet.getSubimage(6 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassUpRightInner", darkGrassSheet.getSubimage(5 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("darkGrassBottomRightInner", darkGrassSheet.getSubimage(5 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass1", darkGrassSheet.getSubimage(5 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass2", darkGrassSheet.getSubimage(2 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass3", darkGrassSheet.getSubimage(0, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass4", darkGrassSheet.getSubimage(6 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass5", darkGrassSheet.getSubimage(9 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass6", darkGrassSheet.getSubimage(7 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass7", darkGrassSheet.getSubimage(4 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass8", darkGrassSheet.getSubimage(7 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass9", darkGrassSheet.getSubimage(4 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass10", darkGrassSheet.getSubimage(3 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass11", darkGrassSheet.getSubimage(3 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass12", darkGrassSheet.getSubimage(3 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass13", darkGrassSheet.getSubimage(10 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass14", darkGrassSheet.getSubimage(tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass15", darkGrassSheet.getSubimage(9 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass16", darkGrassSheet.getSubimage(9 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass17", darkGrassSheet.getSubimage(4 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass18", darkGrassSheet.getSubimage(10 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass19", darkGrassSheet.getSubimage(9 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass20", darkGrassSheet.getSubimage(7 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("weirdDarkGrass21", darkGrassSheet.getSubimage(3 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("threeDarkStonesDarkGrass", darkGrassSheet.getSubimage(4 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("bigGrassDarkGrass", darkGrassSheet.getSubimage(0, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("threeGrassDarkGrass", darkGrassSheet.getSubimage(2 * tileSize, 5 * tileSize, tileSize, tileSize));
        mapTilesMap.put("twoDarkStonesDarkGrass", darkGrassSheet.getSubimage(3 * tileSize, 5 * tileSize, tileSize, tileSize));

        // bridges
        BufferedImage bridgesSheet = imageMap.get("bridges");
        mapTilesMap.put("leftBridge", bridgesSheet.getSubimage(0, 0, tileSize, tileSize));
        mapTilesMap.put("bridge", bridgesSheet.getSubimage(0, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("rightBridge", bridgesSheet.getSubimage(tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("waterLeftBridge", bridgesSheet.getSubimage(0, tileSize, tileSize, tileSize));

        // water decor
        BufferedImage waterDecor = imageMap.get("waterDecor");
        mapTilesMap.put("waterStone1", waterDecor.getSubimage( 0, 0, tileSize, tileSize));
        mapTilesMap.put("waterStone2", waterDecor.getSubimage( tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("waterStone3", waterDecor.getSubimage( 2 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("waterStone4", waterDecor.getSubimage( 3 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("bigWaterStone1", waterDecor.getSubimage( 4 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("bigWaterStone2", waterDecor.getSubimage( 5 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("threeWaterSticks", waterDecor.getSubimage( 6 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("fourWaterSticks", waterDecor.getSubimage( 7 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("waterLily1", waterDecor.getSubimage( 8 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("waterLily2", waterDecor.getSubimage( 9 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("waterLily3", waterDecor.getSubimage( 10 * tileSize, 0, tileSize, tileSize));
        mapTilesMap.put("waterLily4", waterDecor.getSubimage( 11 * tileSize, 0, tileSize, tileSize));

        // grass decor
        BufferedImage grassDecor = imageMap.get("grassDecor");
        mapTilesMap.put("stone1", grassDecor.getSubimage( 0, tileSize, tileSize, tileSize));
        mapTilesMap.put("stone2", grassDecor.getSubimage( tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("stone3", grassDecor.getSubimage( 2 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("stone4", grassDecor.getSubimage( 3 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("stone5", grassDecor.getSubimage( 4 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("stone6", grassDecor.getSubimage( 5 * tileSize, tileSize, tileSize, tileSize));
        mapTilesMap.put("grassDecor1", grassDecor.getSubimage(0, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassDecor2", grassDecor.getSubimage(tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassDecor3", grassDecor.getSubimage( 2 * tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("grassDecor4", grassDecor.getSubimage( 3 * tileSize, 2 * tileSize, tileSize, tileSize));

        // paths
        BufferedImage paths = imageMap.get("paths");
        mapTilesMap.put("pathUp", paths.getSubimage( 0, 0, tileSize, tileSize));
        mapTilesMap.put("pathVertical", paths.getSubimage( 0, tileSize, tileSize, tileSize));
        mapTilesMap.put("pathBottom", paths.getSubimage( 0, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("pathLeft", paths.getSubimage( tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("pathHorizontal", paths.getSubimage( 2 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("pathRight", paths.getSubimage( 3 * tileSize, 3 * tileSize, tileSize, tileSize));
        mapTilesMap.put("pathArc1", paths.getSubimage( tileSize, 2 * tileSize, tileSize, tileSize));
        mapTilesMap.put("pathArc2", paths.getSubimage( 2 * tileSize, 2 *tileSize, tileSize, tileSize));
        mapTilesMap.put("pathArc3", paths.getSubimage( tileSize, tileSize, tileSize, tileSize));
    }


    // creating animations
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


    // loading image from file to map
    private void loadImageToMap(Map<String, BufferedImage> map, String path, String key) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            map.put(key, image);

        } catch (IOException exception) {
            throw new RuntimeException("Problem with creating BufferedImage from file: " + path + "\n" + exception.getMessage());
        }
    }


    // getters
    public Animation getWaterAnimation() {
        return createWaterAnimation();
    }
}

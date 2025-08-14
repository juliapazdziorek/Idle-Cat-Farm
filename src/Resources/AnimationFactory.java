package Resources;

import Game.Farm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimationFactory {

    // image map
    private final Map<String, BufferedImage> imageMap;

    public AnimationFactory(Map<String, BufferedImage> imageMap) {
        this.imageMap = imageMap;
    }


    // factories

    // water
    public Animation createWaterAnimation() {
        return new Animation(imageMap.get("water"), 4, 1, 0, 4, 20);
    }


    // tree click animations
    public Animation createClickTreePartAnimation(int id) {
        return switch (id) {
            case 173 -> createClickTreePartAnimation173();
            case 174 -> createClickTreePartAnimation174();
            case 175 -> createClickTreePartAnimation175();
            case 176 -> createClickTreePartAnimation176();
            case 177 -> createClickTreePartAnimation177();
            case 178 -> createClickTreePartAnimation178();
            case 179 -> createClickTreePartAnimation179();
            case 180 -> createClickTreePartAnimation180();
            case 181 -> createClickTreePartAnimation181();
            default -> throw new IllegalArgumentException("Invalid tree part ID: " + id);
        };
    }

    private Animation createClickTreePartAnimation173() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation173Frames = new ArrayList<>();
        treeClickAnimation173Frames.add(treeClickAnimationImage.getSubimage(0, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation173Frames.add(treeClickAnimationImage.getSubimage(3 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation173Frames.add(treeClickAnimationImage.getSubimage(6 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation173Frames.add(treeClickAnimationImage.getSubimage(9 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation173Frames.add(treeClickAnimationImage.getSubimage(12 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation173Frames.add(treeClickAnimationImage.getSubimage(15 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation173Frames, 8);
    }

    private Animation createClickTreePartAnimation174() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation174Frames = new ArrayList<>();
        treeClickAnimation174Frames.add(treeClickAnimationImage.getSubimage(Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation174Frames.add(treeClickAnimationImage.getSubimage(4 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation174Frames.add(treeClickAnimationImage.getSubimage(7 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation174Frames.add(treeClickAnimationImage.getSubimage(10 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation174Frames.add(treeClickAnimationImage.getSubimage(13 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation174Frames.add(treeClickAnimationImage.getSubimage(16 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation174Frames, 8);
    }

    private Animation createClickTreePartAnimation175() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation175Frames = new ArrayList<>();
        treeClickAnimation175Frames.add(treeClickAnimationImage.getSubimage(2 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation175Frames.add(treeClickAnimationImage.getSubimage(5 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation175Frames.add(treeClickAnimationImage.getSubimage(8 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation175Frames.add(treeClickAnimationImage.getSubimage(11 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation175Frames.add(treeClickAnimationImage.getSubimage(14 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation175Frames.add(treeClickAnimationImage.getSubimage(17 * Farm.tileSize, 6 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation175Frames, 8);
    }

    private Animation createClickTreePartAnimation176() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation176Frames = new ArrayList<>();
        treeClickAnimation176Frames.add(treeClickAnimationImage.getSubimage(0, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation176Frames.add(treeClickAnimationImage.getSubimage(3 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation176Frames.add(treeClickAnimationImage.getSubimage(6 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation176Frames.add(treeClickAnimationImage.getSubimage(9 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation176Frames.add(treeClickAnimationImage.getSubimage(12 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation176Frames.add(treeClickAnimationImage.getSubimage(15 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation176Frames, 8);
    }

    private Animation createClickTreePartAnimation177() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation177Frames = new ArrayList<>();
        treeClickAnimation177Frames.add(treeClickAnimationImage.getSubimage(Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation177Frames.add(treeClickAnimationImage.getSubimage(4 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation177Frames.add(treeClickAnimationImage.getSubimage(7 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation177Frames.add(treeClickAnimationImage.getSubimage(10 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation177Frames.add(treeClickAnimationImage.getSubimage(13 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation177Frames.add(treeClickAnimationImage.getSubimage(16 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation177Frames, 8);
    }

    private Animation createClickTreePartAnimation178() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation178Frames = new ArrayList<>();
        treeClickAnimation178Frames.add(treeClickAnimationImage.getSubimage(2 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation178Frames.add(treeClickAnimationImage.getSubimage(5 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation178Frames.add(treeClickAnimationImage.getSubimage(8 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation178Frames.add(treeClickAnimationImage.getSubimage(11 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation178Frames.add(treeClickAnimationImage.getSubimage(14 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation178Frames.add(treeClickAnimationImage.getSubimage(17 * Farm.tileSize, 7 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation178Frames, 8);
    }

    private Animation createClickTreePartAnimation179() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation179Frames = new ArrayList<>();
        treeClickAnimation179Frames.add(treeClickAnimationImage.getSubimage(0, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation179Frames.add(treeClickAnimationImage.getSubimage(3 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation179Frames.add(treeClickAnimationImage.getSubimage(6 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation179Frames.add(treeClickAnimationImage.getSubimage(9 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation179Frames.add(treeClickAnimationImage.getSubimage(12 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation179Frames.add(treeClickAnimationImage.getSubimage(15 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation179Frames, 8);
    }

    private Animation createClickTreePartAnimation180() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation180Frames = new ArrayList<>();
        treeClickAnimation180Frames.add(treeClickAnimationImage.getSubimage(Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation180Frames.add(treeClickAnimationImage.getSubimage(4 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation180Frames.add(treeClickAnimationImage.getSubimage(7 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation180Frames.add(treeClickAnimationImage.getSubimage(10 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation180Frames.add(treeClickAnimationImage.getSubimage(13 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation180Frames.add(treeClickAnimationImage.getSubimage(16 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation180Frames, 8);
    }

    private Animation createClickTreePartAnimation181() {
        BufferedImage treeClickAnimationImage = imageMap.get("tree");
        ArrayList<BufferedImage> treeClickAnimation181Frames = new ArrayList<>();
        treeClickAnimation181Frames.add(treeClickAnimationImage.getSubimage(2 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation181Frames.add(treeClickAnimationImage.getSubimage(5 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation181Frames.add(treeClickAnimationImage.getSubimage(8 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation181Frames.add(treeClickAnimationImage.getSubimage(11 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation181Frames.add(treeClickAnimationImage.getSubimage(14 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        treeClickAnimation181Frames.add(treeClickAnimationImage.getSubimage(17 * Farm.tileSize, 8 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(treeClickAnimation181Frames, 8);
    }


    // entrances
    public Animation createEntranceOpenAnimation(int id) {
        return switch (id) {
            case 194 -> createGateOpenAnimation194();
            case 195 -> createGateOpenAnimation195();
            case 197 -> createGateOpenAnimation197();
            case 198 -> createGateOpenAnimation198();
            case 199 -> createGateOpenAnimation199();
            case 200 -> createGateOpenAnimation200();
            case 201 -> createGateOpenAnimation201();
            case 202 -> createGateOpenAnimation202();
            case 203 -> createGateOpenAnimation203();
            case 204 -> createGateOpenAnimation204();
            case 217 -> createDoorOpenAnimation217();
            case 218 -> createDoorOpenAnimation218();
            case 219 -> createDoorOpenAnimation219();
            case 220 -> createDoorOpenAnimation220();
            default -> throw new IllegalArgumentException("Invalid entrance ID: " + id);
        };
    }

    public Animation createEntranceCloseAnimation(int id) {
        return switch (id) {
            case 194 -> createGateCloseAnimation194();
            case 195 -> createGateCloseAnimation195();
            case 197 -> createGateCloseAnimation197();
            case 198 -> createGateCloseAnimation198();
            case 199 -> createGateCloseAnimation199();
            case 200 -> createGateCloseAnimation200();
            case 201 -> createGateCloseAnimation201();
            case 202 -> createGateCloseAnimation202();
            case 203 -> createGateCloseAnimation203();
            case 204 -> createGateCloseAnimation204();
            case 217 -> createDoorCloseAnimation217();
            case 218 -> createDoorCloseAnimation218();
            case 219 -> createDoorCloseAnimation219();
            case 220 -> createDoorCloseAnimation220();
            default -> throw new IllegalArgumentException("Invalid entrance ID: " + id);
        };
    }

    private Animation createGateOpenAnimation194() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation194Frames = new ArrayList<>();
        gateOpenAnimation194Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation194Frames.add(gateAnimationImage.getSubimage(9 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation194Frames.add(gateAnimationImage.getSubimage(13 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation194Frames, 5);
    }

    private Animation createGateOpenAnimation195() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation195Frames = new ArrayList<>();
        gateOpenAnimation195Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation195Frames.add(gateAnimationImage.getSubimage(10 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation195Frames.add(gateAnimationImage.getSubimage(14 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation195Frames, 5);
    }

    private Animation createGateOpenAnimation197() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation197Frames = new ArrayList<>();
        gateOpenAnimation197Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation197Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation197Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation197Frames, 5);
    }

    private Animation createGateOpenAnimation198() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation198Frames = new ArrayList<>();
        gateOpenAnimation198Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation198Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation198Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation198Frames, 5);
    }

    private Animation createGateOpenAnimation199() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation199Frames = new ArrayList<>();
        gateOpenAnimation199Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation199Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation199Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation199Frames, 5);
    }

    private Animation createGateOpenAnimation200() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation200Frames = new ArrayList<>();
        gateOpenAnimation200Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation200Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation200Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation200Frames, 5);
    }

    private Animation createGateOpenAnimation201() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation201Frames = new ArrayList<>();
        gateOpenAnimation201Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation201Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation201Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation201Frames, 5);
    }

    private Animation createGateOpenAnimation202() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation202Frames = new ArrayList<>();
        gateOpenAnimation202Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation202Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation202Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation202Frames, 5);
    }

    private Animation createGateOpenAnimation203() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation203Frames = new ArrayList<>();
        gateOpenAnimation203Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation203Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation203Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation203Frames, 5);
    }

    private Animation createGateOpenAnimation204() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateOpenAnimation204Frames = new ArrayList<>();
        gateOpenAnimation204Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation204Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateOpenAnimation204Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateOpenAnimation204Frames, 5);
    }

    private Animation createGateCloseAnimation194() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation194Frames = new ArrayList<>();
        gateCloseAnimation194Frames.add(gateAnimationImage.getSubimage(13 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation194Frames.add(gateAnimationImage.getSubimage(9 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation194Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation194Frames, 5);
    }

    private Animation createGateCloseAnimation195() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation195Frames = new ArrayList<>();
        gateCloseAnimation195Frames.add(gateAnimationImage.getSubimage(14 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation195Frames.add(gateAnimationImage.getSubimage(10 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation195Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation195Frames, 5);
    }

    private Animation createGateCloseAnimation197() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation197Frames = new ArrayList<>();
        gateCloseAnimation197Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation197Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation197Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation197Frames, 5);
    }

    private Animation createGateCloseAnimation198() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation198Frames = new ArrayList<>();
        gateCloseAnimation198Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation198Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation198Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation198Frames, 5);
    }

    private Animation createGateCloseAnimation199() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation199Frames = new ArrayList<>();
        gateCloseAnimation199Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation199Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation199Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation199Frames, 5);
    }

    private Animation createGateCloseAnimation200() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation200Frames = new ArrayList<>();
        gateCloseAnimation200Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation200Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 2 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation200Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, 2 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation200Frames, 5);
    }

    private Animation createGateCloseAnimation201() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation201Frames = new ArrayList<>();
        gateCloseAnimation201Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation201Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation201Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation201Frames, 5);
    }

    private Animation createGateCloseAnimation202() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation202Frames = new ArrayList<>();
        gateCloseAnimation202Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation202Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 3 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation202Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, 3 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation202Frames, 5);
    }

    private Animation createGateCloseAnimation203() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation203Frames = new ArrayList<>();
        gateCloseAnimation203Frames.add(gateAnimationImage.getSubimage(6 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation203Frames.add(gateAnimationImage.getSubimage(4 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation203Frames.add(gateAnimationImage.getSubimage(2 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation203Frames, 5);
    }

    private Animation createGateCloseAnimation204() {
        BufferedImage gateAnimationImage = imageMap.get("gates");
        ArrayList<BufferedImage> gateCloseAnimation204Frames = new ArrayList<>();
        gateCloseAnimation204Frames.add(gateAnimationImage.getSubimage(7 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation204Frames.add(gateAnimationImage.getSubimage(5 * Farm.tileSize, 4 *Farm.tileSize, Farm.tileSize, Farm.tileSize));
        gateCloseAnimation204Frames.add(gateAnimationImage.getSubimage(3 * Farm.tileSize, 4 * Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(gateCloseAnimation204Frames, 5);
    }

    private Animation createDoorOpenAnimation217() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorOpenAnimation217Frames = new ArrayList<>();
        doorOpenAnimation217Frames.add(doorAnimationImage.getSubimage(4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation217Frames.add(doorAnimationImage.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation217Frames.add(doorAnimationImage.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        return new Animation(doorOpenAnimation217Frames, 5);
    }

    private Animation createDoorOpenAnimation218() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorOpenAnimation218Frames = new ArrayList<>();
        doorOpenAnimation218Frames.add(doorAnimationImage.getSubimage(12 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation218Frames.add(doorAnimationImage.getSubimage(9 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation218Frames.add(doorAnimationImage.getSubimage(6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(doorOpenAnimation218Frames, 5);
    }

    private Animation createDoorOpenAnimation219() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorOpenAnimation219Frames = new ArrayList<>();
        doorOpenAnimation219Frames.add(doorAnimationImage.getSubimage(13 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation219Frames.add(doorAnimationImage.getSubimage(10 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation219Frames.add(doorAnimationImage.getSubimage(7 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(doorOpenAnimation219Frames, 5);
    }

    private Animation createDoorOpenAnimation220() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorOpenAnimation220Frames = new ArrayList<>();
        doorOpenAnimation220Frames.add(doorAnimationImage.getSubimage(14 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation220Frames.add(doorAnimationImage.getSubimage(11 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorOpenAnimation220Frames.add(doorAnimationImage.getSubimage(8 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(doorOpenAnimation220Frames, 5);
    }

    private Animation createDoorCloseAnimation217() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorCloseAnimation217Frames = new ArrayList<>();
        doorCloseAnimation217Frames.add(doorAnimationImage.getSubimage(2 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation217Frames.add(doorAnimationImage.getSubimage(3 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation217Frames.add(doorAnimationImage.getSubimage(4 * Farm.tileSize, 0, Farm.tileSize, Farm.tileSize));
        return new Animation(doorCloseAnimation217Frames, 5);
    }

    private Animation createDoorCloseAnimation218() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorCloseAnimation218Frames = new ArrayList<>();
        doorCloseAnimation218Frames.add(doorAnimationImage.getSubimage(6 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation218Frames.add(doorAnimationImage.getSubimage(9 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation218Frames.add(doorAnimationImage.getSubimage(12 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(doorCloseAnimation218Frames, 5);
    }

    private Animation createDoorCloseAnimation219() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorCloseAnimation219Frames = new ArrayList<>();
        doorCloseAnimation219Frames.add(doorAnimationImage.getSubimage(7 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation219Frames.add(doorAnimationImage.getSubimage(10 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation219Frames.add(doorAnimationImage.getSubimage(13 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(doorCloseAnimation219Frames, 5);
    }

    private Animation createDoorCloseAnimation220() {
        BufferedImage doorAnimationImage = imageMap.get("doors");
        ArrayList<BufferedImage> doorCloseAnimation220Frames = new ArrayList<>();
        doorCloseAnimation220Frames.add(doorAnimationImage.getSubimage(8 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation220Frames.add(doorAnimationImage.getSubimage(11 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        doorCloseAnimation220Frames.add(doorAnimationImage.getSubimage(14 * Farm.tileSize, Farm.tileSize, Farm.tileSize, Farm.tileSize));
        return new Animation(doorCloseAnimation220Frames, 5);
    }


    // farm cat
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

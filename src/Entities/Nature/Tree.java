package Entities.Nature;

import Entities.StaticEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree extends StaticEntity {

    public List<TreePart> treeParts;
    public boolean isAnimating;

    public Tree(Point centerPosition) {
        super(centerPosition);
        treeParts = new ArrayList<>();
    }


    // adding tree parts
    public void addTreePart(TreePart part) {
        treeParts.add(part);
        part.setParentTree(this);
    }


    // animation handling
    public void playClickAnimation() {
        if (!isAnimating) {
            isAnimating = true;

            for (TreePart part : treeParts) {
                part.startAnimation();
            }
        }
    }


    // updating & rendering
    @Override
    public void update() {
        for (TreePart part : treeParts) {
            part.update();
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        for (TreePart part : treeParts) {
            part.render(graphics2D);
        }
    }
}

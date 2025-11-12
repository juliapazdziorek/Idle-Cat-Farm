package Entities.Nature;

import Entities.Entity;

public class Tree extends Entity {

    public Tree() {
        super();

        isParent = true;
    }

    public void playTreeClickAnimation() {
        if (!isAnimating) {
            isAnimating = true;

            for (Entity part : parts) {
                ((TreePart) part).startTreeClickAnimation();
            }
        }
    }
}

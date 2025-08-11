package Entities.Nature;

import Entities.Entity;

public class Tree extends Entity {

    public Tree() {
        super();

        // set flags
        isParent = true;
    }


    // tree click animation handling
    public void playTreeClickAnimation() {
        if (!isAnimating) {
            isAnimating = true;

            for (Entity part : parts) {
                ((TreePart) part).startTreeClickAnimation();
            }
        }
    }
}

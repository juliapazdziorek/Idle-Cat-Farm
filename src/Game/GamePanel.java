package Game;

import Resources.Colors;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setPreferredSize(new Dimension(1200, 800));
        setBackground(Colors.waterColor);
        setDoubleBuffered(true);
        setFocusable(true);
    }


    // updating & rendering
    public void update() {
        Farm.entitiesHandler.update();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Farm.entitiesHandler.render(graphics2D);
        graphics2D.dispose();
    }
}
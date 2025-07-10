package Game;

import Resources.Colors;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public Panel() {
        setPreferredSize(new Dimension(1200, 800));
        setBackground(Color.decode(Colors.waterColor));
        setDoubleBuffered(true);
        setFocusable(true);
    }


    // updating & rendering
    public void update() {
        FocusFarm.entitiesHandler.update();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        FocusFarm.entitiesHandler.render(graphics2D);
        graphics2D.dispose();
    }
}
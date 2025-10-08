package Game;

import Resources.Colors;

import javax.swing.*;
import java.awt.*;

/** Main game rendering panel focused on updates and visual display. */
public class GamePanel extends JPanel {

    /** Initializes the game panel with optimized rendering settings. */
    public GamePanel() {
        setPreferredSize(new Dimension(1200, 800));
        setBackground(Colors.waterColor);
        setDoubleBuffered(true);
        setFocusable(true);
    }

    /** 
     * Updates all game entities and systems for the current frame.
     * Called by the main game loop at 60 FPS.
     */
    public void update() {
        Farm.entitiesHandler.update();
    }

    /**
     * Renders the complete game world to the screen.
     * Handles all visual elements including entities, map, and UI overlays.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Farm.entitiesHandler.render(graphics2D);
        graphics2D.dispose();
    }
}
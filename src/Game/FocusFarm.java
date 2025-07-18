package Game;

import Resources.ResourceHandler;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FocusFarm {

    // loop
    static public final int fps = 60;
    static public Loop loop;

    // sprites & map numbers
    static public final int tileSize = 16;
    static public final int scale = 3;
    static public final int scaledTileSize = tileSize * scale;
    static public final int mapWidthTiles = 48;
    static public final int mapHeightTiles = 36;
    static public final int mapWidth = mapWidthTiles * scaledTileSize;
    static public final int mapHeight = mapHeightTiles * scaledTileSize;

    // window
    static public JFrame frame;
    static public Panel panel;
    static public Camera camera;

    // handlers
    static public ResourceHandler resourceHandler;
    static public EntitiesHandler entitiesHandler;
    static public KeyHandler keyHandler;
    static public MouseHandler mouseHandler;


    public FocusFarm() {
        SwingUtilities.invokeLater(this::launchFarm);
    }

    private void launchFarm() {

        // prepare properties
        resourceHandler = new ResourceHandler();
        entitiesHandler = new EntitiesHandler();
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        panel = new Panel();
        camera = new Camera();
        loop = new Loop();


        // window setup
        frame = new JFrame("FocusFarm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                loop.stopLooping();
            }
        });

        frame.addKeyListener(keyHandler);
        frame.addMouseListener(mouseHandler);
        frame.addMouseListener(entitiesHandler);
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        // launching farm loop
        loop.startLooping();
    }
}
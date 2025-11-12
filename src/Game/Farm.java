package Game;

import Resources.ResourceHandler;
import Entities.EntitiesHandler;
import Map.Map;
import UI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Farm {

    static public final int fps = 60;
    static public Loop loop;

    static public final int tileSize = 16;
    static public final int emojiSize = 32;
    static public final int scale = 3;
    static public final int scaledTileSize = tileSize * scale;
    
    static public final int mapWidthTiles = 48;
    static public final int mapHeightTiles = 36;
    
    static public final int mapWidth = mapWidthTiles * scaledTileSize;
    static public final int mapHeight = mapHeightTiles * scaledTileSize;

    static public JFrame frame;
    static public GamePanel gamePanel;
    static public MenuPanel menuPanel;
    static public Camera camera;
    static public Resources.Fonts fonts;

    static public int levelUpsDone = 0;
    static public final int maxLvlUps = 24;
    static public int catsCount = 1;

    static public FarmResourcesHandler farmResourcesHandler;
    static public ResourceHandler resourceHandler;
    static public EntitiesHandler entitiesHandler;
    static public OrdersHandler ordersHandler;

    public Farm() {
        SwingUtilities.invokeLater(this::launchFarm);
    }

    private void launchFarm() {
        resourceHandler = new ResourceHandler();
        fonts = new Resources.Fonts();
        entitiesHandler = new EntitiesHandler();
        farmResourcesHandler = new FarmResourcesHandler();
        ordersHandler = new OrdersHandler(farmResourcesHandler);
        gamePanel = new GamePanel();
        menuPanel = new MenuPanel();
        camera = new Camera();
        loop = new Loop();

        camera.setupInputBindings(gamePanel);

        Map.initializePathfinder();
        entitiesHandler.map.calculateBedPositions();
        ordersHandler.maintainOrderCount();
        menuPanel.refreshResourcesDisplay();

        frame = new JFrame("Cat Farm");
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

        frame.addMouseListener(entitiesHandler);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.EAST);
        
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        loop.startLooping();
    }
}
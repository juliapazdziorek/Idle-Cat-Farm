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

    // loop
    static public final int fps = 60;
    static public Loop loop;

    // sprites & map numbers
    static public final int tileSize = 16;
    static public final int scale = 4;
    static public final int scaledTileSize = tileSize * scale;
    static public final int mapWidthTiles = 48;
    static public final int mapHeightTiles = 36;
    static public final int mapWidth = mapWidthTiles * scaledTileSize;
    static public final int mapHeight = mapHeightTiles * scaledTileSize;

    // window
    static public JFrame frame;
    static public GamePanel gamePanel;
    static public MenuPanel menuPanel;
    static public Camera camera;
    static public FarmResourcesHandler farmResourcesHandler;

    // fonts
    static public Resources.Fonts fonts;

    // money
    static public int money = 0;

    // handlers
    static public ResourceHandler resourceHandler;
    static public EntitiesHandler entitiesHandler;
    static public KeyHandler keyHandler;
    static public MouseHandler mouseHandler;
    static public OrdersHandler ordersHandler;


    public Farm() {
        SwingUtilities.invokeLater(this::launchFarm);
    }

    private void launchFarm() {

        // prepare properties
        resourceHandler = new ResourceHandler();
        fonts = new Resources.Fonts();
        entitiesHandler = new EntitiesHandler();
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        farmResourcesHandler = new FarmResourcesHandler();
        ordersHandler = new OrdersHandler(farmResourcesHandler);
        gamePanel = new GamePanel();
        menuPanel = new MenuPanel();
        camera = new Camera();
        loop = new Loop();
        
        // initialize pathfinder
        Map.initializePathfinder();

        // initialize orders
        ordersHandler.maintainOrderCount();

        // refresh UI
        if (menuPanel != null) {
            menuPanel.refreshResourcesDisplay();
        }

        // window setup
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

        frame.addKeyListener(keyHandler);
        frame.addMouseListener(mouseHandler);
        frame.addMouseListener(entitiesHandler);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.EAST);
        frame.add(mainPanel);

        frame.pack();
        frame.setVisible(true);

        // launching farm loop
        loop.startLooping();
    }


    // money management methods
    public static void addMoney(int amount) {
        money += amount;

        if (menuPanel != null) {
            menuPanel.refreshResourcesDisplay();
        }
    }

    public static void subtractMoney(int amount) {
        money -= amount;

        if (menuPanel != null) {
            menuPanel.refreshResourcesDisplay();
        }
    }
}
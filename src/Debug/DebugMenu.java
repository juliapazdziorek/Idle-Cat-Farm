package Debug;

import Game.FocusFarm;
import Map.Map;
import javax.swing.*;
import java.awt.*;

public class DebugMenu extends JFrame {
    
    private static DebugMenu instance;

    private JCheckBox mouseMovementCheckbox;
    private JPanel areaLevelPanel;

    // debug settings
    public static boolean mouseMovementEnabled = true;
    
    private DebugMenu() {
        initializeGUI();
    }
    
    public static void toggleDebugMenu() {
        if (instance == null) {
            instance = new DebugMenu();
        }
        
        boolean currentlyVisible = instance.isVisible();
        instance.setVisible(!currentlyVisible);
        
        if (!currentlyVisible) {
            instance.refreshUI();
            instance.toFront();
            instance.requestFocus();
        }
    }
    
    private void initializeGUI() {
        setTitle("Debug Menu");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        
        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // farm cat controls section
        createFarmCatControlsSection(mainPanel);
        
        // map section
        createMapSection(mainPanel);
        
        // close button
        JButton closeButton = new JButton("Close (F3)");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(_ -> toggleDebugMenu());
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(closeButton);
        
        add(mainPanel);
    }
    
    private void createFarmCatControlsSection(JPanel mainPanel) {
        JPanel catPanel = new JPanel();
        catPanel.setLayout(new BoxLayout(catPanel, BoxLayout.Y_AXIS));
        catPanel.setBorder(BorderFactory.createTitledBorder("Farm cat controls"));
        catPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // mouse movement toggle
        mouseMovementCheckbox = new JCheckBox("Enable mouse movement", mouseMovementEnabled);
        mouseMovementCheckbox.addActionListener(_ -> {
            mouseMovementEnabled = mouseMovementCheckbox.isSelected();
        });
        catPanel.add(mouseMovementCheckbox);
        
        mainPanel.add(catPanel);
        mainPanel.add(Box.createVerticalStrut(15));
    }
    
    private void createMapSection(JPanel mainPanel) {
        JPanel areasPanel = new JPanel();
        areasPanel.setLayout(new BoxLayout(areasPanel, BoxLayout.Y_AXIS));
        areasPanel.setBorder(BorderFactory.createTitledBorder("Map controls"));
        areasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // managing areas levels
        areaLevelPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        for (Map.MapArea area : Map.MapArea.values()) {
            JLabel areaLabel = new JLabel(area.name() + ":");
            JComboBox<Map.MapLevels> levelCombo = new JComboBox<>(Map.MapLevels.values());
            
            if (FocusFarm.entitiesHandler != null && FocusFarm.entitiesHandler.map != null) {
                levelCombo.setSelectedItem(FocusFarm.entitiesHandler.map.getAreaLevel(area));
            }
            
            levelCombo.addActionListener(_ -> {
                Map.MapLevels selectedLevel = (Map.MapLevels) levelCombo.getSelectedItem();
                if (FocusFarm.entitiesHandler != null && FocusFarm.entitiesHandler.map != null) {
                    FocusFarm.entitiesHandler.map.setAreaLevel(area, selectedLevel);
                }
            });
            
            areaLevelPanel.add(areaLabel);
            areaLevelPanel.add(levelCombo);
        }
        
        areasPanel.add(areaLevelPanel);
        
        // bulk actions
        JPanel bulkPanel = new JPanel(new FlowLayout());
        JButton setAllLevel0 = new JButton("All → level 0");
        setAllLevel0.addActionListener(_ -> setAllAreasToLevel(Map.MapLevels.LEVEL_0));

        JButton setAllLevel1 = new JButton("All → level 1");
        setAllLevel1.addActionListener(_ -> setAllAreasToLevel(Map.MapLevels.LEVEL_1));

        JButton setAllLevel2 = new JButton("All → level 2");
        setAllLevel2.addActionListener(_ -> setAllAreasToLevel(Map.MapLevels.LEVEL_2));

        JButton setAllLevel3 = new JButton("All → level 3");
        setAllLevel3.addActionListener(_ -> setAllAreasToLevel(Map.MapLevels.LEVEL_3));

        JButton setAllLevelStar = new JButton("All → level Star");
        setAllLevelStar.addActionListener(_ -> setAllAreasToLevel(Map.MapLevels.LEVEL_Star));

        bulkPanel.add(setAllLevel0);
        bulkPanel.add(setAllLevel1);
        bulkPanel.add(setAllLevel2);
        bulkPanel.add(setAllLevel3);
        bulkPanel.add(setAllLevelStar);
        areasPanel.add(bulkPanel);
        
        mainPanel.add(areasPanel);
    }
    
    private void setAllAreasToLevel(Map.MapLevels level) {
        if (FocusFarm.entitiesHandler != null && FocusFarm.entitiesHandler.map != null) {
            for (Map.MapArea area : Map.MapArea.values()) {
                FocusFarm.entitiesHandler.map.setAreaLevel(area, level);
            }
            refreshUI();
        }
    }

    private void refreshUI() {
        mouseMovementCheckbox.setSelected(mouseMovementEnabled);

        Component[] components = areaLevelPanel.getComponents();
        for (int i = 1; i < components.length; i += 2) {
            if (components[i] instanceof JComboBox) {
                @SuppressWarnings("unchecked")
                JComboBox<Map.MapLevels> combo = (JComboBox<Map.MapLevels>) components[i];
                Map.MapArea area = Map.MapArea.values()[(i - 1) / 2];
                combo.setSelectedItem(FocusFarm.entitiesHandler.map.getAreaLevel(area));
            }
        }
    }
}

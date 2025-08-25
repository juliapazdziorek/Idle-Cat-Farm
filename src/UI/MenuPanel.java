package UI;

import Game.Farm;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final WorkstationTab workstationTab;
    private final CatsTab catsTab;
    private final FarmTab farmTab;
    private final StatsPanel statsPanel;
    private JTabbedPane tabbedPane;

    public MenuPanel() {
        workstationTab = new WorkstationTab();
        catsTab = new CatsTab();
        farmTab = new FarmTab();
        statsPanel = new StatsPanel();
        initializeMenu();
    }

    private void initializeMenu() {
        setPreferredSize(new Dimension(350, 800));
        setLayout(new BorderLayout());
        setBackground(Colors.beigeColor);
        setBorder(null);
        
        // create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBackground(Colors.beigeColor);
        tabbedPane.setForeground(Colors.darkBeigeColor);
        tabbedPane.setFont(Farm.fonts.minecraftiaFont);

        // add tabs
        tabbedPane.addTab("Workstation", workstationTab.createWorkstationTab());
        tabbedPane.addTab("Cats", catsTab.createCatsTab());
        tabbedPane.addTab("Farm", farmTab.createFarmTab());
        
        add(tabbedPane, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
    }

    public void refreshResourcesDisplay() {
        SwingUtilities.invokeLater(() -> {
            tabbedPane.setComponentAt(0, workstationTab.createWorkstationTab());
            statsPanel.refreshStats();
            tabbedPane.revalidate();
            tabbedPane.repaint();
        });
    }
}

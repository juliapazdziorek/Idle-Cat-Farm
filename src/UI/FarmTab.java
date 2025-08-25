package UI;

import Game.Farm;
import Map.Map;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FarmTab {

    // upgrade costs for all areas (level 0->1, 1->2, 2->3, 3->Star)
    private static final int[] UPGRADE_COSTS = {10, 25, 50, 100};

    public FarmTab() {
    }

    public JPanel createFarmTab() {
        UIUtils.RoundedPanel panel = new UIUtils.RoundedPanel(12);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Colors.beigeColor);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // title
        JLabel titleLabel = new JLabel("Farm", JLabel.CENTER);
        titleLabel.setFont(Farm.fonts.dayDreamFont18f);
        titleLabel.setForeground(Colors.darkBeigeColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // content with scroll
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // add area sections in order: house, fields, coop, barn, orchard, park
        contentPanel.add(createAreaSection(Map.MapArea.HOUSE, "standingCat"));
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(createAreaSection(Map.MapArea.FIELDS, "fields"));
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(createAreaSection(Map.MapArea.COOP, "coop"));
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(createAreaSection(Map.MapArea.COWS, "cows"));
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(createAreaSection(Map.MapArea.ORCHARD, "orchard"));
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(createAreaSection(Map.MapArea.PARK, "park"));

        // scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        UIUtils.customizeScrollBar(scrollPane);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAreaSection(Map.MapArea area, String iconKey) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBackground(Colors.lightBeigeColor);
        sectionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.beigeColor, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        // header with icon and name
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        headerPanel.setOpaque(false);

        // area icon
        JLabel iconLabel = new JLabel();
        try {
            if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey(iconKey)) {
                BufferedImage iconImage = Farm.resourceHandler.iconsMap.get(iconKey);
                ImageIcon icon = new ImageIcon(iconImage.getScaledInstance(24, 24, Image.SCALE_SMOOTH));
                iconLabel.setIcon(icon);
            } else {
                iconLabel.setText("?");
                iconLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
                iconLabel.setForeground(Colors.darkBeigeColor);
            }
        } catch (Exception e) {
            iconLabel.setText("?");
            iconLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
            iconLabel.setForeground(Colors.darkBeigeColor);
        }

        // area name
        String areaName = area.name().charAt(0) + area.name().substring(1).toLowerCase();
        if (area == Map.MapArea.COOP) areaName = "Coop";
        if (area == Map.MapArea.COWS) areaName = "Barn";
        
        JLabel nameLabel = new JLabel(areaName);
        nameLabel.setFont(Farm.fonts.dayDreamFont12f);
        nameLabel.setForeground(Colors.darkBeigeColor);

        headerPanel.add(iconLabel);
        headerPanel.add(nameLabel);

        // get current level
        Map.MapLevels currentLevel = getCurrentAreaLevel(area);
        String levelText = getLevelText(currentLevel);
        int upgradeCost = getUpgradeCost(currentLevel);

        // level and upgrade info
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);
        int topPadding = (currentLevel == Map.MapLevels.LEVEL_Star) ? 4 : 8;
        infoPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, 0, 0, 0));

        // level display with cost below
        JPanel levelDisplayPanel = new JPanel();
        levelDisplayPanel.setLayout(new BoxLayout(levelDisplayPanel, BoxLayout.Y_AXIS));
        levelDisplayPanel.setOpaque(false);

        // level text
        JPanel levelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        levelPanel.setOpaque(false);
        
        if (currentLevel == Map.MapLevels.LEVEL_Star) {
            JLabel levelTextLabel = new JLabel("lvl ");
            levelTextLabel.setFont(Farm.fonts.minecraftiaFont);
            levelTextLabel.setForeground(Colors.darkBeigeColor);
            levelPanel.add(levelTextLabel);
            
            // star icon
            JLabel starIconLabel = new JLabel();
            try {
                if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey("star")) {
                    BufferedImage starImage = Farm.resourceHandler.iconsMap.get("star");
                    ImageIcon starIcon = new ImageIcon(starImage.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
                    starIconLabel.setIcon(starIcon);
                } else {
                    starIconLabel.setText("★");
                    starIconLabel.setFont(Farm.fonts.minecraftiaFont);
                    starIconLabel.setForeground(Colors.darkBeigeColor);
                }
            } catch (Exception e) {
                starIconLabel.setText("★");
                starIconLabel.setFont(Farm.fonts.minecraftiaFont);
                starIconLabel.setForeground(Colors.darkBeigeColor);
            }
            levelPanel.add(starIconLabel);
        } else {
            JLabel levelLabel = new JLabel(levelText);
            levelLabel.setFont(Farm.fonts.minecraftiaFont);
            levelLabel.setForeground(Colors.darkBeigeColor);
            levelPanel.add(levelLabel);
        }
        
        levelDisplayPanel.add(levelPanel);

        // cost display below level if upgrade is available (not for star level)
        if (upgradeCost > 0 && currentLevel != Map.MapLevels.LEVEL_Star) {
            JPanel costPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            costPanel.setOpaque(false);
            
            JLabel costTextLabel = new JLabel("lvl up cost: ");
            costTextLabel.setFont(Farm.fonts.minecraftiaFont);
            costTextLabel.setForeground(Colors.darkBeigeColor);
            costPanel.add(costTextLabel);
            
            // money icon
            JLabel moneyIconLabel = new JLabel();
            try {
                if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey("coin")) {
                    BufferedImage coinImage = Farm.resourceHandler.iconsMap.get("coin");
                    ImageIcon coinIcon = new ImageIcon(coinImage.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
                    moneyIconLabel.setIcon(coinIcon);
                } else {
                    moneyIconLabel.setText("$");
                    moneyIconLabel.setFont(Farm.fonts.minecraftiaFont);
                    moneyIconLabel.setForeground(Colors.darkBeigeColor);
                }
            } catch (Exception e) {
                moneyIconLabel.setText("$");
                moneyIconLabel.setFont(Farm.fonts.minecraftiaFont);
                moneyIconLabel.setForeground(Colors.darkBeigeColor);
            }
            costPanel.add(moneyIconLabel);
            
            // Add spacing between icon and number
            costPanel.add(Box.createHorizontalStrut(3));
            
            JLabel costLabel = new JLabel(String.valueOf(upgradeCost));
            costLabel.setFont(Farm.fonts.minecraftiaFont);
            costLabel.setForeground(Colors.darkBeigeColor);
            costPanel.add(costLabel);
            
            levelDisplayPanel.add(costPanel);
        }

        // upgrade button (only show if upgrade is possible and affordable)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setOpaque(false);

        if (upgradeCost > 0 && Farm.money >= upgradeCost) {
            JButton upgradeButton = UIUtils.createRoundedButton("lvl up!", 70, 22);
            upgradeButton.setFont(Farm.fonts.minecraftiaFont);
            upgradeButton.addActionListener(_ -> upgradeArea(area));
            buttonPanel.add(upgradeButton);
        }

        infoPanel.add(levelDisplayPanel, BorderLayout.WEST);
        infoPanel.add(buttonPanel, BorderLayout.EAST);

        // TODO: action handling
        JLabel actionLabel = new JLabel("action handling coming soon!");
        actionLabel.setFont(Farm.fonts.minecraftiaFont);
        actionLabel.setForeground(Colors.darkBeigeColor);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(infoPanel, BorderLayout.CENTER);

        sectionPanel.add(topPanel, BorderLayout.NORTH);
        sectionPanel.add(actionLabel, BorderLayout.SOUTH);

        return sectionPanel;
    }

    private Map.MapLevels getCurrentAreaLevel(Map.MapArea area) {
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            return Farm.entitiesHandler.map.mapAreasLevels.getOrDefault(area, Map.MapLevels.LEVEL_0);
        }
        return Map.MapLevels.LEVEL_0; // default
    }

    private String getLevelText(Map.MapLevels level) {
        return switch (level) {
            case LEVEL_0 -> "level 0";
            case LEVEL_1 -> "level 1";
            case LEVEL_2 -> "level 2";
            case LEVEL_3 -> "level 3";
            case LEVEL_Star -> "level";
        };
    }

    private int getUpgradeCost( Map.MapLevels currentLevel) {
        return switch (currentLevel) {
            case LEVEL_0 -> UPGRADE_COSTS[0];
            case LEVEL_1 -> UPGRADE_COSTS[1];
            case LEVEL_2 -> UPGRADE_COSTS[2];
            case LEVEL_3 -> UPGRADE_COSTS[3];
            case LEVEL_Star -> 0; // max level
        };
    }

    private void upgradeArea(Map.MapArea area) {
        Map.MapLevels currentLevel = getCurrentAreaLevel(area);
        int cost = getUpgradeCost(currentLevel);
        
        if (Farm.money >= cost && cost > 0) {
            // deduct money
            Farm.subtractMoney(cost);

            // upgrade area in a map
            if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
                switch (area) {
                    case FIELDS -> Farm.entitiesHandler.map.levelUpFieldsArea();
                    case HOUSE -> Farm.entitiesHandler.map.levelUpHouseArea();
                    case COOP -> Farm.entitiesHandler.map.levelUpCoopArea();
                    case COWS -> Farm.entitiesHandler.map.levelUpCowsArea();
                    case ORCHARD -> Farm.entitiesHandler.map.levelUpOrchardArea();
                    case PARK -> Farm.entitiesHandler.map.levelUpParkArea();
                }
            }
            
            // increment level ups done for perfection tracking
            Farm.levelUpsDone++;
            
            // refresh UI
            if (Farm.menuPanel != null) {
                Farm.menuPanel.refreshResourcesDisplay();
            }
        }
    }
}

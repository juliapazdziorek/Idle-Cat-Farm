package UI;

import Game.Farm;
import Game.FieldsHandler;
import Game.MoneyHandler;
import Map.Map;
import Map.Field;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

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
        contentPanel.add(createAreaSection(Map.MapArea.HOUSE, "happyStandingCat"));
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(createFieldsSection());
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

        if (upgradeCost > 0 && MoneyHandler.canAfford(upgradeCost)) {
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

    private JPanel createFieldsSection() {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBackground(Colors.lightBeigeColor);
        sectionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.beigeColor, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        // header with icon and name
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        headerPanel.setOpaque(false);

        // fields icon
        JLabel iconLabel = new JLabel();
        try {
            if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey("fields")) {
                BufferedImage iconImage = Farm.resourceHandler.iconsMap.get("fields");
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

        JLabel nameLabel = new JLabel("Fields");
        nameLabel.setFont(Farm.fonts.dayDreamFont12f);
        nameLabel.setForeground(Colors.darkBeigeColor);

        headerPanel.add(iconLabel);
        headerPanel.add(nameLabel);

        // get current level info
        Map.MapLevels currentLevel = getCurrentAreaLevel(Map.MapArea.FIELDS);
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

        // add field status display between level and button
        Map map = Farm.entitiesHandler != null ? Farm.entitiesHandler.map : null;
        if (map != null) {
            java.util.Map<Field.FieldType, Field> activeFields = getActiveFields(map);

            if (!activeFields.isEmpty()) {

                // add divider line before fields
                JPanel dividerPanel = new JPanel();
                dividerPanel.setOpaque(false);
                dividerPanel.setPreferredSize(new Dimension(180, 1));
                dividerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
                dividerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colors.beigeColor));
                levelDisplayPanel.add(Box.createVerticalStrut(6));
                levelDisplayPanel.add(dividerPanel);

                JPanel fieldsStatusPanel = new JPanel();
                fieldsStatusPanel.setLayout(new BoxLayout(fieldsStatusPanel, BoxLayout.Y_AXIS));
                fieldsStatusPanel.setOpaque(false);
                fieldsStatusPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

                // add fields in specific order: EAST on top, then WEST
                int unlockedFields = activeFields.size();
                if (unlockedFields == 1) {
                    Field singleField = activeFields.values().iterator().next();
                    fieldsStatusPanel.add(createFieldEntry(singleField, true));
                } else {
                    boolean first = true;
                    Field eastField = activeFields.get(Field.FieldType.EAST);
                    if (eastField != null) {
                        fieldsStatusPanel.add(createFieldEntry(eastField, false));
                        first = false;
                    }
                    Field westField = activeFields.get(Field.FieldType.WEST);
                    if (westField != null) {
                        if (!first) {
                            fieldsStatusPanel.add(Box.createVerticalStrut(2));
                        }
                        fieldsStatusPanel.add(createFieldEntry(westField, false));
                    }
                }

                levelDisplayPanel.add(fieldsStatusPanel);
            }
        }

        // upgrade button (only show if upgrade is possible and affordable)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setOpaque(false);

        if (upgradeCost > 0 && MoneyHandler.canAfford(upgradeCost)) {
            JButton upgradeButton = UIUtils.createRoundedButton("lvl up!", 70, 22);
            upgradeButton.setFont(Farm.fonts.minecraftiaFont);
            upgradeButton.addActionListener(_ -> upgradeArea(Map.MapArea.FIELDS));
            buttonPanel.add(upgradeButton);
        }

        infoPanel.add(levelDisplayPanel, BorderLayout.WEST);
        infoPanel.add(buttonPanel, BorderLayout.EAST);

        sectionPanel.add(headerPanel, BorderLayout.NORTH);
        sectionPanel.add(infoPanel, BorderLayout.CENTER);

        return sectionPanel;
    }

    private java.util.Map<Field.FieldType, Field> getActiveFields(Map map) {
        java.util.Map<Field.FieldType, Field> activeFields = new HashMap<>();
        
        Field eastField = FieldsHandler.getFieldByType(Field.FieldType.EAST);
        if (eastField != null) {
            activeFields.put(Field.FieldType.EAST, eastField);
        }
        
        Field westField = FieldsHandler.getFieldByType(Field.FieldType.WEST);
        if (westField != null) {
            activeFields.put(Field.FieldType.WEST, westField);
        }
        
        return activeFields;
    }

    private JPanel createFieldEntry(Field field) {
    return createFieldEntry(field, false);
    }

    // Overloaded to support single field label logic
    private JPanel createFieldEntry(Field field, boolean singleField) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        fieldPanel.setOpaque(false);
        fieldPanel.setPreferredSize(new Dimension(200, 20));
        fieldPanel.setMaximumSize(new Dimension(200, 20));
        fieldPanel.setMinimumSize(new Dimension(200, 20));

        String fieldName;
        if (singleField) {
            fieldName = "field:";
        } else {
            fieldName = field.getFieldType().name().toLowerCase() + " field:";
        }
        JLabel fieldNameLabel = new JLabel(fieldName);
        fieldNameLabel.setFont(Farm.fonts.minecraftiaFont);
        fieldNameLabel.setForeground(Colors.darkBeigeColor);
        int labelWidth = fieldName.equals("field:") ? 50 : 90;
        fieldNameLabel.setPreferredSize(new Dimension(labelWidth, 16));

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        statusPanel.setOpaque(false);

        Game.FarmResourcesHandler.ResourceType cropType = field.getCurrentCropType();
        if (cropType != null) {
            String fieldState = getFieldState(field);
            addFieldStateDisplay(statusPanel, cropType, fieldState);
        } else {
            JLabel emptyLabel = new JLabel("empty");
            emptyLabel.setFont(Farm.fonts.minecraftiaFont);
            emptyLabel.setForeground(Colors.darkBeigeColor);
            statusPanel.add(emptyLabel);
        }

        fieldPanel.add(fieldNameLabel);
        fieldPanel.add(statusPanel);
        return fieldPanel;
    }

    private String getFieldState(Field field) {
        Field.FieldState state = field.getCurrentState();
        return switch (state) {
            case EMPTY -> "empty";
            case PLANTING -> "planting...";
            case GROWING -> "growing...";
            case READY_TO_HARVEST -> "ready!";
        };
    }

    private void addFieldStateDisplay(JPanel statusPanel, Game.FarmResourcesHandler.ResourceType cropType, String state) {

        // add crop icon
        JLabel cropIconLabel = new JLabel();
        String iconKey = getIconKeyForResourceType(cropType);
        try {
            if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey(iconKey)) {
                BufferedImage cropImage = Farm.resourceHandler.iconsMap.get(iconKey);
                ImageIcon cropIcon = new ImageIcon(cropImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
                cropIconLabel.setIcon(cropIcon);
            } else {
                cropIconLabel.setText("?");
                cropIconLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                cropIconLabel.setForeground(Colors.darkBeigeColor);
            }
        } catch (Exception e) {
            cropIconLabel.setText("?");
            cropIconLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
            cropIconLabel.setForeground(Colors.darkBeigeColor);
        }

        // add state text
        JLabel stateLabel = new JLabel(state);
        stateLabel.setFont(Farm.fonts.minecraftiaFont);
        stateLabel.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
        stateLabel.setForeground(Colors.darkBeigeColor);

        statusPanel.add(cropIconLabel);
        statusPanel.add(stateLabel);
    }

    private String getIconKeyForResourceType(Game.FarmResourcesHandler.ResourceType resourceType) {
        return switch (resourceType) {
            case CORN -> "corn";
            case CARROT -> "carrot";
            case CAULIFLOWER -> "cauliflower";
            case TOMATO -> "tomato";
            case EGGPLANT -> "eggplant";
            case LETTUCE -> "lettuce";
            case WHEAT -> "wheat";
            case PUMPKIN -> "pumpkin";
            case RADISH -> "radish";
            case STAR_FRUIT -> "starFruit";
            case CUCUMBER -> "cucumber";
            case APPLE -> "apple";
            case ORANGE -> "orange";
            case PEAR -> "pear";
            case PEACH -> "peach";
            default -> "lettuce"; // fallback
        };
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
        
        if (MoneyHandler.canAfford(cost) && cost > 0) {
            // deduct money
            MoneyHandler.subtractMoney(cost);

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

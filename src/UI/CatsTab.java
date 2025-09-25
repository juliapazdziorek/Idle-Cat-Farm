package UI;

import Entities.Characters.FarmCat;
import Game.Farm;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CatsTab {
    
    public CatsTab() {
    }
    
    public JPanel createCatsTab() {
        UIUtils.RoundedPanel panel = new UIUtils.RoundedPanel(12);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Colors.beigeColor);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // title
        JLabel titleLabel = new JLabel("Cats", JLabel.CENTER);
        titleLabel.setFont(Farm.fonts.dayDreamFont18f);
        titleLabel.setForeground(Colors.darkBeigeColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // content with scroll
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        
        // add each cat from the farm
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.farmCatList != null) {
            for (int i = 0; i < Farm.entitiesHandler.farmCatList.size(); i++) {
                FarmCat cat = Farm.entitiesHandler.farmCatList.get(i);
                contentPanel.add(createCatSection(cat, i + 1));
                
                // add spacing between cats (except after the last one)
                if (i < Farm.entitiesHandler.farmCatList.size() - 1) {
                    contentPanel.add(Box.createVerticalStrut(8));
                }
            }
        }
        
        // scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        UIUtils.customizeScrollBar(scrollPane);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createCatSection(FarmCat cat, int catNumber) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBackground(Colors.lightBeigeColor);
        sectionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.beigeColor, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        sectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        sectionPanel.setMinimumSize(new Dimension(200, 140));
        sectionPanel.setPreferredSize(new Dimension(300, 140));
        
        // header with icon and name
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        headerPanel.setOpaque(false);
        
        // cat icon based on color
        JLabel iconLabel = new JLabel();
        
        String iconKey = "";
        switch (cat.getColor()) {
            case WHITE -> iconKey = "whiteCat";
            case GREY -> iconKey = "greyCat";
            case GINGER -> iconKey = "gingerCat";
            case TRICOLOR -> iconKey = "tricolorCat";
        }
        
        try {
            if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey(iconKey)) {
                ImageIcon icon = new ImageIcon(Farm.resourceHandler.iconsMap.get(iconKey));
                Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                iconLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                iconLabel.setText("🐱");
                iconLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
            }
        } catch (Exception e) {
            iconLabel.setText("🐱");
            iconLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        }
        iconLabel.setForeground(Colors.darkBeigeColor);
        
        // cat name with color
        String catName = cat.getColor().toString().charAt(0) + 
                        cat.getColor().toString().substring(1).toLowerCase() + " Cat";
        JLabel nameLabel = new JLabel(catName);
        nameLabel.setFont(Farm.fonts.dayDreamFont12f);
        nameLabel.setForeground(Colors.darkBeigeColor);
        
        headerPanel.add(iconLabel);
        headerPanel.add(nameLabel);
        
        // stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        // create stat bars
        statsPanel.add(createLevelBarWithButton("farming level", cat));
        statsPanel.add(Box.createVerticalStrut(3));
        statsPanel.add(createStatBar("energy", cat.getEnergy(), 100, cat));
        statsPanel.add(Box.createVerticalStrut(3));
        statsPanel.add(createStatBar("watering can", cat.getWateringCan(), 100, cat));
        
        // assemble section
        sectionPanel.add(headerPanel, BorderLayout.NORTH);
        sectionPanel.add(statsPanel, BorderLayout.CENTER);
        
        return sectionPanel;
    }
    
    private JPanel createStatBar(String statName, int currentValue, int maxValue, FarmCat cat) {
        JPanel statPanel = new JPanel(new BorderLayout(8, 0));
        statPanel.setOpaque(false);
        
        // stat label
        JLabel statLabel = new JLabel(statName + ":");
        statLabel.setFont(Farm.fonts.minecraftiaFont);
        statLabel.setForeground(Colors.darkBeigeColor);
            statLabel.setPreferredSize(new Dimension(120, 22));
        
        // progress bar
        JProgressBar progressBar = UIUtils.createRoundedProgressBar(0, maxValue, currentValue, currentValue + "/" + maxValue, Farm.fonts.minecraftiaFont);
        progressBar.setPreferredSize(new Dimension(175, 22));
        
        JPanel spacingPanel = new JPanel();
        spacingPanel.setOpaque(false);
        spacingPanel.setPreferredSize(new Dimension(5, 22));
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(progressBar);
        
        if (statName.equals("energy") || statName.equals("watering can")) {
            JButton actionButton = UIUtils.createRoundedButton("", 22, 22);
            if (statName.equals("energy")) {
                if (cat.isSleeping() || cat.getActionState() == FarmCat.CatActionState.GOING_TO_SLEEP) {
                    try {
                        if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey("sun")) {
                            ImageIcon icon = new ImageIcon(Farm.resourceHandler.iconsMap.get("sun"));
                            Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                            actionButton.setIcon(new ImageIcon(scaledImage));
                        } else {
                            actionButton.setText("☀️");
                        }
                    } catch (Exception e) {
                        actionButton.setText("☀️");
                    }
                    actionButton.addActionListener(_ -> {
                        cat.wakeUp();
                        Farm.menuPanel.refreshResourcesDisplay();
                    });
                } else {
                    // cat is awake - show sleep button
                    try {
                        if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey("zzz")) {
                            ImageIcon icon = new ImageIcon(Farm.resourceHandler.iconsMap.get("zzz"));
                            Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                            actionButton.setIcon(new ImageIcon(scaledImage));
                        } else {
                            actionButton.setText("+");
                        }
                    } catch (Exception e) {
                        actionButton.setText("+");
                    }
                    actionButton.addActionListener(_ -> {
                        if (cat.tryGoToSleep()) {
                            Farm.menuPanel.refreshResourcesDisplay();
                        }
                    });
                }
            } else {
                try {
                    if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey("waterCan")) {
                        ImageIcon icon = new ImageIcon(Farm.resourceHandler.iconsMap.get("waterCan"));
                        Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                        actionButton.setIcon(new ImageIcon(scaledImage));
                    } else {
                        actionButton.setText("+");
                    }
                } catch (Exception e) {
                    actionButton.setText("+");
                }
                actionButton.addActionListener(_ -> {
                    cat.setWateringCan(100);
                    Farm.menuPanel.refreshResourcesDisplay();
                });
            }
            actionButton.setFont(Farm.fonts.minecraftiaFont);
            rightPanel.add(actionButton);
        }
        
        statPanel.add(statLabel, BorderLayout.WEST);
        statPanel.add(spacingPanel, BorderLayout.CENTER);
        statPanel.add(rightPanel, BorderLayout.EAST);
        
        return statPanel;
    }
    
    private JPanel createLevelBarWithButton(String statName, FarmCat cat) {
        if (cat.getFarmingLevel() == FarmCat.FarmingLevel.LVLSTAR) {
            JPanel levelPanel = new JPanel(new BorderLayout(8, 0));
            levelPanel.setOpaque(false);
            
            // level label - same as other stats
            JLabel levelLabel = new JLabel(statName + ":");
            levelLabel.setFont(Farm.fonts.minecraftiaFont);
            levelLabel.setForeground(Colors.darkBeigeColor);
            levelLabel.setPreferredSize(new Dimension(120, 22));
            levelPanel.add(levelLabel, BorderLayout.WEST);
            
            JLabel starDisplayLabel = new JLabel();
            starDisplayLabel.setOpaque(false);
            starDisplayLabel.setPreferredSize(new Dimension(175, 22));
            starDisplayLabel.setFont(Farm.fonts.minecraftiaFont);
            starDisplayLabel.setForeground(Colors.darkBeigeColor);
            
            if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey("star")) {
                try {
                    BufferedImage starImage = Farm.resourceHandler.iconsMap.get("star");
                    ImageIcon starIcon = new ImageIcon(starImage.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
                    starDisplayLabel.setText("lvl ");
                    starDisplayLabel.setIcon(starIcon);
                    starDisplayLabel.setHorizontalTextPosition(SwingConstants.LEFT);
                } catch (Exception e) {
                    starDisplayLabel.setText("lvl ★");
                }
            } else {
                starDisplayLabel.setText("lvl ★");
            }
            
            levelPanel.add(starDisplayLabel, BorderLayout.CENTER);
            return levelPanel;
        } else {
            JPanel levelPanel = new JPanel();
            levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.Y_AXIS));
            levelPanel.setOpaque(false);
            
            JPanel topRowPanel = new JPanel(new BorderLayout(8, 0));
            topRowPanel.setOpaque(false);
            
            // level label
            JLabel levelLabel = new JLabel(statName + ":");
            levelLabel.setFont(Farm.fonts.minecraftiaFont);
            levelLabel.setForeground(Colors.darkBeigeColor);
            levelLabel.setPreferredSize(new Dimension(120, 22));
            topRowPanel.add(levelLabel, BorderLayout.WEST);
            
            // level display with button next to it
            JPanel levelWithButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
            levelWithButtonPanel.setOpaque(false);
            
            JLabel levelValueLabel = new JLabel(cat.getLevelDisplayText());
            levelValueLabel.setFont(Farm.fonts.minecraftiaFont);
            levelValueLabel.setForeground(Colors.darkBeigeColor);
            levelWithButtonPanel.add(levelValueLabel);
            
            // level up button next to level text (only if can level up)
            if (cat.hasEnoughExperienceToLevelUp()) {
                JButton levelUpButton = UIUtils.createRoundedButton("lvl up!", 60, 20);
                levelUpButton.setFont(Farm.fonts.minecraftiaFont);
                levelUpButton.setBorder(BorderFactory.createEmptyBorder(-1, 0, 1, 0));
                levelUpButton.addActionListener(_ -> {
                    cat.levelUp();
                    if (Farm.menuPanel != null) {
                        Farm.menuPanel.refreshResourcesDisplay();
                    }
                });
                levelWithButtonPanel.add(levelUpButton);
            }
            
            topRowPanel.add(levelWithButtonPanel, BorderLayout.CENTER);
            levelPanel.add(topRowPanel);
            
            JPanel expBarPanel = new JPanel(new BorderLayout(5, 0));
            expBarPanel.setOpaque(false);
            
            JLabel emptyLabel = new JLabel();
            emptyLabel.setPreferredSize(new Dimension(120, 22));
            expBarPanel.add(emptyLabel, BorderLayout.WEST);
            
            JProgressBar expBar = UIUtils.createRoundedProgressBar(0, cat.getRequiredExperience(), cat.getExperience(), 
                cat.getExperience() + "/" + cat.getRequiredExperience() + " exp", Farm.fonts.minecraftiaFont);
            expBar.setPreferredSize(new Dimension(175, 22));
            expBarPanel.add(expBar, BorderLayout.CENTER);
            
            levelPanel.add(expBarPanel);
            return levelPanel;
        }
    }
}

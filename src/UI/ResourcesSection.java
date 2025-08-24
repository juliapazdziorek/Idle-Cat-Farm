package UI;

import Game.Farm;
import Game.FarmResourcesHandler;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ResourcesSection {
    
    public ResourcesSection() {}
    
    public JPanel createResourcesSection() {

        // main panel
        JPanel resourcesPanel = new JPanel(new BorderLayout());
        resourcesPanel.setOpaque(false);

        // title
        JLabel resourcesTitle = new JLabel("Resources", JLabel.CENTER);
        resourcesTitle.setFont(Farm.fonts.dayDreamFont14f);
        resourcesTitle.setForeground(Colors.darkBeigeColor);
        resourcesTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        // wrapper panel
        JPanel resourcesWrapper = new JPanel(new BorderLayout());
        resourcesWrapper.setBackground(Colors.lightBeigeColor);
        resourcesWrapper.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // content panel
        JPanel resourcesContent = new JPanel(new GridBagLayout());
        resourcesContent.setBackground(Colors.lightBeigeColor);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        
        // add resource sections
        addResourceSection(resourcesContent, gbc, "Crops", getCropResources());
        addResourceSection(resourcesContent, gbc, "Fruits", getFruitResources());
        addResourceSection(resourcesContent, gbc, "Animal Products", getAnimalResources());

        // vertical glue to push content to the top
        gbc.weighty = 1.0;
        resourcesContent.add(Box.createVerticalGlue(), gbc);
        
        resourcesWrapper.add(resourcesContent, BorderLayout.NORTH);

        // scroll pane
        JScrollPane resourcesScrollPane = new JScrollPane(resourcesWrapper);
        resourcesScrollPane.setBorder(null);
        resourcesScrollPane.setOpaque(false);
        resourcesScrollPane.getViewport().setOpaque(false);
        resourcesScrollPane.setPreferredSize(new Dimension(0, 180));
        resourcesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resourcesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIUtils.customizeScrollBar(resourcesScrollPane);
        
        resourcesPanel.add(resourcesTitle, BorderLayout.NORTH);
        resourcesPanel.add(resourcesScrollPane, BorderLayout.CENTER);
        return resourcesPanel;
    }
    
    private void addResourceSection(JPanel parent, GridBagConstraints gbc, String sectionTitle, ResourceInfo[] resources) {
        if (resources.length == 0) {
            return;
        }

        // section title
        JLabel titleLabel = new JLabel(sectionTitle);
        titleLabel.setFont(Farm.fonts.minecraftiaFont);
        titleLabel.setForeground(Colors.darkBeigeColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 10, 5, 10));
        gbc.insets = new Insets(0, 0, 0, 0);
        parent.add(titleLabel, gbc);
        gbc.gridy++;

        // add each resource
        for (ResourceInfo resource : resources) {
            JPanel resourceItem = createListResourceItem(resource.icon, resource.name, getResourceQuantity(resource.type));
            gbc.insets = new Insets(2, 0, 2, 0);
            parent.add(resourceItem, gbc);
            gbc.gridy++;
        }

        // add small spacing between sections (except after last)
        if (!sectionTitle.equals("Animal Products")) {
            gbc.insets = new Insets(5, 0, 0, 0);
            parent.add(Box.createVerticalStrut(5), gbc);
            gbc.gridy++;
        }
    }
    
    private JPanel createListResourceItem(String iconKey, String name, int quantity) {

        // panel
        JPanel resourcePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        resourcePanel.setBackground(Colors.lightBeigeColor);
        resourcePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.beigeColor, 1),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        resourcePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        // icon button, name and quantity labels
        JButton iconButton = new JButton();
        iconButton.setPreferredSize(new Dimension(28, 28));
        iconButton.setBackground(Colors.beigeColor);
        iconButton.setForeground(Colors.darkBeigeColor);
        iconButton.setBorder(null);
        iconButton.setContentAreaFilled(false);
        iconButton.setFocusPainted(false);
        iconButton.setOpaque(false);

        iconButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                JButton button = (JButton) c;

                if (button.getModel().isPressed()) {
                    g2.setColor(Colors.beigeColor.darker());
                } else if (button.getModel().isRollover()) {
                    g2.setColor(Colors.beigeColor.brighter());
                } else {
                    g2.setColor(Colors.beigeColor);
                }
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);

                g2.setColor(Colors.beigeColor.darker());
                g2.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 8, 8);
                
                g2.dispose();

                if (button.getIcon() != null) {
                    Icon icon = button.getIcon();
                    int x = (c.getWidth() - icon.getIconWidth()) / 2;
                    int y = (c.getHeight() - icon.getIconHeight()) / 2;
                    icon.paintIcon(c, g, x, y);
                } else if (button.getText() != null && !button.getText().isEmpty()) {
                    g.setColor(button.getForeground());
                    g.setFont(button.getFont());
                    FontMetrics fm = g.getFontMetrics();
                    int x = (c.getWidth() - fm.stringWidth(button.getText())) / 2;
                    int y = (c.getHeight() + fm.getAscent()) / 2 - 2;
                    g.drawString(button.getText(), x, y);
                }
            }
        });
        
        try {
            if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey(iconKey)) {
                ImageIcon icon = new ImageIcon(Farm.resourceHandler.iconsMap.get(iconKey));
                Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                iconButton.setIcon(new ImageIcon(scaledImage));
            } else {
                iconButton.setText("?");
                iconButton.setFont(new Font("Dialog", Font.PLAIN, 12));
                iconButton.setForeground(Colors.darkBeigeColor);
            }
        } catch (Exception exception) {
            iconButton.setText("?");
            iconButton.setFont(new Font("Dialog", Font.PLAIN, 12));
            iconButton.setForeground(Colors.darkBeigeColor);
        }
        
        // placeholder action listener (will do nothing for now)
        iconButton.addActionListener(e -> {
            System.out.println("clicked on " + name + " resource button");
        });

        // name label
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(Farm.fonts.minecraftiaFont);
        nameLabel.setForeground(Colors.darkBeigeColor);
        nameLabel.setPreferredSize(new Dimension(80, 20));

        // quantity label
        JLabel quantityLabel = new JLabel("x" + quantity);
        quantityLabel.setFont(Farm.fonts.minecraftiaFont);
        quantityLabel.setForeground(Colors.darkBeigeColor);
        
        resourcePanel.add(iconButton);
        resourcePanel.add(Box.createHorizontalStrut(3));
        resourcePanel.add(nameLabel);
        resourcePanel.add(Box.createHorizontalGlue());
        resourcePanel.add(quantityLabel);
        
        return resourcePanel;
    }
    
    // helper class for resource information
    private static class ResourceInfo {
        String icon, name;
        FarmResourcesHandler.ResourceType type;
        
        ResourceInfo(String icon, String name, FarmResourcesHandler.ResourceType type) {
            this.icon = icon; 
            this.name = name; 
            this.type = type;
        }
    }
    
    private ResourceInfo[] getCropResources() {
        ResourceInfo[] allCrops = {
            new ResourceInfo("lettuce", "Lettuce", FarmResourcesHandler.ResourceType.LETTUCE),
            new ResourceInfo("tomato", "Tomato", FarmResourcesHandler.ResourceType.TOMATO),
            new ResourceInfo("corn", "Corn", FarmResourcesHandler.ResourceType.CORN),
            new ResourceInfo("carrot", "Carrot", FarmResourcesHandler.ResourceType.CARROT),
            new ResourceInfo("wheat", "Wheat", FarmResourcesHandler.ResourceType.WHEAT),
            new ResourceInfo("cucumber", "Cucumber", FarmResourcesHandler.ResourceType.CUCUMBER),
            new ResourceInfo("radish", "Radish", FarmResourcesHandler.ResourceType.RADISH),
            new ResourceInfo("cauliflower", "Cauliflower", FarmResourcesHandler.ResourceType.CAULIFLOWER),
            new ResourceInfo("eggplant", "Eggplant", FarmResourcesHandler.ResourceType.EGGPLANT),
            new ResourceInfo("pumpkin", "Pumpkin", FarmResourcesHandler.ResourceType.PUMPKIN),
            new ResourceInfo("star", "Star", FarmResourcesHandler.ResourceType.STAR)
        };

        return filterUnlockedResources(allCrops);
    }
    
    private ResourceInfo[] getFruitResources() {
        ResourceInfo[] allFruits = {
            new ResourceInfo("apple", "Apple", FarmResourcesHandler.ResourceType.APPLE),
            new ResourceInfo("pear", "Pear", FarmResourcesHandler.ResourceType.PEAR),
            new ResourceInfo("peach", "Peach", FarmResourcesHandler.ResourceType.PEACH),
            new ResourceInfo("orange", "Orange", FarmResourcesHandler.ResourceType.ORANGE)
        };

        return filterUnlockedResources(allFruits);
    }
    
    private ResourceInfo[] getAnimalResources() {
        ResourceInfo[] allAnimalProducts = {
            new ResourceInfo("egg", "Egg", FarmResourcesHandler.ResourceType.EGG),
            new ResourceInfo("milk", "Milk", FarmResourcesHandler.ResourceType.MILK),
            new ResourceInfo("chockMilk", "Choc Milk", FarmResourcesHandler.ResourceType.CHOCOLATE_MILK),
            new ResourceInfo("berryMilk", "Berry Milk", FarmResourcesHandler.ResourceType.STRAWBERRY_MILK)
        };

        return filterUnlockedResources(allAnimalProducts);
    }
    
    // filter only unlocked resources
    private ResourceInfo[] filterUnlockedResources(ResourceInfo[] allResources) {
        if (Farm.farmResources == null) {
            return new ResourceInfo[0];
        }
        
        return Arrays.stream(allResources)
            .filter(resource -> Farm.farmResources.isUnlocked(resource.type))
            .toArray(ResourceInfo[]::new);
    }

    // get the quantity of resource type
    private int getResourceQuantity(FarmResourcesHandler.ResourceType resourceType) {
        if (Farm.farmResources != null) {
            return Farm.farmResources.getQuantity(resourceType);
        }
        return 0;
    }
}

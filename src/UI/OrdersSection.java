package UI;

import Game.Farm;
import Game.OrdersHandler;
import Game.FarmResourcesHandler.ResourceType;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class OrdersSection {
    
    private JPanel ordersContent;
    
    public OrdersSection() {}
    
    public JPanel createOrdersSection() {

        // main panel
        JPanel ordersPanel = new JPanel(new BorderLayout());
        ordersPanel.setOpaque(false);
        ordersPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // title
        JLabel ordersTitle = new JLabel("Orders", JLabel.CENTER);
        ordersTitle.setFont(Farm.fonts.dayDreamFont14f);
        ordersTitle.setForeground(Colors.darkBeigeColor);
        ordersTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        
        // content panel
        ordersContent = new JPanel();
        ordersContent.setLayout(new BoxLayout(ordersContent, BoxLayout.Y_AXIS));
        ordersContent.setBackground(Colors.lightBeigeColor);
        ordersContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        loadOrders();
        
        // scroll pane
        JScrollPane ordersScrollPane = new JScrollPane(ordersContent);
        ordersScrollPane.setBorder(null);
        ordersScrollPane.setOpaque(false);
        ordersScrollPane.getViewport().setOpaque(false);
        ordersScrollPane.setPreferredSize(new Dimension(0, 150));
        UIUtils.customizeScrollBar(ordersScrollPane);
        
        ordersPanel.add(ordersTitle, BorderLayout.NORTH);
        ordersPanel.add(ordersScrollPane, BorderLayout.CENTER);
        
        return ordersPanel;
    }
    
    private void loadOrders() {
        ordersContent.removeAll();
        if (Farm.ordersHandler != null && !Farm.ordersHandler.orders.isEmpty()) {
            for (int i = 0; i < Farm.ordersHandler.orders.size(); i++) {
                ArrayList<OrdersHandler.OrderPart> order = Farm.ordersHandler.orders.get(i);
                JPanel orderItem = createOrderItem(order);
                ordersContent.add(orderItem);
                
                if (i < Farm.ordersHandler.orders.size() - 1) {
                    ordersContent.add(Box.createVerticalStrut(3));
                }
            }
        }
        
        ordersContent.revalidate();
        ordersContent.repaint();
    }
    
    private JPanel createOrderItem(ArrayList<OrdersHandler.OrderPart> orderParts) {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBackground(Colors.lightBeigeColor);
        orderPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.beigeColor, 1),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        orderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        JPanel resourcesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 5));
        resourcesPanel.setBackground(Colors.lightBeigeColor);
        resourcesPanel.setOpaque(false);

        for (OrdersHandler.OrderPart orderPart : orderParts) {

            // icon
            JLabel iconLabel = new JLabel();
            BufferedImage resourceIcon = getResourceIcon(orderPart.resourceType);
            if (resourceIcon != null) {
                ImageIcon icon = new ImageIcon(resourceIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                iconLabel.setIcon(icon);
            } else {
                iconLabel.setText("?");
                iconLabel.setFont(Farm.fonts.minecraftiaFont);
                iconLabel.setForeground(Colors.darkBeigeColor);
            }
            resourcesPanel.add(iconLabel);
            
            // quantity label
            JLabel quantityLabel = new JLabel("x" + orderPart.quantity);
            quantityLabel.setFont(Farm.fonts.minecraftiaFont);
            quantityLabel.setForeground(Colors.darkBeigeColor);
            resourcesPanel.add(quantityLabel);
            
            resourcesPanel.add(Box.createHorizontalStrut(4));
        }
        
        // complete button - only show if order can be fulfilled
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        buttonPanel.setBackground(Colors.lightBeigeColor);
        buttonPanel.setOpaque(false);
        
        // check if order can be fulfilled
        boolean canFulfill = Farm.ordersHandler != null && Farm.ordersHandler.canFulfillOrder(orderParts);
        
        if (canFulfill) {
            JButton fulfillButton = UIUtils.createRoundedButton("done", 60, 25);
            fulfillButton.setFont(Farm.fonts.minecraftiaFont);
            
            fulfillButton.addActionListener(_ -> {
                if (Farm.ordersHandler != null && Farm.ordersHandler.fulfillOrder(orderParts)) {
                    loadOrders();
                }
            });
            
            buttonPanel.add(fulfillButton);
        }
        
        orderPanel.add(resourcesPanel, BorderLayout.CENTER);
        orderPanel.add(buttonPanel, BorderLayout.EAST);
        
        return orderPanel;
    }
    
    private BufferedImage getResourceIcon(ResourceType resourceType) {
        if (Farm.resourceHandler == null) {
            return null;
        }

        String iconName = switch (resourceType) {
            case CHOCOLATE_MILK -> "chockMilk";
            case STRAWBERRY_MILK -> "berryMilk";
            default ->
                    resourceType.name().toLowerCase();
        };

        return Farm.resourceHandler.iconsMap.get(iconName);
    }


    public void refreshOrders() {
        if (Farm.ordersHandler != null) {
            loadOrders();
        }
    }
    
    public void updateOrderCount() {
        if (Farm.ordersHandler != null) {
            Farm.ordersHandler.maintainOrderCount();
            loadOrders();
        }
    }
}

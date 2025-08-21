package UI;

import Game.Farm;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;

public class WorkstationTab {
    
    private final OrdersSection ordersSection;
    private final ResourcesSection resourcesSection;
    
    public WorkstationTab() {
        ordersSection = new OrdersSection();
        resourcesSection = new ResourcesSection();
    }
    
    public JPanel createWorkstationTab() {

        // main panel
        UIUtils.RoundedPanel panel = new UIUtils.RoundedPanel(12);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Colors.beigeColor);

        // title
        JLabel titleLabel = new JLabel("Workstation", JLabel.CENTER);
        titleLabel.setFont(Farm.fonts.dayDreamFont18f);
        titleLabel.setForeground(Colors.darkBeigeColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // content panel with orders and resources sections
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        
        JPanel orders = ordersSection.createOrdersSection();
        JPanel resources = resourcesSection.createResourcesSection();
        
        contentPanel.add(orders, BorderLayout.NORTH);
        contentPanel.add(resources, BorderLayout.CENTER);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
}

package UI;

import Game.Farm;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;

public class FarmTab {
    
    public FarmTab() {
    }
    
    public JPanel createFarmTab() {
        UIUtils.RoundedPanel panel = new UIUtils.RoundedPanel(12);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Colors.beigeColor);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("Farm", JLabel.CENTER);
        titleLabel.setFont(Farm.fonts.dayDreamFont18f);
        titleLabel.setForeground(Colors.darkBeigeColor);
        panel.add(titleLabel, BorderLayout.NORTH);

        JLabel placeholderLabel = new JLabel("Farm management coming soon!", JLabel.CENTER);
        placeholderLabel.setFont(Farm.fonts.minecraftiaFont);
        placeholderLabel.setForeground(Colors.darkBeigeColor);
        panel.add(placeholderLabel, BorderLayout.CENTER);
        
        return panel;
    }
}

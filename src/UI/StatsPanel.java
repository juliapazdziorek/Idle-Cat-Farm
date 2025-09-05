package UI;

import Game.Farm;
import Game.MoneyHandler;
import Resources.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StatsPanel extends JPanel {

    public StatsPanel() {
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        setBackground(Colors.lightBeigeColor);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.beigeColor, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        setPreferredSize(new Dimension(0, 35));

        // money info
        JPanel moneyPanel = createInfoItem("coin", String.valueOf(MoneyHandler.getMoney()));

        // cats info
        JPanel catsPanel = createInfoItem("standingCat", String.valueOf(Farm.catsCount));

        // perfection info
        int perfectionPercent = (Farm.levelUpsDone * 100) / Farm.MAX_LEVEL_UPS;
        JPanel perfectionPanel = createInfoItem("star", perfectionPercent + "%");

        add(moneyPanel);
        add(catsPanel);
        add(perfectionPanel);
    }

    private JPanel createInfoItem(String iconKey, String value) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        itemPanel.setOpaque(false);

        // icon
        JLabel iconLabel = new JLabel();
        try {
            if (Farm.resourceHandler != null && Farm.resourceHandler.iconsMap.containsKey(iconKey)) {
                BufferedImage iconImage = Farm.resourceHandler.iconsMap.get(iconKey);
                ImageIcon icon = new ImageIcon(iconImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
                iconLabel.setIcon(icon);
            } else {
                iconLabel.setText("?");
                iconLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                iconLabel.setForeground(Colors.darkBeigeColor);
            }
        } catch (Exception e) {
            iconLabel.setText("?");
            iconLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
            iconLabel.setForeground(Colors.darkBeigeColor);
        }

        // value label
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(Farm.fonts.minecraftiaFont);
        valueLabel.setForeground(Colors.darkBeigeColor);

        itemPanel.add(iconLabel);
        itemPanel.add(valueLabel);

        return itemPanel;
    }

    public void refreshStats() {
        removeAll();
        initializePanel();
        revalidate();
        repaint();
    }
}

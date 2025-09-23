package UI;

import Resources.Colors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class UIUtils {

    public static void customizeScrollBar(JScrollPane scrollPane) {

        // scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setOpaque(false);
        verticalScrollBar.setBackground(Colors.lightBeigeColor);
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Colors.darkBeigeColor;
                this.trackColor = Colors.lightBeigeColor;
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
            
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Colors.darkBeigeColor);
                g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2,
                    thumbBounds.width - 4, thumbBounds.height - 4, 6, 6);
                g2.dispose();
            }
            
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Colors.lightBeigeColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });
        
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setOpaque(false);
        horizontalScrollBar.setBackground(Colors.lightBeigeColor);
        horizontalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Colors.darkBeigeColor;
                this.trackColor = Colors.lightBeigeColor;
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
            
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Colors.darkBeigeColor);
                g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2, 
                    thumbBounds.width - 4, thumbBounds.height - 4, 6, 6);
                g2.dispose();
            }
            
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Colors.lightBeigeColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });
    }


    // rounded panel
    public static class RoundedPanel extends JPanel {
        private final int cornerRadius;

        public RoundedPanel(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(Colors.lightBeigeColor);
            graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            graphics2D.dispose();
            super.paintComponent(graphics);
        }
    }


    // button
    public static JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(b.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);
                
                g2.setColor(Colors.beigeColor);
                g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 8, 8);
                
                String buttonText = b.getText();
                if (buttonText != null && !buttonText.isEmpty()) {
                    g2.setFont(b.getFont());
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (c.getWidth() - fm.stringWidth(buttonText)) / 2;
                    int y = (c.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                    
                    g2.setColor(Colors.darkBeigeColor);
                    g2.drawString(buttonText, x, y);
                }
                
                g2.dispose();
            }
        });
        
        return button;
    }

    // rounded button with resource button styling
    public static JButton createRoundedButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(Colors.beigeColor);
        button.setForeground(Colors.darkBeigeColor);
        button.setBorder(null);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        
        // Custom UI for rounded corners matching resource buttons
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                JButton btn = (JButton) c;
                
                // Fill background with rounded corners
                if (btn.getModel().isPressed()) {
                    g2.setColor(Colors.beigeColor.darker());
                } else if (btn.getModel().isRollover()) {
                    g2.setColor(Colors.beigeColor.brighter());
                } else {
                    g2.setColor(Colors.beigeColor);
                }
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);
                
                // Draw border
                g2.setColor(Colors.beigeColor.darker());
                g2.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 8, 8);
                
                g2.dispose();
                
                // Now paint the icon/text on top
                if (btn.getIcon() != null) {
                    Icon icon = btn.getIcon();
                    int x = (c.getWidth() - icon.getIconWidth()) / 2;
                    int y = (c.getHeight() - icon.getIconHeight()) / 2;
                    icon.paintIcon(c, g, x, y);
                } else if (btn.getText() != null && !btn.getText().isEmpty()) {
                    g.setColor(btn.getForeground());
                    g.setFont(btn.getFont());
                    FontMetrics fm = g.getFontMetrics();
                    int x = (c.getWidth() - fm.stringWidth(btn.getText())) / 2;
                    int y = (c.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                    g.drawString(btn.getText(), x, y);
                }
            }
        });
        
        return button;
    }

    // Custom rounded progress bar with beige styling
    public static JProgressBar createRoundedProgressBar(int minValue, int maxValue, int currentValue, String text, Font font) {
        JProgressBar progressBar = new JProgressBar(minValue, maxValue);
        progressBar.setValue(currentValue);
        progressBar.setStringPainted(true);
        progressBar.setString(text);
        progressBar.setFont(font);
        progressBar.setBorder(null);
        progressBar.setOpaque(false);
        
        // Custom UI with rounded corners matching scroll bar style
        progressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, javax.swing.JComponent c) {
                if (!(g instanceof Graphics2D)) {
                    return;
                }
                
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = c.getWidth();
                int height = c.getHeight();
                int cornerRadius = 6; // Same as scroll bar thumb
                
                // Draw background with rounded corners
                g2.setColor(Colors.lightBeigeColor);
                g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
                
                // Calculate filled area
                double progress = (double) (progressBar.getValue() - progressBar.getMinimum()) / 
                                 (progressBar.getMaximum() - progressBar.getMinimum());
                int filledWidth = (int) (width * progress);
                
                // Draw filled area with rounded corners (clipped to progress)
                if (filledWidth > 0) {
                    g2.setColor(Colors.beigeColor);
                    
                    // Create a clip to ensure filled area doesn't exceed filledWidth
                    Shape originalClip = g2.getClip();
                    g2.setClip(0, 0, filledWidth, height);
                    
                    // Draw filled rounded rectangle (will be clipped to actual progress)
                    g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
                    
                    // Restore original clip
                    g2.setClip(originalClip);
                }
                
                // Draw border
                g2.setColor(Colors.darkBeigeColor);
                g2.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
                
                // Draw text
                if (progressBar.isStringPainted() && progressBar.getString() != null) {
                    g2.setColor(Colors.darkBeigeColor);
                    g2.setFont(progressBar.getFont());
                    String textStr = progressBar.getString();
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (width - fm.stringWidth(textStr)) / 2;
                    int y = (height - fm.getHeight()) / 2 + fm.getAscent();
                    g2.drawString(textStr, x, y);
                }
                
                g2.dispose();
            }
        });
        
        return progressBar;
    }
}

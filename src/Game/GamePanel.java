package Game;

import Resources.Colors;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setPreferredSize(new Dimension(1200, 800));
        setBackground(Colors.waterColor);
        setDoubleBuffered(true);
        setFocusable(true);
        
        setupKeyBindings();
    }
    
    // key action system
    private enum KeyActionType {
        MOVE_UP_PRESS, MOVE_UP_RELEASE,
        MOVE_DOWN_PRESS, MOVE_DOWN_RELEASE, 
        MOVE_LEFT_PRESS, MOVE_LEFT_RELEASE,
        MOVE_RIGHT_PRESS, MOVE_RIGHT_RELEASE,
        DEBUG_TOGGLE
    }
    
    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        
        Object[][] keyMappings = {
            // {KeyStroke, ActionEnum}
            {KeyStroke.getKeyStroke("pressed W"), KeyActionType.MOVE_UP_PRESS},
            {KeyStroke.getKeyStroke("pressed UP"), KeyActionType.MOVE_UP_PRESS},
            {KeyStroke.getKeyStroke("pressed S"), KeyActionType.MOVE_DOWN_PRESS},
            {KeyStroke.getKeyStroke("pressed DOWN"), KeyActionType.MOVE_DOWN_PRESS},
            {KeyStroke.getKeyStroke("pressed A"), KeyActionType.MOVE_LEFT_PRESS},
            {KeyStroke.getKeyStroke("pressed LEFT"), KeyActionType.MOVE_LEFT_PRESS},
            {KeyStroke.getKeyStroke("pressed D"), KeyActionType.MOVE_RIGHT_PRESS},
            {KeyStroke.getKeyStroke("pressed RIGHT"), KeyActionType.MOVE_RIGHT_PRESS},
            
            {KeyStroke.getKeyStroke("released W"), KeyActionType.MOVE_UP_RELEASE},
            {KeyStroke.getKeyStroke("released UP"), KeyActionType.MOVE_UP_RELEASE},
            {KeyStroke.getKeyStroke("released S"), KeyActionType.MOVE_DOWN_RELEASE},
            {KeyStroke.getKeyStroke("released DOWN"), KeyActionType.MOVE_DOWN_RELEASE},
            {KeyStroke.getKeyStroke("released A"), KeyActionType.MOVE_LEFT_RELEASE},
            {KeyStroke.getKeyStroke("released LEFT"), KeyActionType.MOVE_LEFT_RELEASE},
            {KeyStroke.getKeyStroke("released D"), KeyActionType.MOVE_RIGHT_RELEASE},
            {KeyStroke.getKeyStroke("released RIGHT"), KeyActionType.MOVE_RIGHT_RELEASE},
            
            {KeyStroke.getKeyStroke("F3"), KeyActionType.DEBUG_TOGGLE}
        };
        
        for (Object[] mapping : keyMappings) {
            KeyStroke keyStroke = (KeyStroke) mapping[0];
            KeyActionType actionType = (KeyActionType) mapping[1];
            
            inputMap.put(keyStroke, actionType);
            actionMap.put(actionType, new keyAction(actionType));
        }
    }
    

    private static class keyAction extends AbstractAction {
        private final KeyActionType actionType;
        
        public keyAction(KeyActionType actionType) {
            this.actionType = actionType;
        }
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (Farm.keyHandler == null) return;
            
            switch (actionType) {
                case MOVE_UP_PRESS -> Farm.keyHandler.upPressed = true;
                case MOVE_UP_RELEASE -> Farm.keyHandler.upPressed = false;
                case MOVE_DOWN_PRESS -> Farm.keyHandler.downPressed = true;
                case MOVE_DOWN_RELEASE -> Farm.keyHandler.downPressed = false;
                case MOVE_LEFT_PRESS -> Farm.keyHandler.leftPressed = true;
                case MOVE_LEFT_RELEASE -> Farm.keyHandler.leftPressed = false;
                case MOVE_RIGHT_PRESS -> Farm.keyHandler.rightPressed = true;
                case MOVE_RIGHT_RELEASE -> Farm.keyHandler.rightPressed = false;
                case DEBUG_TOGGLE -> Debug.DebugMenu.toggleDebugMenu();
            }
        }
    }


    // updating & rendering
    public void update() {
        Farm.entitiesHandler.update();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Farm.entitiesHandler.render(graphics2D);
        graphics2D.dispose();
    }
}
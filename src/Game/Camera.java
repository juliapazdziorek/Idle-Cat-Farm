package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Self-contained camera system with integrated input handling for smooth movement.
 * Manages viewport position and captures keyboard input directly.
 */
public class Camera implements KeyListener {

    public Point position;
    public final int cameraSpeed = 5;

    public boolean blockedUp;
    public boolean blockedDown;
    public boolean blockedLeft;
    public boolean blockedRight;

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    /** Input action types for camera movement and debug controls. */
    private enum KeyActionType {
        MOVE_UP_PRESS, MOVE_UP_RELEASE,
        MOVE_DOWN_PRESS, MOVE_DOWN_RELEASE, 
        MOVE_LEFT_PRESS, MOVE_LEFT_RELEASE,
        MOVE_RIGHT_PRESS, MOVE_RIGHT_RELEASE,
        DEBUG_TOGGLE
    }

    /** Initializes the camera at starting position and sets up input handling. */
    public Camera() {
        position = new Point(-375, -500);
    }
    
    /**
     * Configures camera input bindings on the game panel.
     * Call this after GamePanel is created to enable camera controls.
     */
    public void setupInputBindings(JPanel gamePanel) {
        InputMap inputMap = gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = gamePanel.getActionMap();
        
        Object[][] keyMappings = {
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
            actionMap.put(actionType, new CameraInputAction(actionType));
        }
    }

    /** Updates camera position based on input and boundary constraints. */
    public void update() {
        if (upPressed && !blockedUp) {
            position.y += cameraSpeed;
        }
        if (downPressed && !blockedDown) {
            position.y -= cameraSpeed;
        }
        if (leftPressed && !blockedLeft) {
            position.x += cameraSpeed;
        }
        if (rightPressed && !blockedRight) {
            position.x -= cameraSpeed;
        }

        checkIfBlocked();
    }

    /** Calculates movement boundaries to prevent the camera from going out of bounds. */
    private void checkIfBlocked() {
        blockedUp = position.y + cameraSpeed > 0;
        blockedDown = position.y - cameraSpeed < - Farm.mapHeight + Farm.frame.getHeight();
        blockedLeft = position.x + cameraSpeed > 0;
        blockedRight = position.x - cameraSpeed < - Farm.mapWidth + Farm.gamePanel.getWidth();
    }

    /** KeyListener implementation for direct input handling. **/
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_F3 -> Debug.DebugMenu.toggleDebugMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> rightPressed = false;
        }
    }

    /**
     * Action handler that processes camera input commands.
     * Handles both movement and debug toggle functionality.
     */
    private class CameraInputAction extends AbstractAction {
        private final KeyActionType actionType;
        
        public CameraInputAction(KeyActionType actionType) {
            this.actionType = actionType;
        }
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            switch (actionType) {
                case MOVE_UP_PRESS -> upPressed = true;
                case MOVE_UP_RELEASE -> upPressed = false;
                case MOVE_DOWN_PRESS -> downPressed = true;
                case MOVE_DOWN_RELEASE -> downPressed = false;
                case MOVE_LEFT_PRESS -> leftPressed = true;
                case MOVE_LEFT_RELEASE -> leftPressed = false;
                case MOVE_RIGHT_PRESS -> rightPressed = true;
                case MOVE_RIGHT_RELEASE -> rightPressed = false;
                case DEBUG_TOGGLE -> Debug.DebugMenu.toggleDebugMenu();
            }
        }
    }
}
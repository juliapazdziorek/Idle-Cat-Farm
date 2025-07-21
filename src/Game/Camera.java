package Game;

import java.awt.*;

public class Camera {

    public Point position;
    public final int cameraSpeed = 5;

    public boolean blockedUp;
    public boolean blockedDown;
    public boolean blockedLeft;
    public boolean blockedRight;

    public Camera() {
        position = new Point(0, 0);
    }

    // getters for backward compatibility
    public int getCameraX() {
        return position.x;
    }
    
    public int getCameraY() {
        return position.y;
    }
    
    // setters for convenience
    public void setCameraPosition(int x, int y) {
        position.setLocation(x, y);
    }
    
    public void setCameraPosition(Point newPosition) {
        position.setLocation(newPosition);
    }

    // updating
    public void update() {

        if (FocusFarm.keyHandler.upPressed && !blockedUp) {
            position.y += cameraSpeed;
        }
        if (FocusFarm.keyHandler.downPressed && !blockedDown) {
            position.y -= cameraSpeed;
        }
        if (FocusFarm.keyHandler.leftPressed && !blockedLeft) {
            position.x += cameraSpeed;
        }
        if (FocusFarm.keyHandler.rightPressed && !blockedRight) {
            position.x -= cameraSpeed;
        }

        checkIfBlocked();
    }

    // checking for blockage
    private void checkIfBlocked() {
        blockedUp = position.y + cameraSpeed > 0;
        blockedDown = position.y - cameraSpeed < - FocusFarm.mapHeight + FocusFarm.frame.getHeight();
        blockedLeft = position.x + cameraSpeed > 0;
        blockedRight = position.x - cameraSpeed < - FocusFarm.mapWidth + FocusFarm.frame.getWidth();
    }
}
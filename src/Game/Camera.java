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

        if (Farm.keyHandler.upPressed && !blockedUp) {
            position.y += cameraSpeed;
        }
        if (Farm.keyHandler.downPressed && !blockedDown) {
            position.y -= cameraSpeed;
        }
        if (Farm.keyHandler.leftPressed && !blockedLeft) {
            position.x += cameraSpeed;
        }
        if (Farm.keyHandler.rightPressed && !blockedRight) {
            position.x -= cameraSpeed;
        }

        checkIfBlocked();
    }

    // checking for blockage
    private void checkIfBlocked() {
        blockedUp = position.y + cameraSpeed > 0;
        blockedDown = position.y - cameraSpeed < - Farm.mapHeight + Farm.frame.getHeight();
        blockedLeft = position.x + cameraSpeed > 0;
        blockedRight = position.x - cameraSpeed < - Farm.mapWidth + Farm.frame.getWidth();
    }
}
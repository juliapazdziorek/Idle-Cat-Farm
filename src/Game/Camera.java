package Game;

public class Camera {

    public int cameraX;
    public int cameraY;
    public final int cameraSpeed = 5;

    public boolean blockedUp;
    public boolean blockedDown;
    public boolean blockedLeft;
    public boolean blockedRight;


    // updating
    public void update() {

        if (FocusFarm.keyHandler.upPressed && !blockedUp) {
            cameraY += cameraSpeed;
        }
        if (FocusFarm.keyHandler.downPressed && !blockedDown) {
            cameraY -= cameraSpeed;
        }
        if (FocusFarm.keyHandler.leftPressed && !blockedLeft) {
            cameraX += cameraSpeed;
        }
        if (FocusFarm.keyHandler.rightPressed && !blockedRight) {
            cameraX -= cameraSpeed;
        }

        checkIfBlocked();
    }

    // checking for blockage
    private void checkIfBlocked() {
        blockedUp = cameraY + cameraSpeed > 0;
        blockedDown = cameraY - cameraSpeed < - FocusFarm.mapHeight + FocusFarm.frame.getHeight();
        blockedLeft = cameraX + cameraSpeed > 0;
        blockedRight = cameraX - cameraSpeed < - FocusFarm.mapWidth + FocusFarm.frame.getWidth();

    }
}
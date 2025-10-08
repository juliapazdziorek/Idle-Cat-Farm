package Game;

/** Game loop implementation. Maintains consistent 60 FPS update rate. */
public class Loop implements Runnable {

    private boolean running = false;
    private Thread gameThread;

    /** Starts the game loop on a separate thread */
    public void startLooping() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /** Stops the game loop and waits for the thread to terminate */
    public void stopLooping() {
        running = false;
        try {
            if (gameThread != null) gameThread.join();
        } catch (InterruptedException ignored) {}
    }

    /**
     * Main game loop using fixed timestep with delta time accumulation.
     * Ensures consistent 60 FPS updates while allowing variable render rates.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;
        final double timePerUpdate = 1_000_000_000.0 / Farm.fps;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            render();
        }
    }

    /** Updates game state on panel and camera position. */
    private void update() {
        Farm.gamePanel.update();
        Farm.camera.update();
    }

    /** Triggers a repaint of the game panel. */
    private void render() {
        Farm.gamePanel.repaint();
    }
}
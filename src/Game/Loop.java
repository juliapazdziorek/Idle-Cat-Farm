package Game;

public class Loop implements Runnable {

    private boolean running = false;
    private Thread gameThread;

    public void startLooping() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopLooping() {
        running = false;
        try {
            if (gameThread != null) gameThread.join();
        } catch (InterruptedException ignored) {}
    }

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

    private void update() {
        Farm.gamePanel.update();
        Farm.camera.update();
    }

    private void render() {
        Farm.gamePanel.repaint();
    }
}
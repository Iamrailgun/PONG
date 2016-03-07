import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
    public static final String TITLE = "PONG";
    private boolean running = true;
    private Random r;
    private Thread thread;
    private Player player;
    private Player2 player2;
    private Boll boll;
    private HUD hud;
    private Traillist traillist;
    private AutoPlay autoPlay;
    private float spdLeft = -7, spdRight = 7;
    public static boolean singlePlayer;

    public enum gameState {
        InHelp,
        InGame,
        GameOver,
        Menu,
        Choose,
    }

    public static gameState currentState;

    public Game() {
        new Window(WIDTH, HEIGHT, TITLE, this);
        traillist = new Traillist();
        r = new Random();
        hud = new HUD();
        boll = new Boll(512, 384, hud, traillist, this);
        player = new Player(16, HEIGHT / 2 - 48);
        player2 = new Player2(WIDTH - 38, HEIGHT / 2 - 48);
        addKeyListener(new Input(player, player2, boll, hud, this));
        autoPlay = new AutoPlay(player2, boll);
        currentState = gameState.Menu;
        singlePlayer = false;
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lostTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ms = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lostTime) / ms;
            lostTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        if (currentState == gameState.InGame) {
            player.render(g);
            player2.render(g);
            boll.render(g);
            traillist.render(g);
        }
        hud.render(g);

        g.dispose();
        bs.show();
    }


    private void tick() {
        if (currentState == gameState.InGame) {
            player2.tick();
            boll.tick();
            collision();
            player.tick();
            traillist.tick();
        }
        if (singlePlayer) {
            autoPlay.move();
        }
        hud.tick();
    }

    public static int clamp(int var, int max, int min) {
        if (var <= min)
            return min;
        else if (var >= max)
            return max;
        else
            return var;
    }

    public void resetSpd() {
        spdLeft = -7;
        spdRight = 7;
    }

    public void collision() {
        if (boll.getRect().intersects(player.getRect())) {
            boll.setVelX(spdRight);
            spdRight += 0.2;
        }
        if (boll.getRect().intersects(player2.getRect())) {
            boll.setVelX(spdLeft);
            spdLeft -= 0.2;
        }
    }

    public static void main(String... args) {
        new Game();
    }

}

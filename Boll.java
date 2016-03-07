import java.awt.*;
import java.util.Random;

public class Boll extends GameObjects {

    private Rectangle rect;
    private Random r;
    private HUD hud;
    private Traillist traillist;
    private Game game;

    public Boll(float x, float y, HUD hud, Traillist traillist, Game game) {
        super(x, y);
        this.hud = hud;
        this.traillist = traillist;
        this.game = game;
        rect = new Rectangle();
        r = new Random();
        velX = 0;
        velY = 0;
    }

    public void go() {
        if (r.nextInt(2) == 1) velX = 7;
        else velX = -7;
        if (r.nextInt(2) == 1) velY = 7;
        else velY = -7;
    }

    public void tick() {
        traillist.createObject(new Trail(x, y, 0.04f, traillist));
        rect.setBounds((int) x, (int) y, 25, 25);
        x += velX;
        y += velY;
        if (x <= -10) {
            Input.newTry = true;
            velX = 0;
            velY = 0;
            x = 512;
            y = r.nextInt(Game.HEIGHT - 65);
            game.resetSpd();
            hud.p2addScore();
        }
        if (x >= Game.WIDTH - 25) {
            Input.newTry = true;
            velX = 0;
            velY = 0;
            x = 512;
            y = r.nextInt(Game.HEIGHT - 65);
            game.resetSpd();
            hud.p1addScore();
        }
        if (y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, 25, 25);
    }

    public Rectangle getRect() {
        return rect;
    }
}

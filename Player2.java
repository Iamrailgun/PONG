import java.awt.*;

public class Player2 extends GameObjects{

    private Rectangle rect;

    public Player2(int x, int y) {
        super(x, y);
        rect = new Rectangle();
    }

    public void tick() {
        y += velY * scale;
        y = Game.clamp((int)y, 633, 0);
        rect.setBounds((int)x, (int)y, 16, 96);
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)x, (int)y, 16, 96);
    }

    public Rectangle getRect() {
        return rect;
    }
}

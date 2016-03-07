import java.awt.*;

public class Trail extends GameObjects {

    private float life, alpha = 0.5f;
    private Traillist traillist;

    public Trail(float x, float y, float life, Traillist traillist) {
        super(x, y);
        this.life = life;
        this.traillist = traillist;
    }

    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.001f;
        } else {
            traillist.removeObject(this);
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransperent(alpha));
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, 25, 25);
    }

    private AlphaComposite makeTransperent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }
}

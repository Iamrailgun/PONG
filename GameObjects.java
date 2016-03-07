import java.awt.*;

/**
 * Created by PC on 13.02.2016.
 */
public abstract class GameObjects {

    public float x, y, velX, velY, scale;

    public GameObjects(float x, float y) {
        this.x = x;
        this.y = y;
    }

    abstract public void tick();
    abstract public void render(Graphics g);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setScale(float scale){
        this.scale = scale;
    }
}

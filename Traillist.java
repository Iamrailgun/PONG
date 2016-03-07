import java.awt.*;
import java.util.LinkedList;

public class Traillist {

    public LinkedList<GameObjects> object = new LinkedList<>();

    public void tick() {
        for (int i = 0; i < object.size(); i++){
            GameObjects tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObjects tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void createObject(GameObjects object){
        this.object.add(object);
    }

    public void removeObject(GameObjects object){
        this.object.remove(object);
    }
}

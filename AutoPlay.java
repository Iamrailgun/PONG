
public class AutoPlay {

    private Player2 p2;
    private Boll boll;

    public AutoPlay(Player2 p2, Boll boll) {
        this.p2 = p2;
        this.boll = boll;
    }

    public void move(){
        if (boll.getVelX() > 0) {
            if (boll.getY() <= p2.getY() + 48) {
                p2.setVelY(-8);
            }
            if (boll.getY() >= p2.getY() + 48) {
                p2.setVelY(8);
            }
        } else
            p2.setVelY(0);
    }
}

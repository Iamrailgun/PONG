import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Input extends KeyAdapter {

    private int key;
    private Player player;
    private boolean p1key;
    private boolean p2key;
    private boolean sh1key;
    private boolean sh2key;
    private HUD hud;
    private Boll boll;
    private Game game;
    private Random r;
    public static boolean newTry;

    private Player2 player2;
    public Input(Player player, Player2 player2, Boll boll, HUD hud, Game game) {
        this.player = player;
        this.player2 = player2;
        this.hud = hud;
        this.boll = boll;
        this.game = game;
        p1key = false;
        p2key = false;
        sh1key = false;
        sh2key = false;
        newTry = true;
        r = new Random();
    }

    public void keyPressed(KeyEvent e){
        key = e.getKeyCode();
        if (Game.currentState == Game.gameState.InGame) {
            if (key == KeyEvent.VK_W){
                if (newTry){
                    boll.go();
                    newTry = false;
                }
                p1key = true;
                player.setVelY(-8);
            }
            if (key == KeyEvent.VK_S){
                if (newTry){
                    boll.go();
                    newTry = false;
                }
                p1key = true;
                player.setVelY(8);
            }
            if (key == KeyEvent.VK_UP){
                if (newTry){
                    boll.go();
                    System.out.println("go");
                    newTry = false;
                }
                p2key = true;
                player2.setVelY(-8);
            }
            if (key == KeyEvent.VK_DOWN){
                if (newTry){
                    boll.go();
                    System.out.println("go");
                    newTry = false;
                }
                p2key = true;
                player2.setVelY(8);
            }
            if (key == KeyEvent.VK_SPACE){
                sh1key = true;
                player.setScale(2);
            }
            if (key == KeyEvent.VK_SHIFT){
                sh2key = true;
                player2.setScale(2);
            }
            if (key == KeyEvent.VK_ESCAPE){
                Game.currentState = Game.gameState.Menu;
            }
        }
        if (Game.currentState == Game.gameState.Menu){
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
                hud.buttonUp();
            }
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
                hud.buttonDown();
            }
            if (hud.getButton() == 0 && key == KeyEvent.VK_ENTER){
                Game.currentState = Game.gameState.Choose;
                return;
            }
            if (hud.getButton() == 1 && key == KeyEvent.VK_ENTER){
                Game.currentState = Game.gameState.InHelp;
                return;
            }
            if (hud.getButton() == 2 && key == KeyEvent.VK_ENTER){
                System.exit(1);
            }
        }
        if (Game.currentState == Game.gameState.Choose) {
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                hud.buttonUp();
            }
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                hud.buttonDown();
            }
            if (hud.getButton() == 0 && key == KeyEvent.VK_ENTER) {
                player.setY(Game.HEIGHT / 2 - 48);
                player2.setY(Game.HEIGHT / 2 - 48);
                boll.setX(512);
                boll.setY(r.nextInt(Game.HEIGHT - 65));
                boll.setVelX(0);
                boll.setVelY(0);
                game.resetSpd();
                hud.setScore(0);
                Game.singlePlayer = false;
                Game.currentState = Game.gameState.InGame;
                newTry = true;
                return;
            }
            if (hud.getButton() == 1 && key == KeyEvent.VK_ENTER) {
                player2.setY(Game.HEIGHT / 2 - 48);
                boll.setX(512);
                boll.setY(r.nextInt(Game.HEIGHT - 65));
                boll.setVelX(0);
                boll.setVelY(0);
                game.resetSpd();
                hud.setScore(0);
                player.setY(Game.HEIGHT / 2 - 48);
                Game.singlePlayer = true;
                Game.currentState = Game.gameState.InGame;
                return;
            }
            if (hud.getButton() == 2 && key == KeyEvent.VK_ENTER) {
                Game.currentState = Game.gameState.Menu;
                return;
            }
        }
        if (Game.currentState == Game.gameState.GameOver){
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
                hud.buttonUp();
            }
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
                hud.buttonDown();
            }
            if (hud.getButton() == 0 && key == KeyEvent.VK_ENTER){
                player.setY(Game.HEIGHT / 2 - 48);
                player2.setY(Game.HEIGHT / 2 - 48);
                newTry = true;
                boll.setVelX(0);
                boll.setVelY(0);
                hud.setScore(0);
                Game.currentState = Game.gameState.InGame;
                return;
            }
            if (hud.getButton() == 1 && key == KeyEvent.VK_ENTER){
                hud.setScore(0);
                Game.currentState = Game.gameState.Menu;
                return;
            }
            if (hud.getButton() == 2 && key == KeyEvent.VK_ENTER){
                System.exit(1);
            }
        }
        if (Game.currentState == Game.gameState.InHelp){
            if (key == KeyEvent.VK_ENTER){
                Game.currentState = Game.gameState.Menu;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        key = e.getKeyCode();
        if (Game.currentState == Game.gameState.InGame){
            if (key == KeyEvent.VK_W) {
                p1key = false;
            }
            if (key == KeyEvent.VK_S) {
                p1key = false;
            }
            if (key == KeyEvent.VK_UP) {
                p2key = false;
            }
            if (key == KeyEvent.VK_DOWN){
                p2key = false;
            }
            if (!p1key) player.setVelY(0);
            if (!p2key) player2.setVelY(0);
            if (key == KeyEvent.VK_SPACE){
                sh1key = false;
            }
            if (key == KeyEvent.VK_SHIFT){
                sh2key = false;
            }
            if (!sh1key) player.setScale(1);
            if (!sh2key) player2.setScale(1);
        }
    }
}

import java.awt.*;

public class HUD {

    private int p1Score = 0;
    private int p2Score = 0;
    private int button = 0;

    public void tick() {
        if (p1Score == 5 || p2Score == 5){
            setScore(0);
            Game.currentState = Game.gameState.GameOver;
        }
    }

    public void createButton(String s, int x, int y, int width, int height, int number, Graphics g){
        if (button == number) {
            g.setColor(Color.BLUE);
            g.fillRect(x + 1, y + 1, width - 1, height - 1);
        }
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        g.drawString(s, x + 5, y + height / 2 + 10);
    }

    public void render(Graphics g) {
        Font font = new Font("TimesRoman", Font.BOLD, 30);
        Font font2 = new Font("TimesRoman", Font.BOLD, 75);
        g.setFont(font);
        g.setColor(Color.WHITE);
        if (Game.currentState == Game.gameState.InGame) {
            g.drawString("Player1: " + p1Score, 50, 30);
            g.drawString("Player2: " + p2Score, Game.WIDTH - 200, 30);
        }
        if (Game.currentState == Game.gameState.GameOver){
            g.setFont(font2);
            g.drawString("Game over!", Game.WIDTH / 2 - 220, 100);
            g.setFont(font);
            if (p1Score > p2Score){
                g.drawString("Player1 WIN!!!", Game.WIDTH / 2 - 135, Game.HEIGHT / 2 - 200);
            } else {
                g.drawString("Player2 WIN!!!", Game.WIDTH / 2 - 135, Game.HEIGHT / 2 - 200);
            }
            createButton("Try again?", 10, 300, 200, 64, 0, g);
            createButton("Menu", 10, 400, 200, 64, 1, g);
            createButton("Quit", 10, 500, 200, 64, 2, g);
        }
        if (Game.currentState == Game.gameState.Menu){
            g.setFont(font2);
            g.drawString("Pong", Game.WIDTH / 2 - 120, 100);
            g.setFont(font);
            createButton("Play", 10, 300, 200, 64, 0, g);
            createButton("Help", 10, 400, 200, 64, 1, g);
            createButton("Quit", 10, 500, 200, 64, 2, g);
        }
        if (Game.currentState == Game.gameState.InHelp){
            g.setFont(font2);
            g.drawString("Help", Game.WIDTH / 2 - 120, 100);
            g.setFont(font);
            g.drawString("Created by DESU!!!", 3, Game.HEIGHT - 40);
            g.drawString("        "+"Player1   "+"   Playwer2", Game.WIDTH / 2 - 270, 200);
            g.drawString("control:"+"   W  S"+"        UP  DOWN", Game.WIDTH / 2 - 320, 235);
            g.drawString("BOOST:"+"SPACE      "+"    SHIFT", Game.WIDTH / 2 - 320, 270);
            g.setColor(Color.BLUE);
            g.fillRect(11, 456, 199, 63);
            g.setColor(Color.WHITE);
            g.drawRect(10, 455, 200, 64);
            g.drawString("Back", 15, 500);
        }
        if (Game.currentState == Game.gameState.Choose){
            createButton("Multi", 10, 300, 200, 64, 0, g);
            createButton("Solo", 10, 400, 200, 64, 1, g);
            createButton("Back", 10, 500, 200, 64, 2, g);
        }
    }

    public void buttonUp() {
        button--;
            if (button < 0) button = 2;
    }

    public void buttonDown() {
        button++;
            if (button > 2) button = 0;
    }

    public void p1addScore(){
        p1Score++;
    }

    public void p2addScore(){
        p2Score++;
    }

    public int getButton(){
        return button;
    }

    public int getP1Score() {
        return p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public void setScore(int Score) {
        p1Score = Score;
        p2Score = Score;
    }
}

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;

public class Memory extends JFrame {
    private Card[] allCards;
    private int numberOfC;
    private int n;
    private int rc;
    
    public Memory(int n, String path) {
        numberOfC = n;
        this.n = n;
        rc = (int)Math.sqrt(numberOfC);
        allCards = new Card[n/2];
        File[] pics = Tools.getPics(path);
        for (int i = 0;  i < n/2; i++) {
            ImageIcon icon = new ImageIcon(pics[i].getPath());
            allCards[i] = new Card(icon);
        }
    }
    public void newGame() {
        JPanel game = new JPanel(new GridLayout(rc,rc));
        Card[] gameCards = new Card[n];
        Card[] arrayCopy = new Card[n/2];
        int k = 0;
        boolean insert;
        for (int i = 0; i < numberOfC/2; i++) {
            insert = false;
            while (insert != true) {
                int rand = Tools.randInt(0, numberOfC-1, new Random());
                if (gameCards[rand] != null) {
                    gameCards[rand] = allCards[i];
                    insert = true;
                }
            }
        }
        for (int i = 0; i < numberOfC; i++) {
            if (gameCards[i] != null) {
                arrayCopy[k] = gameCards[i].copy();
                k++;
            }
        }
        for (int i = 0; i < numberOfC/2; i++) {
            insert = false;
            while (insert != true) {
                int rand = Tools.randInt(0, numberOfC-1, new Random());
                if (gameCards[rand] != null) {
                    gameCards[rand] = arrayCopy[i];
                    insert = true;
                }
            }
        }
        for (int i = 0; i < numberOfC; i++) {
            game.add(gameCards[i]);
        }
        add(game);
        setSize(420,420);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
       new Memory(16, "mypictures").newGame();

    }
}

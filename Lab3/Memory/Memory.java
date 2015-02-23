import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
//import java.swing.Timer;

public class Memory extends JFrame {
    private String[] players;
    private Card[] allCards;
    private JPanel game;
    private JPanel playerBar;
    private JPanel control;
    private int numberOfC;
    private int n;
    private int rc;
    
    public Memory(int n, String path, String... p) {
        players = p;
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
    
    public void addController() {
        playerBar = new PlayerBar(players);
        control = new MemController(this);
        add(control, BorderLayout.SOUTH);
    }
    
    public void clearGame() {
        game.removeAll();
    }
    
    public void newGame() {
        for (Card c : allCards) {
            c.setStatus(Card.Status.HIDDEN);
        }
        game = new JPanel(new GridLayout(rc,rc));
        Card[] gameCards = new Card[n];
        Card[] arrayCopy = new Card[n/2];
        int k = 0;
        boolean insert;
        for (int i = 0; i < numberOfC/2; i++) {
            insert = false;
            while (insert != true) {
                int rand = Tools.randInt(0, numberOfC-1, new Random());
                if (gameCards[rand] == null) {
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
                if (gameCards[rand] == null) {
                    gameCards[rand] = arrayCopy[i];
                    insert = true;
                }
            }
        }
        for (int i = 0; i < numberOfC; i++) {
        gameCards[i].addActionListener((ActionListener)control);
        gameCards[i].setActionCommand("button");
            game.add(gameCards[i]);
        }
        add(game, BorderLayout.CENTER);
        add(playerBar, BorderLayout.WEST);
        setSize(420,420);
        game.setVisible(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

    public int getNumberOfC() {
        return numberOfC;
    }


    
    public static void main(String[] args) {
       String[] play = new String[2];
       play[0] = "Axel";
       play[1] = "Sigge";
       Memory game = new Memory(4, "mypictures", play);
       game.addController();
       game.newGame();

    }
}

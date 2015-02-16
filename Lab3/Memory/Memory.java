import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
//import java.swing.Timer;

public class Memory extends JFrame implements ActionListener {
    private static ArrayList<Card> list = new ArrayList<Card>();
    private Card[] allCards;
    private JPanel game;
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
            game.add(gameCards[i]);
        }
        add(game, BorderLayout.CENTER);
        Buttonpanel();
        setSize(420,420);
        game.setVisible(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void Buttonpanel(){
        JPanel control = new JPanel();
        JButton nytt = new JButton("Nytt spel");
        JButton sluta = new JButton("Avsluta");
        nytt.addActionListener(this);
        sluta.addActionListener(this);
        control.add(nytt);
        control.add(sluta);
        add(control, BorderLayout.SOUTH);
        control.setVisible(true);
        
    }
    
    public static void turnListener(Card c) {
        if (list.size() == 0) {
            System.out.println("Size: " + list.size());
            list.add(c);
        }
        else if (list.size() == 1) {
            list.add(c);
            Timer timer = new Timer(1500, c);
            timer.setInitialDelay(1500);
            timer.start(); 
                       
            for (Card ca : list) {
                ca.setStatus(Card.Status.HIDDEN);
            }
            System.out.println("Size: " + list.size());
            list.clear();
        }        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Nytt spel") {
            game.removeAll();
            newGame();
        }
        else if (e.getActionCommand() == "Avsluta") {
            System.exit(0);
        }
        System.out.println(e.getActionCommand());
        
    }
    
    public static void main(String[] args) {
       Memory game = new Memory(16, "mypictures");
       game.newGame();

    }
}

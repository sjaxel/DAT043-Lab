import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class MemController extends JPanel implements ActionListener {
    private boolean lock = false;
    Memory parent;
    Timer timer;
    private static ArrayList<Card> list = new ArrayList<Card>();
    private static int missing;
    public MemController(Memory m) {
        missing = 0;
        parent = m;
        buttonPanel();
    }
    
    public void buttonPanel() {
        JButton nytt = new JButton("Nytt spel");
        JButton sluta = new JButton("Avsluta");
        nytt.addActionListener(this);
        sluta.addActionListener(this);
        add(nytt);
        add(sluta);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Nytt spel") {
            missing = 0;
            parent.clearGame();
            parent.newGame();
        }
        else if (e.getActionCommand() == "Avsluta") {
            System.exit(0);
        }
        else if (e.getActionCommand() == "button" && lock == false 
                && ((Card)(e.getSource())).getStatus() == Card.Status.HIDDEN) {
            Card c = (Card)(e.getSource());
            c.setStatus(Card.Status.VISIBLE);
            if (list.size() == 0) {
                list.add(c);
            }
            else if (list.size() == 1) {
                lock = true;              
                list.add(c);
                timer = new Timer(500, this);
                timer.setActionCommand("time");
                timer.start(); 
            }        
        }
        else if (e.getActionCommand() == "time") {
            timer.stop();
            if (list.get(0).equalIcon(list.get(1))) {
                for(Card ca : list) {
                    System.out.println("missing");
                    missing++;
                    ca.setStatus(Card.Status.MISSING);
                    if (missing == parent.getNumberOfC()) {
                        int value = JOptionPane.showConfirmDialog(parent, "New game?");
                        if (value == 0) {
                            missing = 0;
                            parent.clearGame();
                            parent.newGame();
                        } else {
                            System.exit(0);
                        }
                    }
                }
            }
            else {
                for (Card ca : list) {
                    ca.setStatus(Card.Status.HIDDEN);
                }
            }
            list.clear();
            lock = false;
        }
        
    }
}

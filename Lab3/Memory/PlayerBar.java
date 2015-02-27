import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class PlayerBar extends JPanel {
    private ArrayList<Player> playerList;
    private String[] playerNames;

    public PlayerBar(String... players) {
        playerList = new ArrayList<Player>();
        playerNames = players;
        addPlayers();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
        
    }
    
    public ArrayList<Player> getPlayers() {
        return playerList;
    }
    
    void addPlayers() {
        for (String p : playerNames) {
            addPlayer(p);
        }
    }
    
    public class Player extends JLabel{
        
        int points;
        String name;
        
        Player(String name){
            setBackground(Color.white);
            setOpaque(true);
            setText("<html>Player: <br> " + name + "<br>Total points: " + getPoints());
            this.name = name;
            points = 0;
            
        }
        
        public int getPoints(){
            return points;
        }
        
        public void addPoint(){
           points++;
           setText("<html>Player: <br> " + name + "<br>Total points: " + getPoints());
           repaint();
        }
        
        public void setActive() {
            setBackground(Color.yellow);
        }
        
        public void setPassive() {
            setBackground(Color.white);
        }
        
        public void resetScore(){
            points = 0;
            setText("<html>Player: <br> " + name + "<br>Total points: " + getPoints());
            repaint();
        }
    
    
    }
    
    public void resetScore(){
        for (Player p : playerList)
            p.resetScore();   
    }
    
    private void addPlayer(String name) {
        Player p = new Player(name);
        playerList.add(p);
        add(p);
    }
    
}

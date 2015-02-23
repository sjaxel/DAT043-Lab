import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class PlayerBar extends JPanel {
    private String[] players;

    public PlayerBar(String... players) {
        this.players = players;
        addPlayers();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
        
    }
    
    void addPlayers() {
        for (String p : players) {
            addPlayer(p);
        }
    }
    
    private void addPlayer(String name) {
        JPanel player = new JPanel();
        JLabel playerlabel = new JLabel("<html>Player: <br> " + name);
        player.add(playerlabel);
        add(player);
    }
    
}

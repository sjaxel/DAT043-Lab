import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuBar extends JMenuBar {
    MemController parent;
    
    MenuBar(JPanel parent) {
    this.parent = (MemController)parent;
    addGameMenu();
    }
    
    private void addGameMenu() {
        JMenu menu = new JMenu("Game");
        JMenuItem menuItem = new JMenuItem("Nytt spel");
        menuItem.setActionCommand("Nytt spel");
        menuItem.addActionListener(parent);
        menu.add(menuItem);
        add(menu);
    }
}

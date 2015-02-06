import java.awt.*;
import javax.swing.*;

public class Card extends JButton {
    private Icon picture;
    private Status state;
    enum Status {
        HIDDEN, VISIBLE, MISSING;
    }
    
    public Card(Icon pic, Status s) {
        super(pic);
        state = s;
        picture = pic;
        if (state == Status.HIDDEN) {
            setIcon(null);
            setBackground(Color.BLUE); }
        else if (state == Status.MISSING) {
            setIcon(null);
            setBackground(Color.WHITE); }
    }
    
    public Card(Icon pic) {
        this(pic, Status.MISSING);
    }
    
    public Card copy() {
        Card copycard = new Card(picture, state);
        return copycard;
    }
    
    public void setStatus(Status s) {
        state = s;
        if (state == Status.HIDDEN) {
            setIcon(null);
            setBackground(Color.BLUE); }
        else if (state == Status.MISSING) {
            setIcon(null);
            setBackground(Color.WHITE); }
        else if (state == Status.VISIBLE) {
            setIcon(picture);
            setBackground(Color.BLUE); }
    }
    
    public Status getStatus() {
        return state;
    }
    
    public boolean equalIcon(Card c) {
        if (c.getIcon() == this.getIcon()) {
        return true;
        }
        else
        {
        return false;
        }
    }
    
}



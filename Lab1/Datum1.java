import javax.swing.*;
public class Datum1 {
    public static void main (String[] arg) {
        while (true) {
            String syear = JOptionPane.showInputDialog(null, "Årtal");
            if (syear != null) {
                int year = Integer.parseInt(syear);
                if (year%4 == 0 | year%100 == 0) {
                    JOptionPane.showMessageDialog(null, "Skottår");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Inte Skottår");
                }
            }
            else {
                break;
            }
        }
    }
}


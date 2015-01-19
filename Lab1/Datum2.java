import javax.swing.*;
public class Datum2 {
    public static void main (String[] arg) {
        while (true) {
            String q = JOptionPane.showInputDialog("Skriv i formen åååå-mm-dd");
            if (q != null) {
                String syear  = q.substring(0, 4);
                String smonth = q.substring(5, 7);
                String sday = q.substring(8, 10);
                int year = Integer.parseInt(syear);
                int month = Integer.parseInt(smonth);
                int day = Integer.parseInt(sday);
                int leapyear;
                if ((year%4 == 0 | year%100 == 0) && month >= 3) {  
                    leapyear = 1;
                }
                else {
                    leapyear = 0;
                }
                int m = (month-3);
                int days;
                
                if (month == 01) {
                    days = day;
                }
                else if (month == 02) {
                    days = 31 + day;
                }
                else if (month == 03) {
                    days = 31 + 28 + leapyear + day;
                }                
                else {
                    days = 90 + leapyear + ((m*306 + 5)/10);
                }
                JOptionPane.showMessageDialog(null, "Dagens nummer är: " + days);
            }
            else {
                break;
            }
        }
    }
}

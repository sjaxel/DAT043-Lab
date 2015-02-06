import java.util.*;
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
                
                int totdays = 0;

                List<Integer> months = Arrays.asList(31,28,31,30,31,30,31,31,30,31,30,31);
                for (int i=0; i < month-1; i++){
                    totdays += months.get(i);
                }
                totdays += day;
                if(month>=3){
                    totdays += leapyear;
                }
                
               
                JOptionPane.showMessageDialog(null, "Dagens nummer är: " + totdays);
            }
            else {
                break;
            }
        }
    }
}

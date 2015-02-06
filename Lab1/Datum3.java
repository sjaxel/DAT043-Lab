import javax.swing.*;
import java.util.*;

public class Datum3 {
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
                
                System.out.println(year + "-" + month + "-" + day);
                
                int i = 1754;
                int totaldays = 0;
                while (i < year) {
                    if ((i % 4 == 0) && (i % 100 != 0) || (i % 400 == 0)) {
                        totaldays += 366;
                    }
                    else {
                        totaldays += 365;
                    }
                    i = i + 1;
                }    
                int leapyear;
                if (((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) && month >= 3) {  
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
                    days = 59 + leapyear + ((m*306 + 5)/10) + day;
                }
                totaldays += days;
                
                List<String> weekdays = Arrays.asList("Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag");
                
                
                System.out.println("Det är " + weekdays.get(totaldays%7));

            }
            else {
                break;
            }
        }
    }
}

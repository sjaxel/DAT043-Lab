import javax.swing.*;

public class Date {
	// instansvariabler
	private int year, month, day, tdays;
	private boolean leapyear;
	
	Date(int y, int m, int d) {
		year = y;
		month = m;
		day = d;
		leapyear = isLeap();
		if (leapyear == true) {
		    tdays = 366;
		}
		else {
		    tdays = 365;
		}
		
	}
	public int dayNum() {
		return day;
	}
	public boolean isLeap() {
	    if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
	        leapyear = true;
	    }
	    else {
	        leapyear = false;
	    }
	    return leapyear;
	}
	
	    
	public static void main (String[] arg) {
		while (true) {
		String q = JOptionPane.showInputDialog("Skriv i formen åååå-mm-dd");
        if (q != null) {
            
            String syear  = q.substring(0, 4);
            String smonth = q.substring(5, 7);
            String sday = q.substring(8, 10);
            
            int y = Integer.parseInt(syear);
            int m = Integer.parseInt(smonth);
            int d = Integer.parseInt(sday);
            int[] listOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            
            int totaldays = 0;
            int i = y;
            while (i > 1754) {
                Date iyear = new Date(i-1, 01, 01);
                totaldays = totaldays + iyear.tdays;
                i = i - 1;
            }
            i = m;
            while (i > 1) {
                totaldays = totaldays + listOfMonth[i-2];
                i = i - 1;
            }
            totaldays = totaldays + d;
            
            Date userdate = new Date(y,m,d);
            if ((userdate.leapyear == true) && (userdate.month > 2)) {
                totaldays = totaldays + 1;
            }
            System.out.println(totaldays);
            if (totaldays%7 == 0) {
                   System.out.println("Det är en Måndag");
                }
                else if (totaldays%7 == 1 ) {
                   System.out.println("Det är en Tisdag");
                }
                else if (totaldays%7 == 2 ) {
                   System.out.println("Det är en Onsdag");
                }
                else if (totaldays%7 == 3 ) {
                   System.out.println("Det är en Torsdag");
                }
                else if (totaldays%7 == 4 ) {
                   System.out.println("Det är en Fredag");
                }
                else if (totaldays%7 == 5 ) {
                   System.out.println("Det är en Lördag");
                }
                else if (totaldays%7 == 6 ) {
                   System.out.println("Det är en Söndag");
                }
           }
           else break;
        }
	}
}

import javax.swing.*;

class Axeltest {
	// instansvariabler
	private int year, month, day;
	
	Axeltest(int y, int m, int d) {
		year = y;
		month = m;
		day = d;
	}
	public int dayNum() {
		return day;
	}
	public static void main (String[] arg) {
		String q = JOptionPane.showInputDialog("Skriv i formen åååå-mm-dd");
        if (q != null) {
            
            String syear  = q.substring(0, 4);
            String smonth = q.substring(5, 7);
            String sday = q.substring(8, 10);
            
            int y = Integer.parseInt(syear);
            int m = Integer.parseInt(smonth);
            int d = Integer.parseInt(sday);
            
            Axeltest date = new Axeltest(y,m,d);
            System.out.println(date.year + "-" + date.month + "-" + date.day);
        }
	}
}

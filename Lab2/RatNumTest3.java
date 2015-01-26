import java.io.*;
import java.util.*;
import java.util.regex.*;

class RatNum {
	// Instance variables
	private int m, n;
	
	//Constructor methods
	RatNum() {
		m = 0;
		n = 1;
	}
	RatNum(int a) {
		m = a;
		n = 1;
	}
	RatNum(int a, int b) {
		if (b != 0) {
		    if (a == 0) {
		        m = 0;
		        n = 1;
		    }
		    else {
		        if ((a < 0) && (b < 0)) {
		            a = -a;
		            b = -b;
		            }
		        else if ((a > 0) && (b < 0)) {
		            a = -a;
		            b = -b;
		        }
			    if (RatNum.sgd(a, b) == 1) {
			    	m = a;
			    	n = b;
			    }
			    else {
			    	m = a/RatNum.sgd(a, b);
			    	n = b/RatNum.sgd(a, b);
			    }
			}
		}
		else {
		throw new NumberFormatException("Denominator = 0");
		}
	}
	//Copy constructor
	RatNum(RatNum r) {
		m = r.getNumerator();
		n = r.getDenominator();
	}
	
	//Parse constructor
	RatNum (String s) {
	    m = RatNum.parse(s).getNumerator();
        n = RatNum.parse(s).getDenominator();
    }
	
	//Methods
	public int getNumerator() { // Returns Numerator
		return m;
	}
	
	public int getDenominator() { // Return Denominator
		return n;
	}
	
	// Greatest common denomoniator method
	public static int sgd(int m, int n) {
		if ((m != 0) && (n != 0)) {
			int r = 1;
			while (r != 0) {
				r = m % n;
				m = n;
				n = r;
			}
			return Math.abs(m);
		}
		throw new IllegalArgumentException ();	
	}
	
	//To string method. Returns as string.
	public String toString() {
	    String s = "Talet är: " + m + "/" + n;
	    return s;
	    }
	    
    //To double method
    public double toDouble() {
        return ((double) m/(double) n);
	}
	
	//Parse method.
	public static RatNum parse(String s) {
	    Pattern pattern_whitespace = Pattern.compile("\\s");
	    Pattern pattern_slash = Pattern.compile("/");
        Matcher matcher_whitespace = pattern_whitespace.matcher(s);
        Matcher matcher_slash = pattern_slash.matcher(s);
        boolean found_whitespace = matcher_whitespace.find();
        boolean found_slash = matcher_slash.find();
        if (found_whitespace)
            throw new NumberFormatException("Whitespace in exp");
	    String[] tokens = s.split("[/]");
	    if (tokens.length == 1 && !(found_slash)) {
	        int m = Integer.parseInt(tokens[0]);
	        return new RatNum(m);
	    }
	    else if (tokens.length == 2) {
	        int m = Integer.parseInt(tokens[0]);
	        int n = Integer.parseInt(tokens[1]);
	        return new RatNum(m, n);	        
	    }
	    else {
	        throw new NumberFormatException("Incorrect formating");
	    }
	}
	
	//Clone method
	public Object clone() {
	    Object clone = new RatNum(m, n);
	    return clone;
	}
	
	public boolean equals(RatNum r) {
	    if (m == r.getNumerator() && n == r.getDenominator())
	        return true;
	    else
	        return false;
	}
	
	public boolean lessThan(RatNum r) {
	    int a = r.getNumerator();
	    int b = r.getDenominator();
	    if (m*b < a*n)
	        return true;
	    else
	        return false;
	}
	
	public RatNum add(RatNum r) {
	    int a = r.getNumerator();
	    int b = r.getDenominator();
	    return new RatNum((m*b+n*a), (n*b));
	}
	
	public RatNum sub(RatNum r) {
	    int a = r.getNumerator();
	    int b = r.getDenominator();
	    return new RatNum((m*b-n*a), (n*b));
	}
	
	public RatNum mul(RatNum r) {
	    int a = r.getNumerator();
	    int b = r.getDenominator();
	    return new RatNum((m*a), (n*b));
	}
	
	public RatNum div(RatNum r) {
	    int a = r.getNumerator();
	    int b = r.getDenominator();
	    return new RatNum((m*b), (n*a));
	}	
}

class RatNumTest3 {

  private static void divTester() {
    // Testar equals och clone
    RatNum x = new RatNum(6,2);
    RatNum y = new RatNum(0);
    RatNum z = new RatNum(0,1);
    RatNum w = new RatNum(75,25);    
    if (x.equals(y) || !y.equals(z) || !x.equals(w))
      System.out.println("RatNumTest3: FEL i equals!!");
    try {
      y = (RatNum) x.clone();
    }
    catch (Exception ce) {}
    if (!y.equals(x) || y==x)
       System.out.println("RatNumTest3: FEL I clone!!");
    // Testar toDouble         
    for (int i=1; i<=9; i++) 
      for (int j=0; j <= 2*i; j++) 
        if( Math.abs(new RatNum(j, i).toDouble() - (double)j/i) > 1.0e-13)
          System.out.println("RatNumTest3: FEL i toDouble!! för " + j + "/" + i);  
  }     
    
  public static void main(String[] arg) throws IOException {
    RatNumTest2.divTester();
    divTester();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Skriv uttryck på formen a/b ? c/d, där ? är något av tecknen + - * / = <");
    while (true) {
      System.out.print("> ");  System.out.flush();
      String s = in.readLine();
      if (s == null)
        break;
      Scanner sc = new Scanner(s);
      String[] a = new String[3];
      int i;
      for(i=0; i<3 && sc.hasNext(); i++)
        a[i] = sc.next();
      if (i > 0 ) {
        System.out.print(s + "\t--> ");
        if (i != 3 || sc.hasNext())  
          System.out.println("Felaktigt uttryck!");
        else 
        try {            
          RatNum r1 = RatNum.parse(a[0]);
          String op = a[1];
          char c = op.charAt(0);
          RatNum r2 = new RatNum(a[2]);
          if (op.length() != 1 || "+-*/=<".indexOf(c) < 0)
            System.out.println("Felaktig operator!");
          else {
            RatNum res = null;
            if (c == '+')
              res = r1.add(r2);
            else if (c == '-')
              res = r1.sub(r2);
            else if (c == '*')
              res = r1.mul(r2);
            else  if (c == '/')
              res = r1.div(r2);
            else if (c == '=')
              System.out.println(r1.equals(r2));
            else if (c == '<')
              System.out.println(r1.lessThan(r2));
            if ("+-*/".indexOf(c) >= 0) 
              if (res == null)
                System.out.println("Fel i add, sub, mul eller div");
              else        
                System.out.println(res);
          }
        }
        catch (NumberFormatException e) {
          System.out.println("NumberFormatException: " + e.getMessage());
        }         
      }
    }
  }
}




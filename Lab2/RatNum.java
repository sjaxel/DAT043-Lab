import java.io.*;
import java.util.*;
import java.util.regex.*;

public class RatNum {
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
		        if (b < 0) {
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









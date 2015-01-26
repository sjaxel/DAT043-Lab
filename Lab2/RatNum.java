import javax.swing.*;
import java.util.*;

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
			if (RatNum.sgd(a, b) == 1) {
				m = a;
				n = b;
			}
			else {
				m = a/RatNum.sgd(a, b);
				n = b/RatNum.sgd(a, b);
			}
		}
		else {
		throw new NumberFormatException("Denominator = 0");
		}
	}
	
	RatNum(RatNum r) { // Copy constructor (why is this useful?)
		m = r.getNumerator();
		n = r.getDenominator();
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
	
	//Main loop for testing
	public static void main (String[] arg) {
		RatNum tal = new RatNum(25, 15);
		RatNum tal2 = new RatNum(tal);
		System.out.println(tal2.getNumerator());
		System.out.println(tal2.getDenominator());
	}
}

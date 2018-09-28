public class Fraction {
			
	//Greatest Common Denominator
	private long calculateGCD() {
	    long gcd = numerator;
	    long remainder = denominator;
	    while(remainder != 0) {
	        long temp = remainder;
	        remainder = gcd % remainder;
	        gcd = temp;
	    }
	    return gcd;
	}
	
	//Reduce based on Greatest Common Denominator
	private void reduce() {
		long gcd = calculateGCD();
		numerator /= gcd;
		denominator /= gcd;
	}
	
	
	
	public long numerator;
	public long denominator;
	
	

	//Constructor
	public Fraction() {
		numerator = 0;
		denominator = 1;
	}
	
	//Constructor
	public Fraction(long n, long d) throws Exception {
		numerator = n;
		denominator = d;
		
		reduce();
		
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
		
		if (denominator == 0) {
			throw new Exception("Denominator is Zero");
		}
		
	}
	
	
	
	public Fraction plus(Fraction rhs) throws Exception {
		long newNumer = (numerator * rhs.denominator) + (rhs.numerator * denominator);
		long newDenom = (denominator * rhs.denominator);
		Fraction newFraction = new Fraction (newNumer, newDenom);
		
		return newFraction;
	}
	
	public Fraction minus(Fraction rhs) throws Exception {
		long newNumer = (numerator * rhs.denominator) - (rhs.numerator * denominator);
		long newDenom = (denominator * rhs.denominator);
		Fraction newFraction = new Fraction (newNumer, newDenom);
		
		return newFraction;
	}
	
	public Fraction times(Fraction rhs) throws Exception {
		long newNumer = (numerator * rhs.numerator);
		long newDenom = (denominator * rhs.denominator);
		Fraction newFraction = new Fraction (newNumer, newDenom);
		
		return newFraction;
	}
	
	public Fraction dividedBy(Fraction rhs) throws Exception {
		Fraction reciprocalFraction = rhs.reciprocal();
		long newNumer = (numerator * reciprocalFraction.numerator);
		long newDenom = (denominator * reciprocalFraction.denominator);
		Fraction newFraction = new Fraction (newNumer, newDenom);
		
		return newFraction;
	}
	
	
	
	public Fraction reciprocal() throws Exception {
		long newNumer = denominator;
		long newDenom = numerator;
		Fraction newFraction = new Fraction(newNumer, newDenom);
		
		return newFraction;
	}
	
	
	
	public String toString() {
		String stringNumer = String.valueOf(numerator);
		String stringDenom = Long.toString(denominator);
		
		return (stringNumer + "/" + stringDenom);
	}
	
	public double toDouble() {
		return (double) numerator/(double) denominator;
	}
	
	
}

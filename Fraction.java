
public class Fraction {
	private int num;
	private int den;
	
	// full constructor
	public Fraction(int num, int den){
		this.num = num;
		this.den = den;
		if (den < 0) {
			this.den *= -1;
			this.num *= -1;
		}
		this.toString();
	}
	
	
	// fraction to integer as fraction
	public Fraction(int num){
		this(num, 1);
	} 
	
	// fraction to default (0/1) value
	public Fraction(){
		this(0, 1);
	}
	
	
	// methods
	public int getNumerator(){
		return num;
	}
	
	public int getDenominator(){
		return den;
	}
	
	public String toString(){
		return num + "/" + den; 
	}
	
	public double toDouble(){
		return (double) num/den;
	}
	
	public Fraction add(Fraction other){
		int newNum = (num * other.den)+(den * other.num);
		int newDen = den * other.den;
		return new Fraction(newNum, newDen);
	}
	
	public Fraction subtract(Fraction other){
		int newNum = (num * other.den)-(den * other.num);
		int newDen = den * other.den;
		return new Fraction(newNum, newDen);
	}
	
	public Fraction multiply(Fraction other){
		int newNum = num * other.num;
		int newDen = den * other.den;
		return new Fraction(newNum, newDen);
	}
	
	public Fraction divide(Fraction other){
		if (den == 0){
			throw new IllegalArgumentException();
		} else{
			int newNum = num * other.den;
			int newDen = den * other.num;
			return new Fraction(newNum, newDen);
		}
				
	}
	
	public boolean equals(Object other){
		//return num * other.den == other.num * den;
		if (!(other instanceof Fraction)){
			return false;
		}
		Fraction otherFrac = (Fraction) other;
		return num * otherFrac.den == otherFrac.num * den;
	}  

	public void toLowestTerms(){
		int gcd = gcd(num, den);
		if (gcd > 0){
			num = num / gcd;
			den = den / gcd;
		}
	}
	
	private static int gcd(int num, int den){
		while (num != 0 && den != 0){
			int remain = num % den;
			num = den;
			den = remain;
		} return num;
	}
}

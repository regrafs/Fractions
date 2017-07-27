import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FractionCalculator {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		
		intro();

		while (true){
			System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
			String operator = getOperation(reader);
			System.out.print("Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
			Fraction fraction1 = getFraction(reader);			
			System.out.print("Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
			Fraction fraction2 = getFraction(reader);
			allTogether(fraction1, fraction2, operator);
			System.out.println("--------------------------------------------------------------------------------");
		}

	}
	// explains program and user interaction
	private static void intro(){
		System.out.println("This program is a fraction calculator.\n"
				+ "It will add, subtract, multiply, and divide fractions until you type Q to quit.\n"
				+ "Please enter your fractions in the form a/b, where a and b are integers.\n"
				+ "--------------------------------------------------------------------------------");
	}
		//gets an operator and checks for quit 
	public static String getOperation(Scanner reader){
		List<String> acceptedOps = new ArrayList<>(Arrays.asList("+","-","*","/","="));
		String operator = reader.nextLine();

		while(!acceptedOps.contains(operator)){
			if (operator.equals("q")||operator.equals("Q")){
				System.exit(0);
			} 
			System.out.print("Invalid operation. Please enter an operation (+, -, /, *, = or Q to quit): ");
			operator = reader.nextLine();
		} return operator;
	}

	public static boolean validFraction(String frac){
		if (frac.contains("/")&&!frac.contains(".")){  // finds if String is fraction by asking if it has a slash
			int slashIndex = frac.indexOf("/");
			String num = frac.substring(0, slashIndex); 
			String den = frac.substring(slashIndex+1);
			String concat = num+den;
			return isNumber(concat)&&!frac.endsWith("/0")&&!num.isEmpty()&&!den.isEmpty()&&!den.contains("-");
		} else return isNumber(frac)&&!frac.isEmpty();
	}
    
         
         //checks if user input String is a number
    private static boolean isNumber(String concat){
    	concat = concat.replace("-", "");
    	for (int i = 0; i < concat.length(); i++){
    		if (!Character.isDigit(concat.charAt(i))){
    			return false;
    		}
    	} return true;
//    	return concat.matches("\\d{1,}")&&!concat.matches("\\D+");  alternate method with matches() instead of looping
    }
	
    public static Fraction getFraction(Scanner reader){
    	String frac = reader.nextLine();
    	
    	//user input to exit program
  		if (frac.equals("q")||frac.equals("Q")){
			System.exit(0);
		}
  		
  		//validation, re-prompting
    	while(!validFraction(frac)){
    		System.out.println("Invalid fraction.  Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
    		frac = reader.nextLine();
    	} if (frac.contains("/")){
    		int slashIndex = frac.indexOf("/");
    		int num = Integer.parseInt(frac.substring(0, slashIndex));
    		int den = Integer.parseInt(frac.substring(slashIndex+1));
    		return new Fraction(num, den); // creates irrational
    	} 
    	int num = Integer.parseInt(frac);
    	return new Fraction(num); // creates "whole" fraction
    }

	public static void allTogether(Fraction frac1, Fraction frac2, String operator){
		String[] acceptedOps = {"+","-","*","/"};
		Fraction add = frac1.add(frac2);
		Fraction sub = frac1.subtract(frac2);
		Fraction mult = frac1.multiply(frac2);
		Fraction div = frac1.divide(frac2);
		Fraction[] fracList = {add, sub, mult, div};

		
		for (int i=0; i<acceptedOps.length; i++){
			if (acceptedOps[i].equals(operator)){
				fracList[i].toLowestTerms();
				String total = fracList[i].toString();
				String newFrac1 = frac1.toString();
				String newFrac2 = frac2.toString();
				
				// "rounds" String to whole number
				if (newFrac1.endsWith("/1")||newFrac1.charAt(0)=='0'){
					newFrac1 = newFrac1.substring(0, newFrac1.length()-2);
				} 
				if(newFrac2.endsWith("/1")||newFrac2.charAt(0)=='0'){
					newFrac2 = newFrac2.substring(0, newFrac2.length()-2);
				}
				
				if (total.endsWith("/0")){  // dividing by zero results in an undefined equation
					System.out.println(newFrac1 + " " + operator + " " + newFrac2 + " = Undefined");
				} else if(total.endsWith("/1")||total.charAt(0)=='0'){
					System.out.println(newFrac1 + " " + operator + " " + newFrac2 + " = " + total.substring(0, total.length()-2));
				} else {
					System.out.println(newFrac1 + " " + operator + " " + newFrac2 + " = " + total);
				}
			}
		}
		
		if (operator.equals("=")){
			boolean equals = frac1.equals(frac2);
			System.out.println(frac1 + " " + operator + " " + frac2 + " is " + equals);
		} 
	}
}

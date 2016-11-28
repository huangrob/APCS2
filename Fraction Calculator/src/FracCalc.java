import java.util.*;

public class FracCalc {
	
	public static void main (String[] args){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter two mixed fractions (with underscore representing \"and\") separated by spaces and an operator.");
		String mixedFraction = userInput.nextLine();
		do {
			if (mixedFraction.contains(" ") && (mixedFraction.contains("+")||mixedFraction.contains("-")||mixedFraction.contains("*")||mixedFraction.contains("/"))){
				System.out.println(produceAnswer(mixedFraction));
			} 
			else {
				System.out.println("Please enter a fraction with an operator and spaces between each term.");
			}
			System.out.println("If you wish to quit, type quit");
			System.out.println("Enter two mixed fractions (with underscore representing \"and\") separated by spaces and an operator.");
			mixedFraction = userInput.nextLine();
		}
		while (!mixedFraction.equals("quit"));
	}
	
	public static String produceAnswer(String mixedFraction){
		if (mixedFraction.contains(" ")){
			String[] produceAnswer = mixedFraction.split(" ");
			String secondOperand = produceAnswer[2];
			if (secondOperand.contains("_") && secondOperand.contains("/")){
				return mixedFraction(secondOperand);
			} else if (!secondOperand.contains("/") && !secondOperand.contains("_")){
				return integer(secondOperand);
			} else {
				return improperFraction(secondOperand);
			}
		} else {
			return "Please enter spaces between the fractions.";
		}
	}
	
	public static String mixedFraction (String secondOperand){
		String[] produceAnswerWhole = secondOperand.split("_");
		String whole = produceAnswerWhole[0];
		String fraction = produceAnswerWhole[1];
		String[] produceAnswerFrac = fraction.split("/");
		String numerator = produceAnswerFrac[0];
		String denominator = produceAnswerFrac[1];
		String parsedFraction = "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
		return parsedFraction;
	}
	
	public static String integer (String secondOperand){
		String whole = secondOperand;
		String numerator = "0";
		String denominator = "1";
		String parsedFraction = "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
		return parsedFraction;
	}
	
	public static String improperFraction (String secondOperand){
		String[] produceAnswerFrac = secondOperand.split("/");
		String numerator = produceAnswerFrac[0];
		String denominator = produceAnswerFrac[1];
		String whole = "0";
		String parsedFraction = "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
		return parsedFraction;
	}
}

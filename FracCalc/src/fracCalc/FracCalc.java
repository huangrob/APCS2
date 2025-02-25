// Robert Huang
// 12/10/16
// APCS Period 1
// Fraction Calculator

package fracCalc;
import java.util.*;

public class FracCalc {
	
	public static void main (String[] args){
		Scanner userInput = new Scanner(System.in); //gets user input from scanner
		System.out.println("Enter two mixed fractions (with underscore representing \"and\") separated by spaces and an operator.");
		String mixedFraction = userInput.nextLine();
		do {
			//checks if userInput contains the required characters in the equation
			if (mixedFraction.contains(" ") && (mixedFraction.contains("+")||mixedFraction.contains("-")||mixedFraction.contains("*")||mixedFraction.contains("/"))){
				System.out.println(produceAnswer(mixedFraction)); //sends userInput to produceAnswer and prints the answer
			} 
			else {
				System.out.println("Please enter a fraction with an operator and spaces between each term.");
			}
			System.out.println("If you wish to quit, type quit");
			System.out.println("Enter two mixed fractions (with underscore representing \"and\") separated by spaces and an operator.");
			mixedFraction = userInput.nextLine(); //gets next equation from user
		}
		while (!mixedFraction.equals("quit")); //continually asks for input until user types quit
	}
	
	//produceAnswer will split the userInput into the first operand, second operand, and operator and send them to calculateEquation
	public static String produceAnswer(String mixedFraction){
		String[] partsOfFrac = mixedFraction.split(" "); //splits userInput at the spaces to obtain the two operands
		//store the two operands and the operator of the userInput
		String[] firstOperand = produceOperand(partsOfFrac, 0); 
		String[] secondOperand = produceOperand(partsOfFrac, 2);
		String operator = partsOfFrac[1];
		return calculateEquation(firstOperand, secondOperand, operator); //passes the parts to calculateEquation
	}
	
	//produceOperand will split the operand into the parts of the fraction
	public static String[] produceOperand(String[] partsOfFrac, int element) {
		String operand = partsOfFrac[element];
		//declare the necessary components of the fraction
		String whole = "";
		String numerator = "";
		String denominator = "";
		//checks and splits the operand if it is a mixed number
		if (operand.contains("_") && operand.contains("/")){
			String[] produceWhole = operand.split("_");
			whole = produceWhole[0];
			String fraction = produceWhole[1];
			String[] produceAnswerFrac = fraction.split("/");
			numerator = produceAnswerFrac[0];
			denominator = produceAnswerFrac[1];
		//checks and splits the operand if it is an integer
		} else if (!operand.contains("/") && !operand.contains("_")){
			whole = operand;
			numerator = "0";
			denominator = "1";
		//splits the operand if it is an improper fraction
		} else {
			String[] produceAnswerFrac = operand.split("/");
			numerator = produceAnswerFrac[0];
			denominator = produceAnswerFrac[1];
			whole = "0";
		}
		String[] splitFraction = {whole, numerator, denominator};
		return splitFraction;
	}
	
	//calculateEquation will call the calculation methods and return the answer given by those calculations
	public static String calculateEquation (String[] firstOperand, String[] secondOperand, String operator){
		//convert the components of each operand to integers
		int[] first = stringToInt(firstOperand);
		int[] second = stringToInt(secondOperand);
		//convert the operands to improper fractions is the form {0, numerator, denominator}
		first = toImproperFrac(first);
		second = toImproperFrac(second);
		int[] answer = new int[3];
		//checks the operator of the equation and calls the appropriate calculation method
		if (operator.equals("+")){
			answer = add(first, second);
		}
		else if (operator.equals("-")){
			answer = subtract(first, second);
		}
		else if (operator.equals("*")){
			answer = multiply(first, second);
		}
		else if (operator.equals("/")){
			answer = divide(first, second);
		}
		answer = toMixedNum(answer); //sends the answer to toMixedNum
		answer = reduceFraction(answer); //sends the answer to reduceFraction
		String finalAnswer = toString(answer); //converts the answer from an integer array to a String
		return finalAnswer;
	}
	
	//stringToInt converts the string array into an integer array
	public static int[] stringToInt (String[] stringArray){
		int[] intArray = new int[3];
		if (stringArray[0].contains("-")){
			stringArray[1] = "-" + stringArray[1]; //if the whole component is negative, the numerator is also negative (this negative will be removed later)
		}
		for (int i = 0; i <= 2; i++) {
			intArray[i] = Integer.parseInt(stringArray[i]); //parseInt converts strings to integers
		}
		return intArray;
	}
	
	//toImproperFrac converts a fraction into an improper fraction
	public static int[] toImproperFrac(int[] operand){
		int[] improperFrac = new int[3];
		improperFrac[0] = 0; //the whole part is equal to 0
		improperFrac[1] = operand[0] * operand[2] + operand[1]; //the numerator of the fraction
		improperFrac[2] = operand[2]; //the denominator of the fraction stays the same
		return improperFrac;
	}
	
	//add adds the two operands of the user input
	public static int[] add (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[2] == secondOperand[2]){ //if the denominators are the same
			for (int i = 0; i <= 1; i++) {
				answer[i] = firstOperand[i] + secondOperand[i]; //adds the whole and numerator parts
			}
			answer[2] = firstOperand[2]; //denominator stays the same
		}
		else { //if denominators are different
			int gcf = gcf(firstOperand[2], secondOperand[2]); //find greatest common factor of the denominators
			answer[1] = (firstOperand[1] * (secondOperand[2]/gcf) + (secondOperand[1] * (firstOperand[2])/gcf)); //add the fractions with a common denominator
			answer[2] = firstOperand[2] * (secondOperand[2]/gcf); //denominator is now common denominator
		}
		return answer;
	}
	
	//subtract subtracts the two operands of the user input
	public static int[] subtract (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[2] == secondOperand[2]){ //if denominators are the same
			for (int i = 0; i <= 1; i++) {
				answer[i] = firstOperand[i] - secondOperand[i]; //subtracts the whole and numerator parts
			}
			answer[2] = firstOperand[2]; //denominator stays the same
		}
		else { //if denominators are different
			int gcf = gcf(firstOperand[2], secondOperand[2]); //find greatest common factor of the denominators
			answer[1] = (firstOperand[1] * (secondOperand[2]/gcf) - (secondOperand[1] * (firstOperand[2])/gcf)); //subtract the fractions with a common denominator
			answer[2] = firstOperand[2] * (secondOperand[2]/gcf); //denominator is now common denominator
		}
		return answer;
	}
	
	//multiply multiplies the two operands of the user input
	public static int[] multiply (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[1] * secondOperand[1] == 0){ //if one numerator is equal to 0
			answer[1] = 0; //numerator is set to 0
			answer[2] = 1; //denominator is set to 1
		}
		else { //if neither numerator is 0
			for (int j = 1; j <= 2; j++) {
				answer[j] = firstOperand[j] * secondOperand[j]; //multiply the respective numerators and the denominators
			}
		}
		return answer;
	}
	
	//divide divides the two operands of the user input
	public static int[] divide (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[1] == 0){ //if the first operand's numerator is 0
			answer[1] = 0; //numerator is set to 0
			answer[2] = 1; //numerator is set to 1
		}
		else { //if the first operand's numerator is not 0
			//first operand is multiplied by the reciprocal of the second operand
			answer[1] = firstOperand[1] * secondOperand[2];
			answer[2] = firstOperand[2] * secondOperand[1];
			//if the denominator is negative, move the negative to the numerator
			if (answer[2] < 0){
				answer[2] = Math.abs(answer[2]);
				answer[1] = -1 * answer[1];
			}
		}
		return answer;
	}

	//gcf calculates the greatest common factor of two numbers
	public static int gcf(int num1, int num2){
		//can only calculate gcf of positive numbers
		num1 = Math.abs(num1);
		num2 = Math.abs(num2);
		if(num1 > num2){ //if first number is larger than second number
			for(int i = num2; i >= 1; i--){ //for loop checks integers from smaller number to 1 if both numbers are divisible by the same integer
				if(num1 % i == 0 && num2 % i == 0){
					return i;
				}
			}
		} else { //if second number is larger than the first number
			for(int j = num1; j >=1; j--){ //for loop checks integers from smaller number to 1 if both numbers are divisible by the same integer
				if(num1 % j == 0 && num2 % j == 0){
					return j;
				}
			}
		}
		return 1; //if both previous if statements do not pass, return 1
	}
	
	//toMixedNum converts an improper fraction into a mixed number
	public static int[] toMixedNum(int[] improperFrac){
		if (Math.abs(improperFrac[1]) >= improperFrac[2]){ //absolute value to test whether the magnitude of the numerator is greater than the denominator
			int[] mixedNum = new int[3];
			mixedNum[0] = improperFrac[1] / improperFrac[2]; //integer division of numerator and denominator
			mixedNum[1] = Math.abs(improperFrac[1] % improperFrac[2]); //absolute value to make numerator positive from when stringToInt turned it negative
			mixedNum[2] = improperFrac[2]; //denominator stays constant
			return mixedNum;
		}
		else { //if the fraction is a proper fraction, return the fraction
			return improperFrac;
		}
	}
	
	//reduceFraction reduces the fraction into its simplest form
	public static int[] reduceFraction(int[] fraction){
		int gcf = gcf(fraction[1], fraction[2]);
		if (gcf > 1){ //if fraction is not in its simplest form, divide both numerator and denominator by the gcf
			fraction[1] = fraction[1] / gcf;
			fraction[2] = fraction[2] / gcf;
		}
		return fraction; //returns simplest form of the fraction
	}
	
	//toString converts the integer array into a String
	public static String toString(int[] mixedNum){
		if (mixedNum[0] == 0 && mixedNum[1] != 0){ //if the whole component is equal to 0 and the numerator is not equal to 0
			return (mixedNum[1] + "/" + mixedNum[2]); //return the fraction in the form of a proper fraction
		}
		else if (mixedNum[0] == 0 && mixedNum[1] == 0){ //if the whole component and the numerator are both equal to 0
			return ("0"); //return 0
		}
		else if (mixedNum[0] != 0 && mixedNum[1] == 0){ //if the whole component is not equal to 0 and the numerator is
			return mixedNum[0] + ""; //return the fraction in the form of an integer
		}
		else { //if the whole component and the numerator are both not equal to 0
			return (mixedNum[0] + "_" + mixedNum[1] + "/" + mixedNum[2]); //return the fraction in the form of a mixed number
		}
	}
}

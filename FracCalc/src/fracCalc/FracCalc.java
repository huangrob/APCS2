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
			mixedFraction = userInput.nextLine(); //gets next equation
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
	
	//this method will call the calculation methods and return the answer given by those calculations
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
		answer = reduceFraction(answer); //sends the answer to reduceFraction
		answer = toMixedNum(answer); //sends the answer to toMixedNum
		String finalAnswer = toString(answer); //converts the answer from an integer array to a String
		return finalAnswer;
	}
	
	//this method converts the string array into an integer array
	public static int[] stringToInt (String[] stringArray){
		int[] intArray = new int[3];
		if (stringArray[0].contains("-")){
			stringArray[1] = "-" + stringArray[1]; //if the whole part is negative, the numerator is also negative
		}
		for (int i = 0; i <= 2; i++) {
			intArray[i] = Integer.parseInt(stringArray[i]); //parseInt converts strings to integers
		}
		return intArray;
	}
	
	//this method converts a fraction into an improper fraction
	public static int[] toImproperFrac(int[] operand){
		int[] improperFrac = new int[3];
		improperFrac[0] = 0; //the whole part is equal to 0
		improperFrac[1] = operand[0] * operand[2] + operand[1]; //the numerator of the fraction
		improperFrac[2] = operand[2]; //the denominator of the fraction stays the same
		return improperFrac;
	}
	
	//this method adds the two operands of the userInput
	public static int[] add (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[2] == secondOperand[2]){
			for (int i = 0; i <= 1; i++) {
				answer[i] = firstOperand[i] + secondOperand[i];
			}
			answer[2] = firstOperand[2];
		}
		else {
			int gcf = gcf(firstOperand[2], secondOperand[2]);
			answer[1] = (firstOperand[1] * (secondOperand[2]/gcf) + (secondOperand[1] * (firstOperand[2])/gcf));
			answer[2] = firstOperand[2] * (secondOperand[2]/gcf);
		}
		return answer;
	}
	
	//this method subtracts the two operands of the userInput
	public static int[] subtract (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[2] == secondOperand[2]){
			for (int i = 0; i <= 1; i++) {
				answer[i] = firstOperand[i] - secondOperand[i];
			}
			answer[2] = firstOperand[2];
		}
		else {
			int gcf = gcf(firstOperand[2], secondOperand[2]);
			answer[1] = (firstOperand[1] * (secondOperand[2]/gcf) - (secondOperand[1] * (firstOperand[2])/gcf));
			answer[2] = firstOperand[2] * (secondOperand[2]/gcf);
		}
		return answer;
	}
	
	public static int[] multiply (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[1] * secondOperand[1] == 0){
			for(int i = 0; i <= 1; i++){
				answer[i] = 0;
			}
			answer[2] = 1;
		}
		else {
			for (int j = 1; j <= 2; j++) {
				answer[j] = firstOperand[j] * secondOperand[j];
			}
		}
		return answer;
	}
	
	public static int[] divide (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		if (firstOperand[1] == 0){
			for(int i = 0; i <= 1; i++){
				answer[i] = 0;
			}
			answer[2] = 1;
		}
		else {
			answer[1] = firstOperand[1] * secondOperand[2];
			answer[2] = firstOperand[2] * secondOperand[1];
		}
		return answer;
	}

	public static int gcf(int num1, int num2){
		int gcf = 1;
		if(num1 > num2){
			for(int i = num2; i >= 1; i--){
				if(num1 % i == 0 && num2 % i == 0){
					return i;
				}
			}
		} else {
			for(int j = num1; j >=1; j--){
				if(num1 % j == 0 && num2 % j == 0){
					return j;
				}
			}
		}
		return gcf;
	}
	
	public static int[] reduceFraction(int[] fraction){
		int gcf = gcf(fraction[1], fraction[2]);
		if (gcf > 1){
			fraction[1] = fraction[1] / gcf;
			fraction[2] = fraction[2] / gcf;
		}
		fraction = toMixedNum(fraction);
		return fraction;
	}
	
	public static int[] toMixedNum(int[] improperFrac){
		int[] mixedNum = new int[3];
		if (Math.abs(improperFrac[1]) >= improperFrac[2]){
			mixedNum[0] = improperFrac[1] / improperFrac[2];
		}
		else {
			mixedNum[0] = improperFrac[0];
		}
		mixedNum[1] = Math.abs(improperFrac[1] % improperFrac[2]);
		mixedNum[2] = improperFrac[2];
		return mixedNum;
	}
	
	public static String toString(int[] mixedNum){
		if (mixedNum[0] == 0 && mixedNum[1] != 0){
			return (mixedNum[1] + "/" + mixedNum[2]);
		}
		else if (mixedNum[0] == 0 && mixedNum[1] == 0){
			return ("0");
		}
		else if (mixedNum[0] != 0 && mixedNum[1] == 0){
			return mixedNum[0] + "";
		}
		else {
			return (mixedNum[0] + "_" + mixedNum[1] + "/" + mixedNum[2]);
		}
	}

}

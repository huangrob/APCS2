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
			String[] partsOfFrac = mixedFraction.split(" ");
			String[] firstOperand = produceOperand(partsOfFrac, 0);
			String[] secondOperand = produceOperand(partsOfFrac, 2);
			String operator = partsOfFrac[1];
			return calculateEquation(firstOperand, secondOperand, operator);
		} else {
			return "Please enter spaces between the fractions.";
		}
	}
	
	public static String calculateEquation (String[] firstOperand, String[] secondOperand, String operator){
		int[] first = stringToInt(firstOperand);
		int[] second = stringToInt(secondOperand);
		first = toImproperFrac(first);
		second = toImproperFrac(second);
		int[] answer = new int[3];
		if (operator.equals("+")){
			answer = add(first, second);
		}
		if (operator.equals("-")){
			answer = subtract(first, second);
		}
		if (operator.equals("*")){
			answer = multiply(first, second);
		}
		if (operator.equals("/")){
			answer = divide(first, second);
		}
		return (answer[0] + "_" + answer[1] + "/" + answer[2]);
	}
	
	public static String[] produceOperand(String[] partsOfFrac, int element) {
		String operand = partsOfFrac[element];
		if (operand.contains("_") && operand.contains("/")){
			return mixed(operand);
		} else if (!operand.contains("/") && !operand.contains("_")){
			return integer(operand);
		} else {
			return improper(operand);
		}
	}
	
	public static String[] mixed (String operand){
		String[] produceWhole = operand.split("_");
		String whole = produceWhole[0];
		String fraction = produceWhole[1];
		String[] produceAnswerFrac = fraction.split("/");
		String numerator = produceAnswerFrac[0];
		String denominator = produceAnswerFrac[1];
		String[] parsedFraction = {whole, numerator, denominator};
		return parsedFraction;
	}
	
	public static String[] integer (String operand){
		String whole = operand;
		String numerator = "0";
		String denominator = "1";
		String[] parsedFraction = {whole, numerator, denominator};
		return parsedFraction;
	}
	
	public static String[] improper (String operand){
		String[] produceAnswerFrac = operand.split("/");
		String numerator = produceAnswerFrac[0];
		String denominator = produceAnswerFrac[1];
		String whole = "0";
		String[] parsedFraction = {whole, numerator, denominator};
		return parsedFraction;
	}
	
	public static int[] stringToInt (String[] stringArray){
		int[] intArray = new int[3];
		for (int i = 0; i <= 2; i++) {
			intArray[i] = Integer.parseInt(stringArray[i]);
		}
		return intArray;
	}
	
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
			answer[1] = firstOperand[1] * (gcf/firstOperand[2]) + secondOperand[1] * (gcf/secondOperand[2]);
			answer[2] = gcf;
		}
		return answer;
	}
	
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
			answer[1] = firstOperand[1] * (gcf/firstOperand[2]) - secondOperand[1] * (gcf/secondOperand[2]);
			answer[2] = gcf;
		}
		return answer;
	}
	
	public static int[] multiply (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		for (int i = 1; i <= 2; i++) {
			answer[i] = firstOperand[i] * secondOperand[i];
		}
		return answer;
	}
	
	public static int[] divide (int[] firstOperand, int[] secondOperand){
		int[] answer = new int[3];
		for (int i = 1; i <= 2; i++) {
			answer[i] = firstOperand[i] / secondOperand[i];
		}
		return answer;
	}
	
	public static int[] toImproperFrac(int[] operand){
		int[] improperFrac = new int[3];
		improperFrac[0] = 0;
		improperFrac[1] = operand[0] * operand[2] + operand[1];
		improperFrac[2] = operand[2];
		return improperFrac;
	}
	
	public static String toMixedNum(int num1, int num2){
		return (num1/num2 + "_" + num1 % num2 + "/" + num2);
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
	
	//public static int[] intoCommonDenominator(int[] firstOperand, int[] secondOperand){
		//int gcf = gcf(firstOperand[2], secondOperand[2]);
		//int[] first = new int[3];
		//int[] second = new int[3];
		//first[1] = firstOperand[1] * (gcf/secondOperand[2]);
		//first[2] = firstOperand[2] * (gcf/secondOperand[2]);
		//second[1] = secondOperand[1] * (gcf/firstOperand[2]);
		//second[2] = secondOperand[2] * (gcf/firstOperand[2]);
		//}
}

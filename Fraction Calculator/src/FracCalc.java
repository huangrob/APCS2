import java.util.*;

public class FracCalc {
	
	public static void main (String[] args){
		Scanner userInput = new Scanner(System.in);
		while (!userInput.equals("quit")){
			System.out.println("Enter two mixed fractions (with underscore representing \"and\") separated by spaces and an operator.");
			String mixedFraction = userInput.nextLine();
			System.out.println(produceAnswer(mixedFraction));
			System.out.println("If you wish to quit, type quit");
		}
	}
	
	public static String produceAnswer(String mixedFraction){
			
		if (mixedFraction.contains(" ")){
			String[] produceAnswer = mixedFraction.split(" ");
			String secondOperand = produceAnswer[2];
			return secondOperand;
			String whole = "";
			String fraction = "";
			String numerator = "";
			String denominator = "";
			if (secondOperand.contains("_") && secondOperand.contains("/")){
				String[] produceAnswerWhole = secondOperand.split("_");
				whole = produceAnswerWhole[0];
				fraction = produceAnswerWhole[1];
				String[] produceAnswerFrac = fraction.split("/");
				numerator = produceAnswerFrac[0];
				denominator = produceAnswerFrac[1];

			} else if (secondOperand.contains("/")){
				whole = secondOperand;
				numerator = "0";
				denominator = "1";
			}
		
	}
	
}

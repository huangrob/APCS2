import java.util.*;
public class FracCalc {
	
	public static void main (String[] args){
		Scanner userInput = new Scanner(System.in);
		while (!userInput.equals("quit")){
			System.out.println("Enter two mixed fractions (with underscore representing \"and\") separated by spaces and an operator.");
			String mixedFraction = userInput.nextLine();
			String[] produceAnswer = mixedFraction.split(" ");
			System.out.println("Second Operand = " + produceAnswer[2]);
			String[] produceAnswerWhole = Arrays.toString(produceAnswer).split("_");
			System.out.println("Whole = " + produceAnswerWhole[0]);
			System.out.println("If you wish to quit, type quit");
		}
		
		
		
	}

}

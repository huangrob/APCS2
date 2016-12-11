import java.util.*;

	public class Test {
		
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
			System.out.println(Arrays.toString(first));
			int[] second = stringToInt(secondOperand);
			System.out.println(Arrays.toString(second));
			first = toImproperFrac(first);
			second = toImproperFrac(second);
			System.out.println(Arrays.toString(first));
			System.out.println(Arrays.toString(second));
			int[] answer = new int[3];
				answer = add(first, second);

			System.out.println(Arrays.toString(answer));
			answer = reduceFraction(answer);
			System.out.println(Arrays.toString(answer));
			answer = toMixedNum(answer);
			System.out.println(Arrays.toString(answer));
			String finalAnswer = toString(answer);
			System.out.println(finalAnswer);
			return finalAnswer;	
		}
		
		public static String[] produceOperand(String[] partsOfFrac, int element) {
			String operand = partsOfFrac[element];
			return integer(operand);
		}
		
		public static String[] integer (String operand){
			String whole = operand;
			String numerator = "0";
			String denominator = "1";
			String[] parsedFraction = {whole, numerator, denominator};
			return parsedFraction;
		}

		
		public static int[] stringToInt (String[] stringArray){
			int[] intArray = new int[3];
			if (stringArray[0].contains("-") && !stringArray[1].contains("0")){
				stringArray[1] = "-" + stringArray[1];
			}
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
				answer[1] = (firstOperand[1] * (secondOperand[2]/gcf) + (secondOperand[1] * (firstOperand[2])/gcf));
				answer[2] = firstOperand[2] * (secondOperand[2]/gcf);
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
			System.out.println(gcf);
			if (gcf > 1){
				fraction[1] = fraction[1] / gcf;
				fraction[2] = fraction[2] / gcf;
			}
			System.out.println(fraction[0]);
			System.out.println(fraction[1]);
			System.out.println(fraction[2]);
			fraction = toMixedNum(fraction);
			System.out.println(Arrays.toString(fraction));
			return fraction;
		}
		
		public static int[] toMixedNum(int[] improperFrac){
			int[] mixedNum = new int[3];
			if (Math.abs(improperFrac[1]) >= improperFrac[2]){
				mixedNum[0] = improperFrac[1] / improperFrac[2];
			}
			else if (improperFrac[1] < improperFrac[2]){
				mixedNum[0] = improperFrac[0];
			}
			mixedNum[1] = improperFrac[1] % improperFrac[2];
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
	
		public static void main (String[] args){
			String[] a = {"-9035", "0", "1"};
			int [] aa = {-9035, 0, 1};
			String[] b = {"0", "0", "1"};
			int[] bb = {0,0,1};
			int[] c = add(aa, bb);
			String operator = "+";
			calculateEquation(a, b, operator);

		}
	}

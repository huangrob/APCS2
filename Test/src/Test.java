import java.util.*;

	public class Test {
		
		public static int[] reduceFraction(int[] fraction){
			int gcf = gcf(fraction[1], fraction[2]);
			System.out.println(gcf);
			if (gcf > 1){
				fraction[1] = fraction[1] / gcf;
				fraction[2] = fraction[2] / gcf;
			}
			System.out.println(Arrays.toString(fraction));
			return fraction;
		}
		
		public static int gcf(int num1, int num2){
			int gcf = 1;
			num1 = Math.abs(num1);
			num2 = Math.abs(num2);
			if(num1 > num2){
				for(int i = num2; i >= 1; i--){
					if(num1 % i == 0 && num2 % i == 0){
						System.out.println(i);
						return i;
					}
				}
			} else {
				for(int j = num1; j >=1; j--){
					if(num1 % j == 0 && num2 % j == 0){
						System.out.println(j);
						return j;
					}
				}
			}
			System.out.println(gcf);
			return gcf;
		}
	
		public static void main (String[] args){
			String[] a = {"0", "2", "5"};
			int [] aa = {0, 2, 5};
			String[] b = {"4", "2", "7"};
			int[] bb = {0, 4, 5};
			int[] c = {0, -15, 63};
			int[] d = {0, -42, 324};
			reduceFraction(c);
			reduceFraction(d);

		}
	}

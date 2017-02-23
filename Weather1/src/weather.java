import java.io.*;
import java.util.*;

public class weather {
	public static void main (String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File ("./Weather2/weather"));
		double[] weather = new double [8];
		for (int i = 0; i < weather.length; i++){
			weather[i] = s.nextDouble();
		}
		for (int j = 0; j < weather.length; j++){
			double change = weather[j+1] - weather[j];
			System.out.println(weather[j] + " to " + weather[j+1] + "; Change = " + change);
		}
	}

}

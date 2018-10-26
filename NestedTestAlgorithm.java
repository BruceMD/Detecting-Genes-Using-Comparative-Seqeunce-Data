import java.util.*;
import java.io.*;
import java.math.*;


public class NestedTestAlgorithm {
	
	static double recTrap(int k, double x) {

		if (k == 1) {
			return x;
		}
		int numIntervals = 10;
		
		double result = x;
		double dp = (1-x)/numIntervals;
		
		for (int i = 0; i < numIntervals; i ++) {
			// Trapezoidal rule
			result += dp * recTrap(k-1, x/(i*dp + x));
		}
				
		return result;
		
	}
	
	static double recSimp(double k, double x) {

		if (k == 1) {
			return x;
		}
		
		int numIntervals = 5;
		
		double result = x;
		double dp = (1-x)/numIntervals;
		
		
		double p;
		for (int i = 0; i < numIntervals; i ++) {

			// Simpson's rule
			p = x + i*dp;
			result += dp/6 * (recSimp(k-1, x/p) + 4*recSimp(k-1, x/(p + dp/2)) + recSimp(k-1, x/(p+dp)));
		}
				
		return result;
		
	}

	
	static ArrayList<ArrayList<Double>> chiSquareTest() throws Exception {
		
		ArrayList<ArrayList<Double>> orfPValues = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> finalOrfSigArray = JsonReader.pValues();
		
		for (int i = 0; i < finalOrfSigArray.size(); i++) {
			if (finalOrfSigArray.get(i).get(4) < 10 && finalOrfSigArray.get(i).get(4) > 1) {
				System.out.println("Probs for Orf" + i + recSimp(finalOrfSigArray.get(i).get(4), finalOrfSigArray.get(i).get(1)));
			}
			
		}
		
		return orfPValues;
		
	}

}

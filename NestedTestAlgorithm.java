import java.util.*;
import java.io.*;
import java.math.*;


public class NestedTestAlgorithm {
	
	static double recTrap(int k, double x) {

		if (k == 1) {
			return x;
		}
		int numIntervals = 2500;
		
		double result = x;
		double dp = (1-x)/numIntervals;
		
		for (int i = 0; i < numIntervals; i ++) {
			// Trapezoidal rule
			result += dp * recTrap(k-1, x/(i*dp + x));
		}
				
		return result;
		
	}
	
	static double recSimp(int k, double x) {

		if (k == 1) {
			return x;
		}
		
		int numIntervals = 2500;
		
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

	

}

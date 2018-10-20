import java.util.*;
import java.io.*;
import java.math.*;


public class NestedTestAlgorithm {
	
	static double pStar () throws Exception{
		
		ArrayList<ArrayList<Double>> pValuesOrf = new ArrayList<ArrayList<Double>>();
		pValuesOrf = JsonReader.orfSigAlgorithm();
		
		double pStarValues = 1;
		
		for (int i = 0; i < pValuesOrf.size(); i++) {		// go through each ORF to get both ProbA>B and ProbB>A
			pStarValues = pStarValues * pValuesOrf.get(i).get(0);
		}
		
		return pStarValues;
	}
	
	static double integrate(double func, double lowerLimit, double upperLimit, int  numberOfIntervals) throws Exception {
		
	      double stepSize = (upperLimit - lowerLimit) / numberOfIntervals;            // step size
	      double sum = 0;    		
	      
	      for (int i = 0; i <  numberOfIntervals; i++) {
	         double midPointBar = lowerLimit + stepSize * (i + 0.5);
	         sum = sum + func/midPointBar;
	      }

	      return sum * stepSize;
	   }
	
	static double recursion(double k) throws Exception {
		
		double pStar = 0.125/4;
		
		
		if (k == 1) {
			return pStar;
		}
		
		double p = pStar + integrate(recursion(k-1), pStar, 1, 100000);
		System.out.println("Please work " + p);
		return p;
//		return pStar + integrate(recursion(k-1), pStar, 1, 100000, (k-1));
	}


	
	
	static double factorial(int n) {
		if (n ==1) {
			return 1;
		}
		else {
			return n * factorial(n-1);
		}
		
	}
}

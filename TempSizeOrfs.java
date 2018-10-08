import java.util.ArrayList;

public class TempSizeOrfs {

public static ArrayList<Integer> Difference() throws Exception{
		
		ArrayList<Integer> arrayOfDifferences = new ArrayList<Integer>();
		ArrayList<ArrayList<ArrayList<Integer>>> array = new ArrayList<ArrayList<ArrayList<Integer>>>();
		array = StopCodons.AllStopCodons();
		
		for (int n = 0; n < array.size(); n++) {
 			for (int o = 1; o < array.get(n).size(); o++) {
 				for (int p = 0; p < array.get(n).get(o).size() - 1; p ++) {
 					System.out.println(n + " " + o + " " + p + " " + array.get(n).get(o).get(p));
 					System.out.println(array.get(n).get(o).get(p) - array.get(n).get(o).get(p+1));
 					int diff = array.get(n).get(o).get(p) - array.get(n).get(o).get(p+1);
 					arrayOfDifferences.add(diff);

 				} 				
 			}
		}
		
/*		for (int n = 0; n < array.size(); n++) {
 			for (int o = 0; o < array.get(n).size(); o++) {
 				for (int p = 0; p < array.get(n).get(o).size() - 1; p ++) {
 					System.out.println(arrayOfDifferences.get(n).get(o).get(p)); }}}
*/		
		System.out.println(arrayOfDifferences);
		int countSmall = 0;
		int countMedium = 0; 
		int countLarge = 0;
		int countMassive = 0;
		int countAll = 0;
		
		for (int m = 0; m < arrayOfDifferences.size(); m++) {
			if ((arrayOfDifferences.get(m) * arrayOfDifferences.get(m))  < 900){		// 30 nucleotides
				countSmall = countSmall + 1;
			}
			else if ((arrayOfDifferences.get(m) * arrayOfDifferences.get(m))  < 10000){	// 100 nucleotides
				countMedium = countMedium + 1;
			}
			else if ((arrayOfDifferences.get(m) * arrayOfDifferences.get(m))  < 4000000){ // 2000 nucleotides
				countLarge = countLarge + 1;
			}
			else {
				countMassive = countMassive + 1;
				System.out.println(arrayOfDifferences.get(m));
			}
			if ((arrayOfDifferences.get(m) * arrayOfDifferences.get(m)) > 33*33) {
				countAll = countAll +1;
			}
		}
		
		System.out.println("Number of Orfs less than 30 bp long:  "+ countSmall);
		System.out.println("Number of Orfs less than 100 bp long: "+ countMedium);		
		System.out.println("Number of Orfs less than 200 bp long:  "+ countLarge);		
		System.out.println("Number of Orfs more than 200 bp long:  "+ countMassive);	
		System.out.println("Orfs more than 33 bp long: " + countAll);
		
		return arrayOfDifferences;
	}
}

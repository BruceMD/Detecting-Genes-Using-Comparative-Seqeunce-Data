import java.util.ArrayList;


public class CSVOutput {
	
	public static void CsvOutout() throws Exception{
		
		ArrayList<ArrayList<Double>> finalOrfSigArray = JsonReader.pValues();
		System.out.println(" ,ProbABRec,ProbBARec,ProbABFish,ProbBAFish,Size");		// header for csv file
		
		for (int i = 0; i < finalOrfSigArray.size(); i ++) {
			for (int j = 0; j < finalOrfSigArray.get(i).size(); j ++) {
				System.out.print(finalOrfSigArray.get(i).get(j) + ", ");
			}
			System.out.println();
		}	
		
	}
	

}

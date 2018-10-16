import java.io.*;
import java.util.ArrayList;

public class FastaOutput {

	public static void FastaWriter() throws Exception{
	
		ArrayList<ArrayList<String>> OrfsArray = new ArrayList<ArrayList<String>>();
		
		OrfsArray = SignificantOrf.allAdjustedOrfs();
		Integer count = 0;
		
//		System.out.println(OrfsArray);
		
		for (int j = 0; j < OrfsArray.size(); j++) {
				
			File fileName = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\ExampleAJ\\Orf" + (count +1) + ".fas");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			for (int k = 0; k < OrfsArray.get(j).size(); k++) {
					
				String orf = OrfsArray.get(j).get(k);
					
				writer.write(orf + "\n");

			}
				
				count = count + 1;
				writer.close();				
			
		}
	}
}

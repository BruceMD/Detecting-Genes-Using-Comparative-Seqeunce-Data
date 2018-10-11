import java.io.*;
import java.util.ArrayList;

public class FastaOutput {

	public static void FastaWriter() throws Exception{
	
		ArrayList<ArrayList<ArrayList<String>>> OrfsArray = new ArrayList<ArrayList<ArrayList<String>>>();
		
		OrfsArray = SignificantOrf.AllOrfs();
		Integer count = 0;
		
//		System.out.println(OrfsArray);
		
		for (int i = 0; i < OrfsArray.size(); i++) {
			for (int j = 0; j < OrfsArray.get(i).size(); j++) {
				
				File fileName = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\ExampleFMDV\\Orf" + (count +1) + ".fas");
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
				for (int k = 0; k < OrfsArray.get(i).get(j).size(); k++) {
					String orf = OrfsArray.get(i).get(j).get(k);
					orf = orf.replace("-", "");
					writer.write(orf + "\n");
				}
				
				count = count + 1;
				writer.close();
				
			}
		}
	}
}

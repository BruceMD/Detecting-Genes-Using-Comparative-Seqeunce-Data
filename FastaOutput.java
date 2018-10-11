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
				
				File fileNameFub = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\ExampleFMDV\\FubOrf" + (count +1) + ".fas");
				BufferedWriter writerFub = new BufferedWriter(new FileWriter(fileNameFub));
				
				File fileNameRax = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\ExampleFMDV\\RaxOrf" + (count +1) + ".fas");
				BufferedWriter writerRax = new BufferedWriter(new FileWriter(fileNameRax));
			
				for (int k = 0; k < OrfsArray.get(i).get(j).size(); k++) {
					
					String orf = OrfsArray.get(i).get(j).get(k);
					
					writerRax.write(orf + "\n");
					
					orf = orf.replace("-", "");
					writerFub.write(orf + "\n");
				}
				
				count = count + 1;
				writer.close();
				
			}
		}
	}
}

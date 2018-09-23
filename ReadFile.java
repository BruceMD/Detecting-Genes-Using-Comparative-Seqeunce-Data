import java.util.*;
import java.io.*;

public class ReadFile {
	
	public static void main(String[] args) throws Exception {
	
		File file = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\Flu.fasta");
		File fileName = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\ExampleSeq.fas");
		BufferedReader buffReader = new BufferedReader(new FileReader(file));
	
		String tempLine = null;
		String tempString = "";
		ArrayList<String> Sequences = new ArrayList<>();
		
		while ((tempLine = buffReader.readLine()) != null) {
//			System.out.println(tempLine);
			if(tempLine.contains(">")){
				if (Sequences.size() == 0) {
					Sequences.add(" ");
				}
				else {
					Sequences.add(tempString + "\n");
					tempString = "";
				}
			}
			else if (!tempLine.contains(">")) {
				System.out.println(tempLine);
				tempString = tempString + tempLine.trim();
			}	
		}
		Sequences.add(tempString);
	
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		for (int i = 1; i < Sequences.size(); i++) {
			writer.write(Sequences.get(i));
		}
		
		System.out.println(Sequences);
		System.out.println(Sequences.size());
	
		buffReader.close();
		writer.close();
	}
}

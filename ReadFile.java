import java.util.*;
import java.io.*;




public class ReadFile {
	
	public static String InputFile() {
		
		Scanner input = new Scanner(System.in);
		String inputFilePath = "";
		
		System.out.println("Enter path fo file: ");
		inputFilePath = input.nextLine();
		
		input.close();
		
		System.out.println(inputFilePath);
		return inputFilePath;
		
	}	
	
	public static ArrayList<String> Sequence() throws Exception {
	
//		File file = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\Example1(PotySeqs).fas");
		File file = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\Example2(A-J-cons-kal153).fas");
//		File file = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\Example3(FMDV).fas");
	
//		File file = new File(ReadFile.InputFile());
		
		BufferedReader buffReader = new BufferedReader(new FileReader(file));
	
		String tempLine = null;
		String tempString = "";
		ArrayList<String> Sequences = new ArrayList<>();
		
		while ((tempLine = buffReader.readLine()) != null) {
//			System.out.println(tempLine);
			if(tempLine.contains(">")){
				if (Sequences.size() == 0) {
					Sequences.add(" ");			// name of FASTA file would work well
				}
				else {							
					Sequences.add(tempString.toUpperCase());
					tempString = "";
				}
			}
			else if (!tempLine.contains(">")) {
//				System.out.println(tempLine);
				tempString = tempString + tempLine.trim();
			}	
		}
		
		Sequences.add(tempString.toUpperCase());
		tempString = "";
		
		buffReader.close();
		
/*		File fileName = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\ExampleSeq1.fas");

 		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		for (int i = 1; i < Sequences.size(); i++) {
			writer.write(Sequences.get(i) + "\n");
		}
*/		
//		System.out.println(Sequences);
//		System.out.println(Sequences.size());
	
		
//		writer.close();
		
		return Sequences;
		
	}
}

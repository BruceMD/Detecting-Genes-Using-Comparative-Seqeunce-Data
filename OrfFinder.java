import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class OrfFinder {

	public static void main(String[] args) throws Exception {

		File file = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\Example1(PotySeqs).fas");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st;
		
		while ((st = br.readLine()) != null) {
			if (st.contains(">")) {
				System.out.println("\n" + st);
			}
			else {
				String tempLine = st.trim();
				System.out.print(tempLine);
			}			
		}
		
		br.close();
				
				
	}

}

import java.util.*;
import java.io.*;

public class OrfDisplay {

	static ArrayList<ArrayList<Integer>> finalDisplay() throws Exception{
		
		ArrayList<ArrayList<Integer>> indexOrfs = new ArrayList<ArrayList<Integer>>(); 
		
		ArrayList<ArrayList<String>> OrfsArray = new ArrayList<ArrayList<String>>();
		OrfsArray = SignificantOrf.allAdjustedOrfs();
		
		ArrayList<Integer> sigOrfs = new ArrayList<Integer>();
		
//		System.out.println(OrfsArray.size());
		
		File results = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\FinalResult\\SigOrfs.txt");
		
		BufferedReader buffReader = new BufferedReader(new FileReader(results));
		
		String tempLine = null;
		
		while ((tempLine = buffReader.readLine()) != null) {
			int sigOrfIndex = new Integer(String.valueOf(tempLine));
			sigOrfs.add(sigOrfIndex);
		}
		buffReader.close();
		
		for (int i = 0; i < sigOrfs.size(); i++) {
			
			boolean indexSwitchI = false;
			boolean indexSwitchU = false;
			String indexStart = "";
			String indexEnd = "";
			
			ArrayList<Integer> tempIndex = new ArrayList<Integer>();
			
			for (int j = 0; j < OrfsArray.get(sigOrfs.get(i)-1).get(0).length(); j++) {
				
				if (indexSwitchI && indexSwitchU) {
					indexEnd = indexEnd + OrfsArray.get(sigOrfs.get(i)-1).get(0).charAt(j);
				}
				if (OrfsArray.get(sigOrfs.get(i)-1).get(0).charAt(j) == '_') {
					indexSwitchU = true;
				}
				if (indexSwitchI && !indexSwitchU) {
					indexStart = indexStart + OrfsArray.get(sigOrfs.get(i)-1).get(0).charAt(j);
				}
				if (OrfsArray.get(sigOrfs.get(i)-1).get(0).charAt(j) == 'I') {
					indexSwitchI = true;
				}
			}
			int indexStartInt = new Integer(String.valueOf(indexStart));
			int indexEndInt = new Integer(String.valueOf(indexEnd));
			int orfNum = new Integer(String.valueOf(sigOrfs.get(i)));
			
			tempIndex.add(orfNum);
			tempIndex.add(indexStartInt); 
			tempIndex.add(indexEndInt);
			indexOrfs.add(tempIndex);
			
			System.out.println(sigOrfs.get(i)+ "," + indexStart + "," + indexEnd + "," + OrfsArray.get(sigOrfs.get(i)-1).get(1).length() +", Seq: " + OrfsArray.get(sigOrfs.get(i)-1).get(1));
		}
		return indexOrfs;
	}
	
	public static String sequence() throws Exception{
		
		File hxb2 = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\Sample Seqs\\HIV.fasta");
		BufferedReader buffReader = new BufferedReader(new FileReader(hxb2));
		
		String referenceSeq = "";
		
		int count = 0;
		String tempLine = null;
		while ((tempLine = buffReader.readLine()) != null) {
			if (tempLine.contains(">")) {
				count += 1;
			}
			if (count == 2)
				break;
			if (!tempLine.contains(">")) {
				referenceSeq = referenceSeq + tempLine;
				
			}
		}
		buffReader.close();
		
		return referenceSeq;
	}
	
	public static String trimmedSequence() throws Exception{
		
		String referenceSeq = OrfDisplay.sequence();
		
		String referenceSeqTrim = "";
		
		for (int i = 0; i < referenceSeq.length(); i++) {
			if (referenceSeq.charAt(i) != '-')
			referenceSeqTrim += referenceSeq.charAt(i);
		}
		
		return referenceSeqTrim;
	}
	
	public static ArrayList<ArrayList<Integer>> HXB2Scaling () throws Exception {
		
		ArrayList<ArrayList<Integer>> indexOrfs = OrfDisplay.finalDisplay();
/*		for (int i = 0; i < indexOrfs.size(); i++) {
			System.out.println(indexOrfs.get(i));
		}
*/
		ArrayList<ArrayList<Integer>> newIndexOrfs = new ArrayList<ArrayList<Integer>>();
		
		String referenceSeq = OrfDisplay.sequence();
		
//		System.out.println(referenceSeq);
//		System.out.println(referenceSeqTrim.length());

		for (int j = 0; j < indexOrfs.size(); j ++) {
			
			ArrayList<Integer> tempNewIndexOrfs = new ArrayList<Integer>();
			int countFirst = 0;
			int countSecond = 0;
			
			for (int k = 0; k < indexOrfs.get(j).get(1); k++) {
				if (referenceSeq.charAt(k) == '-')
					countFirst += 1;
			}
			for (int l = 0; l < indexOrfs.get(j).get(2); l++) {
				if (referenceSeq.charAt(l) == '-')
					countSecond += 1;
			}
			
			tempNewIndexOrfs.add(indexOrfs.get(j).get(0));
			tempNewIndexOrfs.add(indexOrfs.get(j).get(1) - countFirst);
			tempNewIndexOrfs.add(indexOrfs.get(j).get(2) - countSecond);
			
			newIndexOrfs.add(tempNewIndexOrfs);
			System.out.println(newIndexOrfs.get(j));
		}
		
		return newIndexOrfs;
	}
	
	public static void graphValues () throws Exception{
		
		ArrayList<ArrayList<Integer>> newIndexOrfs = OrfDisplay.HXB2Scaling();
		
		ArrayList<Integer> hits = new ArrayList<Integer>();
		
		String trimmedSequence = OrfDisplay.trimmedSequence();
		
		for (int i = 0; i < trimmedSequence.length(); i++) {
			int count = 0;	
			
			for (int j = 0; j < newIndexOrfs.size(); j++) {
				
//				if ((i >= newIndexOrfs.get(j).get(1) && i <= newIndexOrfs.get(j).get(2))  || (i <= newIndexOrfs.get(j).get(1) && i >= newIndexOrfs.get(j).get(2)) ) 
//				if (i >= newIndexOrfs.get(j).get(1) && i <= newIndexOrfs.get(j).get(2))		//forward strand
				if (i <= newIndexOrfs.get(j).get(1) && i >= newIndexOrfs.get(j).get(2))		//reverse strand
					count +=1;
				
			}
			hits.add(count);
		}
		
//		System.out.println(hits);
		
		System.out.println("x,y");
		
		for (int k = 0; k < hits.size(); k++) {
			System.out.println((k+1) + "," + hits.get(k));
		}
		
	}
	
}

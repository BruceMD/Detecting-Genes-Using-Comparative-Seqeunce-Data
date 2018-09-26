import java.util.ArrayList;

public class StopCodons {
	
	public static void FrameOne() throws Exception {
		
		String tempCodon = new String();		// frame +1

		Integer locationOfTempCodon = null;
		ArrayList<String> listOfSequences = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> locationOfAllStopCodons = new ArrayList<ArrayList<Integer>>();	// array within an array
//		ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
		listOfSequences = ReadFile.Sequence();
		
		for (int i = 0; i < listOfSequences.size(); i++) {	// cycle through the items in the Arraylist
			
			ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
			
			for (int j = 0; j < listOfSequences.get(i).length(); j++) { 	// cycle through the characters in each sequence
				
				if (listOfSequences.get(i).charAt(j) == 'A' || 
					listOfSequences.get(i).charAt(j) == 'T' || 
					listOfSequences.get(i).charAt(j) == 'C' || 
					listOfSequences.get(i).charAt(j) == 'G') 
				{													// provided the character is only an nucleotide
//					System.out.println(tempCodon.length());
					
					if (tempCodon.length() == 0 ) {
						locationOfTempCodon = j;							// temporarily store value of temp codon

					}
					
					if (tempCodon.length() < 3) {								// check if tempCodon is full, if not, add more
						 tempCodon = tempCodon + listOfSequences.get(i).charAt(j);
						 
					}
					if(tempCodon.length() >= 3) {												// check if tempCodon is a stop codon
						if (tempCodon.contains("TAA") || tempCodon.contains("TAG") || tempCodon.contains("TGA") || tempCodon.contains("ATT")) {
							locationOfStopCodons.add(locationOfTempCodon);						// append index of Stop Codon to array list
							
						}																		// frame specific
						
						tempCodon = "";										// empty tempCodon to continue cycle	
//						locationOfTempCodon = 0;	 														
		 
					}													
				}	
				

				
			}		// cycle through the characters in each sequence
			
			locationOfAllStopCodons.add(locationOfStopCodons);

		}		// cycle through the items in the Arraylist
		
		for (int m = 0; m < locationOfAllStopCodons.size(); m++) {
			System.out.println(locationOfAllStopCodons.get(m));
		}
		
	
	}

	
	
}
	

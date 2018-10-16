import java.util.*;

public class StopCodons {
	
	public static ArrayList<ArrayList<ArrayList<Integer>>> AllStopCodons() throws Exception{
	
		ArrayList<ArrayList<ArrayList<Integer>>> arrayOfStopCodons = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		arrayOfStopCodons.add(StopCodons.FrameOne());
		arrayOfStopCodons.add(StopCodons.FrameTwo());
		arrayOfStopCodons.add(StopCodons.FrameThree());
		arrayOfStopCodons.add(StopCodons.FrameNOne());
		arrayOfStopCodons.add(StopCodons.FrameNTwo());
		arrayOfStopCodons.add(StopCodons.FrameNThree());
 		
 		//Create a list of all stop codons listed per frame per sequence
 
/* 
 		for (int n = 0; n < arrayOfStopCodons.size(); n++) {
 			System.out.println("Frame " + (n+1));
 			for (int o = 1; o < arrayOfStopCodons.get(n).size(); o++) {
 					System.out.println("Sequence " + (o) + " -> " + arrayOfStopCodons.get(n).get(o));
 			}
 			System.out.println("");

 		}
*/ 		
 		return arrayOfStopCodons;

	}
	
	public static ArrayList<ArrayList<Integer>> FrameOne() throws Exception {	//perfect
		
		String tempCodon = new String();		// frame +1

		Integer locationOfTempCodon = null;
		ArrayList<String> listOfSequences = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> locationOfAllStopCodonsOne = new ArrayList<ArrayList<Integer>>();	// array within an array

		listOfSequences = ReadFile.Sequence();
		
		for (int i = 0; i < listOfSequences.size(); i++) {	// cycle through the items in the Arraylist
			
			ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
			
			for (int j = 0; j < listOfSequences.get(i).length(); j++) { 	// cycle through the characters in each sequence
				
				if (listOfSequences.get(i).charAt(j) == 'A' || 
					listOfSequences.get(i).charAt(j) == 'T' || 
					listOfSequences.get(i).charAt(j) == 'C' || 
					listOfSequences.get(i).charAt(j) == 'G'  ) 
				{													// provided the character is only an nucleotide
//					System.out.println(tempCodon.length());
					
					if (tempCodon.length() == 0 ) {
						locationOfTempCodon = j;							// temporarily store value of temp codon

					}
					
					if (tempCodon.length() < 3) {								// check if tempCodon is full, if not, add more
						 tempCodon = tempCodon + listOfSequences.get(i).charAt(j);
						 
					}
					if(tempCodon.length() >= 3) {												// check if tempCodon is a stop codon
						if (tempCodon.contains("TAA") || tempCodon.contains("TAG") || tempCodon.contains("TGA")) {
							locationOfStopCodons.add(locationOfTempCodon);						// append index of Stop Codon to array list
							
						}																		// frame specific
						
						tempCodon = "";										// empty tempCodon to continue cycle	
//						locationOfTempCodon = 0;	 														
		 
					}													
				}	
				

				
			}		// cycle through the characters in each sequence
			
			locationOfAllStopCodonsOne.add(locationOfStopCodons);

		}		// cycle through the items in the Arraylist

		return locationOfAllStopCodonsOne;
	}

	public static ArrayList<ArrayList<Integer>> FrameTwo() throws Exception {	// perfect
			
			String tempCodon = new String();		// frame +2
			tempCodon = "NN";
	
			Integer locationOfTempCodon = null;
			ArrayList<String> listOfSequences = new ArrayList<String>();
			ArrayList<ArrayList<Integer>> locationOfAllStopCodonsTwo = new ArrayList<ArrayList<Integer>>();	// array within an array
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
							if (tempCodon.contains("TAA") || tempCodon.contains("TAG") || tempCodon.contains("TGA")) {
								locationOfStopCodons.add(locationOfTempCodon);						// append index of Stop Codon to array list
								
							}																		// frame specific
							
							tempCodon = "";										// empty tempCodon to continue cycle	
	//						locationOfTempCodon = 0;	 														
			 
						}													
					}	
					
	
					
				}		// cycle through the characters in each sequence
				
				locationOfAllStopCodonsTwo.add(locationOfStopCodons);
	
			}		// cycle through the items in the Arraylist
			
			return locationOfAllStopCodonsTwo;
			
		}
	
	public static ArrayList<ArrayList<Integer>> FrameThree() throws Exception {	// perfect
		
		String tempCodon = new String();		// frame +3
		tempCodon = "N";
	
		Integer locationOfTempCodon = null;
		ArrayList<String> listOfSequences = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> locationOfAllStopCodonsThree = new ArrayList<ArrayList<Integer>>();	// array within an array
	//	ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
		listOfSequences = ReadFile.Sequence();
		
		for (int i = 0; i < listOfSequences.size(); i++) {	// cycle through the items in the Arraylist
			
			ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
			
			for (int j = 0; j < listOfSequences.get(i).length(); j++) { 	// cycle through the characters in each sequence
				
				if (listOfSequences.get(i).charAt(j) == 'A' || 
					listOfSequences.get(i).charAt(j) == 'T' || 
					listOfSequences.get(i).charAt(j) == 'C' || 
					listOfSequences.get(i).charAt(j) == 'G') 
				{													// provided the character is only an nucleotide
	//				System.out.println(tempCodon.length());
					
					if (tempCodon.length() == 0 ) {
						locationOfTempCodon = j;							// temporarily store value of temp codon
	
					}
					
					if (tempCodon.length() < 3) {								// check if tempCodon is full, if not, add more
						 tempCodon = tempCodon + listOfSequences.get(i).charAt(j);
						 
					}
					if(tempCodon.length() >= 3) {												// check if tempCodon is a stop codon
						if (tempCodon.contains("TAA") || tempCodon.contains("TAG") || tempCodon.contains("TGA")) {
							locationOfStopCodons.add(locationOfTempCodon);						// append index of Stop Codon to array list
							
						}																		// frame specific
						
						tempCodon = "";										// empty tempCodon to continue cycle	
	//					locationOfTempCodon = 0;	 														
		 
					}													
				}	
				
	
				
			}		// cycle through the characters in each sequence
			
			locationOfAllStopCodonsThree.add(locationOfStopCodons);
	
		}		// cycle through the items in the Arraylist

		return locationOfAllStopCodonsThree;
		
	}
	
	public static ArrayList<ArrayList<Integer>> FrameNOne() throws Exception { 	// perfect
		
		String tempCodon = new String();		// frame -1
	
		Integer locationOfTempCodon = null;
		ArrayList<String> listOfSequences = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> locationOfAllStopCodonsNOne = new ArrayList<ArrayList<Integer>>();	// array within an array
	//	ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
		listOfSequences = ReadFile.Sequence();
		
		for (int i = 0; i < listOfSequences.size(); i++) {	// cycle through the items in the Arraylist
			
			ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
			
			for (int j = listOfSequences.get(i).length() - 1; j >= 0 ; j--) { 	// cycle through the characters in each sequence
				
				if (listOfSequences.get(i).charAt(j) == 'A' || 
					listOfSequences.get(i).charAt(j) == 'T' || 
					listOfSequences.get(i).charAt(j) == 'C' || 
					listOfSequences.get(i).charAt(j) == 'G') 
				{													// provided the character is only an nucleotide
	//				System.out.println(tempCodon.length());
					
					if (tempCodon.length() == 0 ) {
						locationOfTempCodon = j;							// temporarily store value of temp codon
	
					}
					
					if (tempCodon.length() < 3) {								// check if tempCodon is full, if not, add more
						 tempCodon = tempCodon + listOfSequences.get(i).charAt(j);
						 
					}
					if(tempCodon.length() >= 3) {												// check if tempCodon is a stop codon
						if (tempCodon.contains("TAA") || tempCodon.contains("TAG") || tempCodon.contains("TGA")) {
							locationOfStopCodons.add(locationOfTempCodon);						// append index of Stop Codon to array list
							
						}																		// frame specific
						
						tempCodon = "";										// empty tempCodon to continue cycle	
	//					locationOfTempCodon = 0;	 														
		 
					}													
				}	
				
	
				
			}		// cycle through the characters in each sequence
			locationOfAllStopCodonsNOne.add(locationOfStopCodons);
	
		}		// cycle through the items in the Arraylist
		
		return locationOfAllStopCodonsNOne;
		
	}
	
	public static ArrayList<ArrayList<Integer>> FrameNTwo() throws Exception {	// perfect
		
		String tempCodon = new String();		// frame -2
		tempCodon = "NN";
	
		Integer locationOfTempCodon = null;
		ArrayList<String> listOfSequences = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> locationOfAllStopCodonsNTwo = new ArrayList<ArrayList<Integer>>();	// array within an array
	//	ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
		listOfSequences = ReadFile.Sequence();
		
		for (int i = 0; i < listOfSequences.size(); i++) {	// cycle through the items in the Arraylist
			
			ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
			
			for (int j = listOfSequences.get(i).length() - 1; j >= 0 ; j--) { 	// cycle through the characters in each sequence
				
				if (listOfSequences.get(i).charAt(j) == 'A' || 
					listOfSequences.get(i).charAt(j) == 'T' || 
					listOfSequences.get(i).charAt(j) == 'C' || 
					listOfSequences.get(i).charAt(j) == 'G') 
				{													// provided the character is only an nucleotide
	//				System.out.println(tempCodon.length());
					
					if (tempCodon.length() == 0 ) {
						locationOfTempCodon = j;							// temporarily store value of temp codon
	
					}
					
					if (tempCodon.length() < 3) {								// check if tempCodon is full, if not, add more
						 tempCodon = tempCodon + listOfSequences.get(i).charAt(j);
						 
					}
					if(tempCodon.length() >= 3) {												// check if tempCodon is a stop codon
						if (tempCodon.contains("TAA") || tempCodon.contains("TAG") || tempCodon.contains("TGA")) {
							locationOfStopCodons.add(locationOfTempCodon);						// append index of Stop Codon to array list
							
						}																		// frame specific
						
						tempCodon = "";										// empty tempCodon to continue cycle	
	//					locationOfTempCodon = 0;	 														
		 
					}													
				}	
				
	
				
			}		// cycle through the characters in each sequence
			locationOfAllStopCodonsNTwo.add(locationOfStopCodons);
	
		}		// cycle through the items in the Arraylist
	
		return locationOfAllStopCodonsNTwo;
		
	}
	
	public static ArrayList<ArrayList<Integer>> FrameNThree() throws Exception {// perfect
		
		String tempCodon = new String();		// frame -3
		tempCodon = "N";
	
		Integer locationOfTempCodon = null;
		ArrayList<String> listOfSequences = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> locationOfAllStopCodonsNThree = new ArrayList<ArrayList<Integer>>();	// array within an array
	//	ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
		listOfSequences = ReadFile.Sequence();
		
		for (int i = 0; i < listOfSequences.size(); i++) {	// cycle through the items in the Arraylist
			
			ArrayList<Integer> locationOfStopCodons = new ArrayList<Integer>();
			
			for (int j = listOfSequences.get(i).length() - 1; j >= 0 ; j--) { 	// cycle through the characters in each sequence
				
				if (listOfSequences.get(i).charAt(j) == 'A' || 
					listOfSequences.get(i).charAt(j) == 'T' || 
					listOfSequences.get(i).charAt(j) == 'C' || 
					listOfSequences.get(i).charAt(j) == 'G') 
				{													// provided the character is only an nucleotide
	//				System.out.println(tempCodon.length());
					
					if (tempCodon.length() == 0 ) {
						locationOfTempCodon = j;							// temporarily store value of temp codon
	
					}
					
					if (tempCodon.length() < 3) {								// check if tempCodon is full, if not, add more
						 tempCodon = tempCodon + listOfSequences.get(i).charAt(j);
						 
					}
					if(tempCodon.length() >= 3) {												// check if tempCodon is a stop codon
						if (tempCodon.contains("TAA") || tempCodon.contains("TAG") || tempCodon.contains("TGA")) {
							locationOfStopCodons.add(locationOfTempCodon);						// append index of Stop Codon to array list
							
						}																		// frame specific
						
						tempCodon = "";										// empty tempCodon to continue cycle	
	//					locationOfTempCodon = 0;	 														
		 
					}													
				}	
				
	
				
			}		// cycle through the characters in each sequence
			locationOfAllStopCodonsNThree.add(locationOfStopCodons);
	
		}		// cycle through the items in the Array list
	
		return locationOfAllStopCodonsNThree;
		
	}
		
}
	

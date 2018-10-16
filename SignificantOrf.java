import java.util.ArrayList;

public class SignificantOrf {

	public static ArrayList<ArrayList<String>> ForwardOrfs() throws Exception{
		
		ArrayList<ArrayList<ArrayList<Integer>>> arrayOfStopCodons = new ArrayList<ArrayList<ArrayList<Integer>>>();
		arrayOfStopCodons = StopCodons.AllStopCodons();		// array of all the stop codons per sequence per frame
		
		ArrayList<String> sequences = new ArrayList<>();
		sequences = ReadFile.Sequence();					// array of all the sequences
		
		ArrayList<ArrayList<String>> arrayOfForwardOrfs = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> repeatFinder = new ArrayList<String>();
		
		for (int i = 0; i < 3; i++) {					//cycle through the first 3 forward frames
			for (int j = 0; j < arrayOfStopCodons.get(i).size(); j++) {			//cycle through the sequences
				for (int k = 0; k < arrayOfStopCodons.get(i).get(j).size() - 1; k++){	// cycle through nucleotides of each sequence
					int x = arrayOfStopCodons.get(i).get(j).get(k);
					int y = arrayOfStopCodons.get(i).get(j).get(k+1);
					
					if (!repeatFinder.contains(x + " " + y)) {
						
						if((x-y)*(x-y) > 2500 ) {	// check to see if ORF will be long enough, x is smaller than y, should give a negative value
							
							int count = 0;
							ArrayList<String> tempOrf = new ArrayList<String>();
							
							for (int l = 0; l < 3; l++) {					//cycle through the first 3 forward frames
								for (int m = 0; m < arrayOfStopCodons.get(l).size(); m++) {			//cycle through the sequences
									for (int n = 0; n < arrayOfStopCodons.get(l).get(m).size() - 1; n++){	// cycle through nucleotides of each sequence
										if (x == arrayOfStopCodons.get(l).get(m).get(n)) {
											if (y == arrayOfStopCodons.get(l).get(m).get(n+1)) {
												count = count +1;
												//need to setup a FASTA format of everything
												tempOrf.add(">S" + m + "I" + x + "_" + y); 		// S for sequence, I for index, want to remember where each ORF is from
												String constructTempOrf = "";
												
												for (int o = x+3; o < y; o++) {					// construct the ORF from indices x +3 (skip that stop codon) to y
													constructTempOrf = constructTempOrf + sequences.get(m).charAt(o);
												}
												
	//											System.out.println("Sequence " + m + " " + x + " " + y + " length: "+ constructTempOrf.length() + " " + constructTempOrf);
												tempOrf.add(constructTempOrf);					// add constructed ORF to tempOrf
											}	
										}	
									}
								}
							}	
							
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
							
							if (count > 9) {													// if the count is sufficient (above 3) append it to arrayOfForwardOrfs
								arrayOfForwardOrfs.add(tempOrf);
							}
							
							repeatFinder.add(x + " " + y);		// add x and y to repeat finder to be avoided in the future
							
						}
					}	
				}
			}
		}	
				
		return arrayOfForwardOrfs;
	}
	
	public static ArrayList<ArrayList<String>> ReverseOrfs() throws Exception{
		
		ArrayList<ArrayList<ArrayList<Integer>>> arrayOfStopCodons = new ArrayList<ArrayList<ArrayList<Integer>>>();
		arrayOfStopCodons = StopCodons.AllStopCodons();		// array of all the stop codons per sequence per frame
		
		ArrayList<String> sequences = new ArrayList<>();
		sequences = ReadFile.Sequence();					// array of all the sequences
		
		ArrayList<ArrayList<String>> arrayOfReverseOrfs = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> repeatFinder = new ArrayList<String>();
		
		for (int i = 3; i < 6; i++) {					//cycle through the 3 REVERSE frames
			for (int j = 0; j < arrayOfStopCodons.get(i).size(); j++) {			//cycle through the sequences
				for (int k = 0; k < arrayOfStopCodons.get(i).get(j).size() - 1; k++){	// cycle through nucleotides of each sequence
					int x = arrayOfStopCodons.get(i).get(j).get(k);
					int y = arrayOfStopCodons.get(i).get(j).get(k+1);
					
					
					if (!repeatFinder.contains(x + " " + y)) {
						
						if((x-y)*(x-y) > 2500 ) {	// check to see if ORF will be long enough, x is larger than y, should give positive value
							
							int count = 0;
							ArrayList<String> tempOrf = new ArrayList<String>();
							
							for (int l = 3; l < 6; l++) {					//cycle through the first 3 forward frames
								for (int m = 0; m < arrayOfStopCodons.get(l).size(); m++) {			//cycle through the sequences
									for (int n = 0; n < arrayOfStopCodons.get(l).get(m).size() - 1; n++){	// cycle through nucleotides of each sequence
										if (x == arrayOfStopCodons.get(l).get(m).get(n)) {
											if (y == arrayOfStopCodons.get(l).get(m).get(n+1)) {
												count = count +1;
												//need to setup a FASTA format of everything
												tempOrf.add(">S" + m + "I" + x + "_" + y); 		// S for sequence, I for index, want to remember where each ORF is from
												String constructTempOrf = "";
												
												for (int o = x-3; o > y; o--) {					// construct the ORF from indices x to y
													constructTempOrf = constructTempOrf + sequences.get(m).charAt(o);
												}
												
	//											System.out.println("Sequence " + m + " " + x + " " + y + " length: "+ constructTempOrf.length() + " " + constructTempOrf);
												tempOrf.add(constructTempOrf);					// add constructed ORF to tempOrf
											}	
										}	
									}
								}
							}	
							
							
							if (count > 3) {								// if the count is sufficient (above 3) append it to arrayOfForwardOrfs
								arrayOfReverseOrfs.add(tempOrf);
							}	
							
							repeatFinder.add(x + " " + y);		// add x and y to repeat finder to be avoided in the future
							
						}
					}
				}
			}
		}
				
		return arrayOfReverseOrfs;
	}
	
	
	public static ArrayList<ArrayList<String>> AdjOrfs() throws Exception{
		
		
		
		ArrayList<ArrayList<String>> ForwardOrfs = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> ReverseOrfs = new ArrayList<ArrayList<String>>();
		
		ArrayList<ArrayList<String>> adjustedOrfs = new ArrayList<ArrayList<String>>();
		
		ForwardOrfs = SignificantOrf.ForwardOrfs();
		 
		ReverseOrfs = SignificantOrf.ReverseOrfs();
		
		
		for (int i = 0; i < ForwardOrfs.size(); i++) {					// look at the ORFs
			
			ArrayList<String> tempOrf = new ArrayList<String>();
			
			ArrayList<Integer> hyphenCol = new ArrayList<Integer>();	// position of columns for that ORF
			hyphenCol.add(0);											// first position
			ArrayList<Integer> hyphenColRepeat = new ArrayList<Integer>();
			
			for (int j = 0; j < ForwardOrfs.get(i).size(); j++) {		// look at the sequences
				
				if (!ForwardOrfs.get(i).get(j).contains(">")) {
				
					for (int k = 0; k < ForwardOrfs.get(i).get(j).length(); k++) {	// look at the nucleotides
						
						if (ForwardOrfs.get(i).get(j).charAt(k) == '-' && !hyphenColRepeat.contains(k)) {	// if we reach a hyphen and that hyphen is not in position k
							
							Integer count = 0;
							
							for (int l = 1; l < ForwardOrfs.get(i).size(); l=l+2) {		// skip sequence names
								
								if (ForwardOrfs.get(i).get(l).charAt(k) == '-') {
									count ++;
								}
							}
							if (count == ForwardOrfs.get(i).size()/2) {
								hyphenCol.add(k);
								hyphenColRepeat.add(k);
								count = 0;
							}
							else {
								hyphenColRepeat.add(k);
							}
						}
						else {
							hyphenColRepeat.add(k);
						}
						
					}
				}	
			}

						
			for (int j = 0; j < ForwardOrfs.get(i).size(); j ++) {
				if (ForwardOrfs.get(i).get(j).contains(">")) {				// add the sequence name
					tempOrf.add(ForwardOrfs.get(i).get(j));
				}
				else {
					String tempSeq = new String();
					if (hyphenCol.size() == 1) {
						tempSeq = tempSeq + (ForwardOrfs.get(i).get(j).substring(0));
						
					}
					else if (hyphenCol.size() > 1) {
						
						tempSeq = tempSeq + (ForwardOrfs.get(i).get(j).substring(0, hyphenCol.get(1)).trim());	// add the first section, 0 to second hyphenCol number
						
						
						for (int p = 1; p < hyphenCol.size() -1 ; p++) {
							if (ForwardOrfs.get(i).get(j).substring( (hyphenCol.get(p)+1) , hyphenCol.get(p+1) ).length() > 0) {
								tempSeq = tempSeq + (ForwardOrfs.get(i).get(j).substring((hyphenCol.get(p)+1), hyphenCol.get(p+1)).trim());
								
							}
						}	
						
						tempSeq = tempSeq + (ForwardOrfs.get(i).get(j).substring(hyphenCol.get(hyphenCol.size()-1)+1).trim());		// add the final section
						
					}
					
					tempOrf.add(tempSeq);
				}
				
			}
		
			
			adjustedOrfs.add(tempOrf);
			
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		for (int i = 0; i < ReverseOrfs.size(); i++) {					// look at the ORFs
					
					ArrayList<String> tempOrf = new ArrayList<String>();
					
					ArrayList<Integer> hyphenCol = new ArrayList<Integer>();	// position of columns for that ORF
					hyphenCol.add(0);											// first position
					ArrayList<Integer> hyphenColRepeat = new ArrayList<Integer>();
					
					for (int j = 0; j < ReverseOrfs.get(i).size(); j++) {		// look at the sequences
						
						if (!ReverseOrfs.get(i).get(j).contains(">")) {
						
							for (int k = 0; k < ReverseOrfs.get(i).get(j).length(); k++) {	// look at the nucleotides
								
								if (ReverseOrfs.get(i).get(j).charAt(k) == '-' && !hyphenColRepeat.contains(k)) {	// if we reach a hyphen and that hyphen is not in position k
									
									Integer count = 0;
									
									for (int l = 1; l < ReverseOrfs.get(i).size(); l=l+2) {		// skip sequence names
										
										if (ReverseOrfs.get(i).get(l).charAt(k) == '-') {
											count ++;
										}
									}
									if (count == ReverseOrfs.get(i).size()/2) {
										hyphenCol.add(k);
										hyphenColRepeat.add(k);
										count = 0;
									}
									else {
										hyphenColRepeat.add(k);
									}
								}
								else {
									hyphenColRepeat.add(k);
								}
								
							}
						}	
					}
		
								
					for (int j = 0; j < ReverseOrfs.get(i).size(); j ++) {
						if (ReverseOrfs.get(i).get(j).contains(">")) {				// add the sequence name
							tempOrf.add(ReverseOrfs.get(i).get(j));
						}
						else {
							String tempSeq = new String();
							if (hyphenCol.size() == 1) {
								tempSeq = tempSeq + (ReverseOrfs.get(i).get(j).substring(0));
								
							}
							else if (hyphenCol.size() > 1) {
								
								tempSeq = tempSeq + (ReverseOrfs.get(i).get(j).substring(0, hyphenCol.get(1)).trim());	// add the first section, 0 to second hyphenCol number
								
								
								for (int p = 1; p < hyphenCol.size() -1 ; p++) {
									if (ReverseOrfs.get(i).get(j).substring( (hyphenCol.get(p)+1) , hyphenCol.get(p+1) ).length() > 0) {
										tempSeq = tempSeq + (ReverseOrfs.get(i).get(j).substring((hyphenCol.get(p)+1), hyphenCol.get(p+1)).trim());
										
									}
								}	
								
								tempSeq = tempSeq + (ReverseOrfs.get(i).get(j).substring(hyphenCol.get(hyphenCol.size()-1)+1).trim());		// add the final section
								
							}
							
							tempOrf.add(tempSeq);
						}
						
					}
				
					
					adjustedOrfs.add(tempOrf);
					
				}
		
		
		
/*		for (int q = 0; q < adjustedOrfs.size(); q ++) {
			for (int r = 0; r < adjustedOrfs.get(q).size(); r++) {
				System.out.println(adjustedOrfs.get(q).get(r));
			}
		}
*/		
		return adjustedOrfs;
		
	}

	
	public static ArrayList<ArrayList<String>> allAdjustedOrfs() throws Exception{	
		
		ArrayList<ArrayList<String>> allOrfs = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> adjustedOrfs = new ArrayList<ArrayList<String>>();
		
		adjustedOrfs = SignificantOrf.AdjOrfs();
		
		for (int i = 0; i < adjustedOrfs.size(); i++) {
			
			ArrayList<String> tempOrf = new ArrayList<String>();
			
			for (int j = 0; j < adjustedOrfs.get(i).size(); j++) {
				
				if (adjustedOrfs.get(i).get(j).contains(">")) {
					tempOrf.add(adjustedOrfs.get(i).get(j));
				}
				
				else {
					Integer hyphenCounter = 0;			//not sure where you should be
					String tempSeq = new String();
					
					for (int k = 0; k < adjustedOrfs.get(i).get(j).length() - 2; k = k+3) {
						
						String tempCodon = "";
						tempCodon = tempCodon + adjustedOrfs.get(i).get(j).charAt(k) +
									adjustedOrfs.get(i).get(j).charAt(k+1) +	
									adjustedOrfs.get(i).get(j).charAt(k+2);
//						System.out.println(tempCodon);
						
						if (adjustedOrfs.get(i).get(j).charAt(k) == '-') {
							hyphenCounter ++;
						}
						if (adjustedOrfs.get(i).get(j).charAt(k+1) == '-') {
							hyphenCounter ++;
						}
						if (adjustedOrfs.get(i).get(j).charAt(k+2) == '-') {
							hyphenCounter ++;
						}
						
						if (hyphenCounter%3 == 0 && !tempCodon.contains("-")) {
//							System.out.println(tempCodon);
							tempSeq = tempSeq + tempCodon;
						}
						else {
							tempSeq = tempSeq + "---";
						}
						
					}
					
					tempOrf.add(tempSeq);
					
				}
			}
			
			allOrfs.add(tempOrf);
			
		}
		
		
		for (int q = 0; q < allOrfs.size(); q ++) {
			for (int r = 0; r < allOrfs.get(q).size(); r++) {
				System.out.println(allOrfs.get(q).get(r));
			}
		}
		
		return allOrfs;
	}
}

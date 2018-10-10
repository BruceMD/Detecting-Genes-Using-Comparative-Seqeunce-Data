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
						
						if((x-y)*(x-y) > 33*33 ) {	// check to see if ORF will be long enough, x is smaller than y, should give a negative value
							
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
							
							
							if (count > 3) {													// if the count is sufficient (above 3) append it to arrayOfForwardOrfs
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
						
						if((x-y)*(x-y) > 33*33 ) {	// check to see if ORF will be long enough, x is larger than y, should give positive value
							
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
	
	
	public static ArrayList<ArrayList<ArrayList<String>>> AllOrfs() throws Exception{
		
		ArrayList<ArrayList<ArrayList<String>>> allOrfs = new ArrayList<ArrayList<ArrayList<String>>>();
		
		
		
		allOrfs.add(SignificantOrf.ForwardOrfs());
		allOrfs.add(SignificantOrf.ReverseOrfs());
		
//		int countOrfs = 0;
		
/*		for (int i = 0; i < 2; i++) {
//			countOrfs = countOrfs + allOrfs.get(i).size();
			for (int j = 0; j < allOrfs.get(i).size(); j ++) {
				for (int k = 0; k < allOrfs.get(i).get(j).size(); k++) {
					System.out.println(allOrfs.get(i).get(j).get(k));
				}
			}
		}
*/		
//		System.out.println("Number of Orfs is : " + countOrfs);
		
		return allOrfs;
	}

}
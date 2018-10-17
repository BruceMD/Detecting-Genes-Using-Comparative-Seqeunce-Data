import java.util.*;
import java.io.*;
import java.math.*;

public class JsonReader {
	
	public static ArrayList<ArrayList<ArrayList<String>>> InputJson() throws Exception{
		
		ArrayList<ArrayList<ArrayList<String>>> allOrfData = new ArrayList<ArrayList<ArrayList<String>>>();
		
		ArrayList<ArrayList<String>> tempData = new ArrayList<ArrayList<String>>();

		try {
		
	//		for (int i = 0; i < OrfsArray.size(); i++) {				// this should give the number of ORFs we are considering
			for (int i = 1; i < 914; i++) {
					
				ArrayList<String> tempArray = new ArrayList<String>();	
					
				File file = new File("C:\\Users\\maxbr\\Desktop\\Bioinformatics\\FubarOutput\\4ExamplePoty\\Orf" + i + ".fas.FUBAR.json");
					
				if (!file.isFile()) {
					tempData.add(tempArray);
					continue;
				}
					
				BufferedReader buffReader = new BufferedReader(new FileReader(file));
				String tempLine = null;
							
				boolean check = false;
					
				while ((tempLine = buffReader.readLine()) != null) {
					
					if (tempLine.contains("settings")) {		// stop when we hit settings
						break;
					}
						
					if (tempLine.contains("content")) {			// turning on the switch to start adding all the lines from content to settings
						check = true;
					}
						
					if (check) {
						tempArray.add(tempLine.trim());
					}
						
						
				}
					
				buffReader.close();
					
				tempData.add(tempArray);
					
			}
			
		}
		
		catch (Exception e) {
			System.out.println("Exception occurred");
		}
			

			
		for (int j = 0; j < tempData.size(); j++) {
			
			ArrayList<ArrayList<String>> orfData = new ArrayList<ArrayList<String>>();
			
			for (int k = 2; k < tempData.get(j).size(); k++) {
				
				String probAB = new String();
				String probBA = new String();
				int spaceCount = 0;
				int commaCount = 0;
				
				ArrayList<String> singleSiteData = new ArrayList<String>();
				
				for (int l = 0; l < tempData.get(j).get(k).length(); l++) {

					if (tempData.get(j).get(k).charAt(l) == ',') {
						commaCount ++; 	
					}
						
					if (spaceCount == 3 && commaCount == 3) {
						probAB = probAB + tempData.get(j).get(k).charAt(l);
					}
					if (spaceCount == 4 && commaCount == 4) {
						probBA = probBA + tempData.get(j).get(k).charAt(l);
					}
						
					if (tempData.get(j).get(k).charAt(l) == ' ') {
						spaceCount ++;	
					}

				}
				
				if (probAB.length() > 0) {
				singleSiteData.add("ORF" + (j+1) + "_AASite #" + (k-1));
				singleSiteData.add(probAB);
				singleSiteData.add(probBA);
				}
				if (singleSiteData.size() >0 ) {
				orfData.add(singleSiteData);
				}
			}

			allOrfData.add(orfData);

		}
		
/*		for (int x = 0; x < allOrfData.size(); x++) {
			for (int y = 0; y < allOrfData.get(x).size(); y++) {
				System.out.println(allOrfData.get(x).get(y));
			}
		}
*/		
		
		return allOrfData;
	}
	
	
	public static ArrayList<ArrayList<String>> orfSigAlgorithm () throws Exception {
		
		ArrayList<ArrayList<String>> finalOrfSigArray = new ArrayList<ArrayList<String>>();
		
		ArrayList<ArrayList<ArrayList<String>>> allOrfData = new ArrayList<ArrayList<ArrayList<String>>>();
		
		allOrfData = JsonReader.InputJson();
				
		for (int i = 0; i < allOrfData.size(); i++) {
			
			double probAB = 1.0;
			double probBA = 1.0;
			
			for (int j = 0; j < allOrfData.get(i).size(); j++) {
				
			Double siteProbAB = new Double(allOrfData.get(i).get(j).get(1).valueOf(allOrfData.get(i).get(j).get(1)));	
			Double siteProbBA = new Double(allOrfData.get(i).get(j).get(2).valueOf(allOrfData.get(i).get(j).get(2)));		
				
			probAB = probAB * siteProbAB;
			probBA = probBA * siteProbBA;
			
			}
			
			double finalProbAB = probAB * (1 - Math.log10(probAB));
			double finalProbBA = probBA * (1 - Math.log10(probBA));
			
			System.out.println("Orf #" + i + " has a combined p value of " + finalProbAB + " " + finalProbBA);
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return finalOrfSigArray;
	}
	
}

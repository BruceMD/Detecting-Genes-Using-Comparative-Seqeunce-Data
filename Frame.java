import java.util.ArrayList;

public class Frame {	
	private ArrayList<SignificantMatchingOrfs> listOfSignificantMatchingOrfs = new ArrayList<>();
	
	private ArrayList<Integer> listOfSignificantOrfLocations = new ArrayList<>();

	public ArrayList<SignificantMatchingOrfs> getListOfSignificantMatchingOrfs() {
		return listOfSignificantMatchingOrfs;
	}

	public void setListOfSignificantMatchingOrfs(ArrayList<SignificantMatchingOrfs> listOfSignificantMatchingOrfs) {
		this.listOfSignificantMatchingOrfs = listOfSignificantMatchingOrfs;
	}

	public ArrayList<Integer> getListOfSignificantOrfLocations() {
		return listOfSignificantOrfLocations;
	}

	public void setListOfSignificantOrfLocations(ArrayList<Integer> listOfSignificantOrfLocations) {
		this.listOfSignificantOrfLocations = listOfSignificantOrfLocations;
	}
}

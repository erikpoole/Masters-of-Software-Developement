package hwjavarainfall;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class RainData {

	public String city;
	public ArrayList<String> month = new ArrayList<String>();
	public ArrayList<Integer> year = new ArrayList<Integer>();
	public ArrayList<Float> amount = new ArrayList<Float>();
	
	//constructor
	public RainData(String filename) throws Exception {
		
		Scanner reader = new Scanner(new FileInputStream(filename));
		
		city = reader.next();
		
		while (reader.hasNext()) {
			month.add(reader.next());
			year.add(reader.nextInt());
			amount.add(reader.nextFloat());
		}
		reader.close();
	}
	
	public float averageRain() {
		float total = 0;
		for (int i = 0; i < amount.size(); i++) {
			total += amount.get(i);
		}
		return total/(float)amount.size();
	}
	
	public float monthAverage(String neededMonth) {
		float total = 0;
		int numMonths = 0;
		for (int i = 0; i < month.size(); i++) {
			if (month.get(i).equals(neededMonth)) {
				total += amount.get(i);
				numMonths++;
			}
		}
		return total / (float)numMonths;
	}
	
}

package hwjavarainfall;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {

		RainData file = new RainData("rainfall_data.txt");

		PrintWriter output = new PrintWriter("rainfall_output_data.txt");
		
		output.println("Java Homework 1");
		output.println("Erik Poole");
		output.println(file.city);
		output.println();
		
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		output.println("The overall average rainfall amount is: " + decimalFormat.format(file.averageRain()) + " inches.");
		
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		for (String month : months) {
			output.println("The average rainfall amount for " + month + " is " + decimalFormat.format(file.monthAverage(month)) + " inches.");
		}
		
		output.close();
	}

}

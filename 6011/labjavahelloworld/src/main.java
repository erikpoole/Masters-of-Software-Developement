import java.util.Random;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		System.out.println("Hello World!");

		int numArray[] = new int[10];
		Random randomNum = new Random();
		
		int total = 0;
		for (int i = 0; i < numArray.length; i++) {
			int oneRandNum = randomNum.nextInt();
			total += oneRandNum;
			numArray[i] = oneRandNum;
			System.out.println(numArray[i]);
		}
		System.out.println();
		System.out.println(total);
		System.out.println();
		
		System.out.println("What is your name?");
		System.out.println("What is your age?");
		Scanner s = new Scanner(System.in);
		int age = s.nextInt();

		if (age >= 18) {
			System.out.println("You are old enough to vote!");
		}
		if (age > 80) {
			System.out.println("You are a member of the greatest generation!");
		} else if (age > 60) {
			System.out.println("You are a baby boomer!");
		} else if (age > 40) {
			System.out.println("You are a part of generation x!");
		} else if (age > 20) {
			System.out.println("You are a millenial!");
		} else if (age > 60) {
			System.out.println("You are an iKid!");
		}
	}
}

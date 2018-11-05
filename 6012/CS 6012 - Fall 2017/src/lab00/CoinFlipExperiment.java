package lab00;

public class CoinFlipExperiment {

	public static void main(String[] args) {

		int[] resultArr = new int[201];
		int numFlips = 10000;
		for (int i = 0; i < numFlips; i++) {
			resultArr[flipCoins(100) + 100]++;
		}

		float total = 0;
		for (int i = 0; i < 201; i++) {
			total += resultArr[i]*(i-100);
		}

		System.out.println("Likelihood of win/loss amount after 100 flips: ");
		System.out.println("Average Result: " + total/numFlips);
		System.out.println("Amount ; Probability");
		System.out.println();

		for (int i = 0; i < 201; i++) {
			System.out.println(i - 100 + " ; " + resultArr[i]);
			System.out.println();
		}

	}

	public static int flipCoins(int input) {
		int headCount = 0;
		for (int i = 0; i < input; i++) {
			double flipResult = Math.random();
			if (flipResult < .505) {
				headCount++;
			}
		}
		int winnings = headCount - (input - headCount);

		return winnings;
	}

}

package lab01;

public class DiffUtil {

	public static void main(String[] args) {

	}

	/**
	 * @param arr -- input array of integers
	 * @return The smallest difference (absolute value of subtraction) among every
	 *         pair of integers in the input array. If the array contains less than
	 *         two items, returns -1.
	 */
	public static int findSmallestDiff(int[] input) {
		if (input.length < 2) {
			return -1;
		}

		int diff = Math.abs(input[0] - input[1]);

		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				int workingDiff = input[i] - input[j];

				if (Math.abs(workingDiff) < Math.abs(diff)) {
					diff = Math.abs(workingDiff);
				}
			}
		}
		return diff;
	}

}

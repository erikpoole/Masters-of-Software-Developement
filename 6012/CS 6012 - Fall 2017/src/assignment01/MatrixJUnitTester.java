/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult;
	/* Initialize some matrices we can play with for every test! */

	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]{{1, 2, 3}, {2, 5, 6}});
		twoByThree = new Matrix(new int[][]{{4, 5}, {3, 2}, {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]{{13, 12}, {29, 26}});
	}

	@Test
	public void equalsMethodTest() {
		assertTrue(threeByTwo.equals(threeByTwo));
		assertTrue(!threeByTwo.equals(twoByThree));
	}
	
	@Test
	public void toStringMethodTests() {
		//System.out.println(threeByTwo.toString());
		assertEquals("1 2 3 \n2 5 6 \n", threeByTwo.toString());
	}

	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}

	@Test
	public void timesWithUnbalancedDimensions() {
		// TODO: More tests like these!
	}

	@Test
	public void twoByTwoToString() {
		String resultString = "what should it equal?";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
}

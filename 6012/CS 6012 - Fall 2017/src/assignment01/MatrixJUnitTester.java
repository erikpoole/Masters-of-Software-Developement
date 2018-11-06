/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, negTwoByTwo, twobyOne, negTwoByOne;
	/* Initialize some matrices we can play with for every test! */

	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		twoByThree = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });

		negTwoByTwo = new Matrix(new int[][] { { -5, -10 }, { -1, -2 } });
		twobyOne = new Matrix(new int[][] { { 1 }, { 0 } });
		negTwoByOne = new Matrix(new int[][] { { -14 }, { -3 } });
	}

	@Test
	public void equalsMethodTest() {
		assertTrue(threeByTwo.equals(threeByTwo));
		assertTrue(!threeByTwo.equals(twoByThree));
	}

	@Test
	public void toStringMethodTests() {
		// System.out.println(threeByTwo.toString());
		assertEquals("1 2 3 \n2 5 6 \n", threeByTwo.toString());
		assertEquals("13 12 \n29 26 \n", twoByTwoResult.toString());
	}

	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		assertTrue(twoByTwoResult.equals(matrixProduct));

		// with negative * negative
		matrixProduct = negTwoByTwo.times(negTwoByTwo);
		assertEquals("35 70 \n7 14 \n", matrixProduct.toString());

		// with negative * positive
		matrixProduct = negTwoByTwo.times(twobyOne);
		assertEquals("-5 \n-1 \n", matrixProduct.toString());
	}

	@Test
	public void timesWithUnbalancedDimensions() {
		// illegal multiplication
		Matrix matrixProduct = twobyOne.times(negTwoByTwo);
		assertNull(matrixProduct);
	}

	@Test
	public void plusWithBalancedDimensions() {
		// negative + negative
		Matrix matrixSum = negTwoByTwo.plus(negTwoByTwo);
		assertEquals("-10 -20 \n-2 -4 \n", matrixSum.toString());

		// negative + positive
		matrixSum = negTwoByOne.plus(twobyOne);
		assertEquals("-13 \n-3 \n", matrixSum.toString());

	}

	@Test
	public void plusWithUnbalancedDimensions() {
		// illegal addition
		Matrix matrixSum = threeByTwo.plus(twobyOne);
		assertNull(matrixSum);
	}
}

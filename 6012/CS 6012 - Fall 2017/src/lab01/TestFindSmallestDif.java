package lab01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFindSmallestDif {

	private int[] arr1, arr2, arr2a, arr2b, arr3, arr4, arr5, arr6;

	@BeforeEach
	void setUp() throws Exception {
		arr1 = new int[0];
		arr2 = new int[] { 3, 3, 3 };
		arr2a = new int[] { 0, 0, 0 };
		arr2b = new int[] { -3, -3, -3 };
		arr3 = new int[] { 30, 1, 0, -4, -10 };
		arr4 = new int[] { 0, 0, 10 };
		arr5 = new int[] { 10, 0, 0 };
		arr6 = new int[] { 2, 3, 4 };
	}

	@AfterEach
	void tearDown() throws Exception {
		arr1 = null;
		arr2 = null;
		arr3 = null;
	}

	@Test
	public void emptyArray() {
		assertEquals(-1, DiffUtil.findSmallestDiff(arr1));
	}

	@Test
	public void allArrayElementsEqual() {
		assertEquals(0, DiffUtil.findSmallestDiff(arr2));
		assertEquals(0, DiffUtil.findSmallestDiff(arr2a));
		assertEquals(0, DiffUtil.findSmallestDiff(arr2b));
		
	}

	@Test
	public void smallRandomArrayElements() {
		assertEquals(1, DiffUtil.findSmallestDiff(arr3));
	}

	@Test
	public void checkLowerArrayLimit() {
		assertEquals(0, DiffUtil.findSmallestDiff(arr4));
	}

	@Test
	public void checkUpperArrayLimit() {
		assertEquals(0, DiffUtil.findSmallestDiff(arr5));
	}
	
	@Test
	public void checkSequentialArray() {
		assertEquals(1, DiffUtil.findSmallestDiff(arr6));
	}

}

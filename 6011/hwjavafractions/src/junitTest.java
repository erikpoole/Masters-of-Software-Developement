import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class junitTest {

	@Test
	public void test() throws Exception {

		Fraction test0 = new Fraction();
		Fraction testHalf = new Fraction(1, 2);
		Fraction test4 = new Fraction(40, 10);
		Fraction testNeg = new Fraction(9, -10);

		assertEquals("0/1", test0.toString());
		assertEquals(4, test4.toDouble());
		assertEquals("-9/10", testNeg.toString());
		assertEquals("2/1", testHalf.reciprocal().toString());

		Fraction addedFracs = test0.plus(testHalf);
		assertEquals(.5, addedFracs.toDouble());

		Fraction subtractedFracs = testNeg.minus(test4);
		assertEquals("-49/10", subtractedFracs.toString());

		Fraction multipliedFracs = testHalf.times(testNeg);
		assertEquals("-9/20", multipliedFracs.toString());

		Fraction dividedFracs = testHalf.dividedBy(testHalf);
		assertEquals(1, dividedFracs.toDouble());

		try {
			Fraction testException = new Fraction(0, 0);
			fail("Bad");
		} catch (Exception e) {
			System.out.println("Divide by Zero Error");
		}

		assertEquals(test4.compareTo(testHalf), 1);
		assertEquals(testHalf.compareTo(test4), -1);
		assertEquals(testNeg.compareTo(testNeg), 0);

		ArrayList<Fraction> fractionList = new ArrayList<Fraction>();
		fractionList.add(test0);
		fractionList.add(testHalf);
		fractionList.add(test4);
		fractionList.add(testNeg);

		Collections.sort(fractionList);
		assertEquals(fractionList.get(0), testNeg);
		assertEquals(fractionList.get(3), test4);

	}

}

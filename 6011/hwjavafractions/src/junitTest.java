import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class junitTest {

	@Test
	public void test() {
		
		Fraction test0 = new Fraction();
		Fraction testHalf = new Fraction(1,2);
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
		
		
	}

}

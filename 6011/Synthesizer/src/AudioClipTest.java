import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AudioClipTest {
	
	@Test
	void test() {
		
		AudioClip testClip = new AudioClip();
		testClip.setSample(0, 5);
		testClip.setSample(1, -5);
		testClip.setSample(2, 0);
		//max Positive value = 32767
		testClip.setSample(3, 32767);
		//max Negative value = -32768
		testClip.setSample(4, -32768);
		//Past Positive bounds
		testClip.setSample(5, 32768);
		//Past Negative bounds
		testClip.setSample(6, -32769);
		
		assertEquals(testClip.getSample(0), 5);
		assertEquals(testClip.getSample(1), -5);
		assertEquals(testClip.getSample(2), 0);
		assertEquals(testClip.getSample(3), 32767);
		assertEquals(testClip.getSample(4), -32768);
		assertEquals(testClip.getSample(5), -32768);
		assertEquals(testClip.getSample(5), -32768);
		
		

	}

}

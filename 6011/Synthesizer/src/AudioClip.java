import javax.sound.sampled.Clip;

public class AudioClip {

	// in Seconds
	private int duration;
	// Samples per Second
	private int sampleRate;
	// amplitude vale for each sample
	private byte[] byteArray;

	public AudioClip() {
		duration = 1;
		sampleRate = 441000;
		byteArray = new byte[882000];
	}

	public int getSample(int input) {
		int firstByte = byteArray[2 * input];
		int secondByte = byteArray[2 * input + 1];
		firstByte &= 0x000000FF;
		secondByte <<= 8;

		int combinedBytes = 0;
		combinedBytes |= firstByte;
		combinedBytes |= secondByte;

		return combinedBytes;

	}

	public void setSample(int index, int input) {
		byte firstByte = (byte) input;
		byte secondByte = (byte) (input >> 8);
		byteArray[index * 2] = firstByte;
		byteArray[index * 2 + 1] = secondByte;
//		System.out.println(firstByte);
//		System.out.println(secondByte);
//		System.out.println();
	}

	public double getDuration() {
		return duration;
	}

	public double getSampleRate() {
		return sampleRate;
	}

	public byte[] getByteArray() {
		return byteArray;
	}
	
	public static void playSound(Clip inputClip) {
		System.out.println("About to Play");
		inputClip.start();
		// hangs for required amount of time for audio to play
		while (inputClip.getFramePosition() < inputClip.getFrameLength() || inputClip.isActive() || inputClip.isRunning()) {
		}
		System.out.println("Finished");
		inputClip.close();
	}

}

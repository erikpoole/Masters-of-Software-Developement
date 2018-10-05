package Backend;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
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
	
	public static void playSound(AudioClip inputClip) throws Exception {
		
		Clip clip = AudioSystem.getClip();
		AudioFormat format16 = new AudioFormat((float) inputClip.getSampleRate(), 16, 1, true, false);
		clip.open(format16, inputClip.getByteArray(), 0, inputClip.getByteArray().length);

		System.out.println("About to Play");
		clip.start();
		// hangs for required amount of time for audio to play
		while (clip.getFramePosition() < clip.getFrameLength() || clip.isActive() || clip.isRunning()) {
		}
		System.out.println("Finished");
		clip.close();
	}

}

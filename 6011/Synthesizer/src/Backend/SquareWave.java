package Backend;

public class SquareWave implements Source {

	private AudioClip audioClip;
	private double frequency;

	public SquareWave(int input) {
		frequency = input;
		audioClip = new AudioClip();

	}

	public AudioClip getAudioClip() {
		for (int i = 0; i < audioClip.getSampleRate(); i++) {
			int value;
			if (i % 1 == 0) {
				value = (int) (32767 * frequency / audioClip.getSampleRate());
			} else {
				value = (int) (-32767 * frequency / audioClip.getSampleRate());
			}
			audioClip.setSample(i, value);
		}
		return audioClip;
	}

	public void setFrequency(double input) {
		frequency = input;
	}

}
package Backend;

public class SineWave implements Source {

	private AudioClip audioClip;
	private double frequency;

	public SineWave(int input) {
		frequency = input;
		audioClip = new AudioClip();

	}

	public AudioClip getAudioClip() {
		System.out.println("frequency " + frequency);
		for (int i = 0; i < audioClip.getSampleRate(); i++) {
			int Value = (int) (32767 * Math.sin(2 * Math.PI * frequency * i / audioClip.getSampleRate()));
			audioClip.setSample(i, Value);
		}
		return audioClip;
	}

	public void setFrequency(double input) {
		System.out.println("new frequency" + input);
		frequency = input;
	}

}

public class SineWave implements Source {

	private AudioClip audioClip;
	public int frequency;

	SineWave(int input) {
		frequency = input;
		audioClip = new AudioClip();

	}


	public AudioClip getAudioClip() {
		for (int i = 0; i < audioClip.getSampleRate(); i++) {
			int Value = (int) (32767 * Math.sin(2 * Math.PI * frequency * i / audioClip.getSampleRate()));
			audioClip.setSample(i, Value);
		}
		return audioClip;
	}
	
	public void setFrequency(int input) {
		frequency = input;
	}

}
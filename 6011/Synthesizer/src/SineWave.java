
public class SineWave implements Source {

	private AudioClip audioClip;

	SineWave(int frequency) {
		audioClip = new AudioClip();

		for (int i = 0; i < audioClip.getSampleRate(); i++) {
			int Value = (int) (32767 * Math.sin(2 * Math.PI * frequency * i / audioClip.getSampleRate()));
			audioClip.setSample(i, Value);
		}
	}

	@Override
	public AudioClip getAudioClip() {
		return audioClip;
	}

}
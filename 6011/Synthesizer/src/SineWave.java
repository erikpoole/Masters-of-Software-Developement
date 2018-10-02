import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

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
	public Clip getSound() throws LineUnavailableException {

		Clip clip = AudioSystem.getClip();
		AudioFormat format16 = new AudioFormat((float) audioClip.getSampleRate(), 16, 1, true, false);
		clip.open(format16, audioClip.getByteArray(), 0, audioClip.getByteArray().length);

		return clip;
	}

	@Override
	public AudioClip getAudioClip() {
		return audioClip;
	}

}
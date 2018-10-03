
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class AdjustVolume implements Filter {

	private double scale;
	
	//store source instead/////////////////////////////////////////////////////////////////////
	private AudioClip audioClip;

	public AdjustVolume(double inputScale) {
		scale = inputScale;
	}

	@Override
	public void connectInput(Source input) throws LineUnavailableException {
		audioClip = input.getAudioClip();
		for (int i = 0; i < audioClip.getByteArray().length; i++) {
			audioClip.getByteArray()[i] *= scale;
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

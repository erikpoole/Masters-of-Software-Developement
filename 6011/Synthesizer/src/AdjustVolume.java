
import javax.sound.sampled.LineUnavailableException;

public class AdjustVolume implements Filter {

	private double scale;

	// store source
	// instead/////////////////////////////////////////////////////////////////////
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
	public AudioClip getAudioClip() {
		return audioClip;
	}

}

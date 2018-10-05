package Backend;

import javax.sound.sampled.LineUnavailableException;

public class AdjustVolume implements Filter {

	public double scale;

	private Source source;
	private AudioClip outputClip;

	public AdjustVolume(double inputScale) {
		scale = inputScale;
	}

	@Override
	public void connectInput(Source input) throws LineUnavailableException {
		source = input;
	}

	@Override
	public AudioClip getAudioClip() {

		outputClip = source.getAudioClip();
		for (int i = 0; i < outputClip.getByteArray().length; i++) {
			outputClip.getByteArray()[i] *= scale;
		}
		return outputClip;
	}

	public void setScale(double input) {
		scale = input;
	}

}

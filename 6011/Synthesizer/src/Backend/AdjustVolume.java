package Backend;

import javax.sound.sampled.LineUnavailableException;

public class AdjustVolume implements Filter {

	private double scale;

	private Source source;

	public AdjustVolume(double inputScale) {
		scale = inputScale;
	}

	@Override
	public void connectInput(Source input) throws LineUnavailableException {
		source = input;
	}

	@Override
	public AudioClip getAudioClip() {
		for (int i = 0; i < source.getAudioClip().getByteArray().length; i++) {
			source.getAudioClip().getByteArray()[i] *= scale;
		}
		return source.getAudioClip();
	}

	public void setScale(int input) {
		scale = input;
	}

}

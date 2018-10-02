
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Adder implements Mixer {

	private AudioClip audioClip;
	private ArrayList<AudioClip> audioClipArray = new ArrayList<AudioClip>();

	@Override
	public void connectInput(Source input) throws LineUnavailableException {
		audioClipArray.add(input.getAudioClip());
	}

	@Override
	public void addInput() {
		audioClip = new AudioClip();
		int divisor = audioClipArray.size();
		for (AudioClip singleClip : audioClipArray) {
			for (int i = 0; i < singleClip.getByteArray().length; i++) {
				audioClip.getByteArray()[i] += (singleClip.getByteArray()[i] / divisor);
			}
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

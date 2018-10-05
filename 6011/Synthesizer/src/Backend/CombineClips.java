package Backend;

import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

public class CombineClips implements Mixer {

	// store source instead of audioclip
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
	public AudioClip getAudioClip() {
		return audioClip;
	}

}

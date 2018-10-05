package Backend;

import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

public class CombineClips implements Mixer {

	private AudioClip workingClip;
	private AudioClip outputClip;

	private ArrayList<Source> sourceArray = new ArrayList<Source>();

	@Override
	public void connectInput(Source input) throws LineUnavailableException {
		sourceArray.add(input);
	}

	@Override
	public void addInput() {
		outputClip = new AudioClip();
		int divisor = sourceArray.size();
		for (Source singleSource : sourceArray) {
			workingClip = singleSource.getAudioClip();
			System.out.println("Working");
			for (int i = 0; i < workingClip.getByteArray().length; i++) {
				outputClip.getByteArray()[i] += (workingClip.getByteArray()[i] / divisor);
			}
		}
	}
	
	@Override
	public AudioClip getAudioClip() {
		return outputClip;
	}

}

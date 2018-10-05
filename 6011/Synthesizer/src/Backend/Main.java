package Backend;

public class Main {

	public static void main(String[] args) throws Exception {
		SineWave sineWave1 = new SineWave(440);
		AudioClip.playSound(sineWave1.getAudioClip());

		AdjustVolume adjustVolume = new AdjustVolume(.5);
		adjustVolume.connectInput(sineWave1);
		AudioClip.playSound(adjustVolume.getAudioClip());

		SineWave sineWave2 = new SineWave(880);
		AudioClip.playSound(sineWave2.getAudioClip());

		adjustVolume.connectInput(sineWave2);
		AudioClip.playSound(adjustVolume.getAudioClip());

		CombineClips combiner = new CombineClips();
		combiner.connectInput(sineWave1);
		combiner.connectInput(sineWave2);
		combiner.addInput();
		AudioClip.playSound(combiner.getAudioClip());
	}

}

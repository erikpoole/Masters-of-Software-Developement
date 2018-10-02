import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Main {

	public static void main(String[] args) throws LineUnavailableException {
		SineWave sineWave1 = new SineWave(440);
		Clip clip = sineWave1.getSound();
		AudioClip.playSound(clip);
		
		AdjustVolume adjustVolume = new AdjustVolume(.5);
		adjustVolume.connectInput(sineWave1);
		clip = adjustVolume.getSound();
		AudioClip.playSound(clip);
		
		SineWave sineWave2 = new SineWave(880);
		clip = sineWave2.getSound();
		AudioClip.playSound(clip);
		
		adjustVolume.connectInput(sineWave2);
		clip = adjustVolume.getSound();
		AudioClip.playSound(clip);
		
		Adder adder = new Adder();
		adder.connectInput(sineWave1);
		adder.connectInput(sineWave2);
		adder.addInput();
		clip = adder.getSound();
		AudioClip.playSound(clip);
	}

}

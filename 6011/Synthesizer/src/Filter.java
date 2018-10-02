import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public interface Filter extends Source {

	void connectInput(Source input) throws LineUnavailableException;

	Clip getSound() throws LineUnavailableException;
	AudioClip getAudioClip();

}

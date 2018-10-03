
import javax.sound.sampled.LineUnavailableException;

public interface Filter extends Source {

	void connectInput(Source input) throws LineUnavailableException;

	AudioClip getAudioClip();

}

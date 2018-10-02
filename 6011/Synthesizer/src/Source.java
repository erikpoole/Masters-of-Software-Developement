import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public interface Source {

	Clip getSound() throws LineUnavailableException;
	AudioClip getAudioClip();

}

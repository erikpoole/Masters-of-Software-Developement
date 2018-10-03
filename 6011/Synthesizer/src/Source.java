import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public interface Source {
	
	//////////////////////////remove clip/////////////////
	Clip getSound() throws LineUnavailableException;
	AudioClip getAudioClip();

}

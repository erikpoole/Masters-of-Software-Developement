package Backend;

import javax.sound.sampled.LineUnavailableException;

public interface Mixer extends Source {

	void connectInput(Source input) throws LineUnavailableException;

	void addInput();

}

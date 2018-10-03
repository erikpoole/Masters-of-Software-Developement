
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PlayButton {

	public Button playButton;
	public static int inputFrequency = 500;

	public PlayButton() {
		playButton = new Button();
		playButton.setText("Play");
		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SineWave sineWave = new SineWave(inputFrequency);
				Clip clip;

				// Ask Ben: any way to get rid of try/catch block here?
				try {
					clip = sineWave.getSound();
					AudioClip.playSound(clip);
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
	
}


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

				// Ask Ben: Way to avoid try catch here?
				try {
					AudioClip.playSound(sineWave.getAudioClip());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

}

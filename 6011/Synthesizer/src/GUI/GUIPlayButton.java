package GUI;

import Backend.AudioClip;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GUIPlayButton {

	public Button playButton;

	public GUIPlayButton(MyApp guiMain) {
		playButton = new Button ();
		playButton.setText("Play");
		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					AudioClip.playSound(guiMain.getFinalAudioClip());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

}

package GUI;

import Backend.AdjustVolume;
import Backend.AudioClip;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class AdjustVolumeWidget extends AbFilterWidget {

	public AdjustVolume adjustVolume;

	public AdjustVolumeWidget() {
		super();
		
		Label label = new Label("AdjustVolumeFilter");
		widget.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

		adjustVolume = new AdjustVolume(500);
		Slider slider = new Slider(0, 1, .5);
		slider.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				adjustVolume.setScale((int) slider.getValue());
			}
		});

		widget.setBottom(slider);

	}

	public AudioClip getAudioClip() {
		return adjustVolume.getAudioClip();
	}

}

package GUI;

import Backend.AudioClip;
import Backend.SineWave;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class SineWaveWidget extends AbSourceWidget {

	public SineWave sineWave;

	public SineWaveWidget() {
		super();
		
		Label label = new Label("Sine Wave");
		widget.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

		sineWave = new SineWave(500);
		Slider slider = new Slider(200, 2000, 500);
		slider.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				sineWave.setFrequency((int) slider.getValue());
			}
		});

		widget.setBottom(slider);

	}

	public AudioClip getAudioClip() {
		return sineWave.getAudioClip();
	}

}

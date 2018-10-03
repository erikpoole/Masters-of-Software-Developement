import javax.sound.sampled.Clip;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;

public class GUISineWave {

	BorderPane sineElement;

	public GUISineWave() {
		sineElement = new BorderPane();
		sineElement.setMaxSize(200, 100);
		sineElement.setStyle("-fx-border-color: black");

		Label label = new Label("Sinewave Frequency");
		sineElement.setAlignment(label, Pos.CENTER);

		Slider slider = new Slider(200, 2000, 500);
		slider.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				PlayButton.inputFrequency = (int) slider.getValue();
			}
		});

		sineElement.setTop(label);
		sineElement.setBottom(slider);

	}

}

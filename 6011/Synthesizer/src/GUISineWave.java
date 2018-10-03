
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class GUISineWave {

	BorderPane sineElement;

	public GUISineWave() {
		sineElement = new BorderPane();
		sineElement.setMaxSize(200, 100);
		sineElement.setStyle("-fx-border-color: black");

		Label label = new Label("Sinewave Frequency");
		//warning - should be accessed in static way?
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

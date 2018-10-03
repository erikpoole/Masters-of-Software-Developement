
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

//add AudioClip to this instead of having it in play button
public class GUISineWave {

	public BorderPane sineElement;
	public SineWave sineWave;

	public GUISineWave() {
		sineElement = new BorderPane();
		sineElement.setMaxSize(200, 100);
		sineElement.setStyle("-fx-border-color: black");

		Label label = new Label("Sinewave Frequency");
		BorderPane.setAlignment(label, Pos.CENTER);

		sineWave = new SineWave(500);
		Slider slider = new Slider(200, 2000, 500);
		slider.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				sineWave.setFrequency((int) slider.getValue());
			}
		});

		sineElement.setTop(label);
		sineElement.setBottom(slider);

	}

	public AudioClip getAudioClip() {
		return sineWave.getAudioClip();
	}

}

//public static int inputFrequency = 500;
//
//public PlayButton() {
//	playButton = new Button();
//	playButton.setText("Play");
//	playButton.setOnAction(new EventHandler<ActionEvent>() {
//
//		@Override
//		public void handle(ActionEvent event) {
//			SineWave sineWave = new SineWave(inputFrequency);
//
//			// Ask Ben: Way to avoid try catch here?
//			try {
//				AudioClip.playSound(sineWave.getAudioClip());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

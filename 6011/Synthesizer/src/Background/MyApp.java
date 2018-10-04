package Background;

import Backend.AudioClip;
import Widgets.AdjustVolumeWidget;
import Widgets.SineWaveWidget;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyApp extends Application {

	// make generic eventually
	private SineWaveWidget finalSineWave;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Synthesizer");

		Pane backgroundPane = new Pane();
		SineWaveWidget sineWaveWidget = new SineWaveWidget();
		AdjustVolumeWidget adjustVolumeWidget = new AdjustVolumeWidget();
		backgroundPane.getChildren().add(sineWaveWidget.widget);
		backgroundPane.getChildren().add(adjustVolumeWidget.widget);

		GUIPlayButton playButton = new GUIPlayButton(this);
		HBox bottom = new HBox();
		bottom.getChildren().add(playButton.playButton);

		GUIButtonLibrary buttonLibrary = new GUIButtonLibrary(this);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(backgroundPane);
		borderPane.setBottom(bottom);
		borderPane.setRight(buttonLibrary.buttonLibrary);
		borderPane.setPadding(new Insets(10));

		Scene scene = new Scene(borderPane, 1000, 1000);

		// make generic evenutally
		finalSineWave = sineWaveWidget;
		stage.setScene(scene);
		stage.show();
	}

	public AudioClip getFinalAudioClip() {
		return finalSineWave.getAudioClip();
	}
}

package GUI;

import Backend.AudioClip;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyApp extends Application {

	//make generic eventually
	private SineWaveWidget finalSineWave;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Synthesizer");

		GUIPlayButton playButton = new GUIPlayButton(this);
		HBox bottom = new HBox();
		bottom.getChildren().add(playButton.playButton);

		Pane pane = new Pane();
		SineWaveWidget sineWaveWidget = new SineWaveWidget();
		AdjustVolumeWidget  adjustVolumeWidget = new AdjustVolumeWidget();
		pane.getChildren().add(sineWaveWidget.widget);
		pane.getChildren().add(adjustVolumeWidget.widget);

		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(bottom);
		borderPane.setPadding(new Insets(10));

		Scene scene = new Scene(borderPane, 500, 500);

		//make generic evenutally
		finalSineWave = sineWaveWidget;
		stage.setScene(scene);
		stage.show();
	}
	
	public AudioClip getFinalAudioClip() {
		return finalSineWave.getAudioClip();
	}
}


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GUIMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Synthesizer");

		PlayButton playButton = new PlayButton();
		HBox bottom = new HBox();
		bottom.getChildren().add(playButton.playButton);

		GUISineWave guiSineWave = new GUISineWave();

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(guiSineWave.sineElement);
		borderPane.setBottom(bottom);
		borderPane.setPadding(new Insets(10));

		Scene scene = new Scene(borderPane, 500, 500, Color.PURPLE);

		stage.setScene(scene);
		stage.show();
	}

}

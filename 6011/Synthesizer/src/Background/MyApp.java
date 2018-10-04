package Background;

import java.util.ArrayList;

import Backend.AudioClip;
import Widgets.AbSourceWidget;
import Widgets.SineWaveWidget;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyApp extends Application {

	// make generic eventually
	private SineWaveWidget finalSineWave;

	public ArrayList<AbSourceWidget> widgetlist = new ArrayList<AbSourceWidget>();

	private double originalX, originalY;
	private double translateX, translateY;

	public Pane backgroundPane = new Pane();
	public Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Synthesizer");

		GUIPlayButton playButton = new GUIPlayButton(this);
		HBox bottom = new HBox();
		bottom.getChildren().add(playButton.playButton);

		GUIButtonLibrary buttonLibrary = new GUIButtonLibrary(this);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(backgroundPane);
		borderPane.setBottom(bottom);
		borderPane.setRight(buttonLibrary.buttonLibrary);
		borderPane.setPadding(new Insets(10));

		scene = new Scene(borderPane, 1000, 1000);

		stage.setScene(scene);
		stage.show();

		// Smoother movement made possible with code from
		// java-buddy.blogspot/2013/07/javafx-drag-and-move-something.html
		// probably a better way to update than OnMouseMoved
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				for (AbSourceWidget specificWidget : widgetlist) {
					specificWidget.widget.setOnMousePressed(new EventHandler<MouseEvent>() {

						public void handle(MouseEvent event) {
							if (specificWidget.outputJack.isPressed()) {
								//specificWidget.cord = new Line(event.getX(), event.getY(), event.getX(), event.getY());

							} else {
								originalX = event.getSceneX();
								originalY = event.getSceneY();
								translateX = specificWidget.widget.getTranslateX();
								translateY = specificWidget.widget.getTranslateY();
							}
						}
					});

					specificWidget.widget.setOnMouseDragged(new EventHandler<MouseEvent>() {

						public void handle(MouseEvent event) {
							if (specificWidget.outputJack.isPressed()) {
								specificWidget.cord.setEndX(event.getX());
								specificWidget.cord.setEndY(event.getY());
							} else {
								specificWidget.widget.setTranslateX(event.getSceneX() - originalX + translateX);
								specificWidget.widget.setTranslateY(event.getSceneY() - originalY + translateY);
								specificWidget.cord.setTranslateX(event.getSceneX() - originalX + translateX);
								specificWidget.cord.setTranslateY(event.getSceneY() - originalY + translateY);
							}
						}
					});

				}

			}

		});

	}

	// make generic evenutally
	public AudioClip getFinalAudioClip() {
		SineWaveWidget sineWaveWidget = new SineWaveWidget();
		finalSineWave = sineWaveWidget;
		return finalSineWave.getAudioClip();
	}
}

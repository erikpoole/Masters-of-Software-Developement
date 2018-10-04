package Background;

import java.util.ArrayList;

import Backend.AudioClip;
import Backend.Source;
import Widgets.AbFilterWidget;
import Widgets.AbSourceWidget;
import Widgets.SineWaveWidget;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import junit.framework.Test;

public class MyApp extends Application {

	// make generic eventually
	private SineWaveWidget finalSineWave;

	public ArrayList<AbSourceWidget> sourceList = new ArrayList<AbSourceWidget>();
	public ArrayList<AbFilterWidget> targestList = new ArrayList<AbFilterWidget>();

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
				for (AbSourceWidget sourceWidget : sourceList) {
					sourceWidget.widget.setOnMousePressed(new EventHandler<MouseEvent>() {

						public void handle(MouseEvent event) {
							if (sourceWidget.outputJack.isPressed()) {
								Point2D jackPosition = sourceWidget.outputJack.localToParent(
										sourceWidget.outputJack.getTranslateX(),
										sourceWidget.outputJack.getTranslateY());
								sourceWidget.cord.setStartX(jackPosition.getX());
								sourceWidget.cord.setStartY(jackPosition.getY());
								sourceWidget.cord.setEndX(jackPosition.getX());
								sourceWidget.cord.setEndY(jackPosition.getY());

							} else {
								originalX = event.getSceneX();
								originalY = event.getSceneY();
								translateX = sourceWidget.widget.getTranslateX();
								translateY = sourceWidget.widget.getTranslateY();
							}
						}
					});

					sourceWidget.widget.setOnMouseDragged(new EventHandler<MouseEvent>() {

						public void handle(MouseEvent event) {
							if (sourceWidget.outputJack.isPressed()) {
								sourceWidget.cord.setEndX(event.getX());
								sourceWidget.cord.setEndY(event.getY());
							} else {
								sourceWidget.widget.setTranslateX(event.getSceneX() - originalX + translateX);
								sourceWidget.widget.setTranslateY(event.getSceneY() - originalY + translateY);
								sourceWidget.cord.setTranslateX(event.getSceneX() - originalX + translateX);
								sourceWidget.cord.setTranslateY(event.getSceneY() - originalY + translateY);
							}
						}
					});

					for (AbFilterWidget targetWidget : targestList) {
						sourceWidget.widget.setOnMouseReleased((e) -> {
							Point2D cordPosition = sourceWidget.cord.localToScene(sourceWidget.cord.getEndX(),
									sourceWidget.cord.getEndY());
							Point2D cordPositionLocal = targetWidget.inputJack.sceneToLocal(cordPosition);

							if (targetWidget.inputJack.contains(cordPositionLocal)) {
								// System.out.println("Connected!");
								
							}

						});
					}
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

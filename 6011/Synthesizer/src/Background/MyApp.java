package Background;

import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import Widgets.AbFilterWidget;
import Widgets.AbSourceWidget;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyApp extends Application {

	public ArrayList<AbSourceWidget> sourceList = new ArrayList<AbSourceWidget>();
	public ArrayList<AbFilterWidget> targestList = new ArrayList<AbFilterWidget>();
	public ArrayList<GUICable> cableList = new ArrayList<GUICable>();

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

		HBox bottom = new HBox();
		GUIPlayButton playButton = new GUIPlayButton(this);
		bottom.getChildren().add(playButton.playButton);

		GridPane right = new GridPane();
		right.setVgap(300);
		GUIButtonLibrary buttonLibrary = new GUIButtonLibrary(this);
		GUISpeaker guiSpeaker = new GUISpeaker(this);
		right.add(buttonLibrary.buttonLibrary, 0, 0);
		right.add(guiSpeaker.speaker, 0, 1);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(backgroundPane);
		borderPane.setBottom(bottom);
		borderPane.setRight(right);
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
								boolean hasCable = false;
								for (GUICable cable : cableList) {
									if (sourceWidget.equals(cable.sourceWidget)) {
										hasCable = true;
									}
								}
								if (!hasCable) {
									// -10 accounts for padding on scene
									Point2D jackPosition = sourceWidget.outputJack.localToScene(
											sourceWidget.outputJack.getTranslateX() - 10,
											sourceWidget.outputJack.getTranslateY() - 10);
									GUICable guiCable = new GUICable(sourceWidget, jackPosition);
									cableList.add(guiCable);
									backgroundPane.getChildren().add(guiCable.line);
								}
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

								for (GUICable cable : cableList) {
									if (sourceWidget.equals(cable.sourceWidget)) {
										cable.line.setEndX(event.getSceneX());
										cable.line.setEndY(event.getSceneY());
									}
								}

							} else {
								sourceWidget.widget.setTranslateX(event.getSceneX() - originalX + translateX);
								sourceWidget.widget.setTranslateY(event.getSceneY() - originalY + translateY);

								for (GUICable cable : cableList) {
									if (sourceWidget.equals(cable.sourceWidget)) {
										// -10 accounts for padding on scene
										Point2D jackPosition = sourceWidget.outputJack.localToScene(
												sourceWidget.outputJack.getTranslateX() - 10,
												sourceWidget.outputJack.getTranslateY() - 10);
										cable.line.setStartX(jackPosition.getX());
										cable.line.setStartY(jackPosition.getY());
									}
								}

							}
						}
					});

					sourceWidget.widget.setOnMouseReleased((e) -> {
						for (AbFilterWidget targetWidget : targestList) {
							for (GUICable cable : cableList) {
								if (sourceWidget.equals(cable.sourceWidget)) {
									Point2D cordPosition = cable.line.localToScene(cable.line.getEndX(),
											cable.line.getEndY());
									Point2D cordPositionLocalFilter = targetWidget.inputJack.sceneToLocal(cordPosition);
									Point2D cordPositionLocalSpeaker = guiSpeaker.speaker.sceneToLocal(cordPosition);

									if (targetWidget.inputJack.contains(cordPositionLocalFilter)) {
										cable.filterWidget = targetWidget;
										cable.hasOutput = true;
										try {
											targetWidget.getFilter().connectInput(sourceWidget.getSource());
										} catch (LineUnavailableException e1) {
											e1.printStackTrace();
										}
									} else if (guiSpeaker.speaker.contains(cordPositionLocalSpeaker)) {
										cable.hasOutput = true;
										GUISpeaker.source = sourceWidget.getSource();
									} else {
										cable.hasOutput = false;
									}
								}
							}

						}
						for (int i = 0; i < cableList.size(); i++) {
							if (cableList.get(i).hasOutput == false) {
								System.out.println("Should Delete");
								cableList.get(i).filterWidget.source = null;
								backgroundPane.getChildren().remove(cableList.get(i).line);
								cableList.remove(i);
								i++;
								System.out.println(cableList.size());

							}
						}

					});
				}

			}

		});

	}

}

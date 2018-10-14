package Widgets;

import Backend.AudioClip;
import Backend.Source;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class AbSourceWidget {

	public BorderPane widget;
	public Button playButton;

	public Source source;

	public Circle outputJack;

	protected AbSourceWidget() {
		widget = new BorderPane();
		widget.setPrefSize(200, 120);
		widget.setStyle("-fx-border-color: black");
		widget.setPadding(new Insets(10));

		outputJack = new Circle(20);
		outputJack.setFill(Color.GREEN);
		widget.setRight(outputJack);
		BorderPane.setAlignment(outputJack, Pos.CENTER);

		playButton = new Button("Play");
		widget.setBottom(playButton);
		BorderPane.setAlignment(playButton, Pos.CENTER);

		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					AudioClip.playSound(source.getAudioClip());
				} catch (NullPointerException e) {
					System.out.println("No Audio Source");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public abstract Source getSource();

}

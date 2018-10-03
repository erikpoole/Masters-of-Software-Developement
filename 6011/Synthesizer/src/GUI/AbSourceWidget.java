package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class AbSourceWidget {

	protected BorderPane widget;

	protected AbSourceWidget() {
		widget = new BorderPane();
		widget.setMaxSize(200, 100);
		widget.setStyle("-fx-border-color: black");
		
		Circle outputJack = new Circle(20);
		outputJack.setFill(Color.GREEN);
		widget.setRight(outputJack);
		BorderPane.setAlignment(outputJack, Pos.CENTER);
		
		widget.setOnMouseDragged(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				widget.setTranslateX(event.getSceneX()-70);
				widget.setTranslateY(event.getSceneY()-50);
			}

		});
		
	}

}
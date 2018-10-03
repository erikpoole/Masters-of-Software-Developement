package GUI;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class AbFilterWidget extends AbSourceWidget {

	protected AbFilterWidget() {
		super();
		
		Circle inputJack = new Circle(20);
		inputJack.setFill(Color.RED);
		widget.setLeft(inputJack);
		BorderPane.setAlignment(inputJack, Pos.CENTER);
		
	}

}
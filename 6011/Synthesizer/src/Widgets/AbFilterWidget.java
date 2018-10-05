package Widgets;

import Backend.Filter;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class AbFilterWidget extends AbSourceWidget {

	public Circle inputJack;

	protected Filter filter;

	protected AbFilterWidget() {
		super();

		inputJack = new Circle(20);
		inputJack.setFill(Color.RED);
		widget.setLeft(inputJack);
		BorderPane.setAlignment(inputJack, Pos.CENTER);

	}

	public abstract Filter getFilter();
}
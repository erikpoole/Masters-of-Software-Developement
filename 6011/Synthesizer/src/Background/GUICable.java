package Background;

import Widgets.AbFilterWidget;
import Widgets.AbSourceWidget;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class GUICable {

	public Line line = new Line();
	public AbSourceWidget sourceWidget;
	public AbFilterWidget filterWidget;

	boolean hasOutput;

	public GUICable(AbSourceWidget widget, Point2D location) {

		hasOutput = false;

		sourceWidget = widget;

		line.setStartX(location.getX());
		line.setStartY(location.getY());
		line.setEndX(location.getX());
		line.setEndY(location.getY());

	}

}

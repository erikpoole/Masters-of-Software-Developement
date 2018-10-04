package Widgets;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public abstract class AbSourceWidget {

	public BorderPane widget;
	public Line cord;

	public Circle outputJack;

//	private double originalX, originalY;
//	private double translateX, translateY;

	protected AbSourceWidget() {
		widget = new BorderPane();
		widget.setPrefSize(200, 100);
		widget.setStyle("-fx-border-color: black");
		widget.setPadding(new Insets(10));

		outputJack = new Circle(20);
		outputJack.setFill(Color.GREEN);
		widget.setRight(outputJack);
		BorderPane.setAlignment(outputJack, Pos.CENTER);

		cord = new Line(outputJack.getCenterX() + 170, outputJack.getCenterY() + 50, outputJack.getCenterX() + 170,
				outputJack.getCenterY() + 50);
		// System.out.println(outputJack.parentToLocal(outputJack.getCenterX(),
		// outputJack.getCenterY()));
		// System.out.println(outputJack.getTranslateX());
		// System.out.println(outputJack.getCenterX() + " " + outputJack.getCenterY());

//		// made smoother with code from
//		// java-buddy.blogspot/2013/07/javafx-drag-and-move-something.html
//		widget.setOnMousePressed(new EventHandler<MouseEvent>() {
//
//			public void handle(MouseEvent event) {
//				if (outputJack.isPressed()) {
//					cord = new Line(event.getX(),event.getY(),event.getX(),event.getY());
//					
//				} else {
//					originalX = event.getSceneX();
//					originalY = event.getSceneY();
//					translateX = widget.getTranslateX();
//					translateY = widget.getTranslateY();
//				}
//			}
//
//		});
//
//		widget.setOnMouseDragged(new EventHandler<MouseEvent>() {
//
//			public void handle(MouseEvent event) {
//				if (outputJack.isPressed()) {
//					cord.setEndX(event.getX());
//					cord.setEndY(event.getY());
//				} else {
//					widget.setTranslateX(event.getSceneX() - originalX + translateX);
//					widget.setTranslateY(event.getSceneY() - originalY + translateY);
//				}
//
//			}
//
//		});

	}

}
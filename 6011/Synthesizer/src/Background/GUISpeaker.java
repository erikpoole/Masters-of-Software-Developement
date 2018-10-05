package Background;

import Backend.Source;
import javafx.scene.shape.Circle;

public class GUISpeaker {

	public Circle speaker = new Circle(40);
	public static Source source;

	public GUISpeaker(MyApp guiMain) {
		speaker.setFill(javafx.scene.paint.Color.MEDIUMPURPLE);

	}
}
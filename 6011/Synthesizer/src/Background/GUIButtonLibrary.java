package Background;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GUIButtonLibrary {
	
	public VBox buttonLibrary;

	public GUIButtonLibrary(MyApp guiMain) {
		buttonLibrary = new VBox();
		Button sineWaveButton = new Button("Sine Wave");
		Button adjustVolButton = new Button("Adjust Volume");
		
		sineWaveButton.setPrefSize(150, 8);
		adjustVolButton.setPrefSize(150, 8);
		buttonLibrary.getChildren().add(sineWaveButton);
		buttonLibrary.getChildren().add(adjustVolButton);
	}
	

}

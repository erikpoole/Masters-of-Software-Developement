package Background;

import Widgets.AdjustVolumeWidget;
import Widgets.CombineClipWidget;
import Widgets.SineWaveWidget;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GUIButtonLibrary {

	public VBox buttonLibrary;

	public GUIButtonLibrary(MyApp guiMain) {
		buttonLibrary = new VBox();
		Button sineWaveButton = new Button("Sine Wave");
		Button adjustVolButton = new Button("Adjust Volume");
		Button combineClipsButton = new Button("Combine Clips");

		sineWaveButton.setPrefSize(150, 8);
		adjustVolButton.setPrefSize(150, 8);
		combineClipsButton.setPrefSize(150, 8);
		buttonLibrary.getChildren().add(sineWaveButton);
		buttonLibrary.getChildren().add(adjustVolButton);
		buttonLibrary.getChildren().add(combineClipsButton);

		sineWaveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SineWaveWidget sineWaveWidget = new SineWaveWidget();
				guiMain.sourceList.add(sineWaveWidget);
				guiMain.backgroundPane.getChildren().add(sineWaveWidget.widget);
				guiMain.backgroundPane.getChildren().add(sineWaveWidget.cord);

			}
		});

		adjustVolButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AdjustVolumeWidget adjustVolumeWidget = new AdjustVolumeWidget();
				guiMain.sourceList.add(adjustVolumeWidget);
				guiMain.targestList.add(adjustVolumeWidget);
				guiMain.backgroundPane.getChildren().add(adjustVolumeWidget.widget);
				guiMain.backgroundPane.getChildren().add(adjustVolumeWidget.cord);

			}
		});
		
		combineClipsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CombineClipWidget combineClipWidget = new CombineClipWidget();
				guiMain.sourceList.add(combineClipWidget);
				guiMain.targestList.add(combineClipWidget);
				guiMain.backgroundPane.getChildren().add(combineClipWidget.widget);
				guiMain.backgroundPane.getChildren().add(combineClipWidget.cord);

			}
		});

	}

}

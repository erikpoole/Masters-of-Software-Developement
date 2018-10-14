package Background;

import Widgets.AbSourceWidget;
import Widgets.AdjustVolumeWidget;
import Widgets.CombineClipWidget;
import Widgets.SineWaveWidget;
import Widgets.SquareWaveWidget;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GUIButtonLibrary {

	public VBox buttonLibrary = new VBox();

	public GUIButtonLibrary(MyApp guiMain) {

		Button sineWaveButton = CreateButton("Sine Wave");
		Button squareWaveButton = CreateButton("Square Wave");
		Button adjustVolumeButton = CreateButton("Adjust Volume");
		Button combineClipsButton = CreateButton("Combine Clips");

		sineWaveButton.setOnAction((e) -> {

			AbSourceWidget sineWaveWidget = new SineWaveWidget();
			guiMain.sourceList.add(sineWaveWidget);
			guiMain.backgroundPane.getChildren().add(sineWaveWidget.widget);
			//guiMain.backgroundPane.getChildren().add(sineWaveWidget.cord);
		});

		squareWaveButton.setOnAction((e) -> {

			SquareWaveWidget squareWaveWidget = new SquareWaveWidget();
			guiMain.sourceList.add(squareWaveWidget);
			guiMain.backgroundPane.getChildren().add(squareWaveWidget.widget);
			//guiMain.backgroundPane.getChildren().add(squareWaveWidget.cord);
		});

		adjustVolumeButton.setOnAction((e) -> {

			AdjustVolumeWidget adjustVolumeWidget = new AdjustVolumeWidget();
			guiMain.sourceList.add(adjustVolumeWidget);
			guiMain.targestList.add(adjustVolumeWidget);
			guiMain.backgroundPane.getChildren().add(adjustVolumeWidget.widget);
			//guiMain.backgroundPane.getChildren().add(adjustVolumeWidget.cord);
		});

		combineClipsButton.setOnAction((e) -> {

			CombineClipWidget combineClipWidget = new CombineClipWidget();
			guiMain.sourceList.add(combineClipWidget);
			guiMain.targestList.add(combineClipWidget);
			guiMain.backgroundPane.getChildren().add(combineClipWidget.widget);
			//guiMain.backgroundPane.getChildren().add(combineClipWidget.cord);
		});

		squareWaveButton.setOnAction((e) -> {
			SquareWaveWidget squareWaveWidget = new SquareWaveWidget();
			guiMain.sourceList.add(squareWaveWidget);
			guiMain.backgroundPane.getChildren().add(squareWaveWidget.widget);
			//guiMain.backgroundPane.getChildren().add(squareWaveWidget.cord);
		});

	}

	private Button CreateButton(String name) {
		Button newButton = new Button(name);
		newButton.setPrefSize(150, 8);
		buttonLibrary.getChildren().add(newButton);

		return newButton;
	}

}

package Background;

import Widgets.AdjustVolumeWidget;
import Widgets.SineWaveWidget;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class GUISpeaker {
	
	public Circle speaker = new Circle(40);

	public GUISpeaker(MyApp guiMain) {
		
//		Button sineWaveButton = new Button("Sine Wave");
//		Button adjustVolButton = new Button("Adjust Volume");
//
//		sineWaveButton.setPrefSize(150, 8);
//		adjustVolButton.setPrefSize(150, 8);
//		buttonLibrary.getChildren().add(sineWaveButton);
//		buttonLibrary.getChildren().add(adjustVolButton);
//		
//		sineWaveButton.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				SineWaveWidget sineWaveWidget = new SineWaveWidget();
//				guiMain.sourceList.add(sineWaveWidget);
//				guiMain.backgroundPane.getChildren().add(sineWaveWidget.widget);
//				guiMain.backgroundPane.getChildren().add(sineWaveWidget.cord);
//				
//			}
//		});
//		
//		adjustVolButton.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				AdjustVolumeWidget adjustVolumeWidget = new AdjustVolumeWidget();
//				guiMain.sourceList.add(adjustVolumeWidget);
//				guiMain.targestList.add(adjustVolumeWidget);
//				guiMain.backgroundPane.getChildren().add(adjustVolumeWidget.widget);
//				guiMain.backgroundPane.getChildren().add(adjustVolumeWidget.cord);
//				
//			}
//		});
//		
		

	}

}


//package Background;
//
//import Backend.AudioClip;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//
//public class GUIPlayButton {
//
//	public Button playButton;
//
//	public GUIPlayButton(MyApp guiMain) {
//		playButton = new Button();
//		playButton.setText("Play");
//		playButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//
//				try {
//					AudioClip.playSound(guiMain.getFinalAudioClip());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
//		});
//
//	}
//
//}

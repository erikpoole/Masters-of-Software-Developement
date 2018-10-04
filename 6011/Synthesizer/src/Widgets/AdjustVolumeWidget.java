package Widgets;

import Backend.AdjustVolume;
import Backend.AudioClip;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class AdjustVolumeWidget extends AbFilterWidget {

	public AdjustVolume adjustVolume;

	public AdjustVolumeWidget() {
		super();
		
		Label label = new Label("AdjustVolumeFilter");
		widget.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

		adjustVolume = new AdjustVolume(500);
		Slider slider = new Slider(0, 1, 1);
		widget.setCenter(slider);
		
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				adjustVolume.setScale(newValue.doubleValue());
			}
		});

	}

	public AudioClip getAudioClip() {
		return adjustVolume.getAudioClip();
	}

}

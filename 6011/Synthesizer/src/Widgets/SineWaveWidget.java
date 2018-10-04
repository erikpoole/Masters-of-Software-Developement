package Widgets;

import Backend.AudioClip;
import Backend.SineWave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class SineWaveWidget extends AbSourceWidget {

	public SineWave sineWave;

	public SineWaveWidget() {
		super();
		
		source = new SineWave(500);
		sineWave = (SineWave) source;
		
		Label label = new Label("Sine Wave");
		widget.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

		Slider slider = new Slider(200, 2000, 500);
		widget.setCenter(slider);
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				sineWave.setFrequency(newValue.doubleValue());
			}
		});
		
	}

	public AudioClip getAudioClip() {
		return sineWave.getAudioClip();
	}

}

package Widgets;

import Backend.Source;
import Backend.SquareWave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class SquareWaveWidget extends AbSourceWidget {

	public SquareWave squareWave;

	public SquareWaveWidget() {
		super();

		source = new SquareWave(500);
		squareWave = (SquareWave) source;

		Label label = new Label("Square Wave");
		widget.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

		Slider slider = new Slider(200, 2000, 500);
		widget.setCenter(slider);
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				squareWave.setFrequency(newValue.doubleValue());
			}
		});

	}

	@Override
	public Source getSource() {
		return source;
	}
	
}

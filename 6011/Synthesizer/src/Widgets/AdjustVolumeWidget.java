package Widgets;

import Backend.AdjustVolume;
import Backend.Filter;
import Backend.Source;
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

		filter = new AdjustVolume(.5);
		adjustVolume = (AdjustVolume) filter;
		source = filter;

		Label label = new Label("Adjust Volume");
		widget.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

		Slider slider = new Slider(0, 1, 1);
		widget.setCenter(slider);
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				adjustVolume.setScale(newValue.doubleValue());
			}
		});

	}

	@Override
	public Source getSource() {
		return source;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

}

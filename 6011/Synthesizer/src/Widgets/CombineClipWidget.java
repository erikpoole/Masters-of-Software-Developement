package Widgets;

import Backend.CombineClips;
import Backend.Filter;
import Backend.Source;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class CombineClipWidget extends AbFilterWidget {

	public CombineClips combineClips;

	public CombineClipWidget() {
		super();

		filter = new CombineClips();
		combineClips = (CombineClips) filter;
		source = filter;

		Label label = new Label("Combine Clips");
		widget.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

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

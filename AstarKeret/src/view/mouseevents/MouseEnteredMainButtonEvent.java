package view.mouseevents;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MouseEnteredMainButtonEvent implements EventHandler<Event> {
	//change main pane buttons
	@Override
	public void handle(Event event) {
		((Button)event.getSource()).setTextFill(Color.LIGHTYELLOW);
	}

}

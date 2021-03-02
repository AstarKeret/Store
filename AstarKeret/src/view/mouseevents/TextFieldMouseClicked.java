package view.mouseevents;



import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.root.ConsoleMsg;

public class TextFieldMouseClicked implements EventHandler<MouseEvent>{
	ConsoleMsg msg = ConsoleMsg.getMsg();
	
	@Override
	public void handle(MouseEvent tf) {
		((TextField)tf.getSource()).setText(" ");
		((TextField)tf.getSource()).setStyle("-fx-text-fill: black ;-fx-font-weight: bold");
		msg.setDefault();
	}

}

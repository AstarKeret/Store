package view.root.my;

import javafx.scene.control.TextField;
import view.mouseevents.TextFieldMouseClicked;

public class MyTextField extends TextField{
	public MyTextField(String promptText, double size) {
		setPromptText(promptText);
		setOnMouseClicked(new TextFieldMouseClicked());
		setMaxWidth(size);
	}
}

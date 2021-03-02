package view.root;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConsoleMsg extends Label{
	//singelton class
	//bottom of the main pane
	private static ConsoleMsg msg = new ConsoleMsg();

	private ConsoleMsg() {
		setAlignment(Pos.TOP_CENTER);
		setFont(new Font("david", 20));
	}
	
	public static ConsoleMsg getMsg() {
		return msg;
	}
	
	public void setDefault() {
		setText("Message Box");
		setTextFill(Color.BLACK);
	}

	public void setColor() {
		if(getText().charAt(ConsoleMsg.getMsg().getText().length() - 1) == ' ')
			setTextFill(Color.RED);	//failed
		else
			setTextFill(Color.BLUE);//succeeded
	}
	
	
}

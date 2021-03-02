package view.root.my;


import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MyLabel extends Label{
	public MyLabel(String txt, String font, double size, Color color) {
		setText(txt);
		setFont(new Font(font, size));
		setTextFill(color);
	}
	public void setTitelEffect() {
		setEffect(new DropShadow(BlurType.ONE_PASS_BOX ,Color.BLACK, 6 , 1 , 0 , 0));
	}
	
}

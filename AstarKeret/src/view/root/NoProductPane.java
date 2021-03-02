package view.root;

import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NoProductPane extends Label{
	private static NoProductPane pane = new NoProductPane();
	//pane that show in print screens only when the map is empty
	private NoProductPane() {
		setText("No products to show");
		setFont(new Font("Copperplate Gothic Bold", 28));
		setTextFill(Color.TEAL);
		setEffect(new DropShadow(BlurType.ONE_PASS_BOX ,Color.BLACK, 6 , 1 , 0 , 0));
	}
	
	public static NoProductPane getPane() {
		return pane;
	}
	
	public void setVisible() {
		setVisible(true);
	}
	
	public void setInvisible() {
		setVisible(false);		
	}
}

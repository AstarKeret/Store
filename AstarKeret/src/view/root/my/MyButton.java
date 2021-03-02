package view.root.my;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Font;

public class MyButton extends Button{
	public MyButton(String txt) {
		setText(txt);
		setMouseEnteredEvent();
		setMouseExitedEvent();
		setMouseClickedEvent();
		setButtonsStyle();
		}
	private void setButtonsStyle() {
		
		setStyle("-fx-font-weight: bold");
		setBackground(new Background(new BackgroundFill(LinearGradient.valueOf("#d6d6d6 50%, white 100%") , new CornerRadii(9), Insets.EMPTY)));
		setFont(new Font("Helvetica", 12));
		setTextFill(Color.SEAGREEN);
		setBorder(new Border(new BorderStroke(Color.DIMGREY,  BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(3))));
	}
	private void setMouseExitedEvent() {	
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {	
				setBackground(new Background(new BackgroundFill(LinearGradient.valueOf("#d6d6d6 50%, white 100%") , new CornerRadii(9), Insets.EMPTY)));
				setEffect(null);
			}
		});
	}
	
	private void setMouseEnteredEvent() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setBackground(new Background(new BackgroundFill(LinearGradient.valueOf("#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%"), new CornerRadii(9), Insets.EMPTY)));			
			}
		});
	}

	private void setMouseClickedEvent() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setEffect(new DropShadow(BlurType.ONE_PASS_BOX, Color.BLACK, 2, 1, 1, -2)); 
			}
		});
		
	}
}

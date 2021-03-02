package views.screen;

import command.Command;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.root.ConsoleMsg;
import view.root.my.MyButton;
import view.root.my.MyLabel;
import view.root.my.MyTextField;

public class SendPromotionsScreen extends BorderPane {
	private GridPane gpSecTop;
	private GridPane gpBottomCenter;
	private VBox vbCenter;
	private VBox vbTop;
	private Label lbTop; 
	private Label lbLine; 
	private Label lbDesc; 
	private Button bSend;
	private Button bClear;
	private TextField tfName;
	private TextField tfDesc;
	public SendPromotionsScreen(){
		initTop();
		initCenter();
		initBottom();
		initRoot();
	}

	private void initBottom() {
		bClear = new MyButton("Clear");
		bClear.setDisable(true);
		bClear.setOnAction(e->{
			bClear.setDisable(true);
			gpBottomCenter.setVisible(false);
			tfName.setText(" ");
			tfName.setPromptText("Required");
			tfDesc.setText(" ");
			tfDesc.setPromptText("Required");
		});
	}

	private void initCenter() {
		gpBottomCenter = new GridPane();
		gpBottomCenter.setHgap(25);
		gpBottomCenter.setVgap(25);
		gpBottomCenter.setAlignment(Pos.BOTTOM_CENTER);
		gpBottomCenter.setVisible(false);
		vbCenter = new VBox(gpSecTop, gpBottomCenter);
		vbCenter.setAlignment(Pos.CENTER);
	}

	private void initSecondeLine() {
		initSendLines();
		gpSecTop = new GridPane();
		gpSecTop.setAlignment(Pos.TOP_CENTER);
		gpSecTop.setHgap(10);
		gpSecTop.setVgap(20);
		gpSecTop.add(lbLine, 0, 0);
		gpSecTop.add(tfName, 1, 0);
		gpSecTop.add(lbDesc, 0, 1);
		gpSecTop.add(tfDesc, 1, 1);
		gpSecTop.add(bSend, 2, 1);
	}

	private void initSendLines() {
		lbLine = new MyLabel("Product Name:", "GungsuhChe", 18, Color.TEAL);
		lbDesc = new MyLabel("Describe:", "GungsuhChe", 18, Color.TEAL);
		tfName = new MyTextField("Required", 110);
		tfDesc = new MyTextField("Required", 110);
		bSend = new MyButton("Send");
		bSend.setOnAction(e->{
			if( tfName.getText().isBlank() || tfDesc.getText().isBlank()) {
				ConsoleMsg.getMsg().setText("Product Name and promotion describe are required ");
				ConsoleMsg.getMsg().setColor();
				return;
			}
			Command cmd = new SendPromotionCommand(gpBottomCenter);
			cmd.execute();		
			bClear.setDisable(false);
		});
	}
	

	private void initTop() {
		lbTop = new MyLabel("Send Promotions", "Copperplate Gothic Bold", 28, Color.SEAGREEN);
		((MyLabel) lbTop).setTitelEffect();
		initSecondeLine();
		vbTop = new VBox(lbTop, gpSecTop);
		vbTop.setAlignment(Pos.TOP_CENTER);

	}
	
	private void initRoot() {
		setTop(vbTop);
		setAlignment(getTop(), Pos.TOP_CENTER);
		setCenter(vbCenter);
		setBottom(bClear);
		setAlignment(getBottom(), Pos.BOTTOM_CENTER);
		setPadding(new Insets(15));
	}
}

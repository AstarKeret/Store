package views.screen.printproduct;

import command.PrintCommand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.root.my.MyButton;
import views.screen.printproduct.command.PrintAllProductCommand;
import views.screen.printproduct.command.PrintOneProductCommand;

public class PrintProductScreen  extends VBox{
	private HBox hbTop;
	private Button bAll;
	private Button bOne;
	private StackPane hbCenter;
	private VBox vbAll;
	private BorderPane bpOne;
	private PrintCommand cAll;
	private PrintCommand cOne;
	public PrintProductScreen(){
		initTop();
		initCenter();
		initRoot();
	}
	private void initRoot() {
		setSpacing(25);
		setPadding(new Insets(15));
		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(hbTop, hbCenter);
	}
	
	private void initCenter() {
		vbAll = new PrintAllProduct();
		cAll = new PrintAllProductCommand((PrintAllProduct)vbAll);
		bpOne = new PrintOneProduct();
		cOne = new PrintOneProductCommand((PrintOneProduct)bpOne);
		hbCenter = new StackPane(vbAll, bpOne);
	}
	
	private void initTop() {
		bAll = setButton("Print All Product", "all");
		bOne = setButton("Print One Product", "one");
	
		hbTop = new HBox(bAll, bOne);
		hbTop.setSpacing(120);
		hbTop.setAlignment(Pos.TOP_CENTER);
	}
	private Button setButton(String msg, String paneType) {
		Button btn = new MyButton(msg);
	
		btn.setOnAction(e->{
			if(paneType.equals("all")) {
				cAll.turnOn();
				cOne.turnOf();
				cAll.execute();
			}
			else {
				cAll.turnOf();
				cOne.turnOn();
				cOne.execute();
			}
		});
		return btn;
	}
}

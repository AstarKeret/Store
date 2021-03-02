package views.screen.printprofits;

import command.PrintCommand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.root.my.MyButton;
import views.screen.printprofits.command.PrintAllProfitsCommand;
import views.screen.printprofits.command.PrintTotalProfitsCommand;

public class PrintProfitsScreen extends VBox {
	private HBox hbTop;
	private Button bByProduct;
	private Button bTotal;
	private StackPane hbCenter;
	private VBox vbByProduct;
	private VBox bpTotal;
	private PrintCommand cByProduct;
	private PrintCommand cTotal;
	public PrintProfitsScreen(){
		initTop();
		initTotalPane();
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
		cTotal = new PrintTotalProfitsCommand(bpTotal);
		vbByProduct = new PrintAllProfits();
		cByProduct = new PrintAllProfitsCommand((PrintAllProfits)vbByProduct);
		hbCenter = new StackPane(vbByProduct, bpTotal);
	}
	
	private void initTotalPane() {
		bpTotal = new VBox();
		bpTotal.setSpacing(40);
		bpTotal.setAlignment(Pos.TOP_CENTER);
	}
	private void initTop() {
		bByProduct = setButton("Profits By Product", "By");
		bTotal = setButton("Total Profits", " ");
	
		hbTop = new HBox(bByProduct, bTotal);
		hbTop.setSpacing(120);
		hbTop.setAlignment(Pos.TOP_CENTER);
	}
	private Button setButton(String msg, String paneType) {
		Button btn = new MyButton(msg);
	
		btn.setOnAction(e->{
			if(paneType.equals("By")) {
				cByProduct.turnOn();
				cTotal.turnOf();
				cByProduct.execute();
			}
			else {
				cByProduct.turnOf();
				cTotal.turnOn();
				cTotal.execute();
			}
		});
		return btn;
	}
}
package views.screen.printprofits.command;

import command.PrintCommand;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.ConsoleMsg;
import view.root.SystemManagementViewable;
import view.root.my.MyLabel;

public class PrintTotalProfitsCommand implements PrintCommand, SystemManagementViewable{

	private Label title;
	private VBox hbRoot;
	public PrintTotalProfitsCommand(VBox hbRoot) {
		this.hbRoot = hbRoot;
	}
	@Override
	public void turnOf() {
		hbRoot.setVisible(false);
	}

	@Override
	public void turnOn() {
		hbRoot.setVisible(true);
	}

	@Override
	public void execute() {
		hbRoot.getChildren().clear();
		long sum = fireViewAskTotalProfits();
		if(sum == -1) {
			ConsoleMsg.getMsg().setText("No profits to show ");
			ConsoleMsg.getMsg().setColor();
			
			return;
		}
		title = new MyLabel("Store Total Profits",  "Copperplate Gothic Bold", 28, Color.SEAGREEN);
		((MyLabel) title).setTitelEffect();
		hbRoot.getChildren().addAll(title, new MyLabel(sum + " ", "Copperplate Gothic Bold", 22, Color.DARKSLATEGRAY));
	}

	private long  fireViewAskTotalProfits() {
		for(ViewListenable vl : AllListeners.getAllListeners())
			return vl.fireViewAskTotalProfits();
		
		return -1;
	}
	@Override
	public void registerListener(ViewListenable l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fireModelDeleteProduct(String msg) {
		// TODO Auto-generated method stub
		
	}
}

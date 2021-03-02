package views.screen.printproduct.command;

import command.Command;
import javafx.scene.layout.GridPane;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.SystemManagementViewable;

public class DeleteAllProductCommand implements Command , SystemManagementViewable{
	private GridPane gpRoot;
	public DeleteAllProductCommand(GridPane gpRows) {
		gpRoot = gpRows;
	}
	@Override
	public void execute() {
		fireViewDeleteAllProducts();
		gpRoot.getChildren().clear();;
	}
	private void fireViewDeleteAllProducts() {
		for(ViewListenable vl : AllListeners.getAllListeners())
			vl.fireViewDeleteAllProducts();
	}
	@Override
	public void registerListener(ViewListenable vl) {
		AllListeners.registerListener(vl);	
	}
	@Override
	public void fireModelDeleteProduct(String msg) {
		// TODO Auto-generated method stub
		
	}
}

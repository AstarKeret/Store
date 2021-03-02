package views.screen.printproduct.command;

import java.io.IOException;

import command.Command;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.ConsoleMsg;
import view.root.SystemManagementViewable;
import views.screen.printproduct.PrintOneProduct;

public class DeleteProductCommand implements Command , SystemManagementViewable{
	private  PrintOneProduct bpRoot;
	
	public DeleteProductCommand(PrintOneProduct borderPane) {
		this.bpRoot = borderPane;
	}
	@Override
	public void execute() {
		VBox vbCenter = ((VBox)bpRoot.getCenter());
		HBox temp = (HBox)vbCenter.getChildrenUnmodifiable().get(0);
		try {
			fireViewDeleteProduct(((TextField)temp.getChildrenUnmodifiable().get(1)).getText());
		} catch (IOException e) {
			ConsoleMsg.getMsg().setText("Failed deleting product ");
			ConsoleMsg.getMsg().setColor();
		}
	}

	private void fireViewDeleteProduct(String catalogNumber) throws IOException {
		for(ViewListenable vl : AllListeners.getAllListeners())
			vl.fireViewDeleteProduct(catalogNumber);
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

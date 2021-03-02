package views.screen.printproduct.command;

import command.Command;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import listeners.AllListeners;
import listeners.ViewListenable;
import model.Product;
import view.root.ConsoleMsg;
import view.root.SystemManagementViewable;
import view.root.my.MyProductGridPane;
import views.screen.printproduct.PrintOneProduct;

public class SearchProductCommand implements Command, SystemManagementViewable{
	private PrintOneProduct bpRoot;

	public SearchProductCommand(PrintOneProduct borderPane) {
		this.bpRoot = borderPane;
	}
	

	@Override
	public void execute() {
		VBox vbCenter = ((VBox)bpRoot.getCenter());
		HBox temp = (HBox)vbCenter.getChildrenUnmodifiable().get(0);
		String catalogNumber = ((TextField)temp.getChildrenUnmodifiable().get(1)).getText();
		MyProductGridPane mpGridPane = (MyProductGridPane)vbCenter.getChildrenUnmodifiable().get(1);
		Product result = null;
		try {
			result = fireViewSearchProduct(catalogNumber);
			if(result == null) {
				ConsoleMsg.getMsg().setText("Product Not Found ");
				ConsoleMsg.getMsg().setColor();
				return;
			}	
			mpGridPane.setProductDetails(catalogNumber, result);
			mpGridPane.setVisible(true);

		}catch (NullPointerException e) {	
			ConsoleMsg.getMsg().setText("Product Catalog Number is required ");
			ConsoleMsg.getMsg().setColor();
			}
	}

	
	private Product fireViewSearchProduct(String catalogNumber) {
		for(ViewListenable vl : AllListeners.getAllListeners())
			return vl.fireViewSearchProduct(catalogNumber);
		return null;
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

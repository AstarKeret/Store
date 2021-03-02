package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import listeners.SystemManagementListenable;
import listeners.ViewListenable;
import model.Product;
import model.SystemManagement;
import view.root.SystemManagementViewable;

public class Controller  implements SystemManagementListenable, ViewListenable{
	private SystemManagement theModel;
	private SystemManagementViewable theView;
	public Controller(SystemManagement m, SystemManagementViewable v) {
		theModel = m; 	//system manager
		theView = v;	//the view root
		theModel.registerListener(this);
		theView.registerListener(this);
		

	}

	@Override
	public String fireViewAddProduct(String productName, String catalogNumber, String cost, String price, String customerName, String phoneNumberBegin, String phoneNumberEnd, boolean isPromotion) throws FileNotFoundException, IOException {
		return theModel.fireViewAddProduct(productName, catalogNumber, cost, price, customerName, phoneNumberBegin, phoneNumberEnd, isPromotion);
	}

	@Override
	public String fireViewSetMap(String value) {
		return theModel.fireViewSetMap(value);
	}

	@Override
	public Map<String, Product> fireViewAskAllProduct() {
		return theModel.getProductsList();
	}

	@Override
	public Product fireViewSearchProduct(String catalogNumber) {
		return theModel.fireViewSearchProduct(catalogNumber);
	}

	@Override
	public void fireViewDeleteProduct(String catalogNumber) throws IOException {
		theModel.fireViewDeleteProduct(catalogNumber);
	}

	@Override
	public void fireViewDeleteAllProducts() {
		theModel.fireViewDeleteAllProducts();
	}

	@Override
	public Map<String, Integer> fireViewAskAllProductsProfits() {
		return theModel.fireViewAskAllProductsProfits();
	}

	@Override
	public long fireViewAskTotalProfits() {
		return theModel.fireViewAskTotalProfits();
	}

	@Override
	public int fireViewAskMapSize() {
		return theModel.fireViewAskMapSize();
	}

	@Override
	public void fireViewSendPromotion() {
		theModel.fireViewSendPromotion();
	}

	@Override
	public void fireModelDeleteProduct(String msg) {
		theView.fireModelDeleteProduct(msg);
	}

	@Override
	public void fireViewUndoMap() throws IOException {
		theModel.fireViewUndoMap();
		
	}
}


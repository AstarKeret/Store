package listeners;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Product;

public interface SystemManagementListenable {
	//the manager interface
	String fireViewAddProduct(String productName, String catalogNumber, String cost, String price, String customerName, String phoneNumber, String phoneNumberEnd, boolean isPromotion) throws FileNotFoundException, IOException;
	String fireViewSetMap(String value);
	Product fireViewSearchProduct(String catalogNumber);
	void fireModelDeleteProduct(String msg);
	void fireViewDeleteProduct(String catalogNumber) throws IOException;
	void fireViewDeleteAllProducts();
}

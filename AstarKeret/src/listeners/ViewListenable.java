package listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import model.Product;

public interface ViewListenable {
	//interface for all the view classes that dependents or uses sata from the SystemManagment class 
	String fireViewAddProduct(String productName, String catalogNumber, String cost, String price, String customerName, String phoneNumberBegin, String phoneNumberEnd, boolean isPromotion) throws FileNotFoundException, IOException;	//add product to the products map
	String fireViewSetMap(String value);							
	Map<String, Product> fireViewAskAllProduct();					
	Product fireViewSearchProduct(String catalogNumber);			
	void fireViewDeleteProduct(String catalogNumber) throws IOException;
	void fireViewDeleteAllProducts();
	Map<String, Integer> fireViewAskAllProductsProfits();
	long fireViewAskTotalProfits();
	int fireViewAskMapSize();
	void fireViewSendPromotion();
	void fireViewUndoMap() throws IOException;
	
}

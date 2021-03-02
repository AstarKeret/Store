package view.root.my;

import java.util.Map.Entry;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Product;

public class MyProductGridPane extends GridPane{
	private Label lbCatalogNum;
	private Label lbName;
	private Label lbCost;
	private Label lbPrice;
	private Label lbCustomerName;
	private Label lbHeadCatalogNum;
	private Label lbHeadName;
	private Label lbHeadCost;
	private Label lbHeadPrice;
	private Label lbHeadCustomerName;
	private Label lbHeadCustomerPhone;
	private Label lbCustomerPhone;
	private Label lbHeadPromotion;
	private Label lbPromotion;
	public MyProductGridPane() {
		initHead();
		initResult();
		initRoot();
	}

	public MyProductGridPane(Entry<String, Product> itr) {
		initHead();
		initResult(itr.getKey(), itr.getValue());
		addToRoot();
		initRoot();
		setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1.5))));
	}

	private void initRoot() {
		setHgap(10);
		setHgap(20);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
	}

	private void initHead() {
		lbHeadCatalogNum = new MyLabel("Catalog Number:", "David", 18, Color.DARKSLATEGRAY);
		lbHeadName = new MyLabel("Product Name:", "David", 18, Color.DARKSLATEGRAY);
		lbHeadCost = new MyLabel("Cost:", "David", 18, Color.DARKSLATEGRAY);	
		lbHeadPrice = new MyLabel("Cost:", "David", 18, Color.DARKSLATEGRAY);	
		lbHeadCustomerName = new MyLabel("Customer Name:", "David", 18, Color.DARKSLATEGRAY);
		lbHeadCustomerPhone = new MyLabel("Phone Number:", "David", 18, Color.DARKSLATEGRAY);
		lbHeadPromotion = new MyLabel("Want Promotion:" , "David", 18, Color.DARKSLATEGRAY);
		
	}
	private void initResult(String catalogNum, Product product) {
		lbCatalogNum = new  MyLabel(catalogNum, "David", 15, Color.BLACK);
		lbName = new MyLabel(product.getName(), "David", 15, Color.BLACK);
		lbCost = new MyLabel(product.getStoreCost() + "", "David", 15, Color.BLACK);
		lbPrice = new MyLabel(product.getCustomerPrice() + "", "David", 15, Color.BLACK);
		lbCustomerName = new MyLabel(product.getCustomer().getName(), "David", 15, Color.BLACK);
		String phoneNumber = phoneNumberFromLongToString(product.getCustomer().getPhoneNumber());
		lbCustomerPhone = new MyLabel(phoneNumber, "David", 15, Color.BLACK);
		lbPromotion = new MyLabel(product.getCustomer().getGetPromotion() == true ? "Yes" : "No" , "David", 15, Color.BLACK);
	}
	

	private String phoneNumberFromLongToString(long phoneNumber) {
		if(phoneNumber == 0)
			return " ";
		String strPhoneNumber = "05" + Long.toString(phoneNumber).charAt(1) + "-";
		strPhoneNumber = strPhoneNumber + Long.toString(phoneNumber).substring(2);
		return strPhoneNumber;
	}

	private void initResult() {
		lbCatalogNum = new  MyLabel("", "David", 15, Color.BLACK);
		lbName = new MyLabel("", "David", 15, Color.BLACK);
		lbCost = new MyLabel("", "David", 15, Color.BLACK);
		lbPrice = new MyLabel("", "David", 15, Color.BLACK);
		lbCustomerName = new MyLabel("", "David", 15, Color.BLACK);
		lbCustomerPhone = new MyLabel("", "David", 15, Color.BLACK);
		lbPromotion = new MyLabel("" , "David", 15, Color.BLACK);
	}

	public void setProductDetails(String catalogNum, Product product) {
		lbCatalogNum.setText(catalogNum);
		lbName.setText(product.getName());
		lbCost.setText(product.getStoreCost() + "");
		lbPrice.setText(product.getCustomerPrice() + "");
		lbCustomerName.setText(product.getCustomer().getName());
		lbCustomerPhone.setText(product.getCustomer().getPhoneNumber() + "");
		lbPromotion.setText(product.getCustomer().getGetPromotion() == true ? "Yes" : "No");
		addToRoot();
		setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1.5))));
	}

	private void addToRoot() {
		add(lbHeadCatalogNum, 0, 0);
		add(lbCatalogNum, 1, 0);
		add(lbHeadName, 0, 1);
		add(lbName, 1, 1);	
		add(lbHeadCost, 0, 2);
		add(lbCost, 1, 2);
		add(lbHeadPrice, 0, 3);
		add(lbPrice, 1, 3);		
		add(lbHeadCustomerName, 0, 4);
		add(lbCustomerName, 1, 4);
		add(lbHeadCustomerPhone, 0, 5);
		add(lbCustomerPhone, 1, 5);
		add(lbHeadPromotion, 0, 6);
		add(lbPromotion, 1, 6);
		//lbPromotion
	}

}



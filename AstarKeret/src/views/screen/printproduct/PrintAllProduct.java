package views.screen.printproduct;

import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import command.Command;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import listeners.AllListeners;
import listeners.ViewListenable;
import model.Product;
import view.root.ConsoleMsg;
import view.root.SystemManagementViewable;
import view.root.my.MyButton;
import view.root.my.MyLabel;
import view.root.my.MyProductGridPane;
import views.screen.printproduct.command.DeleteAllProductCommand;

public class PrintAllProduct extends VBox implements SystemManagementViewable{
	public final int PRODUCT_IN_ROW = 2;
	private Map<String, Product> allProduct;
	private GridPane gpRows;
	private Label lbTop; 
	private ScrollPane spCenter;
	private Button bDelete;
	public PrintAllProduct() {		
		lbTop = new MyLabel("Products sold", "Copperplate Gothic Bold", 28, Color.SEAGREEN);
		((MyLabel) lbTop).setTitelEffect();
		lbTop.setAlignment(Pos.TOP_CENTER);
		gpRows = new GridPane();
		setScrollPane();
		initBottom();
		initRoot();
	}

	public void setRows() {
		int row = 0, col = 0;
		allProduct = fireViewAskAllProduct();
		if(allProduct == null){
			ConsoleMsg.getMsg().setText("No products to show ");
			ConsoleMsg.getMsg().setColor();
			return;
		}
		Iterator<Map.Entry<String, Product>> itr = allProduct.entrySet().iterator(); 	
		while(itr.hasNext()) {
			if(col == PRODUCT_IN_ROW) {
				row++;
				col = 0;
			}
			gpRows.add(new MyProductGridPane(itr.next()), col++, row);
		}
		gpRows.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
		gpRows.setAlignment(Pos.CENTER);
		gpRows.setHgap(45);
		gpRows.setVgap(15);
		gpRows.setPrefHeight(300);
	}

	private void setScrollPane() {
		spCenter = new ScrollPane(gpRows);
		spCenter.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
		spCenter.setPrefSize(gpRows.getPrefWidth(), gpRows.getPrefHeight());
		spCenter.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		spCenter.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		spCenter.setFitToWidth(true);

		
	}

	public void cleanRows() {
		gpRows.getChildren().clear();
	}
	
	private void initBottom() {
		bDelete = new MyButton("Delete All");
		bDelete.setOnAction(e->{
		int areYouSure= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all products sales?", "Warning", JOptionPane.YES_NO_OPTION);
		Command cmd = new DeleteAllProductCommand(gpRows);
		if(areYouSure == 0)
			cmd.execute();
		});
	}
	
	private void initRoot() {
		getChildren().addAll(lbTop, spCenter, bDelete);
		setAlignment(Pos.CENTER);
		setSpacing(20);
		setVisible(false);
	}
	
	private Map<String, Product> fireViewAskAllProduct() {
		for(ViewListenable vl : AllListeners.getAllListeners())
			return vl.fireViewAskAllProduct();
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

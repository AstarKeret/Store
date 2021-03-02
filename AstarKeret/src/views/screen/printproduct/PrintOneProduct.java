package views.screen.printproduct;

import command.Command;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.SystemManagementViewable;
import view.root.my.MyButton;
import view.root.my.MyLabel;
import view.root.my.MyProductGridPane;
import view.root.my.MyTextField;
import views.screen.printproduct.command.DeleteProductCommand;
import views.screen.printproduct.command.SearchProductCommand;

public class PrintOneProduct extends BorderPane implements SystemManagementViewable{
	private Label lbTop; 
	private HBox hbSearchLine;
	private VBox vbCenter;
	private GridPane gpProduct;
	private Button bSearch;
	private Button bDelete;
	private TextField tf;
	private PrintOneProduct pop;
	public PrintOneProduct() {
		pop = this;
		lbTop = new MyLabel("Search Product", "Copperplate Gothic Bold", 28, Color.SEAGREEN);
		((MyLabel) lbTop).setTitelEffect();
		gpProduct = new MyProductGridPane();	
		initCenter();
		initRoot();
	}
	
	private void initButton() {
		bSearch = new MyButton("Search");
		bSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gpProduct.getChildren().clear();
				Command cmd = new SearchProductCommand(pop);				
				cmd.execute();
				bDelete.setDisable(false);
			}
		});
		
		bDelete = new MyButton("Delete Product");
		bDelete.setDisable(true);
		bDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Command cmd = new DeleteProductCommand(pop);
				cmd.execute();
				tf.setText(" ");
				gpProduct.getChildren().clear();
			}
			
		});
	}
	private void initCenter() {
		initSearchLine();
		vbCenter = new VBox(hbSearchLine, gpProduct);
		vbCenter.setSpacing(15);
		vbCenter.setAlignment(Pos.TOP_CENTER);
		vbCenter.setPrefHeight(300);
	}

	private void initSearchLine() {
		tf = new MyTextField("Required", 110);
		initButton();
		hbSearchLine = new HBox(new MyLabel("Product Catalog Number", "GungsuhChe", 18,  Color.TEAL), tf, bSearch);
		hbSearchLine.setAlignment(Pos.CENTER);
		hbSearchLine.setSpacing(20);
		hbSearchLine.setPadding(new Insets(10));
	
	}
	private void initRoot() {
		setTop(lbTop);
		setAlignment(getTop(), Pos.CENTER);
		setCenter(vbCenter);
		setBottom(bDelete);
		setAlignment(getBottom(), Pos.BOTTOM_CENTER);
		setPadding(new Insets(10));
		setVisible(false);
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

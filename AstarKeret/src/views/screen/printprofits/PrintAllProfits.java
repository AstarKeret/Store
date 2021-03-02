package views.screen.printprofits;

import java.util.Iterator;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import view.root.ConsoleMsg;
import view.root.SystemManagementViewable;
import view.root.my.MyLabel;
import view.root.my.MyProfitsGridPane;

public class PrintAllProfits extends VBox  implements SystemManagementViewable{
	public final int PRODUCT_IN_ROW = 3;
	private Map<String, Integer> productsProfits;
	private GridPane gpRows;
	private Label lbTop; 
	private ScrollPane spCenter;
	
	public PrintAllProfits() {		
		lbTop = new MyLabel("Products Profits", "Copperplate Gothic Bold", 28, Color.SEAGREEN);
		((MyLabel) lbTop).setTitelEffect();
		lbTop.setAlignment(Pos.TOP_CENTER);
		gpRows = new GridPane();
		setScrollPane();
		initRoot();
	}

	public void setRows() {
		int row = 0, col = 0;
		productsProfits = fireViewAskAllProductsProfits();
		if(productsProfits == null){
			ConsoleMsg.getMsg().setText("No products were sold to show a profit");
			ConsoleMsg.getMsg().setColor();
			return;
		}
		Iterator<Map.Entry<String, Integer>> itr = productsProfits.entrySet().iterator(); 	
		while(itr.hasNext()) {
			if(col == PRODUCT_IN_ROW) {
				row++;
				col = 0;
			}
			gpRows.add(new MyProfitsGridPane(itr.next()), col++, row);
		}
		gpRows.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
		gpRows.setAlignment(Pos.CENTER);
		gpRows.setHgap(45);
		gpRows.setVgap(15);
		gpRows.setPrefHeight(300);
	}

	private Map<String, Integer> fireViewAskAllProductsProfits() {
		for(ViewListenable vl : AllListeners.getAllListeners())
			return vl.fireViewAskAllProductsProfits();
		return null;
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
	
	
	private void initRoot() {
		getChildren().addAll(lbTop, spCenter);
		setAlignment(Pos.CENTER);
		setSpacing(20);
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

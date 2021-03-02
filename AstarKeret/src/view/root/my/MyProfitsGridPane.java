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

public class MyProfitsGridPane extends GridPane{

	private Entry<String, Integer> next;
	private Label lbHeadName;
	private Label lbName;
	private Label lbHeadPrifits;
	private Label lbProfits;
	
	public MyProfitsGridPane(Entry<String, Integer> next) {
		this.next = next;
		initRoot();
		initLabels();
		insertLabels();
		
	}

	private void insertLabels() {
		add(lbHeadName, 0, 0);
		add(lbName, 1, 0);
		add(lbHeadPrifits, 0, 1);
		add(lbProfits, 1, 1);
	}

	private void initRoot() {
		setHgap(10);
		setHgap(20);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
		setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1.5))));

	}

	private void initLabels() {
		
		lbHeadName = new MyLabel("Product Name:", "David", 18, Color.DARKSLATEGRAY);
		lbName = new MyLabel(next.getKey(), "David", 15, Color.BLACK);
		lbHeadPrifits = new MyLabel("Profits:", "David", 18, Color.DARKSLATEGRAY);
		lbProfits = new MyLabel(next.getValue() + " ", "David", 15, Color.BLACK);

	}
}

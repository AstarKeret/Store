package views.screen.add;


import command.Command;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.SystemManagement;
import view.root.ConsoleMsg;
import view.root.my.MyButton;
import view.root.my.MyLabel;
import view.root.my.MyTextField;

public class AddProductScreen extends GridPane {
	private ComboBox<String> cbMap;
	private HBox hbMap;
	private HBox hbPhone;
	private HBox hbPromotion;
	private Label lbMap;
	private Label lbSetMap;
	private Label lbProduct;
	private Label lbCustomer;
	private Label lbName;
	private Label lbCatalogNumber;
	private Label lbCost;
	private Label lbPrice;
	private Label lbFullName;
	private Label lbPhonNumber;
	private Label lbPromotion;
	private MyTextField tfName;
	private MyTextField tfCatalogNumber;
	private MyTextField tfCost;
	private MyTextField tfPrice;
	private MyTextField tfFullName;
	private MyTextField tfPhonNumber;
	private RadioButton rbPromotion;
	private Button bAdd;
	private Button bUndo;
	private Button bSetMap;
	public AddProductScreen() {
		initLabels();
		initTextFields();
		initButtons();
		initPhoneNumber();
		initMap();
		initPromotion();
		initRoot();
	}

	private void setAllDisable(Boolean toDo) {
		tfName.setDisable(toDo);
		tfCatalogNumber.setDisable(toDo);
		tfCost.setDisable(toDo);
		tfPrice.setDisable(toDo);
		tfFullName.setDisable(toDo);
		hbPhone.setDisable(toDo);
		hbPromotion.setDisable(toDo);
		bAdd.setDisable(toDo);
		cbMap.setDisable(!toDo);
		bSetMap.setDisable(!toDo);
	}

	private void initMap() {
		lbMap = new MyLabel("Sort By", "GungsuhChe", 18, Color.TEAL);
		cbMap = new ComboBox<>();
		cbMap.getItems().addAll("Catalog Number Up", "Catalog Number Down", "Entry Order");
		cbMap.setPrefWidth(Double.MIN_VALUE);
		hbMap = new HBox(lbMap, cbMap);
		hbMap.setSpacing(10);
	}


	private void initRoot() {
		setHgap(25);
		setVgap(30);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
		add(hbMap, 0, 0);		
		add(bSetMap, 1, 0);
		add(lbSetMap, 2, 0);
		add(lbProduct, 2, 1);		
		add(lbName, 0, 2);				
		add(tfName, 1, 2);				
		add(lbCatalogNumber, 3, 2);
		add(tfCatalogNumber, 4, 2);		
		add(lbCost, 0 ,3);
		add(tfCost, 1 ,3);				
		add(lbPrice, 3 ,3);
		add(tfPrice, 4 ,3);				
		add(lbCustomer, 2, 4);
		add(lbFullName, 0, 5);
		add(tfFullName, 1, 5);			
		add(lbPhonNumber, 3, 5);
		add(hbPhone, 4, 5);				
		add(hbPromotion, 0, 6);			
		add(bAdd, 2,7);
		add(bUndo, 3,7);

	}
	
	private void initButtons() {
		bAdd = new MyButton("Add");
		bAdd.setOnAction(e->{
			Command cmd = new AddButtonCommand(this);
			cmd.execute();
			cleanAllPrompts();
			if(ConsoleMsg.getMsg().getTextFill() == Color.BLUE)
				bUndo.setDisable(false);;
		});
		bUndo = new MyButton("Undo");
		bUndo.setDisable(true);
		bUndo.setOnAction(e->{
			Command cmd = new UndoCommand(bUndo);
			cmd.execute();
		});
		bSetMap = new MyButton("Set");

			bSetMap.setOnAction(e->{
				if(cbMap.getValue() != null) {
					Command cmd = new SetMapCommand(cbMap);
					cmd.execute();
					setAllDisable(false);
					lbSetMap.setFont(new Font(15));
					lbSetMap.setText(cbMap.getValue());
				}
			else
				ConsoleMsg.getMsg().setText("Choose how to sort the product list first ");
	
			ConsoleMsg.getMsg().setColor();
			
		});
		

	}

	private void cleanAllPrompts() {
		tfName.clear();
		tfCatalogNumber.clear();
		tfCost.clear();
		tfPrice.clear();
		tfFullName.clear();
		tfPhonNumber.clear();
		rbPromotion.setSelected(false);
		tfName.setPromptText("Product Name");
		tfCatalogNumber.setPromptText("Required");
		tfCost.setPromptText("Can't be negative");
		tfPrice.setPromptText("Can't be negative");
		tfFullName.setPromptText("Full Name");
		tfPhonNumber.setPromptText("Enter 7 Digit");
	}

	private void initLabels() {
		lbSetMap = new MyLabel("Choose order to continue", "David", 12, Color.BLACK);
		lbProduct = new MyLabel("Product", "Copperplate Gothic Bold", 25, Color.SEAGREEN);
		((MyLabel) lbProduct).setTitelEffect();
		lbName = new MyLabel("Product Name", "GungsuhChe", 18, Color.TEAL);
		lbCatalogNumber = new MyLabel("Catalog Number", "GungsuhChe", 18, Color.TEAL);
		lbCost = new MyLabel("Cost", "GungsuhChe", 18, Color.TEAL);
		lbPrice = new MyLabel("Price", "GungsuhChe", 18, Color.TEAL);
		lbCustomer = new MyLabel("Customer", "Copperplate Gothic Bold", 25, Color.SEAGREEN);
		((MyLabel) lbCustomer).setTitelEffect();
		lbFullName = new MyLabel("Full Name", "GungsuhChe", 18, Color.TEAL);
		lbPhonNumber = new MyLabel("Phone Number", "GungsuhChe", 18, Color.TEAL);
	}

	private void initTextFields() {
		tfName = new MyTextField("Product Name", 110);
		tfCatalogNumber =  new MyTextField("Required", 110);
		tfCost =  new MyTextField("Can't be negative", 110);
		tfPrice =  new MyTextField("Can't be negative", 110);
		tfFullName =  new MyTextField("Full Name", 110);
		tfPhonNumber =  new MyTextField("Enter 7 Digit", 110);
	}

	private void initPhoneNumber() {
		tfPhonNumber =  new MyTextField("7 Digit only", 110);
		ComboBox<String> cbBeginDigits = new ComboBox<>();
		cbBeginDigits.getItems().addAll("050", "051", "052", "053", "054", "055", "056", "057", "058", "059");
		cbBeginDigits.setPrefWidth(Double.MIN_VALUE);
		hbPhone = new HBox(cbBeginDigits, tfPhonNumber);
		
		
	}

	private void initPromotion() {
		lbPromotion = new Label("Want to recive promotions");
		rbPromotion = new RadioButton();
		hbPromotion = new HBox(rbPromotion, lbPromotion);
		hbPromotion.setSpacing(5);
	}

	public SearchMap searchMap() {
		return new SearchMap();
	}
	public class SearchMap {

		public void search() {
			if(SystemManagement.sortType  == -1){
				setAllDisable(true);
				return;
			}	
			setAllDisable(false);
			lbSetMap.setFont(new Font(15));
			switch(SystemManagement.sortType) {
				case 1:
					lbSetMap.setText("Catalog Number Up");
				break;
				case 2:
					lbSetMap.setText("Catalog Number Down");
				break;
				case 3:
					lbSetMap.setText("Entry Order");
				break;
				}
		}
		
	}
}


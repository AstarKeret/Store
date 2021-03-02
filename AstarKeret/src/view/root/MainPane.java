package view.root;

import command.Command;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import view.mouseevents.MouseEnteredMainButtonEvent;
import view.mouseevents.MouseExitedMainButtonEvent;
import views.screen.SendPromotionsScreen;
import views.screen.add.AddProductCommand;
import views.screen.add.AddProductScreen;
import views.screen.printproduct.PrintProductScreen;
import views.screen.printproduct.command.PrintProductCommand;
import views.screen.printprofits.PrintProfitsScreen;
import views.screen.printprofits.command.PrintProfitstCommand;

public class MainPane extends BorderPane{
	//Variables
	private StackPane spCenter;
	private HBox hbTop;
	private StackPane spBottom;
	private VBox vbLeft;
	private VBox vbRight;
	private GridPane gpAddProduct;
	private BorderPane vbSendPromotions;
	private VBox vbPrintProduct;
	private VBox vbPrintProfits;
	private Button bAddProduct;
	private Button bSendPromotions;
	private Button bPrintProduct;
	private Button bPrintProfitst;
	NoProductPane pane;
	ConsoleMsg msg;
	
	//Constructor
	public MainPane(){
		msg = ConsoleMsg.getMsg();
		pane = NoProductPane.getPane();
		init();
	}
	// init components
	public void init() {
		initRoot();
		initLeft();
		initRight();
		initCenter();
		initBottom();
		initTop();
	}

	
	//init the center of the screen
	private void initCenter() {
		spCenter = new StackPane(gpAddProduct, vbSendPromotions, vbPrintProduct, vbPrintProfits, NoProductPane.getPane());
		spCenter.setBackground(new Background(new BackgroundFill(Color.SILVER, new CornerRadii(9), Insets.EMPTY)));
		spCenter.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(9), BorderWidths.DEFAULT)));
		setCenter(spCenter);
		setAlignment(getCenter(),Pos.CENTER);
	}
	
	//init menu HBox in the right side
	private void initRight() {
		initCenterPrintProduct();
		initCenterPrintProfits();
		vbRight = new VBox( bPrintProduct, bPrintProfitst);
		vbRight = setVBoxStyle(vbRight);
		setRight(vbRight);
	}
	//init menu HBox in the left side
	private void initLeft() {
		initCenterAddProduct();
		initCenterSendPromotions();
		vbLeft = new VBox(bAddProduct, bSendPromotions);
		vbLeft = setVBoxStyle(vbLeft);
		setLeft(vbLeft);
		
	}
	//init add product screen
	private void initCenterAddProduct() {
		gpAddProduct = new AddProductScreen();
		gpAddProduct.setVisible(false);
		bAddProduct = setButtonStyle(bAddProduct, "Add Product");
		bAddProduct.setOnAction(e->{
			setAllScreensOff();
			Command cmd = new AddProductCommand((AddProductScreen)gpAddProduct);
			cmd.execute();
		});
	}
	
	//init send promotion screen
	private void initCenterSendPromotions() {
		vbSendPromotions = new SendPromotionsScreen();
		vbSendPromotions.setVisible(false);
		bSendPromotions = setButtonStyle(bSendPromotions, "Send Promotions");
		bSendPromotions.setOnAction(e->{
			setAllScreensOff();
			Command cmd = new PromotionScreenCommand((SendPromotionsScreen)vbSendPromotions);
			cmd.execute();
		});
	}

	//init print product screen
	private void initCenterPrintProduct() {
		vbPrintProduct = new PrintProductScreen();
		vbPrintProduct.setVisible(false);
		bPrintProduct = setButtonStyle(bPrintProduct, "Print Product");
		bPrintProduct.setOnAction(e->{
			setAllScreensOff();
			Command cmd = new PrintProductCommand((PrintProductScreen) vbPrintProduct);
			cmd.execute();
		});
	}
	
	//init print profits screen
	private void initCenterPrintProfits() {
		vbPrintProfits = new PrintProfitsScreen();
		vbPrintProfits.setVisible(false);
		bPrintProfitst = setButtonStyle(bPrintProfitst, "Print Profits");
		bPrintProfitst.setOnAction(e->{
			setAllScreensOff();
			Command cmd = new PrintProfitstCommand((PrintProfitsScreen) vbPrintProfits);
			cmd.execute();
		});
		
	}	

	// init root pane
	private void initRoot() {
		setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		pane.setInvisible();
	}

	private void initBottom() {
		Rectangle rec = new Rectangle(450, 50, Color.WHITESMOKE);
		rec.setStroke(Color.BLACK);
		rec.setStrokeWidth(3);
		msg.setDefault();
		
		spBottom = new StackPane(rec, ConsoleMsg.getMsg());
		spBottom.setMinHeight(80);
		spBottom.setAlignment(Pos.CENTER);

		setBottom(spBottom);
		setAlignment(getBottom(), Pos.TOP_CENTER);
	}
	//init title 
	private void initTop() {
		Text title = new Text("Store Name");
		title.setFont(Font.font("Goudy Stout", FontWeight.BOLD, 50));
		title.setTabSize(60);
		title.setEffect(new DropShadow(BlurType.ONE_PASS_BOX ,Color.BISQUE, 8 , 2.3 , 0.05 , 2));
		title.setFill(Color.DARKORANGE);
		hbTop = new HBox(title);
		hbTop.setMinHeight(80);	
		hbTop.setAlignment(Pos.CENTER);
		
		setTop(hbTop);
		setAlignment(getTop(),Pos.CENTER);
	}
	//sets all the screens to be invisibla
	private void setAllScreensOff() {
		msg.setDefault();
		gpAddProduct.setVisible(false);
		vbPrintProduct.setVisible(false);
		vbSendPromotions.setVisible(false);
		vbPrintProfits.setVisible(false);
	}

	private VBox setVBoxStyle(VBox vb) {
		vb.setPadding(new Insets(5));
		vb.setSpacing(55);
		vb.setAlignment(Pos.CENTER);
		vb.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
	return vb;
	}
	private Button setButtonStyle(Button btn, String buttonTitle) {
		btn = new Button(buttonTitle);
		btn.setFont(new Font("Britannic Bold", 25));
		btn.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 1 , 0 , 0 );");
		btn.setTextFill(Color.BISQUE);
		btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		btn.setOnMouseEntered(new MouseEnteredMainButtonEvent());
		btn.setOnMouseExited(new MouseExitedMainButtonEvent());

		return btn;
	}
}

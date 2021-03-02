package model;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import view.root.View;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SystemManagement theModel = new SystemManagement();			//initializes the system manager
		View theView = new View(primaryStage);						//initializes the view root
		@SuppressWarnings("unused")
		Controller controller = new Controller(theModel, theView);	//initializes the system control for connect the view to the manager 
	}
}
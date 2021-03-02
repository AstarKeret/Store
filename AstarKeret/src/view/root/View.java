package view.root;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import listeners.AllListeners;
import listeners.ViewListenable;

public class View implements SystemManagementViewable{
	private MainPane pane;
	public View(Stage primaryStage) {
		pane = new MainPane();	
		primaryStage.setTitle("Store Sales");
		primaryStage.setScene(new Scene(pane));
		primaryStage.getIcons().add(new Image("store-icon.png"));
		primaryStage.show();
	}

	@Override
	public void registerListener(ViewListenable vl) {
		AllListeners.registerListener(vl);
	}

	@Override
	public void fireModelDeleteProduct(String msg) {
		ConsoleMsg.getMsg().setText(msg);
		ConsoleMsg.getMsg().setColor();
	}


}

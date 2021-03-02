package views.screen;

import java.util.ArrayList;

import command.Command;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import listeners.AllListeners;
import listeners.ViewListenable;
import observer.Promotion;
import view.root.SystemManagementViewable;
import view.root.my.MyLabel;

public class SendPromotionCommand implements Command, SystemManagementViewable {
	private GridPane gpRoot; 
	ArrayList<String> confirmed;
	private int index;
	private int row;
	private int col;
	public SendPromotionCommand(GridPane gpRoot) {
		this.gpRoot = gpRoot;
		row = 0;
		col = 0;
		index = 0;
	}
	@Override
	public void execute() {
		gpRoot.getChildren().clear();
		gpRoot.setVisible(true);
		Promotion.getProm().getconfirmed().clear();
		fireViewSendPromotion();
		confirmed = Promotion.getProm().getconfirmed();
	new Thread(()->{
		try {
		for(int i = 0 ; i < confirmed.size() ;i ++) {
			Thread t = new Thread(()->{
		
				Platform.runLater(()->{
					gpRoot.add(new MyLabel(confirmed.get(index++), "David", 18, Color.DARKSLATEGRAY), col++, row);	
					});	
				try{
					Thread.sleep(2000);	
				}catch (Exception e) {
				}
			});
			t.start();
			t.join();
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}).start();

	}

	private void fireViewSendPromotion(){
		for(ViewListenable vl : AllListeners.getAllListeners()) 
			vl.fireViewSendPromotion();
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

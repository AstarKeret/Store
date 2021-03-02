package views.screen.add;

import java.io.IOException;

import command.Command;
import javafx.scene.control.Button;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.SystemManagementViewable;

public class UndoCommand implements Command, SystemManagementViewable{
	private Button btn;
	
	public UndoCommand(Button btn) {
		this.btn = btn;
	}
	@Override
	public void execute() {
		try {
			btn.setDisable(true);
			fireViewUndoMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fireViewUndoMap() throws IOException {
		for(ViewListenable vl : AllListeners.getAllListeners()) 
			vl.fireViewUndoMap();
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

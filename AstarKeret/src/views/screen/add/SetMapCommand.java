package views.screen.add;

import command.Command;
import javafx.scene.control.ComboBox;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.ConsoleMsg;
import view.root.SystemManagementViewable;

public class SetMapCommand implements Command, SystemManagementViewable{
	private ComboBox<String> comboBox;
	public SetMapCommand(ComboBox<String> cmb) {
		this.comboBox = cmb;
	}
	@Override
	public void execute() {
		try {
		ConsoleMsg.getMsg().setText(fireViewSetMap(comboBox.getValue()));
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
	}

	private String fireViewSetMap(String value) {
		for(ViewListenable vl : AllListeners.getAllListeners())
			return vl.fireViewSetMap(value);
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

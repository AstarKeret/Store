package views.screen.printprofits.command;

import command.Command;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.NoProductPane;
import view.root.SystemManagementViewable;
import views.screen.printprofits.PrintProfitsScreen;

public class PrintProfitstCommand implements Command, SystemManagementViewable{
	private PrintProfitsScreen pps;
	NoProductPane pane;
	public PrintProfitstCommand(PrintProfitsScreen pps) {
		this.pps = pps;
		pane = NoProductPane.getPane();
	}
	@Override
	public void execute() {
		if(fireViewAskMapSize() > 0) {
			pane.setInvisible();
			pps.setVisible(true);
			}
		else
			pane.setVisible();
	}
	private int fireViewAskMapSize() {
		for(ViewListenable vl : AllListeners.getAllListeners())
			return vl.fireViewAskMapSize();
		return 0;
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

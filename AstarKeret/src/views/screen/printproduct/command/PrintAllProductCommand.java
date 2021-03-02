package views.screen.printproduct.command;

import command.PrintCommand;
import views.screen.printproduct.PrintAllProduct;

public class PrintAllProductCommand implements PrintCommand{
	private PrintAllProduct vbRoot;
	
	public PrintAllProductCommand(PrintAllProduct vbox) {
		this.vbRoot = vbox;
	}
	@Override
	public void execute() {
		vbRoot.cleanRows();
		vbRoot.setRows();
	}
	@Override
	public void turnOf() {
		vbRoot.setVisible(false);
	}
	@Override
	public void turnOn() {
		vbRoot.setVisible(true);
	}
	
	

}

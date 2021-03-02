package views.screen.printprofits.command;

import command.PrintCommand;
import views.screen.printprofits.PrintAllProfits;

public class PrintAllProfitsCommand implements PrintCommand {

	private PrintAllProfits vbRoot;
	public PrintAllProfitsCommand(PrintAllProfits vbRoot) {
		this.vbRoot = vbRoot;
	}
	@Override
	public void turnOf() {
		vbRoot.setVisible(false);
	}

	@Override
	public void turnOn() {
		vbRoot.setVisible(true);
	}

	@Override
	public void execute() {
		vbRoot.cleanRows();
		vbRoot.setRows();
	}

}

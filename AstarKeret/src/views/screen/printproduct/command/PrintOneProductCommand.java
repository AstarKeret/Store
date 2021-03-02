package views.screen.printproduct.command;

import command.PrintCommand;
import views.screen.printproduct.PrintOneProduct;

public class PrintOneProductCommand implements PrintCommand {
	private PrintOneProduct bpRott;
	public PrintOneProductCommand(PrintOneProduct borderPane) {
		this.bpRott = borderPane;
	}
	@Override
	public void turnOf() {
		bpRott.setVisible(false);
	}

	@Override
	public void turnOn() {
		bpRott.setVisible(true);
	}

	@Override
	public void execute() {
		@SuppressWarnings("unused")
		PrintOneProduct pop = new PrintOneProduct();
	}

}

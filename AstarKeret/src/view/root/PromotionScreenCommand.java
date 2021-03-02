package view.root;

import command.Command;
import views.screen.SendPromotionsScreen;

public class PromotionScreenCommand implements Command {
	private SendPromotionsScreen vbRoot;
	public NoProductPane pane;
	public PromotionScreenCommand(SendPromotionsScreen vbRoot) {
		this.vbRoot = vbRoot;
		pane = NoProductPane.getPane();
	}
	@Override
	public void execute() {
		pane.setInvisible();
		vbRoot.setVisible(true);
	}

}

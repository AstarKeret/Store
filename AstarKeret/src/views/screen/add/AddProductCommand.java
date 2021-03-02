package views.screen.add;

import command.Command;
import view.root.NoProductPane;
import views.screen.add.AddProductScreen.SearchMap;

public class AddProductCommand implements Command{
	private AddProductScreen gpRoot;
	public NoProductPane pane;
	public AddProductCommand(AddProductScreen gpRoot) {
		this.gpRoot = gpRoot;
		pane = NoProductPane.getPane();
	}
	@Override
	public void execute() {
		pane.setInvisible();
		gpRoot.setVisible(true);
		SearchMap sm = gpRoot.searchMap();
		sm.search();
	}

}

package view.root;

import listeners.ViewListenable;

public interface SystemManagementViewable {
	void registerListener(ViewListenable l);
	void fireModelDeleteProduct(String msg);
}

package listeners;

import java.util.ArrayList;

public class AllListeners extends  ArrayList<ViewListenable>{
	//singelton array of all the listeners class the implements SystemManagementViewable for connecting to the model 
	private static final long serialVersionUID = 1L;
	private static AllListeners allListeners = new AllListeners();

	
	public static void registerListener(ViewListenable vl) {
		allListeners.add(vl);
	}
	public static AllListeners getAllListeners() {
		return allListeners;
	}
}

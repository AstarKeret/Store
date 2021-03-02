package memento;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import comparator.CompareProductByCatalogDown;
import comparator.CompareProductByCatalogUp;
import model.Product;

public class SetMapByType {
	public static Map<String, Product> setMap(int type){
		//initializing the map dependents the type value
		Map<String, Product> map = null;
		switch(type) {
		case 1:
			map = new TreeMap<String, Product>(new CompareProductByCatalogUp());	//order by catalog number sort up 
			break;
		case 2:
			map = new TreeMap<String, Product>(new CompareProductByCatalogDown());	//order by catalog number sort down
			break;
		case 3:
			map = new LinkedHashMap<String, Product>();								//sort by the entry order to the map
			break;
		}
		return map;
	}
}

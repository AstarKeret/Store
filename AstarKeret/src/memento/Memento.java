package memento;
import java.util.Map;

import model.Product;

public class Memento {
	private Map<String, Product> map;
	
	public Memento(Map<String, Product> map, int type){
		this.map = SetMapByType.setMap(type);	//initializing the map dependents the type value: 1.catalog number up; 2. catalog number down; 3. entry order
        this.map.putAll(map); 					//copy the original map to the memnto one
	}
	
	public Map<String, Product> getMap(){
		return map;			//returns the last map before the adding
	}

}
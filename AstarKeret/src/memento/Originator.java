package memento;

import java.util.Iterator;
import java.util.Map;

import model.Product;


public class Originator {
	//the main map that the system managment work on
    private Map<String, Product> map;
    private int type;
    private long lastIndex;
    public Originator(Map<String, Product> map, int type){
        this.map = SetMapByType.setMap(type);	//initializing the map dependents the type value: 1.catalog number up; 2. catalog number down; 3. entry order
        this.type = type;						//set this.type to use when creating memento
    }
	public Memento save() {
		return new Memento(map, type);			//create new memento with the update map (before adding)
	}

	public void undo(Memento memento) {
		map = memento.getMap();				//set the main map to be the same before adding the last product
	}
 
    public Map<String, Product> getMap(){
        return map;							//returns the main map
    }
 
    public void addToMap(String catalogNumber, Product product) {
    	//adds the new product to the main map and update lastIndex to be same as its index (to be use when the user do undo)
		map.put(catalogNumber, product);		
    	if(type == 3) {							//linked map
    		lastIndex = map.size() - 1;			//product index is the lest one index 
    		return;
    	}
    	lastIndex = searchProductIndex(catalogNumber); //search and returns the product index in the main map
    }
    public long searchProductIndex(String catalogNumber) {
		long index = 0;		//counter
    	Iterator<String> itr = map.keySet().iterator();
		while(true) {
			if(itr.next().equals(catalogNumber.trim()))		//loof ends when its finds the same catalog number
				break;
				index++; //add one to the counter
			}
		return index;
	}
	public long getLastIndex() {
		return lastIndex;		//returns the last index (for deletes from the file)
    }
 }

 

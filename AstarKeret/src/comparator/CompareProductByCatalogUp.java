package comparator;

import java.util.Comparator;

public class CompareProductByCatalogUp implements Comparator<String> {
	//string comparator- lexicographic up
	@Override
	public int compare(String catalogNum1, String catalogNum2) {
		
		return catalogNum1.compareTo(catalogNum2);
	}

}

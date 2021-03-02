package comparator;

import java.util.Comparator;

public class CompareProductByCatalogDown implements Comparator<String> {
	//string comparator- lexicographic down
	@Override
	public int compare(String catalogNum1, String catalogNum2) {
		return catalogNum2.compareTo(catalogNum1);
	}

}

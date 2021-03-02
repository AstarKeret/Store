package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.TreeMap;

import comparator.CompareProductByCatalogDown;
import comparator.CompareProductByCatalogUp;
import listeners.SystemManagementListenable;
import memento.CareTaker;
import memento.Originator;
import observer.Promotion;

public class SystemManagement implements SystemManagementListenable{
	public final int STRING_SIZE = 32;
	public final int INT_SIZE = Integer.SIZE / Byte.SIZE;
	public final int LONG_SIZE = Long.SIZE / Byte.SIZE;
	public final int PRODUCT_AND_KEY_SIZE = 3*STRING_SIZE + INT_SIZE*2 + LONG_SIZE + 1;
	public final int PHONE_NUMBER_END_LENGTH =  7;
	public final String F_NAME = "products.txt";
	public static int sortType;
	private ArrayList<SystemManagementListenable> allListeners;
	private ProductsFile pf;
	private File f;
	private CareTaker ct;
	private Originator map;
	public SystemManagement() throws FileNotFoundException, IOException {
		allListeners = new ArrayList<SystemManagementListenable>();  
		ct = new CareTaker();
		initFile();
	}

	private void initFile() throws IOException  {
		f = new File(F_NAME);
		if(!f.exists()) { //check if there is a file
			sortType = -1;
			return;	
		}
		scanFromFile();
	}

	private void scanFromFile() throws FileNotFoundException, IOException {
		pf = new ProductsFile(f);
		if(pf.getFileLength() == 0){	//if file is empty its acting like its not exists
			sortType = -1;
			return;	
		}
		
		Iterator<Byte> itr = pf.iterator();					//the file iterator to scan with it
		byte[] data = readBytesFromFile(itr, INT_SIZE);		//array to save the bytes that the iterator returns
		sortType = ConvertByteArray.toInt(data);			//set sort type to from 4 byte to int
		//goes to function that initializing the map with the type that is written in the file
		switch (sortType) {
		case 1: 
			fireViewSetMap("Catalog Number Up");			
			break;
		case 2: 
				fireViewSetMap("Catalog Number Down");
			break;
		case 3: 
			fireViewSetMap("Entry Order");
			break;
		}
			
		readProducts(itr);
		pf.closeFile();		//close the file using the file iterator function
	}


	private void readProducts(Iterator<Byte> itr) throws IOException {
	
		byte[] data;
		while(itr.hasNext()) {
			Product product = new Product();
			Customer customer = new Customer();
			data = readBytesFromFile(itr, STRING_SIZE); 			//read 4 bytes with function
			String catalogNumber = new String(data).trim();		
			data = readBytesFromFile(itr, STRING_SIZE);				//read 32 bytes with function
			product.setName(new String(data).trim());
			data = readBytesFromFile(itr, INT_SIZE);				//read 4 bytes with function
			product.setStoreCost(ConvertByteArray.toInt(data)); 	//convert from bytes array to int
			data = readBytesFromFile(itr, INT_SIZE);				//read 4 bytes with function
			product.setCustomerPrice(ConvertByteArray.toInt(data)); //convert from bytes array to int
			data = readBytesFromFile(itr, STRING_SIZE);				//read 32 bytes with function
			customer.setName(new String(data).trim());
			data = readBytesFromFile(itr, LONG_SIZE);				//read 8 bytes with function
			customer.setPhoneNumber(ConvertByteArray.toLong(data)); //convert from bytes array to long
			data = readBytesFromFile(itr, 1);						//read 1 byte with function
			customer.setGetPromotion(data[0] != 0);					//convert from byte array to boolean
			product.setCustomer(customer);
			map.getMap().put(catalogNumber, product);
		}
	}
	
	private void writeToFile() throws FileNotFoundException, IOException {
		//writes the map to file 
		try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
			raf.writeInt(sortType);											
			Set<Entry<String, Product>> set = map.getMap().entrySet();
			for(Entry<String, Product> en : set){
				Product temp = en.getValue();
				FixedLengthString.writeFixedLengthString(en.getKey(), STRING_SIZE, raf);		//writes string in 32 byte
				FixedLengthString.writeFixedLengthString(temp.getName(), STRING_SIZE, raf);		//writes string in 32 byte
				raf.writeInt(temp.getStoreCost());	
				raf.writeInt(temp.getCustomerPrice());
				FixedLengthString.writeFixedLengthString(temp.getCustomer().getName(), STRING_SIZE, raf);	//writes string in 32 byte
				raf.writeLong(temp.getCustomer().getPhoneNumber());
				raf.writeByte((byte)(temp.getCustomer().getGetPromotion() ?1:0));
			}
			raf.close();		
		}
		
	}

	
	
	
	private byte[] readBytesFromFile(Iterator<Byte> itr, int size) {
		//reads in loof from file to a byte array using the file iterator
		byte[] data = new byte[size];
		for(int i = 0 ; i < size ; i++)
			data[i] = itr.next();
		return data;
	}

	public Map<String, Product> getProductsList() {
		return map.getMap();
	}
	
	public void registerListener(SystemManagementListenable sm) {
		allListeners.add(sm);
	}

//	@Override
	public String fireViewAddProduct(String productName, String catalogNumber, String cost, String price, String customerName, String phoneNumberBegin, String phoneNumberEnd, boolean isPromotion) throws FileNotFoundException, IOException {
		
		int storeCost = checkNumbers(cost), customerPrice = checkNumbers(price); //function checks if price.cost string is legal and returns int
		long phoneNumber = checkPhoneNumber(phoneNumberBegin, phoneNumberEnd);	//function checks if phone number string is legal and returns long
		if(catalogNumber.isBlank())
			return "Catalog Number is mandatory ";
		if(storeCost == -1) 
			return "Cost have to be only digit ";
		if(storeCost < 0) 
			return "Cost can't be negative ";
		if(customerPrice == -1 )
			return "Price have to be only digit ";
		if(customerPrice < 0) 
			return "Price can't be negative ";
		if(phoneNumber == -1)
			return "Phone Number have to be only digit ";
		else if(phoneNumber == -2)
			return "Missing digit in Phone Number ";
		else if(phoneNumber == -3)
			return "To Many digit in Phone Number ";
		
		if(productName.isBlank())
			productName = " ";
		else
			productName.trim();
		if(customerName.isBlank())
			customerName = " ";
		else
			customerName.trim();
		Product product = new Product(productName, storeCost, customerPrice, new Customer(customerName, phoneNumber, isPromotion));
		ct.save(map.save());		//saves current map before adding product with memento care given
		map.getMap().put(catalogNumber.trim(), product); //adds product to map
		writeToFile();
		return "Product Added";
	}

	private long checkPhoneNumber(String phoneNumberBegin, String phoneNumberEnd) {
		//check if the phone number string is legal and convert it to long
		long phoneNumber = -1, temp;
		if(phoneNumberEnd.isBlank() || phoneNumberEnd.isBlank())
			return 0;
		try{
			if(phoneNumberEnd.length() == PHONE_NUMBER_END_LENGTH) {
				temp = Integer.parseInt(phoneNumberEnd);
				phoneNumber = (long) (Integer.parseInt(phoneNumberBegin.trim())*Math.pow(10, PHONE_NUMBER_END_LENGTH) + temp);
			}
				
			else if(phoneNumberEnd.length() < PHONE_NUMBER_END_LENGTH)
				phoneNumber = -2;
			else
				phoneNumber = -3;
		}catch (NumberFormatException e) {
			return phoneNumber;
		}
	
		return phoneNumber;
	}

	private int checkNumbers(String number) {
		//check if the string is legal and convert it to int
		int num = -1;
		try {
		if(!number.isBlank()) 
			num = Integer.parseInt(number);
		else
			num = 0;
		}catch (NumberFormatException e) {
			return num;
		}
		return num;
	}

	@Override
	public String fireViewSetMap(String value) {
		//initialize the map to the choosen sort type
		switch(value) {
		case "Catalog Number Up":
			sortType = 1;
			map = new Originator(new TreeMap<String, Product>(new CompareProductByCatalogUp()), sortType);
			sortType = 1;
			break;
		case "Catalog Number Down":
			sortType = 2;
			map = new Originator(new TreeMap<String, Product>(new CompareProductByCatalogDown()), sortType);
			break;
		case "Entry Order":
			sortType = 3;
			map = new Originator(new LinkedHashMap<String, Product>(), sortType);
			break;
		}

		return "Map Order is Seted";
	}

	@Override
	public Product fireViewSearchProduct(String catalogNumber) {
		return map.getMap().get(catalogNumber.trim());	//returns the product of the sented catalog number
	}

	@Override
	public void fireViewDeleteProduct(String catalogNumber) throws IOException {
		long index = map.searchProductIndex(catalogNumber);	//search and returns the product place in the map
		
		if(deleteFromFile(index))
			fireModelDeleteProduct("Deleted Product " + catalogNumber.trim());	//confirmed view that delete the product
		else
			fireModelDeleteProduct("Failed Deleted Product " + catalogNumber);	//confirmed view that delete product failed
	}

	private boolean deleteFromFile(long index) throws IOException {
		long size = (index + 1) * PRODUCT_AND_KEY_SIZE + INT_SIZE;	//amount off byte to run  with the iterator until it gets to the be deleted one
		for(int i = 0 ; i < size ; i++)
		pf = new ProductsFile(f);
		Iterator<Byte> itr = pf.iterator();
		for(int i = 0 ; i < size ; i++) //runs on the file with the iterator
			itr.next();
		for(int i = 0 ; i < PRODUCT_AND_KEY_SIZE ; i++)	//remove bytes in amount of one value and key from file
			itr.remove();

		scanFromFile();			//scan the update file to the map
		return true;
	}
	public int getSortType() {
		return sortType;
	}

	@SuppressWarnings("static-access")
	public void setSortType(int type) {
		this.sortType = type;
	}

	@Override
	public void fireModelDeleteProduct(String msg) {
		for(SystemManagementListenable sl : allListeners)
			sl.fireModelDeleteProduct(msg);		//confirmed view
	}

	@Override
	public void fireViewDeleteAllProducts() {
		try {
			pf = new ProductsFile(f);
			Iterator<Byte> itr = pf.iterator();	//open file iterator
			long size = map.getMap().size() * PRODUCT_AND_KEY_SIZE + INT_SIZE; 		//amount off byte to run  with the iterator to get to the end of the file and then remove one by one
			for(int i = 0 ; i < size ; i++)
				itr.next();
			
			for(int i = 0 ; i < size ; i++)
				itr.remove();
			
			map.getMap().clear();		//clear map data (cant read from an empty file)
			sortType = -1;				//update the sort type like there is no file 
			pf.closeFile();
		} catch (IOException e) {
			fireModelDeleteProduct("Failed Deleted All Products ");
		}
		fireModelDeleteProduct("Deleted All Products");
	}

	public Map<String, Integer> fireViewAskAllProductsProfits() {
		if(map.getMap().size() == 0 || map.getMap() == null)
			return null;
		Map<String, Integer> productsProfits = new TreeMap<String, Integer>(new CompareProductByCatalogUp());	//contain the prosucts name and sum of profit to each
		for(String key : map.getMap().keySet()) {
			Product temp = map.getMap().get(key);
	
			//calculate profit by product name (same name are the same products)
		if(productsProfits.containsKey(temp.getName().trim())) {
				int newSum = productsProfits.get(temp.getName().trim());
				newSum += (temp.getCustomerPrice() - temp.getStoreCost());
				productsProfits.put(temp.getName().trim(), newSum); 	//adds the product profits to its equel name in the map
			}
			else
				productsProfits.put(temp.getName().trim(), temp.getCustomerPrice() - temp.getStoreCost());	//put the product name and profit in profits map
		}
		 return productsProfits;
	}

	public long fireViewAskTotalProfits() {
		long sum = 0;
		for(String key : map.getMap().keySet()) {
			Product temp = map.getMap().get(key);
			sum += (temp.getCustomerPrice() - temp.getStoreCost());	//calculate the total profit
		}
		return sum;
	}

	public int fireViewAskMapSize() {
		if(sortType == -1)
			return 0;
		return map.getMap().size();
	}

	public void fireViewSendPromotion() {
		for(String key : map.getMap().keySet()) {	
			Product temp = map.getMap().get(key);
			Promotion.getProm().sendPromotion(temp.getCustomer()); //sends promotion to customer with obsever by singelton class 
		}
	}

	public void fireViewUndoMap() throws IOException {
		map.undo(ct.restore());		//restore map before adding with memnto
		if(deleteFromFile(map.getLastIndex()))	//deletes product from file to
			fireModelDeleteProduct("Undo add product operation");	//confirmed view
		else
			fireModelDeleteProduct("Undo operation failed");		//confirmed view
			
	}
}

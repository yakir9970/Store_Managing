package model.command;

import java.io.IOException;
import java.util.ArrayList;

public interface Commands {

	void addProducts(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String Cphone, boolean sales) throws IOException;
	
	void setSort(int sort);
	
	boolean removeAllProducts() throws IOException;
	
	boolean removeProduct(String catalogtoSearch) throws IOException;
	
	void undo() throws IOException;
	
	String printProductByCatalogNumber(String catalogNumber);
	
	String getAllProductString();
	
	String getAllProfitsString();
	
	boolean checkFromFile();
	
	boolean checkOnlySort();
	
	boolean sendMSG();
	
	ArrayList<String> getNames();
}

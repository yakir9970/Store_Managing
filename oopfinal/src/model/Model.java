package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Model {
	private int sortBy;
	private Map<String, Product> theMap;
	private final String FILE_NAME = "products.txt";
	private Memento memento;
	private RandomAccessFile raf;
	private ArrayList<String> customerNames;
	private ArrayList<Customer> customerConfirms;
	private static Model singleInstance = null;

	private Model() throws IOException {
		customerNames = new ArrayList<String>();
		customerConfirms = new ArrayList<Customer>();
		File file = new File(FILE_NAME);
		try {
			raf = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		if (file.exists())
			readMapFromFile();
	}

	public static Model getInstance() throws IOException {
		if (singleInstance == null)
			singleInstance = new Model();

		return singleInstance;
	}

	public void UpdateCatalogNum(String catalog, String name, int storeCost, int customerCost, String customerName,
			String phoneNumber, boolean updates) throws IOException {
		memento = getMemento();
		Customer customer = new Customer(customerName, phoneNumber, updates);
		Product product = new Product(name, storeCost, customerCost, customer);
		if (!theMap.containsKey(catalog)) { /// no such catalog, need to create new
			theMap.put(catalog, product);
		} else {
			for (Map.Entry<String, Product> e : theMap.entrySet()) {
				if (e.getKey().compareTo(catalog) == 0) {
					e.setValue(product);
				}
			}
		}
	
		/// saving the map to file
		saveMapToFile();

	}

	public void setSort(int sort) {
		sortBy = sort;
		if (sortBy == 1) {
			theMap = new TreeMap<String, Product>(new SortByAsc());
		} else if (sortBy == 2) {
			theMap = new TreeMap<String, Product>(new SortByDesc());
		} else {
			theMap = new LinkedHashMap<String, Product>();
		}
	}

	private void saveMapToFile() throws IOException {
		raf.seek(0);
		int counter = 0;
		while (raf.getFilePointer() < raf.length()) {
			raf.write(0);
			counter++;
		}
		raf.setLength(raf.length() - counter);
		raf.seek(0);
		try {

			raf.writeInt(sortBy);
			String str = "";
			for (Map.Entry<String, Product> e : theMap.entrySet()) {
				str = e.getKey() + "," + e.getValue().stringToFile();
				raf.writeUTF(str);
			}

		} catch (Exception e) {

		}

	}

	private class FileIterator implements Iterator<String> {

		long writePos;

		public boolean hasNext() {

			try {
				return raf.getFilePointer() < raf.length();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		public String next() {

			try {
				writePos = raf.getFilePointer();
				return raf.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

		public void remove() {

			try {
				byte[] data = new byte[(int) (raf.length() - raf.getFilePointer())];
				long size;
				size = (raf.getFilePointer() - writePos);
				raf.read(data);
				raf.seek(writePos);
				raf.write(data);
				raf.setLength(raf.length() - size);
				raf.seek(writePos);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	public boolean removeAllProducts() throws IOException {
		FileIterator it = new FileIterator();
		raf.seek(0);
		if (it.hasNext()) {
			raf.readInt();
		} else
			return false;
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		theMap.clear();
		raf.setLength(0);
		return true;
	}

	public boolean removeProduct(String catalogtoSearch) throws IOException {
		FileIterator it = new FileIterator();
		raf.seek(0);
		if (it.hasNext()) {
			raf.readInt();
		} else
			return false;
		while (it.hasNext()) {
			String str = (String) it.next();
			String[] info = str.split(",");
			String catalog = info[0];
			if (catalog.compareTo(catalogtoSearch) == 0) {
				it.remove();
				readMapFromFile();
				return true;
			}
		}
		return false;
	}

	private void readMapFromFile() throws IOException {
		FileIterator it = new FileIterator();
		raf.seek(0);
		if (it.hasNext()) {
			sortBy = raf.readInt();
			setSort(sortBy);
		}
		while (it.hasNext()) {
			String str = (String) it.next();
			String[] info = str.split(",");
			String catalog = info[0];
			Customer customer = new Customer(info[4], info[5], Boolean.parseBoolean(info[6]));
			Product product = new Product(info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), customer);
			theMap.put(catalog, product);
		}


	}

	public Memento getMemento() {
		return new Memento(theMap);
	}

	public void setMemento() throws IOException {

		this.theMap.clear();
		this.theMap.putAll(memento.getTreeMap());
		saveMapToFile();
	}

	public String printProductByCatalogNumber(String catalogNumber) {
		String res = null;
		for (Map.Entry<String, Product> e : theMap.entrySet()) {
			if (e.getKey().compareTo(catalogNumber) == 0) {
				res = "Product catalog number: ,";
				res += e.getKey();
				res += e.getValue().toString();
				return res;
			}
		}
		res = "No Such Product!";
		return res;
	}

	public String getAllProductString() {
		StringBuffer str = new StringBuffer("Here are all the products:\n");

		for (Map.Entry<String, Product> e : theMap.entrySet()) {
			str.append("Catalog Number: " + e.getKey() + e.getValue().toString());
			str.append("---------------------------------------------------------------------------------\n");
		}
		return str.toString();
	}

	public String getAllProfitsString() {
		StringBuffer str = new StringBuffer("Here are all the profits:\n");
		int sum = 0;

		for (Map.Entry<String, Product> e : theMap.entrySet()) {
			str.append("Catalog Number: " + e.getKey() + " Product Profit: " + e.getValue().getProfit() + "\n");
			sum += e.getValue().getProfit();
		}

		str.append("Store Total Profit Is: " + sum);
		return str.toString();
	}

	public boolean checkFromFile() {
		return sortBy != 0;
	}

	public boolean checkOnlySort() {
		if ((sortBy != 3 && theMap.size() == 0))
			return true;
		return false;
	}

	public void confirm(String name) {
		customerNames.add(name);
	}

	public boolean sendMSG() {
		customerConfirms.clear();
		customerNames.clear();
		updateCustomerList();
		if (customerConfirms.size() < 1)
			return false;
		for (int i = 0; i < customerConfirms.size(); i++) {
			customerConfirms.get(i).update(this, "Update!");
		}
		return true;
	}

	public ArrayList<String> getNames() {
		return this.customerNames;
	}

	private void updateCustomerList() {
		for (Map.Entry<String, Product> e : theMap.entrySet()) {
			if (e.getValue().getCustomer().getUpdates())
				customerConfirms.add(e.getValue().getCustomer());
		}

	}
}

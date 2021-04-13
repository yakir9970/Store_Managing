package model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Memento {
	private Map<String, Product> theMap;

	public Memento(Map<String, Product> map) {
		this.theMap = new TreeMap<String, Product>();
		this.theMap.putAll(map);

	}



	public Map<String, Product> getTreeMap() {
		return theMap;
	}



}

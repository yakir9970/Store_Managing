package model.command;

import model.Model;

public class PrintProductByCatalogNumber {
	private Model model;

	public PrintProductByCatalogNumber(Model model) {
		this.model = model;
	}
	
	public 	String printProductByCatalogNumber(String catalogNumber) {
		return model.printProductByCatalogNumber(catalogNumber);
	}
}

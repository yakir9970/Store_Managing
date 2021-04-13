package model.command;

import java.io.IOException;

import model.Model;

public class AddProducsCommand {
	private Model model;

	public AddProducsCommand(Model model) {
		this.model = model;
	}
	
	public void addProducts(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String Cphone, boolean sales) throws IOException {
		model.UpdateCatalogNum(catalogNumber, name, priceForStore, priceForCustomer, Cname, Cphone, sales);
	}
}

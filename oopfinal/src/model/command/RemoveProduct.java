package model.command;

import java.io.IOException;

import model.Model;

public class RemoveProduct {
	private Model model;

	public RemoveProduct(Model model) {
		this.model = model;
	}
	
	public boolean removeProduct(String catalogtoSearch) throws IOException {
		return model.removeProduct(catalogtoSearch);
	}
}

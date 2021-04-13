package model.command;

import java.io.IOException;

import model.Model;

public class RemoveAllProducts {
	private Model model;

	public RemoveAllProducts(Model model) {
		this.model = model;
	}
	
	public boolean removeAllProducts() throws IOException {
		return model.removeAllProducts();
	}
}

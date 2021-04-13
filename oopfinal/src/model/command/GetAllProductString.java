package model.command;

import model.Model;

public class GetAllProductString {
	private Model model;

	public GetAllProductString(Model model) {
		this.model = model;
	}
	
	public String getAllProductString() {
		return model.getAllProductString();
	}
}

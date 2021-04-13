package model.command;

import model.Model;

public class GetAllProfitsString {
	private Model model;

	public GetAllProfitsString(Model model) {
		this.model = model;
	}
	
	public String getAllProfitsString() {
		return model.getAllProfitsString();
	}
}

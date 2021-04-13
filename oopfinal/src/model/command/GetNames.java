package model.command;

import java.util.ArrayList;

import model.Model;

public class GetNames {
	private Model model;

	public GetNames(Model model) {
		this.model = model;
	}
	
	public 	ArrayList<String> getNames() {
		return model.getNames();
	}
}

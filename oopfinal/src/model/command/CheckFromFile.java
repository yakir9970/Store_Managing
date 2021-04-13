package model.command;

import model.Model;

public class CheckFromFile {
	private Model model;

	public CheckFromFile(Model model) {
		this.model = model;
	}
	
	public 	boolean checkFromFile() {
		return model.checkFromFile();
	}
}

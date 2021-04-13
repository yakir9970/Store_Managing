package model.command;

import java.io.IOException;

import model.Model;

public class Undo {
	private Model model;

	public Undo(Model model) {
		this.model = model;
	}
	
	public 	void undo() throws IOException {
		model.setMemento();
	}
}

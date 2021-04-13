package model.command;

import model.Model;

public class SetSort {
	private Model model;

	public SetSort(Model model) {
		this.model = model;
	}
	
	public 	void setSort(int sort) {
		model.setSort(sort);
	}
	
}

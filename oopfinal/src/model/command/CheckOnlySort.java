package model.command;

import model.Model;

public class CheckOnlySort {
	private Model model;

	public CheckOnlySort(Model model) {
		this.model = model;
	}
	
	public 	boolean checkOnlySort() {
		return model.checkOnlySort();
	}
}

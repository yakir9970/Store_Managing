package model.command;

import model.Model;

public class SendMSG {
	private Model model;

	public SendMSG(Model model) {
		this.model = model;
	}
	
	public 	boolean sendMSG() {
		return model.sendMSG();
	}
}

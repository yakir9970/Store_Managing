package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.command.AddProducsCommand;
import model.command.AllCommands;
import model.command.CheckFromFile;
import model.command.CheckOnlySort;
import model.command.GetAllProductString;
import model.command.GetAllProfitsString;
import model.command.GetNames;
import model.command.PrintProductByCatalogNumber;
import model.command.RemoveAllProducts;
import model.command.RemoveProduct;
import model.command.SendMSG;
import model.command.SetSort;
import model.command.Undo;
import view.PrintOne;

public class Controller {
	private model.Model theModel;
	private view.View theView;
	private view.PrintOne printOne;
	private view.printAll printAll;
	private view.removeFromFile removeFromFile;
	private view.showProfits showProfits;
	private view.PrintCustomersNames showNames;
	public Controller(model.Model m, view.View v) {
		theModel = m;
		theView = v;
		AllCommands allCommands=new AllCommands(theModel);
		
		if(!(allCommands.checkFromFile()))//file empty
		{
			theView.unlockSave();
			theView.clearView();

		}
		else//file with products
		{
			theView.lockSave();
			
		}

		EventHandler<ActionEvent> saveASCPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				theView.saveASC(allCommands);

			}
		};
		theView.addEventTosaveASC(saveASCPressed);

		EventHandler<ActionEvent> saveDESCPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				theView.saveDESC(allCommands);

			}
		};
		theView.addEventTosaveDESC(saveDESCPressed);

		EventHandler<ActionEvent> saveByOrderPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				theView.saveByOrder(allCommands);

			}
		};
		theView.addEventTosaveByOrder(saveByOrderPressed);

		EventHandler<ActionEvent> saveChangesPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				theView.saveChanges(allCommands);
				if((allCommands.checkOnlySort()))//file empty
					theView.emptyLock();
				else
					theView.notEmptyUnlock();
			}
		};

		theView.addEventTosaveChanges(saveChangesPressed);

		EventHandler<ActionEvent> PrintOnePressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage secondStage = new Stage();
				printOne = new PrintOne(secondStage);

				EventHandler<ActionEvent> mainMenuPressed = new EventHandler<ActionEvent>() {

					public void handle(ActionEvent arg0) {
						printOne.mainMenu();
					}
				};
				printOne.addEventToMainMenu(mainMenuPressed);

				EventHandler<ActionEvent> printPressed = new EventHandler<ActionEvent>() {

					public void handle(ActionEvent arg0) {
						printOne.print(allCommands);
					}
				};
				printOne.addEventToPrint(printPressed);
			}
		};

		theView.addEventToPrintOne(PrintOnePressed);

		EventHandler<ActionEvent> PrintAllPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage secondStage = new Stage();
				String res = allCommands.getAllProductString();
				printAll = new view.printAll(secondStage, res);

				EventHandler<ActionEvent> mainMenuPressed = new EventHandler<ActionEvent>() {

					public void handle(ActionEvent arg0) {
						printAll.mainMenu();
					}
				};
				printAll.addEventToMainMenu(mainMenuPressed);

			}
		};

		theView.addEventToPrintAll(PrintAllPressed);

		EventHandler<ActionEvent> undoPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				theView.undo(allCommands);
				if(allCommands.checkOnlySort())//file with no products but with sort
					theView.emptyLock();
				else
					theView.notEmptyUnlock();
			}
		};
		theView.addEventToUndo(undoPressed);

		EventHandler<ActionEvent> removeFromFilePressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage secondStage = new Stage();
				removeFromFile = new view.removeFromFile(secondStage);

				EventHandler<ActionEvent> mainMenuPressed = new EventHandler<ActionEvent>() {

					public void handle(ActionEvent arg0) {
						removeFromFile.mainMenu();
					}
				};
				removeFromFile.addEventToMainMenu(mainMenuPressed);

				EventHandler<ActionEvent> removePressed = new EventHandler<ActionEvent>() {

					public void handle(ActionEvent arg0) {
						try {
							removeFromFile.remove(allCommands);
							if(allCommands.checkOnlySort())//file with no products but with sort
								theView.emptyLock();
							else
								theView.notEmptyUnlock();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				removeFromFile.addEventToRemove(removePressed);
			}
		};
		theView.addEventToremoveFromFile(removeFromFilePressed);
		
		EventHandler<ActionEvent> removeAllPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				try {
					theView.removeAll(allCommands);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		theView.addEventToRemoveAll(removeAllPressed);
		
		EventHandler<ActionEvent> showProfitsPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage secondStage = new Stage();
				String res = allCommands.getAllProfitsString();
				showProfits = new view.showProfits(secondStage, res);

				EventHandler<ActionEvent> mainMenuPressed = new EventHandler<ActionEvent>() {

					public void handle(ActionEvent arg0) {
						showProfits.mainMenu();
					}
				};
				showProfits.addEventToMainMenu(mainMenuPressed);

			}
		};

		theView.addEventToshowProfits(showProfitsPressed);
		
		EventHandler<ActionEvent> sendUpdatesPressed = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				theView.sendUpdates(allCommands);

			}
		};

		theView.addEventToSendUpdates(sendUpdatesPressed);
		
		EventHandler<ActionEvent> showNamesPressed = new EventHandler<ActionEvent>() {

		public void handle(ActionEvent event) {
			Stage secondStage = new Stage();
			ArrayList<String> names = allCommands.getNames();
			showNames = new view.PrintCustomersNames(secondStage);
			showNames.setCustomersNames(names);

			EventHandler<ActionEvent> mainMenuPressed = new EventHandler<ActionEvent>() {

				public void handle(ActionEvent arg0) {
					showNames.stopShowingNames();
					showNames.mainMenu();
				}
			};
			showNames.addEventToMainMenu(mainMenuPressed);

		}
	};
	theView.addEventToshowNames(showNamesPressed);

	}
	
	

}

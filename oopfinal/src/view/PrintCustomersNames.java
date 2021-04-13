package view;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Model;

public class PrintCustomersNames{
	private ArrayList<Label> customerNames;
	private Button mainMenu;
	private VBox allNamesBox;
	private Stage stage;
	private BorderPane bp;
	private ScrollPane printPane;
	private int count;

	public PrintCustomersNames(Stage primaryStage) {

		mainMenu = new Button("Return To Main Menu");
		printPane = new ScrollPane();
		customerNames = new ArrayList<Label>();
		//printPane.setContent(customerNames);
		printPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		printPane.setStyle("-fx-background-color:transparent;");
		allNamesBox = new VBox();
		printPane.setContent(allNamesBox);
		bp=new BorderPane();
		bp.setLeft(mainMenu);
		bp.setCenter(printPane);
		bp.setMargin(mainMenu, new Insets(200, 0, 0, 50));
		bp.setMargin(printPane, new Insets(100, 0, 0, 150));
		
		stage = primaryStage;
		Scene theScene = new Scene(bp, 2000, 1000);
		stage.setScene(theScene);
		stage.show();
	}

	public void mainMenu() {
		stage.close();
	}
	
	public void showCustomers(boolean ok) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					for (int i = 0; i < customerNames.size(); i++) {
						
						Platform.runLater(() ->{
						allNamesBox.getChildren().add(customerNames.get(count));
						allNamesBox.setVisible(true);
						count++;
						});
						
						Thread.sleep(2000);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
		if(ok)
			thread.start();
		else
			thread.interrupt();
	}
	
	public void stopShowingNames() {
		showCustomers(false);
	}
	public void setCustomersNames(ArrayList<String> names) {
		for (int i = 0; i < names.size(); i++) {
			customerNames.add(new Label(names.get(i)));
			customerNames.get(i).setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		}
		showCustomers(true);
	}

	public void addEventToMainMenu(EventHandler<ActionEvent> mainMenuPressed) {
		mainMenu.setOnAction(mainMenuPressed);

	}

}

package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;
import model.command.AllCommands;
import model.command.RemoveProduct;

public class removeFromFile {
	private Stage stage;
	private BorderPane bp;
	private Text catalog;
	private TextField catalogTextField;
	private VBox catalogBox;
	private Button mainMenu;
	private Button remove;
	private Label printText;

	public removeFromFile(Stage primaryStage) {

		catalog = new Text("Product Catalog Number");
		catalogTextField = new TextField();
		catalogTextField.setMaxSize(200, 20);
		remove = new Button("Remove Product");
		catalogBox = new VBox();
		catalogBox.getChildren().addAll(catalog, catalogTextField, remove);
		catalogBox.setSpacing(50);
		catalogBox.setMargin(catalog, new Insets(0, 0, 0, 30));
		catalogBox.setMargin(remove, new Insets(0, 0, 0, 60));
		mainMenu = new Button("Return To Main Menu");
		printText = new Label();
		printText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

		bp = new BorderPane();
		bp.setTop(catalogBox);
		bp.setLeft(mainMenu);
		bp.setBottom(printText);
		bp.setMargin(catalogBox, new Insets(150, 0, 0, 900));
		bp.setMargin(mainMenu, new Insets(0, 0, 0, 200));
		bp.setMargin(printText, new Insets(0, 0, 400, 300));

		stage = primaryStage;
		Scene theScene = new Scene(bp, 2000, 1000);
		stage.setScene(theScene);
		stage.show();
	}

	public void mainMenu() {
		stage.close();
	}

	public void addEventToMainMenu(EventHandler<ActionEvent> mainMenuPressed) {
		mainMenu.setOnAction(mainMenuPressed);

	}

	public void remove(AllCommands command) throws IOException {
		if (catalogTextField.getText().isEmpty()) {
			Alert msg = new Alert(AlertType.ERROR);
			msg.setContentText("Must fill Product Catalog Number!");
			msg.show();
			return;
		}
		String catalogNumber = catalogTextField.getText();
		if (!command.removeProduct(catalogNumber))
			printText.setText("No Such Product!");
		else
			printText.setText("Product Removed!");

	}

	public void addEventToRemove(EventHandler<ActionEvent> removePressed) {
		remove.setOnAction(removePressed);

	}
}

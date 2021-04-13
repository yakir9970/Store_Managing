package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;

public class printAll {
	private Stage stage;
	private BorderPane bp;
	private Button mainMenu;
	private ScrollPane printPane;
	private Label printText;

	public printAll(Stage primaryStage,String res) {

		mainMenu = new Button("Return To Main Menu");
		printPane = new ScrollPane();
		printText=new Label(res);
		printText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		printPane.setContent(printText);
		printPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		printPane.setStyle("-fx-background-color:transparent;");
		
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

	public void addEventToMainMenu(EventHandler<ActionEvent> mainMenuPressed) {
		mainMenu.setOnAction(mainMenuPressed);

	}
}

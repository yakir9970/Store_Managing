package Default;

import controller.Controller;
import model.Model;
import view.View;
import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model theModel = Model.getInstance();
		View theView = new View(primaryStage);
		Controller theController = new Controller(theModel, theView);
	}
}

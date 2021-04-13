package view;

import model.Model;
import model.command.AddProducsCommand;
import model.command.AllCommands;
import model.command.RemoveAllProducts;
import model.command.SendMSG;
import model.command.SetSort;
import model.command.Undo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {
	
	private Stage stage;
	private BorderPane bp;
	private Label storeName;
	private Button saveASC;
	private Button saveDESC;
	private Button saveByOrder;
	private Button printProducts;
	private Button printOne;
	private Button saveChanges;
	private Button undo;
	private Button removeFromFile;
	private Button removeAll;
	private Button showProfits;	
	private Button sendUpdates;
	private Button showNames;
	private VBox saveBox;
	private TextField productName;
	private TextField productSerial;
	private TextField priceForStore;
	private TextField priceForCustomer;
	private TextField customerName;
	private TextField customerPhone;
	private Text productNameText;
	private Text productSerialText;
	private Text priceForStoreText;
	private Text priceForCustomerText;
	private Text customerNameText;
	private Text customerPhoneText;
	private Text customerSalesText;
	private CheckBox customerSales;
	private HBox texts;
	private HBox textFields;
	private HBox saveUndo;
	private VBox textsAndFields;
	
	
	public View(Stage theStage) {

		storeName=new Label("Our Store");
		storeName.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		
		saveASC=new Button("Save Products ASC Order");
		saveDESC=new Button("Save Products DESC Order");
		saveByOrder=new Button("Save Products By Insert Order");
		printProducts=new Button("Print All Products");
		saveChanges=new Button("Save Changes");
		undo=new Button("Undo");
		undo.setDisable(true);
		printOne=new Button("Print Product By Catalog");
		removeFromFile=new Button("Remove Product From File By Catalog");
		removeAll=new Button("Remove All Products");
		showProfits=new Button("Show Store Profits");
		sendUpdates=new Button("Send Updates To Customers");
		showNames = new Button("Print confirmed customers names");
		showNames.setDisable(true);

		
		saveBox=new VBox();
		saveBox.getChildren().addAll(saveASC,saveDESC,saveByOrder,printOne,printProducts,removeFromFile,removeAll,showProfits,sendUpdates,showNames);
		saveBox.setSpacing(20);
		
		productName=new TextField();
		productSerial=new TextField();
		priceForStore=new TextField();
		priceForCustomer=new TextField();
		customerName=new TextField();
		customerPhone=new TextField();
		customerSales=new CheckBox();
		
		textFields=new HBox();
		textFields.getChildren().addAll(productSerial,productName,priceForStore,priceForCustomer,customerName,customerPhone,customerSales);
		textFields.setSpacing(60);

		productNameText=new Text("Product Name");
		productSerialText=new Text("Product Catalog Number");
		priceForStoreText=new Text("Product Price For Store");
		priceForCustomerText=new Text("Product Price For Customer");
		customerNameText=new Text("Customer Name");
		customerPhoneText=new Text("Customer Phone");
		customerSalesText=new Text("Customer Want Sales?");
		
		texts=new HBox();
		texts.getChildren().addAll(productSerialText,productNameText,priceForStoreText,priceForCustomerText,customerNameText,customerPhoneText,customerSalesText);
		//texts.setSpacing(20);
		texts.setMargin(productNameText, new Insets(0,0,0,100));
		texts.setMargin(productSerialText, new Insets(0,0,0,10));
		texts.setMargin(priceForStoreText, new Insets(0,0,0,100));
		texts.setMargin(priceForCustomerText, new Insets(0,0,0,85));
		texts.setMargin(customerNameText, new Insets(0,0,0,95));
		texts.setMargin(customerPhoneText, new Insets(0,0,0,120));
		texts.setMargin(customerSalesText, new Insets(0,0,0,50));

		textsAndFields=new VBox();
		textsAndFields.getChildren().addAll(texts,textFields);
		textsAndFields.setSpacing(20);
		
		saveUndo=new HBox();
		saveUndo.getChildren().addAll(saveChanges,undo);
		saveUndo.setSpacing(50);

		bp=new BorderPane();
		bp.setCenter(saveBox);
		bp.setRight(textsAndFields);
		bp.setTop(storeName);
		bp.setBottom(saveUndo);
		
		bp.setMargin(storeName, new Insets(20,0,0,950));
		bp.setMargin(saveBox, new Insets(20,0,0,20));
		bp.setMargin(textsAndFields, new Insets(300,250,0,0));
		bp.setMargin(saveUndo, new Insets(0,0,200,900));

		//clearView();
		lockSave();
		
		stage=theStage;
		Scene theScene = new Scene(bp,2000,1000);
		stage.setScene(theScene);
		stage.show();
	}
	
	public void addEventTosaveASC(EventHandler<ActionEvent> saveASCPressed) {
		saveASC.setOnAction(saveASCPressed);
	}
	
	public void saveASC(AllCommands command) {
		emptyLock();
		lockSaveButtons();
		showView();
		undo.setDisable(true);
		command.setSort(1);
	}
	
	public void addEventTosaveDESC(EventHandler<ActionEvent> saveDESCPressed) {
		saveDESC.setOnAction(saveDESCPressed);
	}
	
	public void saveDESC(AllCommands command) {
		emptyLock();
		lockSaveButtons();
		showView();
		undo.setDisable(true);
		command.setSort(2);
	}
	
	public void addEventTosaveByOrder(EventHandler<ActionEvent> saveByOrderPressed) {
		saveByOrder.setOnAction(saveByOrderPressed);
	}
	
	public void saveByOrder(AllCommands command) {
		emptyLock();
		lockSaveButtons();
		showView();
		undo.setDisable(true);
		command.setSort(3);
	}
	
	public void addEventToPrintOne(EventHandler<ActionEvent> printOnePressed) {
		printOne.setOnAction(printOnePressed);
	}
	
	public void addEventToPrintAll(EventHandler<ActionEvent> printAllPressed) {
		printProducts.setOnAction(printAllPressed);
	}
	
	
	public void addEventTosaveChanges(EventHandler<ActionEvent> saveChangesPressed) {
		saveChanges.setOnAction(saveChangesPressed);
	}
	
	public void saveChanges(AllCommands command) {
		int storePrice=-1,customerPrice=-1;
		if(productSerial.getText().isEmpty())
		{
			Alert msg = new Alert(AlertType.ERROR);
			msg.setContentText("Must fill Product Serial Number!");
			msg.show();
			return;
		}
		try {
			
			if(priceForStore.getText().isEmpty())
			{
				storePrice=0;
			}
			if(priceForCustomer.getText().isEmpty())
			{
				customerPrice=0;
			}
			
			if(Integer.parseInt(priceForStore.getText())<0)
				{
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Must fill Positive Price!");
				msg.show();
				return;
				}
			if(Integer.parseInt(priceForCustomer.getText())<0)
			{
			Alert msg = new Alert(AlertType.ERROR);
			msg.setContentText("Must fill Positive Price!");
			msg.show();
			return;
			}
		} catch (Exception e) {
			if(!(priceForStore.getText().isEmpty()&&priceForCustomer.getText().isEmpty())) {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Price Must Be Integer!");
				msg.show();
				return;
			}
				
			
		}
		String catalog=productSerial.getText();
		if(storePrice!=0)
			storePrice=Integer.parseInt(priceForStore.getText());
		if(customerPrice!=0)
			customerPrice=Integer.parseInt(priceForCustomer.getText());
		String pName=productName.getText();
		if(pName==null)
			pName=" ";
		String cName=customerName.getText();
		if(cName==null)
			cName=" ";
		String cPhone=customerPhone.getText();
		if(cPhone==null)
			cPhone=" ";
		boolean sales=false;
		if(!customerSales.isSelected())
			sales=false;
		else
			if(customerName.getText().isEmpty()||customerPhone.getText().isEmpty())
			{
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Must Fill All Customer Details!");
				msg.show();
				return;
			}
			else
				sales=true;
		
		try {
			command.addProducts(catalog, pName, storePrice, customerPrice, cName, cPhone, sales);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clearInsert();
		undo.setDisable(false);
		Alert msg = new Alert(AlertType.CONFIRMATION);
		msg.setContentText("Product Added!");
		msg.show();
	}
	
	public void addEventToUndo(EventHandler<ActionEvent> undoPressed) {
		undo.setOnAction(undoPressed);
	}
	
	public void undo(AllCommands command) {
		try {
			command.undo();
			Alert msg = new Alert(AlertType.CONFIRMATION);
			msg.setContentText("Undodi worked!");
			msg.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		undo.setDisable(true);
	}
	
	public void addEventToremoveFromFile(EventHandler<ActionEvent> removeFromFilePressed) {
		removeFromFile.setOnAction(removeFromFilePressed);
	}
	
	public void addEventToRemoveAll(EventHandler<ActionEvent> removeAllPressed) {
		removeAll.setOnAction(removeAllPressed);
	}
	
	public void addEventToshowProfits(EventHandler<ActionEvent> showProfitsPressed) {
		showProfits.setOnAction(showProfitsPressed);
	}
	
	
	public void removeAll(AllCommands command) throws IOException {
		if(!command.removeAllProducts())
		{
			Alert msg = new Alert(AlertType.ERROR);
			msg.setContentText("Could Not Delete Products!");
			msg.show();
		}
		else
		{
			Alert msg = new Alert(AlertType.CONFIRMATION);
			msg.setContentText("All Products Removed!");
			msg.show();
			unlockSave();
			clearView();
			showNames.setDisable(true);
		}
		
	}
	
	public void addEventToSendUpdates(EventHandler<ActionEvent> sendUpdatesPressed) {
		sendUpdates.setOnAction(sendUpdatesPressed);
	}
	
	public void sendUpdates(AllCommands command) {
		if(command.sendMSG()){
		Alert msg = new Alert(AlertType.CONFIRMATION);
		msg.setContentText("Updates Sent!");
		msg.show();
		showNames.setDisable(false);
		sendUpdates.setDisable(true);
		}
		else {
			Alert msg = new Alert(AlertType.ERROR);
			msg.setContentText("0 Customers wants updates!");
			msg.show();
			showNames.setDisable(true);
		}
	}
	
	
	private void clearInsert() {
		productSerial.clear();
		priceForStore.clear();
		priceForCustomer.clear();
		productName.clear();
		customerName.clear();
		customerPhone.clear();
		customerSales.setSelected(false);
	}
	
	public void lockSaveButtons() {
		saveASC.setDisable(true);
		saveDESC.setDisable(true);
		saveByOrder.setDisable(true);
	}
	
	public void unlockSaveButtons() {
		saveASC.setDisable(false);
		saveDESC.setDisable(false);
		saveByOrder.setDisable(false);
	}

	public void lockSave() {
		saveASC.setDisable(true);
		saveDESC.setDisable(true);
		saveByOrder.setDisable(true);
		printOne.setDisable(false);
		printProducts.setDisable(false);
		removeAll.setDisable(false);
		removeFromFile.setDisable(false);
		showProfits.setDisable(false);
		sendUpdates.setDisable(false);
		//showNames.setDisable(false);
	}
	
	public void unlockSave() {
		saveASC.setDisable(false);
		saveDESC.setDisable(false);
		saveByOrder.setDisable(false);
		printOne.setDisable(true);
		printProducts.setDisable(true);
		removeAll.setDisable(true);
		removeFromFile.setDisable(true);
		showProfits.setDisable(true);
		sendUpdates.setDisable(true);
		//showNames.setDisable(true);
	}
	
	public void clearView() {
		saveChanges.setVisible(false);
		undo.setVisible(false);
		textsAndFields.setVisible(false);
	}
	
	public void showView() {
		undo.setVisible(true);
		saveChanges.setVisible(true);
		textsAndFields.setVisible(true);
	}

	public void addEventToshowNames(EventHandler<ActionEvent> showNamesPressed) {
		showNames.setOnAction(showNamesPressed);
	}

	public void emptyLock() {
		printOne.setDisable(true);
		printProducts.setDisable(true);
		removeAll.setDisable(true);
		removeFromFile.setDisable(true);
		showProfits.setDisable(true);
		sendUpdates.setDisable(true);
		showNames.setDisable(true);
	}

	public void notEmptyUnlock() {
		printOne.setDisable(false);
		printProducts.setDisable(false);
		removeAll.setDisable(false);
		removeFromFile.setDisable(false);
		showProfits.setDisable(false);
		sendUpdates.setDisable(false);
		//showNames.setDisable(false);		
	}
	
}

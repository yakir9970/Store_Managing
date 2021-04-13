package model.command;

import java.io.IOException;
import java.util.ArrayList;

import model.Model;

public class AllCommands implements Commands{
	
	private AddProducsCommand addPrductsCommand;
	private CheckFromFile checkFromFile;
	private CheckOnlySort checkOnlySort;
	private GetAllProductString getAllProductString;
	private GetAllProfitsString getAllProfitsString;
	private GetNames getNames;
	private PrintProductByCatalogNumber printProductByCatalogNumber;
	private RemoveAllProducts removeAllProducts;
	private RemoveProduct removeProduct;
	private SendMSG sendMSG;
	private SetSort setSort;
	private Undo undo;
	
	public AllCommands(Model theModel) {
		this.addPrductsCommand=new AddProducsCommand(theModel);
		this.checkFromFile=new CheckFromFile(theModel);
		this.checkOnlySort=new CheckOnlySort(theModel);
		this.getAllProductString=new GetAllProductString(theModel);
		this.getAllProfitsString=new GetAllProfitsString(theModel);
		this.getNames=new GetNames(theModel);
		this.printProductByCatalogNumber=new PrintProductByCatalogNumber(theModel);
		this.removeAllProducts=new RemoveAllProducts(theModel);
		this.removeProduct=new RemoveProduct(theModel);
		this.sendMSG=new SendMSG(theModel);
		this.setSort=new SetSort(theModel);
		this.undo=new Undo(theModel);
	}

	@Override
	public void addProducts(String catalogNumber, String name, int priceForStore, int priceForCustomer, String Cname,
			String Cphone, boolean sales) throws IOException {
		addPrductsCommand.addProducts(catalogNumber, name, priceForStore, priceForCustomer, Cname, Cphone, sales);
	}

	@Override
	public void setSort(int sort) {
		setSort.setSort(sort);
	}

	@Override
	public boolean removeAllProducts() throws IOException {
		return removeAllProducts.removeAllProducts();
	}

	@Override
	public boolean removeProduct(String catalogtoSearch) throws IOException {
		return removeProduct.removeProduct(catalogtoSearch);
	}

	@Override
	public void undo() throws IOException {
		undo.undo();
	}

	@Override
	public String printProductByCatalogNumber(String catalogNumber) {
		return printProductByCatalogNumber.printProductByCatalogNumber(catalogNumber);
	}

	@Override
	public String getAllProductString() {
		return getAllProductString.getAllProductString();
	}

	@Override
	public String getAllProfitsString() {
		return getAllProfitsString.getAllProfitsString();
	}

	@Override
	public boolean checkFromFile() {
		return checkFromFile.checkFromFile();
	}

	@Override
	public boolean checkOnlySort() {
		return checkOnlySort.checkOnlySort();
	}

	@Override
	public boolean sendMSG() {
		return sendMSG.sendMSG();
	}

	@Override
	public ArrayList<String> getNames() {
		return getNames.getNames();
	}

}

package model;

public class Product {
	
	
	private String name;
	private int storeCost;
	private int customerCost;
	private Customer customer;
	
	
	public Product(String name, int storeCost, int customerCost, Customer customer) {
		this.name = name;
		this.customerCost = customerCost;
		this.storeCost = storeCost;
		this.customer = new Customer();
		this.customer = customer;
	}
	
	
	public String getProductName() {
		return this.name;
	}
	
	public int getStroreCost() {
		return this.storeCost;
	}
	public int getCustomerCost() {
		return this.customerCost;
	}
	public Customer getProductCustomer() {
		return this.customer;
	}
	
	public String stringToFile() {
		return name+ "," + storeCost + "," + customerCost + "," + customer.stringToFile();
	}
	
	@Override
	public String toString() {
		return "   Product name:" + name + ",   store cost:" + storeCost + ",   customer cost:" + customerCost + "\n"
				+ customer.toString();
	}


	public int getProfit() {
		return (customerCost-storeCost);
	}


	public Customer  getCustomer() {
		return this.customer;
	}


}

package com.example.model;

public class Customer {
	 public String name;
	  public String id;
	  public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	static private Customer customer=null;
	  static public Customer getInstance(){
		if(Customer.customer==null)
		{
			Customer.customer=new Customer();
		}
		  
		  return Customer.customer;
		  
	  }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
}

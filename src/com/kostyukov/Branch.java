package com.kostyukov;

import java.util.ArrayList;

public class Branch
{
	private String name;
	private ArrayList<Customer> customers = new ArrayList<>();
	
	public Branch(String branchName)
	{
		this.name = branchName;
	}
	
	public void addCustomer(String customerName, double initialTransaction)
	{
		customers.add(new Customer(customerName, initialTransaction));
	}
	
	public void addTransaction(String customerName, double newTransaction)
	{
		customers.get(findCustomer(customerName)).addTransaction(newTransaction);
	}
	
	private int findCustomer(String name)
	{
		int customerID;
		for (int i = 0; i <= customers.size(); i++)
		{
			if (customers.get(i).getName().equals(name))
				return i;
				
		}
		return -1;
	}
	
	public Customer getCustomer(String customerName)
	{
		return findCustomer(customerName) >= 0 ? customers.get(findCustomer(customerName)) : null;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<Customer> getCustomers()
	{
		return customers;
	}
}
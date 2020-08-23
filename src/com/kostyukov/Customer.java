package com.kostyukov;

import java.util.ArrayList;

public class Customer
{
	private String name;
	
	private ArrayList<Double> transactions = new ArrayList<Double>();
	
	
	public Customer(String name, double initialTransactionAmount)
	{
		this.name = name;
		transactions.add(initialTransactionAmount);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public ArrayList<Double> getTransactions()
	{
		return transactions;
	}
	
	public void addTransaction(double transactionAmount)
	{
		transactions.add(transactionAmount);
	}
}
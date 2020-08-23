package com.kostyukov;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank
{
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<Branch> branches = new ArrayList<>();
	private static Branch selectedBranch;
	
	public static void main(String[] a)
	{
		boolean exit = false;
		printOptions();
		do
		{
			if (selectedBranch != null)
				System.out.println("Selected branch is: " + selectedBranch.getName());
			
			int inputOption = scanner.nextInt();
			scanner.nextLine();
			
			switch (inputOption){
				case 0 -> printOptions();
				case 1 -> printBranches();
				case 2 -> addBranch();
				case 3 -> addCustomer();
				case 4 -> addTransaction();
				case 5 -> setSelectedBranch();
				case 6 -> deleteBranch();
				case 7 -> exit = true;
			}
		}while (!exit);
	}
	
	private static void printOptions()
	{
		System.out.println("\nPress: ");
		System.out.println("\n 0. To print a list of commands.");
		System.out.println("\n 1. To print a list of branches.");
		System.out.println("\n 2. To add a new branch.");
		System.out.println("\n 3. To add a new customer.");
		System.out.println("\n 4. To add a new transaction.");
		System.out.println("\n 5. Select a branch.");
		System.out.println("\n 6. To delete a branch.");
		System.out.println("\n 7. To quit application.");
	}
	
	private static void addCustomer()
	{
		if (selectedBranch == null)
		{
			System.out.println("Branch is not selected");
			setSelectedBranch();
			if (selectedBranch == null)
				return;
		}
		System.out.println("Enter a customer name: ");
		String customerName = scanner.nextLine();
		System.out.println("Enter initial transaction: ");
		double transaction = scanner.nextDouble();
		
		selectedBranch.addCustomer(customerName, transaction);
	}
	
	private static void addTransaction()
	{
		if (selectedBranch == null)
		{
			System.out.println("Branch is not selected");
			setSelectedBranch();
			if (selectedBranch == null)
				return;
		}
		System.out.println("Enter customer name: ");
		String customerName = scanner.nextLine();
		
		if (selectedBranch.getCustomer(customerName) == null)
		{
			System.out.println("Incorrect customer name.");
			return;
		}
		
		System.out.println("Enter customer's transaction: ");
		double transaction = scanner.nextDouble();
		selectedBranch.addTransaction(customerName, transaction);
	}
	
	private static void addBranch()
	{
		String name;
		double amount;
		
		System.out.println("Enter a branch name: ");
		name = scanner.nextLine();
		
		selectedBranch = new Branch(name);
		branches.add(selectedBranch);
		
		System.out.println("Add a customer? Y/N");
		if (scanner.nextLine().toUpperCase().equals("Y"))
			addCustomer();
		
	}
	
	private static void deleteBranch()
	{
		branches.remove(findBranch(selectedBranch.getName()));
		System.out.println("Branch " + selectedBranch.getName() + " deleted");
		selectedBranch = null;
	}
	
	private static void setSelectedBranch()
	{
		String branchName;
		System.out.println("Enter branch name: ");
		branchName = scanner.nextLine();
		
		if (findBranch(branchName) < 0)
		{
			System.out.println("Branch " + branchName + " is not in the list");
			return;
		}
		selectedBranch = branches.get(findBranch(branchName));
	}
	
	private static int findBranch(String branchName)
	{
		int customerID;
		for (int i = 0; i <= branches.size(); i++)
		{
			if (branches.get(i).getName().equals(branchName))
				return i;
			
		}
		return -1;
	}
	
	private static void printBranches()
	{
		if (branches.size() < 1)
		{
			System.out.println("Branches list is empty.");
			return;
		}
		for (int i = 0; i < branches.size(); i++)
		{
			System.out.println("Branch " + (i+1) + ". " + branches.get(i).getName());
		}
		
		System.out.println("Print a list of customers? Y/N");
		if (!scanner.nextLine().toUpperCase().equals("Y"))
			return;
		setSelectedBranch();
		
		for (int i = 0; i < selectedBranch.getCustomers().size(); i++)
		{
			System.out.println("Customer " + (i+1) + ". " + selectedBranch.getCustomers().get(i).getName() +
					" with " + selectedBranch.getCustomers().get(i).getTransactions().size() + " transactions.");
		}
		
		System.out.println("Print a list of customers? Y/N");
		if (!scanner.nextLine().toUpperCase().equals("Y"))
			return;
		System.out.println("Select a customer: ");
		String customerName = scanner.nextLine();
		ArrayList<Double> transactionsList = selectedBranch.getCustomer(customerName).getTransactions();
		
		for (int i = 0; i < transactionsList.size(); i++)
		{
			System.out.println("Transaction " + (i + 1) + " " + transactionsList.get(i));
		}
	}
}
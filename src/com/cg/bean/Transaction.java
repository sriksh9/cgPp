package com.cg.bean;

public class Transaction extends Account {
	public String transType;
	public double amount;
	public double balance;
	public String date;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(String transType, double amount,
			double balance, String date) {
		super();
		this.transType = transType;
		this.amount = amount;
		this.balance = balance;
		this.date = date;
	}


	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "\nDate/Time : " + date + "\nTransaction Type : " + transType
				+ "\nAmount=" + amount + "\nBalance=" + balance;
	}
	

}

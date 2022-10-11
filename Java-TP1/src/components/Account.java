package components;

import operations.Flow;

public abstract class Account {
	//Attributes
	protected String label;
	protected float balance;
	protected long accountNumber;
	protected Client clientData;
	private long accountCounter = 0;
	
	//Constructor
	public Account(String l, Client c) {
		this.label = l;
		this.balance = 0f;
		this.accountNumber = ++accountCounter;
		this.clientData = c;
	}

	//Methods
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getBalance() {
		return balance;
	}
	
	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void addBalance(Flow operation) {
		this.balance += operation.getAmount();
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client getClientData() {
		return clientData;
	}

	public void setClientData(Client clientData) {
		this.clientData = clientData;
	}

	@Override
	public String toString() {
		return "Account [label=" + label + ", balance=" + balance + ", accountNumber=" + accountNumber + ", clientData="
				+ clientData + "]";
	}
	
}
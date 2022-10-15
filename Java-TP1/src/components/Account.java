package components;

import java.util.function.Predicate;

import operations.*;

public abstract class Account {
	//Attributes
	protected String label;
	protected float balance;
	protected long accountNumber;
	protected Client clientData;
	private static long accountCounter = 0;
	
	private Predicate<Float> negativeBalance = i -> (i < 0);
	
	//Constructor
	public Account(String l, Client c) {
		this.label = l;
		this.balance = 0f;
		this.accountNumber = ++accountCounter;
		this.clientData = c;
	}
	public Account(String l, float b, Client c) {
		this.label = l;
		this.balance = b;
		this.accountNumber = ++accountCounter;
		this.clientData = c;
	}
	public Account(String l, float b, long n, Client c) {
		this.label = l;
		this.balance = b;
		this.accountNumber = n;
		this.clientData = c;
	}

	//Methods
	public void modifyBalance(Flow operation) {
		//If its a credit, give money
		if(operation instanceof Credit) {
			this.balance += operation.getAmount();
		
		//If its a debit, reduce money
		}else if(operation instanceof Debit) {
			this.balance -= operation.getAmount();
			
			//If balance is negative, alert
			if(negativeBalance.test(this.balance))
				System.out.println("The account " + accountNumber + " doesn't have enough funds!");
		
		//If its a transfert, give money to this account and reduce from the origin
		}else {
			this.balance += operation.getAmount();
			((Transfert) operation).getOriginAccount().sendTransfert(operation.getAmount());
		}
		
	}
	
	public void sendTransfert(float quantity) {
		this.balance -= quantity;
	}
	
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

	public static void setAccountCounter(long a) {
		accountCounter = a;
	}
	
	@Override
	public String toString() {
		return "Account [label=" + label + ", balance=" + balance + ", accountNumber=" + accountNumber + ", clientData="
				+ clientData + "]";
	}
	
	public String toXMLString() {
		return "\t\t<label>" + label + "</label>" + System.lineSeparator() +
				"\t\t<balance>" + balance + "</balance>" + System.lineSeparator() +
				"\t\t<accountNumber>" + accountNumber + "</accountNumber>" + System.lineSeparator() +
				"\t\t<client>" + System.lineSeparator() +
				clientData.toXMLString() + System.lineSeparator() +
				"\t\t</client>";
	}
	
	public abstract String toJSONString();
}

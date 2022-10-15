package components;

public class SavingsAccount extends Account{

	public SavingsAccount(String l, Client c) {
		super(l, c);
	}
	
	public SavingsAccount(String l, float b, Client c) {
		super(l, b, c);
	}
	
	public SavingsAccount(String l, float b, long n, Client c) {
		super(l, b, n, c);
	}
	
	@Override
	public String toJSONString() {
		return "{type:savingsAccount;label:" + this.label + ";balance:" + this.balance + ";accountNumber:" + this.accountNumber + ";" + System.lineSeparator() + "clientData:" + this.clientData.toJSONString() + "}";
	}

}

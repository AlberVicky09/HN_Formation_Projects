package components;

public class CurrentAccount extends Account{

	//Constructor
	public CurrentAccount(String l, Client c) {
		super(l, c);
	}
	
	public CurrentAccount(String l, float b, Client c) {
		super(l, b, c);
	}
	
	public CurrentAccount(String l, float b, long n, Client c) {
		super(l, b, n, c);
	}
	
	@Override
	public String toJSONString() {
		return "{type:currentAccount;label:" + this.label + ";balance:" + this.balance + ";accountNumber:" + this.accountNumber + ";" + System.lineSeparator() + "clientData:" + this.clientData.toJSONString() + "}";
	}
	
}

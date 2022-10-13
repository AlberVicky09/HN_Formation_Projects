package components;

public class CurrentAccount extends Account{

	//Constructor
	public CurrentAccount(String l, Client c) {
		super(l, c);
	}
	
	public CurrentAccount(String l, Client c, float b) {
		super(l, c, b);
	}
	
	public CurrentAccount(String l, Client c, long n, float b) {
		super(l, c, n, b);
	}
	
}

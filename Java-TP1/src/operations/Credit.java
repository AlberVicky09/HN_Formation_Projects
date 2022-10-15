package operations;

import components.Account;

public class Credit extends Flow{

	//Constructor
	public Credit(long iD, String comment, float amount, long date, Account targetAccount) {
		super(iD, comment, amount, date, targetAccount);
		
	}	
}

package operations;

import java.time.LocalDate;

import components.Account;

public class Credit extends Flow{

	//Constructor
	public Credit(long iD, String comment, float amount, Account targetAccount, boolean effect, LocalDate date) {
		super(iD, comment, amount, targetAccount, effect, date);
		
	}	
}

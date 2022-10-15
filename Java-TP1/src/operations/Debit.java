package operations;

import components.Account;

public class Debit extends Flow{

	public Debit(long iD, String comment, float amount, long date, Account targetAccount) {
		super(iD, comment, amount, date, targetAccount);
	}

}

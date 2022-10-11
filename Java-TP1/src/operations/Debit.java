package operations;

import java.time.LocalDate;

import components.Account;

public class Debit extends Flow{

	public Debit(long iD, String comment, float amount, Account targetAccount, LocalDate date) {
		super(iD, comment, amount, targetAccount, date);
	}

}

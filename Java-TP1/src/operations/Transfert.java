package operations;

import java.time.LocalDate;

import components.Account;

public class Transfert extends Flow{
	//Atributtes
	private Account originAccount;

	//Constructor
	public Transfert(long iD, String comment, float amount, Account targetAccount, LocalDate date,
			Account originAccount) {
		super(iD, comment, amount, targetAccount, date);
		this.originAccount = originAccount;
	}

	//Methods
	public Account getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}

}

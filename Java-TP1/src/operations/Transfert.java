package operations;

import components.Account;

public class Transfert extends Flow{
	//Atributtes
	private Account originAccount;

	//Constructor
	public Transfert(long iD, String comment, float amount, long date, Account targetAccount, Account originAccount) {
		super(iD, comment, amount, date, targetAccount);
		this.originAccount = originAccount;
	}

	//Methods
	public Account getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}
	
	@Override
	public String toJSONString() {
		return "{ID:" + this.getID() + ";comment:" + this.getComment() + ";amount:" + this.getAmount() + ";date:" + this.getDate() + ";" + System.lineSeparator() + "targetAccount:" + this.getTargetAccount().toJSONString() + ";" + System.lineSeparator() + "originAccount:" + this.originAccount.toJSONString() + "}";
	}

}

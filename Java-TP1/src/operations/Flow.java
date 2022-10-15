package operations;

import components.Account;

public abstract class Flow{
	//Attributes
	private long ID;
	private String comment;
	private float amount;
	private Account targetAccount;
	private long date;
	
	//Constructor
	public Flow(long iD, String comment, float amount, long date, Account targetAccount) {
		this.ID = iD;
		this.comment = comment;
		this.amount = amount;
		this.date = date;
		this.targetAccount = targetAccount;
	}

	//Methods
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Account getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(Account targetAccount) {
		this.targetAccount = targetAccount;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}	
	
	public String toJSONString() {
		return "{ID:" + this.ID + ";comment:" + this.comment + ";amount:" + this.amount + ";date:" + this.date + ";" + System.lineSeparator() + "targetAccount:" + this.targetAccount.toJSONString() + "}";
	}
}

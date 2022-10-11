package operations;

import java.time.LocalDate;

import components.Account;

public abstract class Flow {
	//Attributes
	private long ID;
	private String comment;
	private float amount;
	private Account targetAccount;
	private LocalDate date;
	
	//Constructor
	public Flow(long iD, String comment, float amount, Account targetAccount, LocalDate date) {
		this.ID = iD;
		this.comment = comment;
		this.amount = amount;
		this.targetAccount = targetAccount;
		this.date = date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}	
}

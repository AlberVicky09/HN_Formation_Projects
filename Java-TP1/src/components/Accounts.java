package components;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Class used to store accounts for XML
@XmlRootElement(name="accounts")
public class Accounts {
	
	@XmlElement(name="account", type=Account.class)
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Accounts() {}
	
	public Accounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	
	public ArrayList<Account> getAccounts(){
		return this.accounts;
	}
	
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
}

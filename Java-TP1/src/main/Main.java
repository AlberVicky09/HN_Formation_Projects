package main;

import java.util.ArrayList;
import components.*;

public class Main {

	public static void main(String[] args) {
		//Declare array of clients
		ArrayList<Client> clientList;
		//Declare array of accounts
		ArrayList<Account> accountList;		
		
		//Load the clients
		clientList = generateClients(Math.round(Math.random()*100));
		//Display clients
		displayClients(clientList);
		
		//Load the accounts
		accountList = generateAccounts(clientList);
		//Display accounts
		displayAccounts(accountList);
	}
	
	private static ArrayList<Client> generateClients(long numClients){
		//Create aux array
		ArrayList<Client> auxList = new ArrayList<Client>();
		//Fill array
		for(int i = 1; i < numClients+1; i++) 
			auxList.add(new Client("name"+i, "firstname"+i));
		//Return array
		return auxList;
	}
	
	private static void displayClients(ArrayList<Client> clientList){
		//Iterate through all clients
		for (Client client : clientList)
			System.out.println(client.toString());
	}

	private static ArrayList<Account> generateAccounts(ArrayList<Client> clientList){
		//Create aux array
		ArrayList<Account> auxArray = new ArrayList<>();
		
		//Create a new account for each client
		for (int i = 0; i < clientList.size(); i++)
			auxArray.add(new CurrentAccount("Account" + i, clientList.get(i)));
		
		//Return the array
		return auxArray;
	}

	private static void displayAccounts(ArrayList<Account> accountList) {
		//Iterate through accounts
		for (Account account : accountList)
			System.out.println(account.toString());
	}
}

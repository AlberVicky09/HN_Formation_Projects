package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import com.google.gson.*;

import components.*;
import operations.*;

public class Main {

	public static void main(String[] args) {
		//Declare array of clients
		ArrayList<Client> clientList;
		//Declare array of accounts
		ArrayList<Account> accountList;
		//Declare hashtable of accounts
		HashMap<Long, Account> accountHash;
		//Declare array of flows
		ArrayList<Flow> flowList;
		
		//Load the clients
		//clientList = generateClients(Math.round(Math.random()*100));
		clientList = generateClients(5);
		//Display clients
		displayClients(clientList);
		
		//Load the accounts
		accountList = generateAccounts(clientList);
		//Display accounts
		displayAccounts(accountList);
		
		//Pass accounts to hash
		accountHash = listToHash(accountList);
		//Display accounts in balance order
		displayAccountsBalance(accountHash);
		
		//Load the flow operations
		flowList = generateFlows(accountList);
		//Update accounts with flows
		executeFlows(accountList, flowList);
		//Display accounts
		displayAccountsBalance(listToHash(accountList));
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
		System.out.println("_________________________________________");
	}

	private static ArrayList<Account> generateAccounts(ArrayList<Client> clientList){
		//Create aux array
		ArrayList<Account> auxArray = new ArrayList<>();
		
		//Create a new account for each client
		for (int i = 0; i < clientList.size(); i++) {
			if(i%2 == 0)
				//auxArray.add(new CurrentAccount("CurrentAccount " + i, clientList.get(i)));
				auxArray.add(new CurrentAccount("CurrentAccount" + i, clientList.get(i), 10f * i));
			else
				//auxArray.add(new SavingsAccount("Savings Account " + i, clientList.get(i)));
				auxArray.add(new SavingsAccount("Savings Account" + i, clientList.get(i), 10f * i));
		}
			
		//Return the array
		return auxArray;
	}

	private static void displayAccounts(ArrayList<Account> accountList) {
		//Iterate through accounts
		for (Account account : accountList)
			System.out.println(account.toString());
		System.out.println("_________________________________________");
	}

	private static HashMap<Long, Account> listToHash(ArrayList<Account> accountList){
		//Create aux hashMap
		HashMap<Long, Account> auxMap = new HashMap<Long, Account>();
		
		//Add each account to map
		for(Account a : accountList)
			auxMap.put(a.getAccountNumber(), a);
		
		//Return map
		return auxMap;
	}

	private static void displayAccountsBalance(HashMap<Long, Account> accountHash) {
		//Create an aux treemap with balance as key
		TreeMap<Float, Account> auxMap = new TreeMap<Float, Account>();
		
		//Fill aux map with accounts
		for(Long key : accountHash.keySet()) 
			auxMap.put(accountHash.get(key).getBalance(), accountHash.get(key));

		//Display map
		for(Entry<Float, Account> acc : auxMap.entrySet())
			System.out.println(acc.getValue().toString());
		System.out.println("_________________________________________");
	}

	private static ArrayList<Flow> generateFlows(ArrayList<Account> accountList){
		//Create aux array
		ArrayList<Flow> auxList = new ArrayList<Flow>();
		
		//Flow counter
		long counter = 0l;
		//Fill array
		//A debit of 50€ to account 1
		auxList.add(new Debit(counter++, "Debit of 50€", 50f, accountList.get(1), LocalDate.now().plusDays(2)));
		for(Account a : accountList) {
			//A credit of 100.50€ on all current accounts
			if(a instanceof CurrentAccount) {
				auxList.add(new Credit(counter++, "Credit of 100,50€", 100.50f, a, LocalDate.now().plusDays(2)));
			//A credit of 1500€ on all savings accounts
			}else if(a instanceof SavingsAccount) {
				auxList.add(new Credit(counter++, "Credit of 1500€", 1500f, a, LocalDate.now().plusDays(2)));
			}
		}
		//A transfer of 50 from acc 1 to acc 2
		auxList.add(new Transfert(counter++, "Transfer of 50 from acc 1 to acc 2", 50f, accountList.get(1), LocalDate.now().plusDays(2), accountList.get(2)));
		
		//Return array
		return auxList;
	}

	private static void executeFlows(ArrayList<Account> accountList, ArrayList<Flow> flowList) {
		//Get account from each flow, and modify it in the list
		for(Flow f : flowList)
			accountList.get(accountList.indexOf(f.getTargetAccount())).modifyBalance(f);
	}

	private ArrayList<Flow> readFlowFileJSON() {
		//Generate auxiliar array
		ArrayList<Flow> auxList = new ArrayList<Flow>();
		//Use Gson
		Gson gson = new Gson();
		
		//Get path to file
		Path filePath = Paths.get("/files/flows.txt");
		//Fetch file from path
		try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){	       
			//While there are still lines left, keep reading
			String currentLine = null;
			while((currentLine = reader.readLine()) != null){
				//Parse the line as a Flow object
				auxList.add(gson.fromJson(currentLine, Flow.class));
			}
		//Exception if no file existing
	    }catch(IOException ex){
	    	ex.printStackTrace();
	    }
		
		//Return list
		return auxList;
    }
	
	private void writeFlowFileJSON(ArrayList<Flow> flowList) {
		//Use Gson
		Gson gson = new Gson();
		
		//Get path to file
		Path filePath = Paths.get("/files/flows.txt");
		//Write untill there are flows remaining
		try(BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)){
			for(int i = 0; i < flowList.size(); i++) {
				Flow currentFlow = flowList.get(i);
				writer.write(gson.toJson(currentFlow));
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}

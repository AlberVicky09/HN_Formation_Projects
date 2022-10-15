package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

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
		//flowList = generateFlows(accountList);
		//Read flow file
		flowList = readFlowFileJSON();
		//Store flow operations in JSON
		writeFlowFileJSON(flowList);
		
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
				auxArray.add(new CurrentAccount("CurrentAccount" + i, 10f * i, clientList.get(i)));
			else
				//auxArray.add(new SavingsAccount("Savings Account " + i, clientList.get(i)));
				auxArray.add(new SavingsAccount("Savings Account" + i, 10f * i, clientList.get(i)));
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
		//Create date for 2 days time
		long twoDaysFromNow = Instant.now().plus(2, ChronoUnit.DAYS).toEpochMilli();
		//Flow counter
		long counter = 0l;
		//Fill array
		//A debit of 50€ to account 1
		auxList.add(new Debit(counter++, "Debit of 50€", 50f, twoDaysFromNow, accountList.get(1)));
		for(Account a : accountList) {
			//A credit of 100.50€ on all current accounts
			if(a instanceof CurrentAccount) {
				auxList.add(new Credit(counter++, "Credit of 100,50€", 100.50f, twoDaysFromNow, a));
			//A credit of 1500€ on all savings accounts
			}else if(a instanceof SavingsAccount) {
				auxList.add(new Credit(counter++, "Credit of 1500€", 1500f, twoDaysFromNow, a));
			}
		}
		//A transfer of 50 from acc 1 to acc 2
		auxList.add(new Transfert(counter++, "Transfer of 50 from acc 1 to acc 2", 50f, twoDaysFromNow, accountList.get(1), accountList.get(2)));
		
		//Return array
		return auxList;
	}

	private static void executeFlows(ArrayList<Account> accountList, ArrayList<Flow> flowList) {
		//Get account from each flow, and modify it in the list
		for(Flow f : flowList)
			accountList.get(Math.toIntExact(f.getTargetAccount().getAccountNumber())-1).modifyBalance(f);
	}

	//Read json file and parse it into arrayList of flow
	private static ArrayList<Flow> readFlowFileJSON() {
		//Generate auxiliar array
		ArrayList<Flow> auxList = new ArrayList<Flow>();
				
		//Get path to file
		Path filePath = Paths.get("src/files/flows.txt");
		//Fetch file from path
		try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){	       
			//While there are still lines left, keep reading
			String currentLine, tarAccount, orAccount, tarCli, orCli = null;
			while((currentLine = reader.readLine()) != null){
				//Check what kind of Flow it is
				if(currentLine.equals("0")) {
					//Parse the line as a Credit object
					currentLine = reader.readLine();
					tarAccount = reader.readLine();
					tarCli = reader.readLine();
					auxList.add(jsonToFlow(0, currentLine, tarAccount, tarCli, "", ""));
				}else if(currentLine.equals("1")) {
					//Parse the line as a Debit object
					currentLine = reader.readLine();
					tarAccount = reader.readLine();
					tarCli = reader.readLine();
					auxList.add(jsonToFlow(1, currentLine, tarAccount, tarCli, "", ""));
				}else if(currentLine.equals("2")) {
					//Parse the line as a Transfert object
					currentLine = reader.readLine();
					tarAccount = reader.readLine();
					tarCli = reader.readLine();
					orAccount = reader.readLine();
					orCli = reader.readLine();
					auxList.add(jsonToFlow(2, currentLine, tarAccount, tarCli, orAccount, orCli));
				}else {
					System.out.println("Not a known flow");
					return auxList;
				}				
			}
			System.out.println("Flow file successfuly read. Number of flows: " + auxList.size());
		//Exception if no file existing
	    }catch(IOException ex){
	    	ex.printStackTrace();
	    }
		
		//Return list
		return auxList;
    }
	
	//Parse string in JSON to flow
	private static Flow jsonToFlow(int type, String flow, String tarAccount, String tarClient, String oriAccount, String oriClient) {
		//Remove { from flow line
		flow = flow.substring(1);
		//Split in different fields
		String[] splitFlow = flow.split(";"); 
		//Remove fields
		for(int i = 0; i < splitFlow.length; i++) 
			splitFlow[i] = splitFlow[i].substring(splitFlow[i].indexOf(":") + 1);
				
		//Get the target account
		Account tar = jsonToAccount(tarAccount, tarClient);
		//Get the origin account (only for transfert)
		Account ori = null;
		if(!oriAccount.equals(""))
			ori = jsonToAccount(oriAccount, oriClient);
		
		switch(type) {
			//Case credit
			case 0:
				//Create new credit with all params
				return new Credit(Long.parseLong(splitFlow[0]), splitFlow[1], Float.parseFloat(splitFlow[2]), Long.parseLong(splitFlow[3]), tar);
				
			//Case debit
			case 1:
				//Create new debit with all params
				return new Debit(Long.parseLong(splitFlow[0]), splitFlow[1], Float.parseFloat(splitFlow[2]), Long.parseLong(splitFlow[3]), tar);
				
			//Case transfert
			case 2:
				//Create new debit with all params
				return new Transfert(Long.parseLong(splitFlow[0]), splitFlow[1], Float.parseFloat(splitFlow[2]), Long.parseLong(splitFlow[3]), tar, ori);
		}
		
		return null;
	}
	
	//Parse string in JSON to account
	private static Account jsonToAccount(String account, String client) {
		
		//Remove before {
		account = account.substring(account.indexOf("{") + 1);
		//Split in different fields
		String[] splitAccount = account.split(";");
		//Remove fields
		for(int i = 0; i < splitAccount.length; i++)
			splitAccount[i] = splitAccount[i].substring(splitAccount[i].indexOf(":") + 1);
			
		//Get the client
		Client c = jsonToClient(client);
		switch(splitAccount[0]) {
			//Case savings
			case "savingsAccount":
				//Create new savingsAccount
				return new SavingsAccount(splitAccount[1], Float.parseFloat(splitAccount[2]), Long.parseLong(splitAccount[3]), c);
				
			//Case current
			case "currentAccount":
				//Create new currentAccount
				return new CurrentAccount(splitAccount[1], Float.parseFloat(splitAccount[2]), Long.parseLong(splitAccount[3]), c);
		}
		return null;
	}
	
	//Parse string in JSON to client
	private static Client jsonToClient(String client) {
		//Remove until { and after }
		client = client.substring(client.indexOf("{") + 1, client.indexOf("}"));
		//Split in different fields
		String[] splitClient = client.split(";"); 
		//Remove fields
		for(int i = 0; i < splitClient.length; i++)
			splitClient[i] = splitClient[i].substring(splitClient[i].indexOf(":") + 1);
		
		return new Client(splitClient[0], splitClient[1], Long.parseLong(splitClient[2]));
	}

	//Parse array of flows into JSON
	private static void writeFlowFileJSON(ArrayList<Flow> flowList) {	
		//Get path to file
		Path filePath = Paths.get("src/files/flows.txt");
		//Write untill there are flows remaining
		try(BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)){
			for(Flow currentFlow : flowList) {
				//Write a int to know what type of flow it is
				if(currentFlow instanceof Credit)
					writer.write("0" + System.getProperty("line.separator"));
				else if(currentFlow instanceof Debit)
					writer.write("1" + System.getProperty("line.separator"));
				else if(currentFlow instanceof Transfert)
					writer.write("2" + System.getProperty("line.separator"));
				else {
					System.out.println("Not a known flow");
					return;
				}
				writer.write(currentFlow.toJSONString() + System.getProperty("line.separator"));
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}

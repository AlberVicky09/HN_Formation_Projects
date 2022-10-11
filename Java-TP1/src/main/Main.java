package main;

import java.util.ArrayList;
import components.*;

public class Main {

	public static void main(String[] args) {
		//Declare array of clients
		ArrayList<Client> clientList;
		
		//Load the clients
		clientList = generateClients(Math.round(Math.random()*100));
		//Display clients
		displayClients(clientList);
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
		for (Client client : clientList) {
			System.out.println(client.toString());
		}
	}

}

package components;

public class Client {
	//Atributes
	private String name;
	private String firstName;
	private long clientNumber;
	private static long clientCounter = 0;
	
	//Constructor
	public Client(String n, String fn) {
		this.name = n;
		this.firstName = fn;
		this.clientNumber = ++clientCounter;
	}

	//Methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(long clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", firstName=" + firstName + ", clientNumber=" + clientNumber + "]";
	}
}

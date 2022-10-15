package components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="client")
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
	
	public Client(String n, String fn, long cn) {
		this.name = n;
		this.firstName = fn;
		this.clientNumber = cn;
	}

	//Methods
	public String getName() {
		return name;
	}

	@XmlElement(name= "name")
	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	@XmlElement(name= "firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getClientNumber() {
		return clientNumber;
	}

	@XmlElement(name= "clientNumber")
	public void setClientNumber(long clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", firstName=" + firstName + ", clientNumber=" + clientNumber + "]";
	}
	
	public String toJSONString() {
		return "{name:" + this.name + ";firstName:" + this.firstName + ";clientNumber:" + this.clientNumber + "}";
	}
}

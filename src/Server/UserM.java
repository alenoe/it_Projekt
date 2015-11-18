package Server;

import java.io.Serializable;

public class UserM implements Serializable{

	private String username;
	private String ipAddress;
	private int portNumber;
	
	public UserM(String username, String ipAddress, int portNumber){
		this.setUsername(username);
		this.setIpAddress(ipAddress);
		this.setPortNumber(portNumber);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	
	public String toString(){
		return "username: " + this.username + " \tIP: " + this.ipAddress + " \tPort: " + this.portNumber;
	}
	
}

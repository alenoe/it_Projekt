package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;




public class Client implements Runnable{
	
	private String iP;
	private Integer port;
	private Socket client;
	private String personName;
	
	private ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	private Thread  t;
	
	
	public Client(String port, String ip, String userName){
		this.port = Integer.parseInt(port);
		this.iP = ip;
		this.personName = userName;
		
		try {
            client = new Socket(this.iP, this.port);
            System.out.println("Netzwerkverbindung konnte hergestellt werden");
            
            clientOutputStream = new ObjectOutputStream(client.getOutputStream());
    
		} catch(Exception e) {
            System.out.println("Netzwerkverbindung konnte nicht hergestellt werden");
            e.printStackTrace();
    }
		t = new Thread(this);
		t.start();
		
	}
	
	
	public void setPersonName(String personName){
		this.personName = personName;
	}
	public String getPersonName(){
		return this.personName;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			try {
				clientInputStream = new ObjectInputStream(client.getInputStream());
				

				while((personName = (String) clientInputStream.readObject()) != null){
					System.out.println(personName);
				}
				clientInputStream.close();
			} catch (IOException e) {
				t.stop();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
}
	public void sendNameToServer() {
		try {
			clientOutputStream.writeObject(personName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
}
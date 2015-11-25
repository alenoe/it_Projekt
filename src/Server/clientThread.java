package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Server.UserM;


public class clientThread extends Thread{
	
	private Socket socket;
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	private int id;
	
	public clientThread(int id, Socket socket) throws IOException{
		this.socket = socket;
		this.id = id;
		
		serverInputStream = new    
		ObjectInputStream(socket.getInputStream());
		
		serverOutputStream = new 
		ObjectOutputStream(socket.getOutputStream());
		
	}
	
	public void run(){
		try{
			listen();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void listen()throws IOException {
		//String personName = null;
		UserM user = null;
      while(!this.socket.isClosed()){
      try {
		//personName = (String) serverInputStream.readObject();
    	  synchronized (serverInputStream){
    	  user = (UserM) serverInputStream.readObject();
    	  System.out.println(user.toString());
    	  }
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      	System.out.println(user.toString());
     	 /*System.out.println(personName);
          
          personName = "boobs";
          
          serverOutputStream.writeObject(personName);
          serverOutputStream.flush();
          
          personName = "Kacke";
          
          serverOutputStream.writeObject(personName);
          serverOutputStream.flush(); */
      }
	}
}

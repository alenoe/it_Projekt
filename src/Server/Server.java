package Server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;




public class Server {

    private String personName = null;
    private int port;
    private String ip;
    private ServerSocket socketConnection;
    private Socket pipe;
    private static int client_id = 0;
    private final Logger logger = Logger.getLogger("");
    
   public Server(String ip, int port){
	   this.ip = ip;
	   this.port = port;
	   startServer();
   }

   private void startServer(){

      try {

         socketConnection = new ServerSocket(port);

         System.out.println(port +": Server wartet auf Clients...");
         logger.info("Port: " + port + " : Server wartet auf Clients...");
         while(!socketConnection.isClosed()){
        	 pipe = socketConnection.accept();
        	 client_id++;
        	 System.out.println(client_id + ". Client hinzugefügt");
        	 logger.info(client_id + ". Client hinzugefügt");
        	 new clientThread(client_id, pipe).start(); 
         }    
      }  catch(Exception e) {
    	 System.out.println(e);
    	 logger.info("Port "+port+ " ist schon besetzt oder ist fehlerhaft. Bitte einen anderen gültigen/offenen Port wählen.");
   }
 }
}


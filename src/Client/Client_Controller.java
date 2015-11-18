package Client;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class Client_Controller implements Initializable{
	
	@FXML
	AnchorPane anchorPane;
	
	@FXML
	TextField tfUsername;
	
	@FXML
	TextField tfIP;
	
	@FXML
	TextField tfPort;
	
	@FXML
	Button btnConnect;
	
	@FXML
	Label lMessage;
	
	private Client clientSocket;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		anchorPane.setStyle("-fx-background-color: #FFFFFF");
		
		tfPort.setText("14000");
		tfIP.setText("localhost");
		
		btnConnect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				clientSocket = new Client(tfPort.getText(), tfIP.getText(), tfUsername.getText());
//				clientSocket.setPersonName(tfUsername.getText());
//				clientSocket.runClient();
				clientSocket.sendNameToServer();
//				clientSocket.run();
			}
		});
		
//		btnConnect.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				clientSocket.sendNameToServer();
//			}	
//		});
		
	}
}

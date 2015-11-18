package Bonuspunkte.Client.LobbyOverview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class LobbyOverviewListView_Controller implements Initializable {
	
//	private ServiceLocator serviceLocator = ServiceLocator.getServiceLocator(); // resources, after initialization 
//    Logger logger = serviceLocator.getLogger();
//    Translator t = serviceLocator.getTranslator();
//    Locale current = t.getCurrentLocale();  // Das ist die gerade aktuelle sprache / Current ist ein Objekt der Local-Klasse
	
    @FXML
    MenuItem menuItemClose, menuItemDelete, menuItemAbout;
	
	// ListView
	// Idea from: http://code.makery.ch/blog/javafx-8-event-handling-examples/
	@FXML
	private ListView<PseudoLobby> listView = new ListView<>();
	private ObservableList<PseudoLobby> listViewData = FXCollections.observableArrayList(); // NICHT FERTIG
			
	
	@FXML
	TextField txtFieldChoosedLobby, textFieldSelectedLobby;
	
	@FXML
	Button btnNewLobby, btnJoinLobby, btnGameSettings;
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		listView.setItems(listViewData);
	 	listView.setCellFactory(myList -> new MyCustomCell());
	 	
	 	int counter = 0;
	 	while (counter < 5) {
	 		PseudoLobby ps = new PseudoLobby();
	 		listViewData.add(ps);
	 	}
	 	
	 	// Handle ListView selection changes.
	 	 // http://stackoverflow.com/questions/13264017/getting-selected-element-from-listview
	 	listView.getSelectionModel().selectedItemProperty().addListener((observable, oldVlaue, newValue) -> {
	  		System.out.println("ListView Selection Changed (selected: " + newValue.toString() + ")");
//	  		logger.info("\""+selectedPerson+"\" is the newly selected Person and loaded into the variable 'selectedPerson'");
	  		textFieldSelectedLobby.setText(newValue.toString());
	  	});
		
		
		
	}
	
	
}

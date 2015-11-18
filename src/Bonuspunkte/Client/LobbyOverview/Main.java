package Bonuspunkte.Client.LobbyOverview;

	
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(final Stage appStage) throws Exception {

		// START - Copy & Paste till comment "END" into the JavaFX_App_Template where you start the second GUI to test this program
		final URL fxmlUrl = getClass().getResource(
				"LobbyOverviewListView.fxml");
		final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
		fxmlLoader.setController(new LobbyOverviewListView_Controller());
		final Parent root = fxmlLoader.load();
		appStage.setScene(new Scene(root, 733, 686));
		appStage.setTitle("Bonuspunkte - Lobbyübersicht - ListView Lobbyauswahl");
		appStage.show();
		// END
		appStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit(); // ends any JavaFX activities
                System.exit(0);  // end all activities (our server task) - not good code
            }
        });
	}

	public static void main(final String[] args) {
		launch(args);
	}
}

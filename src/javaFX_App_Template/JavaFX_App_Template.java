package javaFX_App_Template;



import java.io.File;
import java.net.URL;
import java.util.Random;

import Client.Client_Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class JavaFX_App_Template extends Application {
    private static JavaFX_App_Template mainProgram; // singleton
    private Splash_View splashView;
    // NICHT MEHR BENÖTIGT - private ClientLogin_View view; // Da es ja keinen Klassischen View mehr gibt, wie gehe ich hier vor???  Einfach die leere Bonuspunkte_View angeben?
    private Stage appStage = null;
    
	private ServiceLocator serviceLocator; // resources, after initialization

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Note: This method is called on the main thread, not the JavaFX
     * Application Thread. This means that we cannot display anything to the
     * user at this point. Since we want to show a splash screen, this means
     * that we cannot do any real initialization here.
     * 
     * This implementation ensures that the application is a singleton; only one
     * per JVM-instance. On client installations this is not necessary (each
     * application runs in its own JVM). However, it can be important on server
     * installations.
     * 
     * Why is it important that only one instance run in the JVM? Because our
     * initialized resources are a singleton - if two programs instances were
     * running, they would use (and overwrite) each other's resources!
     */
    @Override
    public void init() {
        if (mainProgram == null) {
            mainProgram = this;
        } else {
            Platform.exit();
        }
//        // File-Backupsystem if needed
//        BackupFailsafeSystem backupSystem = new BackupFailsafeSystem();
    }

    /**
     * This method is called after init(), and is called on the JavaFX
     * Application Thread, so we can display a GUI. We have two GUIs: a splash
     * screen and the application. Both of these follow the MVC model.
     * 
     * We first display the splash screen. The model is where all initialization
     * for the application takes place. The controller updates a progress-bar in
     * the view, and (after initialization is finished) calls the startApp()
     * method in this class.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create and display the splash screen and model
        Splash_Model splashModel = new Splash_Model();
        splashView = new Splash_View(primaryStage, splashModel);
        new Splash_Controller(this, splashModel, splashView);
        
        splashView.start();

        // Display the splash screen and begin the initialization
        splashModel.initialize();
    }

    /**
     * This method is called when the splash screen has finished initializing
     * the application. The initialized resources are in a ServiceLocator
     * singleton. Our task is to now create the application MVC components, to
     * hide the splash screen, and to display the application GUI.
     * 
     * Multitasking note: This method is called from an event-handler in the
     * Splash_Controller, which means that it is on the JavaFX Application
     * Thread, which means that it is allowed to work with GUI components.
     * http://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
     */
    public void startApp() {
        // Stage appStage = new Stage();
    	appStage = new Stage();
    	// Resources are now initialized
        ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Translator t = sl.getTranslator();
	    System.out.println("BLABLA");
        try {
			final URL fxmlURL = getClass().getResource("/Client/Login.fxml"); // FXML-File from the ClientLogin-Window
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			fxmlLoader.setController(new Client_Controller());
			final Parent root = fxmlLoader.load();
			Scene scene = new Scene(root, 450, 250);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			appStage.setScene(scene);
			appStage.setTitle(t.getString("program.name.windowName"));
			appStage.show();
			String zwischenstatus = fxmlURL.toString();
			System.out.println("Application FXML Loader Pfad ist: " + zwischenstatus);
			
			
        } catch(Exception e) {
        		e.printStackTrace();
        }

        // Close the splash screen, and set the reference to null, so that all
        // Splash_XXX objects can be garbage collected
        splashView.stop();
        splashView = null;

//        view.start();
    }

    /**
     * The stop method is the opposite of the start method. It provides an
     * opportunity to close down the program, including GUI components. If the
     * start method has never been called, the stop method may or may not be
     * called.
     * 
     * Make the GUI invisible first. This prevents the user from taking any
     * actions while the program is ending.
     */
    @Override
    public void stop() {
        if (appStage != null) {
            // Make the view invisible
        	// Commented because of errors - serviceLocator.getLogger().info("ClientLogin-GUI wird beendet"); // HIIIER Kommt wohl kein check durch - ist es wirklich view den wir prüfen müssen??
        	appStage.hide();
        }
        if (appStage != null) {
        	Platform.exit(); // Ends all JavaFX activities
            System.exit(0); // Ends all Programm activities
        }

    	serviceLocator.getLogger().info("nach dem if vom App_Template stopp");
        serviceLocator.setChangeValueWindowIsActive(); // Changes the ServiceLocator Value for WindowIsActive to false so that the thread ShowTimeInWindow() stops

        // More cleanup code as needed

        serviceLocator.getLogger().info("Application terminated"); // Wieso wird diese Message jetzt mit "appStage.hide();" nicht mehr angezeigt?
    }

    // Static getter for a reference to the main program object
    protected static JavaFX_App_Template getMainProgram() {
        return mainProgram;
    }
    
}

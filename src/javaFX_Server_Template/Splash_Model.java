package javaFX_Server_Template;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

// Alternative Idee zum import:
//import ch.fhnw.richards.lecture04.uebungWebValidatorLanguageAppTemplate.Configuration;
//import ch.fhnw.richards.lecture04.uebungWebValidatorLanguageAppTemplate.ServiceLocator;
//import ch.fhnw.richards.lecture04.uebungWebValidatorLanguageAppTemplate.Translator;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class Splash_Model extends Model {
    ServiceLocator serviceLocator; // HHHHIIIIIEEEERR

    public Splash_Model() {
        super();
    }

    // A task is a JavaFX class that implements Runnable. Tasks are designed to
    // have attached listeners, which we can use to monitor their progress.
    final Task<Void> initializer = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            this.updateProgress(1,  6);

            // Create the service locator to hold our resources
            serviceLocator = ServiceLocator.getServiceLocator();
            this.updateProgress(2,  6);

            // Initialize the resources in the service locator
            serviceLocator.setLogger(configureLogging());
            this.updateProgress(3,  6);

            // der Configurator wird erschaffen. Er liest automatisch die Config-Files + speichert für sich die Sprach-Einstellung
            serviceLocator.setConfiguration(new Configuration());
            this.updateProgress(4,  6);

            // Hier holen wir das erste mal die Sprach-Einstellung (also z.b. "en") heraus + vergleichen sie mit den Vorgegebenen Sprachen des ServiceLocators+ speichern den (neuen) Sprache-Wert (ist auf abruf bereit) + bieten ein ResourceBundle an das mit dem Sprache-Wert zusammen hängt
            String language = serviceLocator.getConfiguration().getOption("Language"); // Hier wird die default-datei angesprochen über Klasse Configurations  // wir laden die Config-Files in die Configuration-Klasse + durch getOption erhalten wir den Wert für die Sprache (gespeicherte Wert welche Sprache wir nutzen), welcher in der default "en" ist
            serviceLocator.setTranslator(new Translator(language)); // Zuerst wird Klassenobjekt Translator erstellt und dann über ServiceLocator angesprochen-allenverfügbar gemacht  ACHTUNG: Interessant: Hier wird im parameter das Translator-Objekt erstellt und im ServiceLocator wird dieser als Returnwert für alle verfügbar gemacht WICHTIG: Language beinhaltet den Wert (z.b. "de"), der Wert wird mit den ServiceLocator-Sprachen vergleichen + in Locale gespeichert (+ ein paar andere Dinge werden bereit gestellt, wie ein ResourceBundle)
            this.updateProgress(5,  6);
            // WICHTIG: Bis hier wurden dem GUI noch keine Sprach-Elemente gegeben (nichtmal das GUI geladen). Bis hier wurden einzig und alleine die Ressourcen geladen!!!
            // Hier können (fals nötig) weitere Resourcen geladen werden. z.B. Sockets für Internet-Verbindungen können vorbereitet werden.
            // (Nicht vergessen dabei den ServiceLocator auch einzubinden.. dort muss ja schliesslich alles hin)
            
            // ... more resources would go here...
            // Load Adressbook
           
            this.updateProgress(6,  6);

            return null;
        }
    };

    public void initialize() {
        new Thread(initializer).start();
    }

    /**
     * We create a logger with the name of the application, and attach a file
     * handler to it. All logging should be done using this logger. Messages to
     * this logger will also flow up to the root logger, and from there to the
     * console-handler.
     * 
     * We set the level of the console-handler to "INFO", so that the console
     * only receives the more important messages. The levels of the loggers and
     * the file-handler are set to "FINEST".
     */
    private Logger configureLogging() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.FINEST);

        // By default there is one handler: the console
        Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
        defaultHandlers[0].setLevel(Level.INFO);

        // Add our logger
        Logger ourLogger = Logger.getLogger(serviceLocator.getAPP_NAME());
        ourLogger.setLevel(Level.FINEST);
        
        // Add a file handler, putting the rotating files in the tmp directory
        try {
            Handler logHandler = new FileHandler("%t/"
                    + serviceLocator.getAPP_NAME() + "_%u" + "_%g" + ".log",
                    1000000, 9);
            logHandler.setLevel(Level.FINEST);
            ourLogger.addHandler(logHandler);
        } catch (Exception e) { // If we are unable to create log files
            throw new RuntimeException("Unable to initialize log files: "
                    + e.toString());
        }

        return ourLogger;
    }
}

package javaFX_Server_Template;

import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;



/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * The singleton instance of this class provide central storage for resources
 * used by the program. It also defines application-global constants, such as
 * the application name.
 * 
 * @author Brad Richards
 */
public class ServiceLocator {
    private static ServiceLocator serviceLocator; // singleton

    // Application-global constants
    final private Class<?> APP_CLASS = JavaFX_Server_Template.class;
    final private String APP_NAME = "JavaFX_Server_Template";
    final private JavaFX_Server_Template JavaFX_Server_Template_mainProgram = JavaFX_Server_Template.getMainProgram();
    
    // Supported locales (for translations)
    final private Locale[] locales = new Locale[] { new Locale("en"), new Locale("de") }; // Wird nur gebraucht damit wir wissen wie viele Sprachen es gibt, Translator-Objekt liest die texte ein aus den files

    // Resources
    private Logger logger;
    private Configuration configuration;
    private Translator translator; // Mutlilingual-Texte werden hier eingelesen
    
    // May be deleted
    private boolean windowIsActive = true; // For deactivating the thread ShowTimeInWindow() in Bonuspunkte_Controller
    
//    // http://stackoverflow.com/questions/26494865/javafx-8-changing-title-of-primary-stage
//    final private String windowTitle =  this.translator.getString("program.name.windowName"); // getWindowTitle - TEST OB MAN WINDOW NAME ÄNDERN KANN
    
    /**
     * Factory method for returning the singleton
     * @param mainClass The main class of this program
     * @return The singleton resource locator
     */
    public static ServiceLocator getServiceLocator() {
        if (serviceLocator == null)
            serviceLocator = new ServiceLocator();
        return serviceLocator;
    }

    /**
     * Private constructor, because this class is a singleton
     * @param appName Name of the main class of this program
     */
    private ServiceLocator() {
        // Currently nothing to do here. We must define this constructor anyway,
        // because the default constructor is public
    }

    public Class<?> getAPP_CLASS() {
        return APP_CLASS;
    }
    
    public String getAPP_NAME() {
        return APP_NAME;
    }

    public JavaFX_Server_Template getJavaFX_App_Template_mainProgram() {
		return JavaFX_Server_Template_mainProgram;
	}

	public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) { // Wenn Parameter "new Configuration" -> in Klasse Configuration wird Konstruktor aufgerufen
        this.configuration = configuration; // Speichere das vom Konstruktor erschaffene Objekt hier im ServiceLocator
    }

    public Locale[] getLocales() {
        return locales;
    }

    public Translator getTranslator() {
        return translator;
    }
    
    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

	public boolean getIsWindowIsActive() {
		return windowIsActive;
	}

	public void setWindowIsActive(boolean windowIsActive) {
		this.windowIsActive = windowIsActive;
	}
	
	// For deactivating the thread ShowTimeInWindow()
	public void setChangeValueWindowIsActive() {
		if (this.windowIsActive) {
			this.windowIsActive = false;
		} else if (this.windowIsActive) {
			this.windowIsActive = true;
		}
	}
	
}

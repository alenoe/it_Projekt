package javaFX_Server_Template;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;



/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * This class provides basic functionality for loading and saving program
 * options. Default options may be delivered with the application; local options
 * (changed by the user) are saved to a file. Program constants can be defined
 * by defining options that the user has no way to change.
 * 
 * @author Brad Richards
 */

// Diese Klasse schaut an sich nur wo  sich die Configurations-Files befinden, (default und local), schaut das local = default ist (falls nötig) und schaut ob sich etwas am "localOptions" ändert. Ansonsten kann er noch speichern

// Die Klasse Configurations ist (genau wie Translator) Teil der Infrastruktur, muss man nicht ändern
public class Configuration {
    ServiceLocator sl = ServiceLocator.getServiceLocator();  // for easy reference
    Logger logger = sl.getLogger();                          // for easy reference

    private Properties defaultOptions; // Hier drin ist der Inhalt der default-Datei gespeichert, bereit auf abruf
    private Properties localOptions; // Hier drin ist der Inhalt der local-Datei (falls vorhanden) gespeichert, bereit zum abruf, bereit den Inhalt zu ändern oder auch um diesen Wert abzuspeichern

    public Configuration() {
        // Load default properties from wherever the code is
        defaultOptions = new Properties();
        String defaultFilename = sl.getAPP_NAME() + "_defaults.cfg"; // Hier wird die default-Datei referenziert
        InputStream inStream = sl.getAPP_CLASS().getResourceAsStream(defaultFilename); // ein InputStream der Dateien lesen kann  // Infos aus default-Datei werden geholt (variabel defaultFilename)
        try {
            defaultOptions.load(inStream);
            logger.config("Default configuration file found"); // Wenn default-datei gefunden wurde
        } catch (Exception e) {
            logger.warning("No default configuration file found: " + defaultFilename); // Wenn default-Datei nicht gefunden
        } finally {
            try {
                inStream.close();
            } catch (Exception ignore) {
            }
        }

        // Define locally-saved properties; link to the default values
        localOptions = new Properties(defaultOptions); // Hier wird LocalOptions mit der defaultOptions verbunden (Damit was drin ist wenn wir nachher kein local file finden)

        // Load the local configuration file, if it exists.
        // Wenn eine Local-Donfig da ist, diese auslesen (ansonsten wird mit referenz auf default fortgefahren)
        try {
            inStream = new FileInputStream(sl.getAPP_NAME() + ".cfg"); // Schaue ob eine Datei mit local-configs vorhanden ist + lese diese
            localOptions.load(inStream);
        } catch (FileNotFoundException e) { // from opening the properties file
            logger.config("No local configuration file found"); // Wenn keine Lokal-Configs gefunden
        } catch (IOException e) { // from loading the properties
            logger.warning("Error reading local options file: " + e.toString());
        } finally {
            try {
                inStream.close();
            } catch (Exception ignore) {
            }
        }
        
        
        // FRAGEN WIE ES GENAU FUNKTIONIERT
        // ACHTUNG!!! Generics, Hashmaptree oder sowas!!! key ist "Language" und hier wird später im ServiceLocator die sprache (also das kürzel z.b. "en") rausgesucht
        for (Enumeration<Object> i = localOptions.keys(); i.hasMoreElements();) { // for -> solange Hashtable noch objekte hat, mache weiter  // Hashtable wird erstell + dabei die positionen mit durchiterieren belegt (immer: mögliche sprache + nextElement) (ACHTUNG: localOptions ist defaultOptions wenn keine localOptionen da waren)
            String key = (String) i.nextElement(); // Speicher in key den Wert vom Tableteil i und wähle das nächste Objekt falls vorhanden  (falls also nicht nur "de" sondern noch "ch" zur genaueren definierung da steht
            logger.config("Option: " + key + " = " + localOptions.getProperty(key)); // Gebe im logger den inhalt von "key" aus + zeige den zu key zugehörigen Property-Inhalt an. z.B. "Option: " + de + " = " deutsch
        }
    }
    
    // Wenn man die aktuellen Configurationen speichern will in einer Datei
    public void save() {
        FileOutputStream propFile = null;
        try {
            propFile = new FileOutputStream(sl.getAPP_NAME() + ".cfg"); // Erstellen/Überschreiben Lokale Config-Datei
            localOptions.store(propFile, null); // Infos in die Lokal-Config-Datei schreiben (mit entsprechendem Format)  WICHTIG: dabei wird das Hashtable format genutzt!!  localOptions hat z.B. gerade "de" gespeichert, in die localconfig datei wird "en" jetzt mit "de" überschrieben (der andere wert wird in ruhe gleassen)
            logger.config("Local configuration file saved");
        } catch (Exception e) {
            logger.warning("Unable to save local options: " + e.toString());
        } finally {
            if (propFile != null) {
                try {
                    propFile.close();
                } catch (Exception e) {
                }
            }
        }
    }
    
    public String getOption(String name) {
        return localOptions.getProperty(name); // getProperty fügt ein Property Listener/Observer hinzu der bei änderungen reagiert
    }
    
    public void setLocalOption(String name, String value) { // Der Wert an stelle value, in local Options (also im Hashtable) kann hier überschrieben werden (bzw. vielleicht ergänzt?)
        localOptions.setProperty(name, value);
    }
}

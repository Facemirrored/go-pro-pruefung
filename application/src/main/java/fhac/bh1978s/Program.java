package fhac.bh1978s;

import fhac.bh1978s.ioStream.IOTextFileReader;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Program {

  /**
   * Haupteinstiegspunkt des Programms
   *
   * @param args Argumente für den Pfad des Ordners zum Lesen der Eingabedateien sowie Pfad des
   *             Ordners für die Generierung der Ausgabedateien. Bei ungeraden oder Fehlerhaften
   *             Argumenten werden diese ignoriert und das Programm arbeitet mit angegebenen
   *             Standardeinstellungen.
   */
  public static void main(String[] args) {
    initResourceProperties();

    if (args.length > 0) {
      if (args.length != 2) {
        // TODO: Aufrufszenarien in Doku angeben (0 argumente, 2 argumente, falsche argumente, etc.)"
        System.out.println("Es wurde eine unpassende Anzahl an Argumenten gefunden!\n"
            + "Alle Argumente werden ignoriert und das Programm verwendet angegebene Standards.");
      } else {
        // TODO: 2 Argumente: Prüfe, ob valide Pfade, sonst ignoriere Argumente (weiterleiten an Input-Controller - setter)
        // TODO: DOKU Hinweis: Zuerst Input dann Output Pfad
        IOTextFileReader ioTextFileReader = IOTextFileReader.getInstance();
        if (ioTextFileReader.validatePath(args[0]) &&
            ioTextFileReader.validatePath(args[1])) {
          ioTextFileReader.setInputFileLocation(args[0]);
          ioTextFileReader.setOutputFileLocation(args[1]);
        }
      }
    }

    System.out.println("Hallo NEUE NEUE Welt!\nDrücke taste zum beenden...");
    (new Scanner(System.in)).nextLine();
  }

  /**
   * Liest relevante Properties von der app.properties-Datei. Diese beinhaltet den Eingabe- sowie
   * Ausgabepfad der Textdateien
   */
  private static void initResourceProperties() {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("app");
    IOTextFileReader.getInstance()
        .setInputFileLocation(resourceBundle.getString("application.fileInputPath"));
    IOTextFileReader.getInstance()
        .setOutputFileLocation(resourceBundle.getString("application.fileOutputPath"));
  }
}
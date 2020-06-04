package fhac.bh1978s;

import fhac.bh1978s.ioStream.IOTextFileReader;
import fhac.bh1978s.ioStream.IOTextFileWriter;
import fhac.bh1978s.nameDerSituation.MainController;
import java.util.ResourceBundle;


public class Program {

  /**
   * Haupteinstiegspunkt des Programms. Diese Verwaltet die Initialisierung relevanter Argumente und
   * startet anschließend die Anwendung der Aufgabenstellung.
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
        System.out.println("Es wurde eine unpassende Anzahl an Argumenten gefunden!\n"
            + "Alle Argumente werden ignoriert und die Anwendung verwendet angegebene Standards.");
      } else {
        IOTextFileReader ioTextFileReader = IOTextFileReader.getInstance();
        IOTextFileWriter ioTextFileWriter = IOTextFileWriter.getInstance();
        if (ioTextFileReader.validatePath(args[0]) &&
            ioTextFileWriter.validatePath(args[1])) {
          ioTextFileReader.setInputFileLocation(args[0]);
          ioTextFileWriter.setOutputFileLocation(args[1]);
        } else {
          System.out.println(
              "Mindestens ein angegebenes Argument ist kein valider Dateipfad. Die Anwendung wird angegebene Standards verwenden.");
        }
      }
    }

    MainController.getInstance().start();
  }

  /**
   * Liest relevante Properties von der app.properties-Datei. Diese beinhaltet den Standardeingabe sowie
   * -ausgabepfad der Textdateien
   */
  private static void initResourceProperties() {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("app", java.util.Locale.getDefault());
    IOTextFileReader.getInstance()
        .setInputFileLocation(resourceBundle.getString("application.fileInputPath"));
    IOTextFileWriter.getInstance()
        .setOutputFileLocation(resourceBundle.getString("application.fileOutputPath"));
  }
}
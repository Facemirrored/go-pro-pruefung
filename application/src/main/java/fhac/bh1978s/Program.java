package fhac.bh1978s;

import fhac.bh1978s.view.TextFileWriter;
import fhac.bh1978s.view.TextFileReader;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Program {

  /**
   * Haupteinstiegspunkt des Programms. Dieser Verwaltet die Initialisierung relevanter Argumente und
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
        System.out.println("SYF1:\tUnpassende Anzahl an Argumenten\nEs wurden " + args.length
            + " Argumente gefunden - es dürfen nur keine oder zwei angegeben werden!\n"
            + "Alle Argumente werden ignoriert und die Anwendung verwendet angegebene Standards.");
      } else {
        TextFileReader ioTextFileReader = TextFileReader.getInstance();
        TextFileWriter ioTextFileWriter = TextFileWriter.getInstance();
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

    System.out.println("Starte MainPresenter mit folgenden Eigenschaften:");
    System.out.println("\t- Input-Pfad:\t" + TextFileReader.getInstance().getInputFileLocation());
    System.out
        .println(
            "\t- Output-Pfad:\t" + TextFileWriter.getInstance().getOutputFileLocation() + "\n\n");

    MainPresenter.getInstance().start();

    System.out.println("\n\nAbgeschlossen. Drücke beliebige Taste zum beenden . . .");
    (new Scanner(System.in)).nextLine();
  }

  /**
   * Liest relevante Properties von der app.properties-Datei. Diese beinhaltet den Standardeingabe
   * sowie -ausgabepfad der Textdateien
   */
  private static void initResourceProperties() {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("app", java.util.Locale.getDefault());
    TextFileReader.getInstance()
        .setInputFileLocation(resourceBundle.getString("application.fileInputPath"));
    TextFileWriter.getInstance()
        .setOutputFileLocation(resourceBundle.getString("application.fileOutputPath"));
  }
}
package fhac.bh1978s.view;

import fhac.bh1978s.view.interfaces.I_FilePathHandler;
import fhac.bh1978s.view.interfaces.I_FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Die IOTextFileWriter-Klasse implementiert in Singleton-Pattern-Style die Verwaltung von
 * Textdateien, welche in angegebenen Pfaden geschrieben werden können. Dabei ist die Hauptaufgabe
 * übergebene Inhalte in von TextFile-Objekten ausgehend in String-Form in Dateien zu schreiben.
 */
public class TextFileWriter implements I_FileWriter<TextFile>, I_FilePathHandler {

  private TextFileWriter() {
  }

  private static TextFileWriter textFileWriter = new TextFileWriter();

  public static TextFileWriter getInstance() {
    return textFileWriter;
  }

  private String outputFileLocation = "";

  public String getOutputFileLocation() {
    return outputFileLocation;
  }

  public void setOutputFileLocation(String outputFileLocation) {
    this.outputFileLocation = outputFileLocation;
  }


  /**
   * Erstellt für jedes TextFile-Objekt in der Liste eine Datei mit Namen und Inhalt aus dem Objekt.
   * Im Falle einer IOException beim Schreiben einer Datei wird diese übersprungen und relevante
   * Informationen werden an der Konsole ausgegeben.
   *
   * @param textFileList Liste mit TextFile-Objekten
   */
  @Override
  public void saveFiles(List<TextFile> textFileList) {
    textFileList.forEach(textFile -> {
      try (BufferedWriter writer = new BufferedWriter(
          new FileWriter(outputFileLocation + "\\out_" + textFile.getName(), false))) {
        writer.append(textFile.getContent());
      } catch (IOException io) {
        System.out
            .println("ERROR:\tFehler beim Schreiben der Datei <" + textFile.getName() + ">.\n"
                + "Prüfen Sie die Gültigkeit sowie Zugriffsrechte und versuchen Sie es erneut. Die Datei wird übersprungen!\n");
        textFile.print();
        System.out.println("\n\nException Info:\n" + io.getMessage());
      }
    });
  }

  /**
   * Methode zum löschen aller Dateien innerhalb des Ausgabepfads.
   */
  @Override
  public void emptyFolder() {
    final File outputPath = new File(outputFileLocation);
    for (File file : Objects.requireNonNull(outputPath.listFiles())) {
      if (!file.isDirectory()) {
        boolean success = file.delete();
        if (!success) {
          System.out.println("Datei <" + file
              + "> konnte nicht gelöscht werden. Beachten Sie, dass diese Datei von einer vorherigen Ausührung stammt.");
        }
      }
    }
  }

  /**
   * Validiert den angebenen String auf gültigen Pfad.
   *
   * @param path Pfad, welcher validiert werden soll.
   * @return Validierungsergebnis in Form von boolean.
   */
  @Override
  public boolean validatePath(final String path) {
    File file = new File(path);
    return file.isDirectory();
  }
}

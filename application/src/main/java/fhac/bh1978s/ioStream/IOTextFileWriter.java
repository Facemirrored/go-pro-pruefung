package fhac.bh1978s.ioStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Die IOTextFileWriter-Klasse implementiert in Singleton-Pattern-Style die Verwaltung von
 * Textdateien, welche in angegebenen Pfaden geschrieben werden können. Dabei ist die Hauptaufgabe
 * übergebene Inhalte in von TextFile-Objekten ausgehend in String-Form in Dateien zu schreiben.
 */
public class IOTextFileWriter implements IOFileWriter<TextFile>, IOTextFilePathHandler {

  private IOTextFileWriter() {
  }

  private static IOTextFileWriter ioTextFileWriter = new IOTextFileWriter();

  public static IOTextFileWriter getInstance() {
    return ioTextFileWriter;
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
        System.out.println(io.getMessage());
      }
    });
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

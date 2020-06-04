package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.OutputFileSaveException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Die IOTextFileWriter-Klasse implementiert in Singleton-Pattern-Style die Verwaltung von
 * Textdateien, welche in angegebenen Pfaden geschrieben werden können. Dabei ist die Hauptaufgabe
 * übergebene Inhalte in String-Form in Dateien zu schreiben.
 */
public class IOTextFileWriter implements IOFileWriter<String>, IOTextFilePathHandler {

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

  @Override
  public void saveFiles(List<String> fileContentList) throws OutputFileSaveException {
    for (int i = 0; i < fileContentList.size(); ++i) {
      try (BufferedWriter writer = new BufferedWriter(
          new FileWriter(outputFileLocation + "\\output_" + i + ".txt", false))) {

        writer.append(fileContentList.get(i));

      } catch (IOException e) {
        throw new OutputFileSaveException(
            "Fehler beim schreiben der Dateien. Prüfen Sie die Gültigkeit "
                + "sowie Zugriffsrechte und versuchen Sie es erneut.");
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

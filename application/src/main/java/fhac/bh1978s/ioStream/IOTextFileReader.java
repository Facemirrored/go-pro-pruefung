package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.InputFileReaderException;
import java.io.File;
import java.util.List;

/**
 * Die IOTextFileReader-Klasse implementiert in Singleton-Pattern-Style die Verwaltung von
 * Textdateien, welche in angebenen Pfaden gelesen werden können. Dabei ist die
 * Hauptaufgabe Inhalte von Textdateien in Form von Strings zurückzugeben.
 */
public class IOTextFileReader implements IOFileReader<String>, IOTextFilePathHandler {

  private IOTextFileReader() {
  }

  private static IOTextFileReader ioTextFileReader = new IOTextFileReader();

  public static IOTextFileReader getInstance() {
    return ioTextFileReader;
  }

  private String inputFileLocation = "";

  public void setInputFileLocation(String inputFileLocation) {
    System.out.println("Setze Datei-Input-Pfad:\t" + inputFileLocation);
    this.inputFileLocation = inputFileLocation;
  }

  @Override
  public List<String> readAllFiles() {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public String readSingleFile() throws InputFileReaderException {
    throw new UnsupportedOperationException("Not implemented.");
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

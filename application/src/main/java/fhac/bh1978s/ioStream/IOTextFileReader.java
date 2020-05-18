package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.InputFileReaderException;
import fhac.bh1978s.exception.OutputFileSaveException;
import java.io.File;
import java.util.List;

/**
 * Die IOTextFileReader-Klasse implementiert in Singleton-Pattern-Style die Verwaltung von Textdateien,
 * welche in angebenen Pfaden gelesen und geschrieben werden können. Dabei ist die Hauptaufgabe, Inhalte
 * von Textdateien in Form von Strings zurückzugeben und zudem übergebene Inhalte in String-Form in Dateien
 * zu schreiben.
 */
public class IOTextFileReader implements ioFileReader {

  private IOTextFileReader() {
  }

  private static IOTextFileReader ioTextFileReader = new IOTextFileReader();

  public static IOTextFileReader getInstance() {
    return ioTextFileReader;
  }

  private String inputFileLocation = "";
  private String outputFileLocation = "";

  public void setInputFileLocation(String inputFileLocation) {
    System.out.println("Setze Datei-Input-Pfad:\t" + inputFileLocation);
    this.inputFileLocation = inputFileLocation;
  }

  public void setOutputFileLocation(String outputFileLocation) {
    System.out.println("Setze Datei-Output-Pfad:\t" + outputFileLocation);
    this.outputFileLocation = outputFileLocation;
  }

  @Override
  public List<String> readAllFiles() {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public String readSingleFile() throws InputFileReaderException {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void saveAllFiles(List<String> fileContentList) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void saveSingleFile(String fileContent) throws OutputFileSaveException {
    throw new UnsupportedOperationException("Not implemented.");
  }

  public boolean validatePath(final String path) {
    File file = new File(path);
    return file.isDirectory();
  }
}

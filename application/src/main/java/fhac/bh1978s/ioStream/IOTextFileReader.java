package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.InputFileReaderException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Die IOTextFileReader-Klasse implementiert in Singleton-Pattern-Style die Verwaltung von
 * Textdateien, welche in angebenen Pfaden gelesen werden können. Dabei ist die Hauptaufgabe Inhalte
 * von Textdateien in Form von TextFile-Objekten zurückzugeben.
 */
public class IOTextFileReader implements IOFileReader<TextFile>, IOTextFilePathHandler {

  private IOTextFileReader() {
  }

  private static IOTextFileReader ioTextFileReader = new IOTextFileReader();

  public static IOTextFileReader getInstance() {
    return ioTextFileReader;
  }

  private String inputFileLocation = "";

  public String getInputFileLocation() {
    return inputFileLocation;
  }

  public void setInputFileLocation(String inputFileLocation) {
    this.inputFileLocation = inputFileLocation;
  }

  /**
   * Liest alle Dateien innerhalb gesetzten Attribut "inputFilelocation". Bei unerwarteten
   * Fehler wird eine Message als Content-String gesetzt.
   * @return Liste aller TextFile-Objekte beinhaltend Name und Content der jeweiligen Datei.
   */
  @Override
  public List<TextFile> readAllFiles() {
    final File inputPath = new File(inputFileLocation);
    final String[] inputFilesList = inputPath.list();
    final List<TextFile> textFileContentList = new ArrayList<>();

    assert inputFilesList != null;

    Arrays.stream(inputFilesList).forEach(file -> {
      try {
        textFileContentList.add(readSingleFile(file));
      } catch (InputFileReaderException e) {
        textFileContentList.add(new TextFile(file, "ERR\n\n" + e.getMessage()));
      }
    });

    return textFileContentList;
  }

  /**
   * Liest Inhalt einer Textdatei aus und gibt diesen als TextFile zurück. Dabei bezieht sich der Pfad
   * auf das Attribut "inputFileLocation". Bei unerwarteten Fehlern wird eine Exception nach oben
   * geworfen.
   *
   * @param file TextFile-Objekt, welche Name der Datei enthält.
   * @return Inhalt der Datei als String-Format.
   * @throws InputFileReaderException Wird geworfen, wenn beim Lesen unerwartete Probleme
   *                                  auftauchen.
   */
  @Override
  public TextFile readSingleFile(final String file) throws InputFileReaderException {
    StringBuilder content = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get(inputFileLocation + "\\" + file), StandardCharsets.UTF_8)) {

      stream.forEach(s -> content.append(s).append("\n"));
      return new TextFile(file, content.toString());

    } catch (IOException io) {
      throw new InputFileReaderException(
          "Fehler beim lesen der Datei <" + inputFileLocation + "\\" + file
              + ">. Prüfen Sie die Gültigkeit sowie Zugriff der Datei und versuchen Sie es erneut.\n");
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

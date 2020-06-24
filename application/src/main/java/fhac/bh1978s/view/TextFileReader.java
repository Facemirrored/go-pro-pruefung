package fhac.bh1978s.view;

import fhac.bh1978s.view.interfaces.I_FilePathHandler;
import fhac.bh1978s.view.interfaces.I_FileReader;
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
public class TextFileReader implements I_FileReader<TextFile>, I_FilePathHandler {

  private TextFileReader() {
  }

  private static TextFileReader textFileReader = new TextFileReader();

  public static TextFileReader getInstance() {
    return textFileReader;
  }

  private String inputFileLocation = "";

  public String getInputFileLocation() {
    return inputFileLocation;
  }

  public void setInputFileLocation(String inputFileLocation) {
    this.inputFileLocation = inputFileLocation;
  }

  /**
   * Liest alle Dateien innerhalb gesetzten Attribut "inputFilelocation". Bei unerwarteten Fehler
   * wird eine Message als Content-String gesetzt.
   *
   * @return Liste aller TextFile-Objekte beinhaltend Name und Content der jeweiligen Datei.
   */
  @Override
  public List<TextFile> readAllFiles() {
    final File inputPath = new File(inputFileLocation);
    final String[] inputFilesList = inputPath.list();
    final List<TextFile> textFileContentList = new ArrayList<>();

    assert inputFilesList != null;

    Arrays.stream(inputFilesList).forEach(file -> {
      textFileContentList.add(readSingleFile(file));
    });

    return textFileContentList;
  }

  /**
   * Liest Inhalt einer Textdatei aus und gibt diesen als TextFile zurück. Dabei bezieht sich der
   * Pfad auf das Attribut "inputFileLocation". Bei unerwarteten Fehlern wird der Fehler in die
   * dazugehörige Datei geschrieben.
   *
   * @param file TextFile-Objekt, welche Name der Datei enthält.
   * @return Inhalt der Datei als String-Format.
   */
  @Override
  public TextFile readSingleFile(final String file) {
    StringBuilder content = new StringBuilder();
    TextFile textFile = new TextFile(file);
    try (Stream<String> stream = Files
        .lines(Paths.get(inputFileLocation + "\\" + file), StandardCharsets.UTF_8)) {

      stream.forEach(s -> content.append(s).append("\n"));
      textFile.setContent(content.toString());

    } catch (IOException io) {
      textFile.setError(true);
      textFile.setName("TE1_" + file);
      textFile.setContent("ERROR:\tFehler beim Lesen der Datei <" + inputFileLocation + "\\" + file
          + ">.\nPrüfen Sie die Gültigkeit sowie Zugriff der Datei und versuchen Sie es erneut.\n\n"
          + "Details:\n" + io.getMessage());
    }

    return textFile;
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

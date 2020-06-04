package fhac.bh1978s.nameDerSituation;

import fhac.bh1978s.ioStream.IOFileWriter;
import fhac.bh1978s.ioStream.IOTextFileReader;
import fhac.bh1978s.ioStream.IOFileReader;
import fhac.bh1978s.ioStream.IOTextFileWriter;
import fhac.bh1978s.nameDerSituation.mapper.GenericMapper;
import java.util.List;

/**
 * Haupt-Verarbeitungscontroller vom MVC-Pattern als Singleton-Pattern-Style implementiert.
 * Dieser dient als Verarbeitungsschnittstelle zwischen der View (Eingabe/Ausgabe) und den Models.
 */
public class MainController {

  private static MainController mainController = new MainController();

  private IOFileReader<String> ioFileReader = IOTextFileReader.getInstance();
  private IOFileWriter<String> ioFileWriter = IOTextFileWriter.getInstance();

  // TODO: Hier später mapper initialisieren
  private GenericMapper<String, Object> mapper;

  public static MainController getInstance() {
    return mainController;
  }

  private MainController() {}

  /**
   * Start-Methode des Haupt-Verarbeitungscontrollers. Diese dient als Verarbeitungsschnittstelle zwischen
   * dem Lesen/Schreiben von Dateien, dem Mappen von Datenstrukturen, als auch der Deligierung an weitere Controller.
   */
  public void start() {
    try {
      // 1. Lese Dateien --> erhalte List<String> (pro Item ein Dateiinhalt - bei Probleme: Fehlercode in Liste)
      final List<String> textFileContent = ioFileReader.readAllFiles();
      System.out.println("\t- Lesen von Input-Dateien abgeschlossen.");
      // 2. Mappe List<String> in interne Datenobjekte (ignoriere Fehler, diese werden später in output geschrieben)
      System.out.println("\t- Internes Daten-Mapping abgeschlossen.");
      // 3. Führe Aufgabe für jede Datei (jedes interne Datenobjekt) aus und erhalte Liste mit Lösungsobjekten
      System.out.println("\t- Berechnungen abgeschlossen.");
      // 4. Mappe List<Lösungsobjekte> in externe List<String> für Ausgabe (beachte vorher nicht berechnete Objekte aufgrund von Fehlern)
      System.out.println("\t- Externes Daten-Mapping abgeschlossen.");
      // 5. Generiere Output-Dateien aus List<String>
      ioFileWriter.saveFiles(textFileContent);
      System.out.println("\t- Schreiben von Output-Dateien abgeschlossen.");

    } catch (Exception e) {

    }
  }
}

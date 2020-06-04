package fhac.bh1978s.nameDerSituation;

import fhac.bh1978s.ioStream.IOFileWriter;
import fhac.bh1978s.ioStream.IOTextFileReader;
import fhac.bh1978s.ioStream.IOFileReader;
import fhac.bh1978s.ioStream.IOTextFileWriter;
import fhac.bh1978s.nameDerSituation.mapper.GenericMapper;

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

  public static MainController getMainController() {
    return mainController;
  }

  /**
   * Start-Methode des Haupt-Verarbeitungscontrollers. Diese dient als Verarbeitungsschnittstelle zwischen
   * dem Lesen/Schreiben von Dateien, dem Mappen von Datenstrukturen, als auch der Deligierung an weitere Controller.
   */
  public void start() {
    try {
      // 1. Lese Dateien --> erhalte List<String> (pro Item ein Dateiinhalt - bei Probleme: Fehlercode in Liste)

      // 2. Mappe List<String> in interne Datenobjekte (ignoriere Fehler, diese werden später in output geschrieben)

      // 3. Führe Aufgabe für jede Datei (jedes interne Datenobjekt) aus und erhalte Liste mit Lösungsobjekten

      // 4. Mappe List<Lösungsobjekte> in externe List<String> für Ausgabe (beachte vorher nicht berechnete Objekte aufgrund von Fehlern)

      // 5. Generiere Output-Dateien aus List<String>

      // Sonstiges: Bei unerwarteten Fehlern Log-Datei in _devLogs erstellen für Entwickler
    } catch (Exception e) {

    }
    System.out.println("Szeraio start...");
  }
}

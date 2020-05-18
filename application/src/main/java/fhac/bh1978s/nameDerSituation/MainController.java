package fhac.bh1978s.nameDerSituation;

import fhac.bh1978s.ioStream.IOTextFileReader;
import fhac.bh1978s.ioStream.IOFileReader;
import fhac.bh1978s.nameDerSituation.mapper.GenericMapper;

/**
 * MainController für die Verwaltung der Aufgabe. Dieser ist im Singleton-Pattern-Style implementiert.
 */
public class MainController {
  private static MainController mainController = new MainController();
  private IOFileReader ioFileReader = IOTextFileReader.getInstance();
  // TODO: Hier später mapper initialisieren
  private GenericMapper<String, Object> mapper;

  public static MainController getInstance() {
    return mainController;
  }

  private MainController() {}

  public static MainController getMainController() {
    return mainController;
  }

  public void start() {

    // 1. Lese Dateien --> erhalte List<String> (pro Item ein Dateiinhalt - bei Probleme: Fehlercode in Liste)

    // 2. Mappe List<String> in interne Datenobjekte (ignoriere Fehler, diese werden später in output geschrieben)

    // 3. Führe Aufgabe für jede Datei (jedes interne Datenobjekt) aus und erhalte Liste mit Lösungsobjekten

    // 4. Mappe List<Lösungsobjekte> in externe List<String> für Ausgabe (beachte vorher nicht berechnete Objekte aufgrund von Fehlern)

    // 5. Generiere Output-Dateien aus List<String>

    // Sonstiges: Bei unerwarteten Fehlern Log-Datei in _devLogs erstellen für Entwickler

    System.out.println("Szeraio start...");
  }
}

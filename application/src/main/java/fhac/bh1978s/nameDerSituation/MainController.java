package fhac.bh1978s.nameDerSituation;

import fhac.bh1978s.ioStream.I_FileWriter;
import fhac.bh1978s.ioStream.TextFileReader;
import fhac.bh1978s.ioStream.I_FileReader;
import fhac.bh1978s.ioStream.TextFileWriter;
import fhac.bh1978s.ioStream.TextFile;
import fhac.bh1978s.nameDerSituation.mapper.I_Mapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Haupt-Verarbeitungscontroller vom MVC-Pattern als Singleton-Pattern-Style implementiert. Dieser
 * dient als Verarbeitungsschnittstelle zwischen der View (Eingabe/Ausgabe) und den Models.
 */
public class MainController {

  private static MainController mainController = new MainController();

  private I_FileReader<TextFile> fileReader = TextFileReader.getInstance();
  private I_FileWriter<TextFile> fileWriter = TextFileWriter.getInstance();

  // TODO: Hier später mapper initialisieren
  private I_Mapper<TextFile, Object> mapper;

  public static MainController getInstance() {
    return mainController;
  }

  private MainController() {
  }

  /**
   * Start-Methode des Haupt-Verarbeitungscontrollers. Diese dient als Verarbeitungsschnittstelle
   * zwischen dem Lesen/Schreiben von Dateien, dem Mappen von Datenstrukturen, als auch der
   * Deligierung an weitere Controller.
   */
  public void start() {
    try {
      // Lese Dateien --> erhalte List<String> (pro Item ein Dateiinhalt - bei Probleme: Fehlercode in Liste)
      final List<TextFile> textFileContent = fileReader.readAllFiles();
      System.out.println("\t- Lesen von Input-Dateien abgeschlossen.");

      List<TextFile> loesung = new ArrayList<>();
      textFileContent.forEach(textFile -> {
        // Mapping von Textfile nacheinander in internes Objekt
        Object object = mapper.mapToInternFormat(textFile);
        // TODO: execute

        // Mapping von Ergebnisobjekt zu externen Objekt

        // Add Ergebnisobjekt
        loesung.add(null);
      });
      System.out.println("\t- Internes Daten-Mapping abgeschlossen.");
      // 3. Führe Aufgabe für jede Datei (jedes interne Datenobjekt) aus und erhalte Liste mit Lösungsobjekten
      System.out.println("\t- Berechnungen abgeschlossen.");
      // 4. Mappe List<Lösungsobjekte> in externe List<String> für Ausgabe (beachte vorher nicht berechnete Objekte aufgrund von Fehlern)
      System.out.println("\t- Externes Daten-Mapping abgeschlossen.");
      // 5. Generiere Output-Dateien aus List<String>
      fileWriter.saveFiles(textFileContent);
      System.out.println("\t- Schreiben von Output-Dateien abgeschlossen.");

    } catch (Exception e) {

    }
  }
}

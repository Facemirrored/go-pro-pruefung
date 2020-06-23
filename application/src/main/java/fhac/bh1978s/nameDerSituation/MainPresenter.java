package fhac.bh1978s.nameDerSituation;

import fhac.bh1978s.exception.ZufallMappingException;
import fhac.bh1978s.ioStream.I_FileWriter;
import fhac.bh1978s.ioStream.TextFileReader;
import fhac.bh1978s.ioStream.I_FileReader;
import fhac.bh1978s.ioStream.TextFileWriter;
import fhac.bh1978s.ioStream.TextFile;
import fhac.bh1978s.nameDerSituation.mapper.I_InputMapper;
import fhac.bh1978s.nameDerSituation.mapper.I_OutputMapper;
import fhac.bh1978s.nameDerSituation.model.ZufallsData;
import fhac.bh1978s.nameDerSituation.model.ZufallsergebnisData;
import fhac.bh1978s.nameDerSituation.presenter.ZufallsgeneratorPresenter;
import java.util.ArrayList;
import java.util.List;

// TODO: controller in presenter umbenennen!!!!!

/**
 * Haupt-Verarbeitungspresenter vom MVC-Pattern als Singleton-Pattern-Style implementiert. Dieser
 * dient als Verarbeitungsschnittstelle zwischen der View (Eingabe/Ausgabe) und den Models.
 */
public class MainPresenter {

  private static MainPresenter mainPresenter = new MainPresenter();

  private I_FileReader<TextFile> fileReader = TextFileReader.getInstance();
  private I_FileWriter<TextFile> fileWriter = TextFileWriter.getInstance();

  // TODO: Hier später mapper initialisieren
  private I_InputMapper<TextFile, ZufallsData> internMapper;
  private I_OutputMapper<ZufallsergebnisData, TextFile> externMapper;

  public static MainPresenter getInstance() {
    return mainPresenter;
  }

  private MainPresenter() {
  }

  /**
   * Start-Methode des Haupt-Verarbeitungspresenters. Diese dient als Verarbeitungsschnittstelle
   * zwischen dem Lesen/Schreiben von Dateien, dem Mappen von Datenstrukturen, als auch der
   * Deligierung an weitere Presenter.
   */
  public void start() {
    final List<TextFile> textFileContent = fileReader.readAllFiles();
    System.out.println("\t- Lesen von Input-Dateien abgeschlossen. Starte mit Verarbeitung...\n");

    List<TextFile> textfileOutput = new ArrayList<>();

    textFileContent.forEach(textFile -> {

      if (textFile.isError()) {
        System.out.println("Fehler beim Lesen der Datei <" + textFile.getName()
            + ">. Fehlermeldung befindet sich in der Ausgabe-Datei.");
        textfileOutput.add(textFile);
      } else {
        try {
          ZufallsData zufallsData = internMapper.mapToInternFormat(textFile);
          ZufallsgeneratorPresenter ergebnisPlaceHolder = new ZufallsgeneratorPresenter(
              zufallsData);
          textfileOutput.add(externMapper.mapToExternFormat(ergebnisPlaceHolder.generiere()));
          System.out.println("Datei <" + textFile.getName()
              + "> berechnet. Ergebnis befindet sich im Ausgabe-Pfad.");
        } catch (ZufallMappingException e) {
          // TODO: TextFile-Objekt anpassen für Fehlerangabe
          System.out.println("Semantikfehler in der Datei <" + textFile.getName()
              + "> entdeckt. Details stehen in der Ausgabe-Datei.");
          textfileOutput.add(new TextFile("eeeeeeerrroooooor"));
          e.printStackTrace();
        }
      }
    });

    fileWriter.saveFiles(textfileOutput);
  }
}

package fhac.bh1978s.zufallsgenerator;

import fhac.bh1978s.exception.BerechnungException;
import fhac.bh1978s.exception.ZufallMappingException;
import fhac.bh1978s.view.TextFile;
import fhac.bh1978s.view.TextFileReader;
import fhac.bh1978s.view.TextFileWriter;
import fhac.bh1978s.view.interfaces.I_FileReader;
import fhac.bh1978s.view.interfaces.I_FileWriter;
import fhac.bh1978s.zufallsgenerator.enumeration.GeneratorType;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import fhac.bh1978s.zufallsgenerator.mapper.ZufallDataInputMapper;
import fhac.bh1978s.zufallsgenerator.mapper.ZufallErgebnisOutputMapper;
import fhac.bh1978s.zufallsgenerator.mapper.interfaces.I_InputMapper;
import fhac.bh1978s.zufallsgenerator.mapper.interfaces.I_OutputMapper;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;
import fhac.bh1978s.zufallsgenerator.model.ZufallErgebnisData;
import fhac.bh1978s.zufallsgenerator.presenter.ZufallsgeneratorPresenter;
import java.util.ArrayList;
import java.util.List;


/**
 * Haupt-Verarbeitungspresenter vom MVC-Pattern als Singleton-Pattern-Style implementiert. Dieser
 * dient als Verarbeitungsschnittstelle zwischen der View (Eingabe/Ausgabe) und den Models.
 */
public class MainPresenter {

  private static MainPresenter mainPresenter = new MainPresenter();

  private I_FileReader<TextFile> fileReader = TextFileReader.getInstance();
  private I_FileWriter<TextFile> fileWriter = TextFileWriter.getInstance();
  private I_InputMapper<TextFile, ZufallData> internMapper = new ZufallDataInputMapper();
  private I_OutputMapper<ZufallErgebnisData, TextFile> externMapper = new ZufallErgebnisOutputMapper();

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
          ZufallData zufallData = internMapper.mapToInternFormat(textFile);
          ZufallsgeneratorPresenter zufallsgenerator = new ZufallsgeneratorPresenter(zufallData);
          ZufallErgebnisData zufallErgebnisData = zufallsgenerator.generiere();

          TextFile ergebnisTextFile = new TextFile(textFile.getName());
          ergebnisTextFile.setContent(buildInputHeader(textFile));
          ergebnisTextFile
              .addContent(externMapper.mapToExternFormat(zufallErgebnisData).getContent());
          textfileOutput.add(ergebnisTextFile);
          System.out.println("Datei <" + textFile.getName()
              + "> berechnet. Ergebnis befindet sich im Ausgabe-Pfad.");


        } catch (ZufallMappingException zme) {
          System.out.println("Semantikfehler in der Datei <" + textFile.getName()
              + "> entdeckt. Details stehen in der Ausgabe-Datei.");
          TextFile errorFile = new TextFile(textFile.getName());
          errorFile.setContent(buildInputHeader(textFile) + "Fehlermeldung:\n" + zme.getMessage());
          textfileOutput.add(errorFile);


        } catch (BerechnungException be) {
          System.out.println("Fehler beim Berechnen der Datei <" + textFile.getName()
              + ">. Details in der Ausgabe-Datei.");
          TextFile errorFile = new TextFile(textFile.getName());
          errorFile.setContent(buildInputHeader(textFile) + "Fehlermeldung:\n" + be.getMessage());
          textfileOutput.add(errorFile);

        } finally {
          fileWriter.saveFiles(textfileOutput);
        }
      }
    });
  }

  private String buildInputHeader(final TextFile textFile) {
    return "-----INPUT-----\n" + textFile.getContent() + "\n\n-----OUTPUT-----\n";
  }
}

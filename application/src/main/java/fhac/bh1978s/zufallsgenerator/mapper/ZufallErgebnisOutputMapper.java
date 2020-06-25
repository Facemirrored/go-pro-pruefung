package fhac.bh1978s.zufallsgenerator.mapper;

import fhac.bh1978s.view.TextFile;
import fhac.bh1978s.zufallsgenerator.enumeration.BewertungType;
import fhac.bh1978s.zufallsgenerator.mapper.interfaces.I_OutputMapper;
import fhac.bh1978s.zufallsgenerator.model.ZufallErgebnisData;
import fhac.bh1978s.zufallsgenerator.presenter.bewertung.BjarnscheGuetefunktion;
import fhac.bh1978s.zufallsgenerator.presenter.bewertung.SequenzUpDownTest;
import fhac.bh1978s.zufallsgenerator.presenter.bewertung.SerielleAutokorrelation;

/**
 * Mapper für das Mapping von ZufallErgebnisData-Objekten zu TextFile-Objekten
 */
public class ZufallErgebnisOutputMapper implements I_OutputMapper<ZufallErgebnisData, TextFile> {

  /**
   * Mappt übergebene Parameter zu einem TextFile-Objekt
   *
   * @param internContent Internes Datenobjekt in Form von ZufallErgebnisData
   * @return Externes Datenobjekt in Form von TextFile
   */
  @Override
  public TextFile mapToExternFormat(ZufallErgebnisData internContent) {

    TextFile textFile = new TextFile("Temp");

    if (internContent.getZufallszahlen() != null && internContent.getZufallszahlen().size() > 0) {
      textFile.addContent("Zufallszahlen:\n");
      for (int i = 0; i < internContent.getZufallszahlen().size() - 1; ++i) {
        textFile.addContent(i + "\t:\t" + internContent.getZufallszahlen().get(i) + "\n");
      }
      textFile.addContent(
          (internContent.getZufallszahlen().size() - 1) + "\t:\t" + internContent.getZufallszahlen()
              .get(internContent.getZufallszahlen().size() - 1) + "\n");
    }

    if (internContent.getBewertung() != null) {
      if (internContent.getBewertung() instanceof SequenzUpDownTest) {
        SequenzUpDownTest sequenzUpDownTest = (SequenzUpDownTest) internContent.getBewertung();
        textFile.addContent(BewertungType.SEQUENZ_UP_DOWN_TEST.getBewertungType()
            + "-Ergebnis:\nk\tCalc: N(k)\t\tExp: N(k)\n");

        for (int i = 0; i < sequenzUpDownTest.getBitCounter().length; ++i) {
          textFile
              .addContent(
                  (i + 1) + "\t" + sequenzUpDownTest.getBitCounter()[i] + "\t\t" + sequenzUpDownTest
                      .getExpCounter()[i] + "\n");
        }
      } else if (internContent.getBewertung() instanceof SerielleAutokorrelation) {
        SerielleAutokorrelation serielleAutokorrelation = (SerielleAutokorrelation) internContent
            .getBewertung();
        textFile.addContent(BewertungType.SERIELLE_AUTOKORRELATION.getBewertungType()
            + "-Ergebnis:\nk\tCalc: Roh(k)\n");
        for (int i = 0; i < serielleAutokorrelation.getRohList().size(); ++i) {
          textFile.addContent(i + "\t" + serielleAutokorrelation.getRohList().get(i) + "\n");
        }
      } else if (internContent.getBewertung() instanceof BjarnscheGuetefunktion) {
        BjarnscheGuetefunktion bjarnscheGuetefunktion = (BjarnscheGuetefunktion) internContent
            .getBewertung();
        textFile.addContent(BewertungType.BJARNSCHE_GUETEFUNKTION.getBewertungType()
            + "-Ergebnis:\nk\tCalc: N(k)\n");

        for (int i = 0; i < bjarnscheGuetefunktion.getBitCounter().length; ++i) {
          textFile
              .addContent(i + "\t" + bjarnscheGuetefunktion.getBitCounter()[i] + "\n");
        }
      }
    }

    return textFile;
  }
}

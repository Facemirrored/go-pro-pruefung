package fhac.bh1978s.zufallsgenerator.presenter;

import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.INKREMENT;
import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.MODUL;
import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.MULTIPLIKATOR;
import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.STARTWERT;

import fhac.bh1978s.exception.BerechnungException;
import fhac.bh1978s.zufallsgenerator.enumeration.GeneratorType;
import fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;
import fhac.bh1978s.zufallsgenerator.model.ZufallErgebnisData;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.math.BigInteger;
import java.util.List;

public class ZufallsgeneratorPresenter {

  private ZufallData zufallData;
  private I_Generatorklasse<?> generatorklasse;
  private I_Bewertung<?> bewertung;
  private ZufallErgebnisData zufallErgebnisData;

  public ZufallsgeneratorPresenter(final ZufallData zufallData) {
    this.zufallData = zufallData;
    this.zufallErgebnisData = new ZufallErgebnisData();
    init();
  }

  @SuppressWarnings("unchecked")
  private void init() {
    if (zufallData.getGeneratorType() != null) {
      switch (zufallData.getGeneratorType()) {
        case LCG:
          generatorklasse = new LcgGenerator(
              zufallData.getParameterList().get(MODUL.getLcgParameter()),
              zufallData.getParameterList().get(MULTIPLIKATOR.getLcgParameter()),
              zufallData.getParameterList().get(INKREMENT.getLcgParameter()),
              zufallData.getParameterList().get(STARTWERT.getLcgParameter()),
              zufallData.getN());
          break;
        case POLAR_METHOD:
          generatorklasse = new PolarMethod();
          break;
        default:
          generatorklasse = null;
      }
    } else if (zufallData.getBewertungType() != null) {
      switch (zufallData.getBewertungType()) {
        case SEQUENZ_UP_DOWN_TEST:
          bewertung = new SequenzUpDownTest();
          break;
        case SERIELLE_AUTOKORRELATION:
          bewertung = new SerielleAutokorrelation(0.5);
          break;
        default:
          bewertung = null;
      }
    }
  }

  public void berechneBewertung() throws BerechnungException {
    if (bewertung == null) {
      throw new BerechnungException("Bewertungsart ist nicht ausgewählt. Bitte vorher setzen.");
    } else if (bewertung instanceof SequenzUpDownTest) {
      SequenzUpDownTest sequenzUpDownTest = (SequenzUpDownTest) bewertung;
      sequenzUpDownTest.berechneBewertung(zufallData.getZufallszahlen());
      zufallErgebnisData.setBewertung(sequenzUpDownTest);
    } else if (bewertung instanceof SerielleAutokorrelation) {
      SerielleAutokorrelation serielleAutokorrelation = (SerielleAutokorrelation) bewertung;
      serielleAutokorrelation
          .berechneBewertung(zufallData.getZufallszahlen());
      zufallErgebnisData.setBewertung(serielleAutokorrelation);
    }
  }

  public void berechneZufall() throws BerechnungException {
    if (generatorklasse == null) {
      throw new BerechnungException("Generatorklasse ist nicht ausgewählt. Bitte vorher setzen");
    }
    List<Double> zufallszahlen = (List<Double>) generatorklasse.generiereZufall();
    zufallErgebnisData.setZufallszahlen(zufallszahlen);
  }

  public ZufallErgebnisData generiere() throws BerechnungException {
    if (zufallData.getZiel() == Ziel.ZUFALLSGENERIERUNG) {
      berechneZufall();
    } else if (zufallData.getZiel() == Ziel.BEWERTUNG) {
      berechneBewertung();
    } else {
      throw new BerechnungException(
          "Weder Zufallszahlen, noch Bewertung wurden generiert. Bitte überprüfe gesetzte Werte!");
    }

    return zufallErgebnisData;
  }
}
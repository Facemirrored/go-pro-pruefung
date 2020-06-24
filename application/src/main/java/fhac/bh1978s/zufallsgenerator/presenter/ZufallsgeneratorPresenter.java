package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.exception.BerechnungException;
import fhac.bh1978s.zufallsgenerator.enumeration.BjarnscheParameter;
import fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter;
import fhac.bh1978s.zufallsgenerator.enumeration.PolarMethodParameter;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;
import fhac.bh1978s.zufallsgenerator.model.ZufallErgebnisData;
import fhac.bh1978s.zufallsgenerator.presenter.bewertung.SequenzUpDownTest;
import fhac.bh1978s.zufallsgenerator.presenter.bewertung.SerielleAutokorrelation;
import fhac.bh1978s.zufallsgenerator.presenter.generator.BjarnscheZufallsmethode;
import fhac.bh1978s.zufallsgenerator.presenter.generator.LcgGenerator;
import fhac.bh1978s.zufallsgenerator.presenter.generator.PolarMethod;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
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

  private LcgGenerator generateLCG() {
    return new LcgGenerator(
        Long.parseLong(
            zufallData.getParameterList().get(LcgParameter.MODUL.getLcgParameter())),
        Long.parseLong(
            zufallData.getParameterList().get(LcgParameter.MULTIPLIKATOR.getLcgParameter())),
        Long.parseLong(
            zufallData.getParameterList().get(LcgParameter.INKREMENT.getLcgParameter())),
        Long.parseLong(
            zufallData.getParameterList().get(LcgParameter.STARTWERT.getLcgParameter())),
        zufallData.getN(),
        (zufallData.getParameterList().get(LcgParameter.DIVIDE.getLcgParameter()).equals("true")));
  }

  @SuppressWarnings("unchecked")
  private void init() {
    if (zufallData.getGeneratorType() != null) {
      switch (zufallData.getGeneratorType()) {
        case LCG:
          generatorklasse = generateLCG();
          break;
        case POLAR_METHOD:
          if (zufallData.getParameterList()
              .get(PolarMethodParameter.GENERATOR.getPolarMethodParameter()) != null) {
            generatorklasse = new PolarMethod(generateLCG());
          } else {
            generatorklasse = new PolarMethod();
          }
          break;
        case BJARNSCHE_ZUFALLSMETHODE:
          generatorklasse = new BjarnscheZufallsmethode(
              Long.parseLong(zufallData.getParameterList()
                  .get(BjarnscheParameter.MODUL.getBjarnscheParameter())),
              Long.parseLong(zufallData.getParameterList()
                  .get(BjarnscheParameter.STARTWERT.getBjarnscheParameter())),
              zufallData.getN());
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

  @SuppressWarnings("unchecked")
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
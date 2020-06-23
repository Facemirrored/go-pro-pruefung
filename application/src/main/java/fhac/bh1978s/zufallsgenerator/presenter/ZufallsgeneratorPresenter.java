package fhac.bh1978s.zufallsgenerator.presenter;

import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.INKREMENT;
import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.MODUL;
import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.MULTIPLIKATOR;
import static fhac.bh1978s.zufallsgenerator.enumeration.LcgParameter.STARTWERT;

import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;
import fhac.bh1978s.zufallsgenerator.model.ZufallErgebnisData;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;

public class ZufallsgeneratorPresenter<ZufallType> {

  private ZufallData<ZufallType, ?> zufallData;
  private ZufallErgebnisData<?> zufallErgebnisData;
  private I_Generatorklasse<?> generatorklasse;
  private I_Bewertung<?> bewertung;

  public ZufallsgeneratorPresenter(final ZufallData<ZufallType, ?> zufallData) {
    this.zufallData = zufallData;
    init();
  }

  private void init() {
    switch (zufallData.getGeneratorType()) {
      case LCG:
        generatorklasse = new LcgGenerator(
            (Integer) zufallData.getParameterList().get(MODUL),
            (Integer) zufallData.getParameterList().get(MULTIPLIKATOR),
            (Integer) zufallData.getParameterList().get(INKREMENT),
            (Integer) zufallData.getParameterList().get(STARTWERT),
            zufallData.getN());
        break;
      case POLAR_METHOD:
        generatorklasse = new PolarMethod();
        break;
      default:
        generatorklasse = null;
    }

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

  public ZufallData<ZufallType, ?> getZufallData() {
    return zufallData;
  }

  public void setZufallData(ZufallData<ZufallType, ?> zufallData) {
    this.zufallData = zufallData;
  }

  public ZufallErgebnisData<?> getZufallErgebnisData() {
    return zufallErgebnisData;
  }

  public void setZufallErgebnisData(
      ZufallErgebnisData<?> zufallErgebnisData) {
    this.zufallErgebnisData = zufallErgebnisData;
  }

  public I_Generatorklasse<?> getGeneratorklasse() {
    return generatorklasse;
  }

  public void setGeneratorklasse(
      I_Generatorklasse<?> generatorklasse) {
    this.generatorklasse = generatorklasse;
  }

  public I_Bewertung<?> getBewertung() {
    return bewertung;
  }

  public void setBewertung(I_Bewertung<?> bewertung) {
    this.bewertung = bewertung;
  }

  public ZufallErgebnisData<?> generiere() {
    if (zufallData.getZiel().stream().anyMatch(z -> z == Ziel.ZUFALLSGENERIERUNG)) {
      zufallErgebnisData.setZufallszahlen(generatorklasse.generiereZufall());
    }
    if (zufallData.getZiel().stream().anyMatch(z -> z == Ziel.BEWERTUNG)) {
      bewertung.berechneBewertung(zufallErgebnisData.getZufallszahlen());
    }

    return zufallErgebnisData;
  }
}

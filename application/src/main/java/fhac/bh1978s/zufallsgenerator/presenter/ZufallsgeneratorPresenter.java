package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;
import fhac.bh1978s.zufallsgenerator.model.ZufallErgebnisData;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;

public class ZufallsgeneratorPresenter<ParaType, T> {

  private ZufallData<ParaType, T> zufallData;
  private ZufallErgebnisData zufallErgebnisData;
  private I_Generatorklasse generatorklasse;
  private I_Bewertung bewertung;

  public ZufallsgeneratorPresenter(final ZufallData<ParaType, T> zufallData) {
    this.zufallData = zufallData;
    init();
  }

  private void init() {
    switch (zufallData.getGeneratorType()) {
      case LCG:
        generatorklasse = new LcgGenerator();
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
        bewertung = new SerielleAutokorrelation();
        break;
      default:
        bewertung = null;
    }
  }

  public ZufallData<ParaType, T> getZufallData() {
    return zufallData;
  }

  public void setZufallData(ZufallData<ParaType, T> zufallData) {
    this.zufallData = zufallData;
  }

  public ZufallErgebnisData getZufallErgebnisData() {
    return zufallErgebnisData;
  }

  public void setZufallErgebnisData(
      ZufallErgebnisData zufallErgebnisData) {
    this.zufallErgebnisData = zufallErgebnisData;
  }

  public I_Generatorklasse getGeneratorklasse() {
    return generatorklasse;
  }

  public void setGeneratorklasse(
      I_Generatorklasse generatorklasse) {
    this.generatorklasse = generatorklasse;
  }

  public I_Bewertung getBewertung() {
    return bewertung;
  }

  public void setBewertung(I_Bewertung bewertung) {
    this.bewertung = bewertung;
  }

  public ZufallErgebnisData generiere() {
    if (zufallData.getZiel() == Ziel.ZUFALLSGENERIERUNG) {
      // TODO: baue zufallszahlen und bewertung
    } else if (zufallData.getZiel() == Ziel.BEWERTUNG) {
      // TODO: baue bewertung
    } else {
      zufallErgebnisData = null;
    }

    return zufallErgebnisData;
  }
}

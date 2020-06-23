package fhac.bh1978s.zufallsgenerator.model;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import java.util.List;

public class ZufallErgebnisData {

  private List<Double> zufallszahlen;
  private I_Bewertung bewertung;

  public List<Double> getZufallszahlen() {
    return zufallszahlen;
  }

  public void setZufallszahlen(List<Double> zufallszahlen) {
    this.zufallszahlen = zufallszahlen;
  }

  public I_Bewertung getBewertung() {
    return bewertung;
  }

  public void setBewertung(I_Bewertung bewertung) {
    this.bewertung = bewertung;
  }
}

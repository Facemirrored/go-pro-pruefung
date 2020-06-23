package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import java.util.ArrayList;
import java.util.List;

public class SerielleAutokorrelation implements I_Bewertung<Double> {

  private double m;
  private List<Double> rohList = new ArrayList<>();

  public SerielleAutokorrelation(double m) {
    this.m = m;
  }

  public double getM() {
    return m;
  }

  public void setM(double m) {
    this.m = m;
  }

  public List<Double> getRohList() {
    return rohList;
  }

  public void setRohList(List<Double> rohList) {
    this.rohList = rohList;
  }

  @Override
  public void berechneBewertung(List<Double> zufallszahlen) {
    rohList.clear();
    double zaehler = 0, nenner = 0;

    // Statistisch Sinnvoll für bis zu circa 1/3 aller Zahlen
    for (int k = 0; k < zufallszahlen.size() / 3; ++k) {

      for (int i = 0; i < zufallszahlen.size() - k; ++i) {
        zaehler += ((zufallszahlen.get(i) - m) * (zufallszahlen.get(i + k) - m));
        nenner += Math.pow(zufallszahlen.get(i) - m, 2);
      }

      rohList.add(zaehler / nenner);
    }
  }
}

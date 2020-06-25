package fhac.bh1978s.zufallsgenerator.presenter.bewertung;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import java.math.BigDecimal;
import java.math.MathContext;
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
    BigDecimal zaehler, nenner;

    // Statistisch Sinnvoll für bis zu circa 1/2 aller Zahlen
    for (int k = 0; k < Math.ceil(zufallszahlen.size() / 2f); ++k) {

      zaehler = BigDecimal.valueOf(0);
      nenner = BigDecimal.valueOf(0);

      for (int i = 0; i < zufallszahlen.size() - k; ++i) {
        zaehler = zaehler
            .add((BigDecimal.valueOf(zufallszahlen.get(i)).subtract(BigDecimal.valueOf(m)))
                .multiply(
                    BigDecimal.valueOf(zufallszahlen.get(i + k)).subtract(BigDecimal.valueOf(m))));
        nenner = nenner.add(BigDecimal.valueOf(Math.pow(zufallszahlen.get(i) - m, 2)));
      }

      rohList.add(zaehler.divide(nenner, new MathContext(100)).doubleValue());
    }
  }
}

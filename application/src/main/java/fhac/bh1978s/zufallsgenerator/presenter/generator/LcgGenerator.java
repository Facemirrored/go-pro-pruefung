package fhac.bh1978s.zufallsgenerator.presenter.generator;

import fhac.bh1978s.zufallsgenerator.generatorexception.CalculationException;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse für die Berechnung von Zufallszahlen auf Basis eines Linear-Congruential-Generator vom
 * Mathematiker D.H. Lehmer entwickelt im Jahre 1949.
 */
public class LcgGenerator implements I_Generatorklasse<Double> {

  private long m;
  private long a;
  private long c;
  private long x0;
  private int n;
  private boolean divide;

  public LcgGenerator(long m, long a, long c, long x0, int n, boolean divide) {
    this.m = m;
    this.a = a;
    this.c = c;
    this.x0 = x0;
    this.n = n;
    this.divide = divide;
  }

  @Override
  public List<Double> generiereZufall() throws CalculationException {

    ArrayList<Long> zufallList = new ArrayList<>();

    if (m <= 0 || m >= Math.pow(2, 64) - 1) {
      throw new CalculationException(
          "LCG-Parameter m befindet sich nicht zwischen 0 und 2^64 (long-range)");
    } else if (a < 0 || a >= m) {
      throw new CalculationException(
          "LCG-Parameter a befindet sich nicht zwischen 0 und m.");
    } else if (c < 0 || c >= m) {
      throw new CalculationException(
          "LCG-Parameter c befindet sich nicht zwischen 0 und m (inklusiv).");
    } else if (x0 < 0 || x0 >= m) {
      throw new CalculationException(
          "LCG-Parameter x0 befindet sich nicht zwischen 0 und m (inklusiv)");
    } else if (n < 1 || n > 50000) {
      throw new CalculationException(
          "LCG-Parameter n befindet sich nicht zwischen 0 und 50000 (inklusiv).");
    }

    zufallList.add(x0);

    for (int i = 1; i < n; ++i) {
      zufallList.add((BigInteger.valueOf(a).multiply(BigInteger.valueOf(zufallList.get(i - 1)))
          .add(BigInteger.valueOf(c))).mod(BigInteger.valueOf(m)).longValue());
    }

    List<Double> finalList = new ArrayList<>();

    for (int i = 0; i < zufallList.size(); ++i) {
      if (divide) {
        finalList.add(zufallList.get(i) / (double) m);
      } else {
        finalList.add(Double.parseDouble("" + zufallList.get(i)));
      }
    }

    return finalList;
  }
}

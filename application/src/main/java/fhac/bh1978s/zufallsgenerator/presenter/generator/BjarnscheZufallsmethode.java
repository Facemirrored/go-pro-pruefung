package fhac.bh1978s.zufallsgenerator.presenter.generator;

import fhac.bh1978s.exception.BerechnungException;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BjarnscheZufallsmethode implements I_Generatorklasse<Double> {

  private long m;
  private int n;
  private long x0;


  public BjarnscheZufallsmethode(final long m, final long x0, final int n) {
    this.m = m;
    this.x0 = x0;
    this.n = n;
  }

  @Override
  public List<Double> generiereZufall() throws BerechnungException {
    if (m <= 0 || m >= Math.pow(2, 64) - 1) {
      throw new BerechnungException("LCG-Parameter m befindet sich nicht zwischen 0 und 2^64 (long-range)");
    } else if (x0 < 0 || x0 >= m) {
      throw new BerechnungException(
          "LCG-Parameter x0 befindet sich nicht zwischen 0 und m (inklusiv)");
    } else if (n < 1 || n > 50000) {
      throw new BerechnungException(
          "LCG-Parameter n befindet sich nicht zwischen 0 und 50000 (inklusiv).");
    }

    Long[] zufallszahlen = new Long[n];
    boolean change = true;
    Long date = Calendar.getInstance().getTime().getTime();
    zufallszahlen[0] = x0;
    int counter = 0;

    for (int i = 1; i < n; ++i) {
      if (m + counter == 0) {
        counter++;
      }

      if (change) {
        zufallszahlen[i] = (date - zufallszahlen[i - 1]) % (m + counter);
      } else {
        zufallszahlen[i] = (date + zufallszahlen[i - 1]) % (m - counter);
      }
      change = !change;
    }

    Double[] finalArray = new Double[zufallszahlen.length];

    for (int i = 0; i < zufallszahlen.length; ++i) {
      finalArray[i] = zufallszahlen[i] / (double) (Math.max(n, m));
    }

    return Arrays.asList(finalArray);
  }
}

package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BjarnscheZufallsmethode implements I_Generatorklasse<Long> {

  private long m;
  private int n;
  private long x0;


  public BjarnscheZufallsmethode(final long m, final long x0, final int n) {
    this.m = m;
    this.x0 = x0;
    this.n = n;
  }

  @Override
  public List<Long> generiereZufall() {
    Long[] zufallszahlen = new Long[n];
    boolean change = true;
    int counter = 0;
    Long date = Calendar.getInstance().getTime().getTime();
    zufallszahlen[0] = x0;

    for (int i = 1; i < n; ++i) {
      if (change) {
        zufallszahlen[i] = (date - zufallszahlen[i - 1]) % (m + counter);
      } else {
        zufallszahlen[i] = (date + zufallszahlen[i - 1]) % (m - counter);
      }
      counter++;
      change = !change;
    }

    return Arrays.asList(zufallszahlen);
  }
}

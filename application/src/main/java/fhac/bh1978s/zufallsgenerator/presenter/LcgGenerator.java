package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class LcgGenerator implements I_Generatorklasse<Long> {

  private long m;
  private long a;
  private long c;
  private long x0;
  private int n;

  public LcgGenerator(long m, long a, long c, long x0, int n) {
    this.m = m;
    this.a = a;
    this.c = c;
    this.x0 = x0;
    this.n = n;
  }

  @Override
  public List<Long> generiereZufall() throws InvalidParameterException {

    ArrayList<Long> zufallList = new ArrayList<>();

    if (m <= 0) {
      throw new InvalidParameterException("Parameter m ist kleiner gleich 0.");
    } else if (a < 0 || a >= m) {
      throw new InvalidParameterException(
          "Parameter a befindet sich nicht zwischen 0 und m (inklusiv).");
    } else if (c < 0 || c >= m) {
      throw new InvalidParameterException(
          "Parameter c befindet sich nicht zwischen 0 und m (inklusiv).");
    } else if (x0 < 0 || x0 >= m) {
      throw new InvalidParameterException(
          "Parameter x0 befindet sich nicht zwischen 0 und m (inklusiv)");
    } else if (n < 1) {
      throw new InvalidParameterException(
          "Parameter n kleiner 1. Mindestens eine Zahl wird benötigt.");
    }

    zufallList.add(x0);

    for (int i = 1; i < n; ++i) {
      zufallList.add((a * zufallList.get(i - 1) + c) % m);
    }

    return zufallList;
  }
}

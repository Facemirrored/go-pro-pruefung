package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.util.ArrayList;
import java.util.List;

public class PolarMethod implements I_Generatorklasse<Double> {

  // TODO: Überladung für spezifische random zahlen (private attribute für u,v)
  @Override
  public List<Double> generiereZufall() {
    ArrayList<Double> zufallList = new ArrayList<>();

    double p, q, u, v;

    do {
      u = Math.random() * 2 - 1;
      v = Math.random() * 2 - 1;
      q = u * u + v * v;

      if (q == 0 || q >= 1) {
        continue;
      }
      break;

    } while (true);

    p = Math.sqrt((-Math.log(q)) / q);

    zufallList.add(u * p);
    zufallList.add(v * p);

    return zufallList;
  }
}

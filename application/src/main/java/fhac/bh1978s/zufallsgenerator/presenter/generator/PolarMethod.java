package fhac.bh1978s.zufallsgenerator.presenter.generator;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.util.ArrayList;
import java.util.List;

public class PolarMethod implements I_Generatorklasse<Double> {

  private I_Generatorklasse generatorklasse;
  private double u;
  private double v;

  public PolarMethod() {
  }

  public PolarMethod(I_Generatorklasse generatorklasse) {
    this.generatorklasse = generatorklasse;
  }

  @Override
  public List<Double> generiereZufall() {
    ArrayList<Double> zufallList = new ArrayList<>();

    double p, q;

    do {
      generiereParameter();
      q = u * u + v * v;

      if (q == 0 || q >= 1) {
        continue;
      }
      break;

    } while (true);

    p = Math.sqrt((-2f * Math.log(q)) / q);

    zufallList.add(u * p);
    zufallList.add(v * p);

    return zufallList;
  }

  private void generiereParameter() {
    if (this.generatorklasse == null) {
      u = Math.random() * 2 - 1;
      v = Math.random() * 2 - 1;
    } else {
      List zufallszahlen = generatorklasse.generiereZufall();
      u = (double) zufallszahlen.get(0);
      v = (double) zufallszahlen.get(1);
    }
  }
}

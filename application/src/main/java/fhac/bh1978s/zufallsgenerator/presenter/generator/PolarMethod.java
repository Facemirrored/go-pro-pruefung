package fhac.bh1978s.zufallsgenerator.presenter.generator;

import fhac.bh1978s.zufallsgenerator.generatorexception.CalculationException;
import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Generatorklasse;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse für die Berechnung von Zufallszahlen auf Basis der Polar-Methode von den Mathematikern
 * George Marsaglia und Thomas A. Bray. Die Berechnung der Zufallszahlen basiert auf bereits
 * vorhandenen Zufallszahlen. Das Verfahren für die Erzeugung dieser wird über den Konstruktor
 * angegeben. Ist keines angegeben, so wird die interne Java-Bibliothek genutzt.
 */
public class PolarMethod implements I_Generatorklasse<Double> {

  private I_Generatorklasse generatorklasse;
  private double u;
  private double v;
  private int counter = 0;

  public PolarMethod() {
  }

  public PolarMethod(I_Generatorklasse generatorklasse) {
    this.generatorklasse = generatorklasse;
  }

  @Override
  public List<Double> generiereZufall() throws CalculationException {
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

  private void generiereParameter() throws CalculationException {
    // Ohne Mocking nicht testbar
    if (counter == 10000) {
      throw new CalculationException(
          "Initiale Zufallszahlengenerierung für Polar-Methode nach " + counter
              + " versuchen gescheitert.");
    }

    counter++;
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

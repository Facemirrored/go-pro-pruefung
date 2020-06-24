package fhac.bh1978s.zufallsgenerator.presenter.bewertung;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import java.util.ArrayList;
import java.util.List;

public class BjarnscheGuetefunktion implements I_Bewertung<Double> {

  private int[] bitCounter;

  public int[] getBitCounter() {
    return bitCounter;
  }

  public void setBitCounter(int[] bitCounter) {
    this.bitCounter = bitCounter;
  }

  @Override
  public void berechneBewertung(List<Double> zufallszahlen) {
    ArrayList<Boolean> bitfolge = new ArrayList<>();
    int itCount = Math.min(zufallszahlen.size(), 17) - 1;
    bitCounter = new int[itCount];

    double abstand = Math.abs(zufallszahlen.get(1) - zufallszahlen.get(0));

    for (int i = 2; i < zufallszahlen.size(); ++i) {
      double a = Math.abs(zufallszahlen.get(i) - zufallszahlen.get(i - 1));
      bitfolge.add((a <= abstand));
      abstand = a;
    }

    int counter = 0;
    boolean last = bitfolge.get(0);
    bitCounter[counter]++;

    for (int i = 1; i < itCount; ++i) {
      if (bitfolge.get(i) == last) {
        bitCounter[counter]--;
        counter++;
        bitCounter[counter]++;
      } else {
        counter = 0;
        bitCounter[counter]++;
      }

      last = bitfolge.get(i);
    }
  }
}

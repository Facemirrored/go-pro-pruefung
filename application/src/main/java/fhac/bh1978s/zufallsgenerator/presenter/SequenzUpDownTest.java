package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import java.util.ArrayList;
import java.util.List;

public class SequenzUpDownTest implements I_Bewertung<Integer> {

  private int[] bitCounter;

  public int[] getBitCounter() {
    return bitCounter;
  }

  public void setBitCounter(int[] bitCounter) {
    this.bitCounter = bitCounter;
  }

  @Override
  public void berechneBewertung(List<Integer> zufallszahlen) {
    ArrayList<Boolean> bitfolge = new ArrayList<>();
    bitCounter = new int[zufallszahlen.size()];

    for (int i = 0; i < zufallszahlen.size() - 1; ++i) {
      bitfolge.add(zufallszahlen.get(i) <= zufallszahlen.get(i + 1));
    }

    int counter = 1;
    boolean last = bitfolge.get(0);

    for (int i = 1; i < bitfolge.size(); ++i) {
      if (bitfolge.get(i) == last) {
        counter++;
      } else {
        bitCounter[counter - 1]++;
      }

      last = bitfolge.get(i);
    }
  }
}
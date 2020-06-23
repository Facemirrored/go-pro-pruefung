package fhac.bh1978s.zufallsgenerator.presenter;

import fhac.bh1978s.zufallsgenerator.presenter.interfaces.I_Bewertung;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SequenzUpDownTest implements I_Bewertung<Double> {

  private int[] bitCounter;
  private double[] expCounter;

  public int[] getBitCounter() {
    return bitCounter;
  }

  public double[] getExpCounter() {
    return expCounter;
  }

  public void setExpCounter(double[] expCounter) {
    this.expCounter = expCounter;
  }

  public void setBitCounter(int[] bitCounter) {
    this.bitCounter = bitCounter;
  }

  @Override
  public void berechneBewertung(List<Double> zufallszahlen) {
    ArrayList<Boolean> bitfolge = new ArrayList<>();
    boolean maxK = zufallszahlen.size() > 11;
    bitCounter = new int[maxK ? 10 : zufallszahlen.size() - 1];
    int maxIteration = maxK ? 11 : zufallszahlen.size() - 1;

    for (int i = 0; i < zufallszahlen.size() - 1; ++i) {
      bitfolge.add((zufallszahlen.get(i) <= zufallszahlen.get(i + 1)));
    }

    int counter = 0;
    boolean last = bitfolge.get(0);
    bitCounter[counter]++;

    for (int i = 1; i < maxIteration; ++i) {
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

    berechneErwartung(zufallszahlen.size());
  }

  private void berechneErwartung(final int n) {
    expCounter = new double[bitCounter.length];

    for (int i = 0; i < expCounter.length; ++i) {
      int k = i + 1;
      double zaehlerP1 = (Math.pow(k, 2) + (3 * k) + 1) * n;
      double zaehlerP2 = (Math.pow(k, 3) + 3 * Math.pow(k, 2) - k - 4);
      double nenner = berechneFak(k + 3) / 2d;
      expCounter[i] = (zaehlerP1 - zaehlerP2) / nenner;
    }
  }

  private long berechneFak(final int n) {
    long erg = 1;
    for (int i = 1; i <= n; ++i) {
      erg *= i;
    }
    return erg;
  }
}
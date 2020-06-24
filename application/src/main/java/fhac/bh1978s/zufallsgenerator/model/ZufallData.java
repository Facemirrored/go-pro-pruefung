package fhac.bh1978s.zufallsgenerator.model;

import fhac.bh1978s.zufallsgenerator.enumeration.BewertungType;
import fhac.bh1978s.zufallsgenerator.enumeration.GeneratorType;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import java.util.HashMap;
import java.util.List;

public class ZufallData {

  private Ziel ziel;
  private GeneratorType generatorType;
  private BewertungType bewertungType;
  private HashMap<String, String> parameterList = new HashMap<>();
  private List<Double> zufallszahlen;
  private int n;

  public int getN() {
    return n;
  }

  public void setN(int n) {
    this.n = n;
  }

  public HashMap<String, String> getParameterList() {
    return parameterList;
  }

  public void setParameterList(HashMap<String, String> parameterList) {
    this.parameterList = parameterList;
  }

  public void addParameter(String key, String value) {
    parameterList.put(key, value);
  }

  public void addParameter(final HashMap<String, String> hashMap) {
    parameterList.putAll(hashMap);
  }

  public Ziel getZiel() {
    return ziel;
  }

  public void setZiel(Ziel ziel) {
    this.ziel = ziel;
  }

  public GeneratorType getGeneratorType() {
    return generatorType;
  }

  public void setGeneratorType(GeneratorType generatorType) {
    this.generatorType = generatorType;
  }

  public BewertungType getBewertungType() {
    return bewertungType;
  }

  public void setBewertungType(BewertungType bewertungType) {
    this.bewertungType = bewertungType;
  }

  public List<Double> getZufallszahlen() {
    return zufallszahlen;
  }

  public void setZufallszahlen(List<Double> zufallszahlen) {
    this.zufallszahlen = zufallszahlen;
  }
}

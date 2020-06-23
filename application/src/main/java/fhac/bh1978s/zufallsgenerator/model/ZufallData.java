package fhac.bh1978s.zufallsgenerator.model;

import fhac.bh1978s.zufallsgenerator.enumeration.BewertungType;
import fhac.bh1978s.zufallsgenerator.enumeration.GeneratorType;
import fhac.bh1978s.zufallsgenerator.enumeration.Parameter;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import java.util.HashMap;
import java.util.List;

public class ZufallData<ParaType, ZufallType> {

  private List<Ziel> ziel;
  private GeneratorType generatorType;
  private BewertungType bewertungType;
  private HashMap<Parameter<ParaType>, ?> parameterList;
  private List<ZufallType> zufallszahlen;
  private int n;

  public int getN() {
    return n;
  }

  public void setN(int n) {
    this.n = n;
  }

  public List<Ziel> getZiel() {
    return ziel;
  }

  public void setZiel(List<Ziel> ziel) {
    this.ziel = ziel;
  }

  public HashMap<Parameter<ParaType>, ?> getParameterList() {
    return parameterList;
  }

  public void setParameterList(
      HashMap<Parameter<ParaType>, ?> parameterList) {
    this.parameterList = parameterList;
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

  public List<ZufallType> getZufallszahlen() {
    return zufallszahlen;
  }

  public void setZufallszahlen(List<ZufallType> zufallszahlen) {
    this.zufallszahlen = zufallszahlen;
  }
}

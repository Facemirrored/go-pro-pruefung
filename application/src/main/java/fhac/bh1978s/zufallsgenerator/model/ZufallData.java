package fhac.bh1978s.zufallsgenerator.model;

import fhac.bh1978s.zufallsgenerator.enumeration.BewertungType;
import fhac.bh1978s.zufallsgenerator.enumeration.GeneratorType;
import fhac.bh1978s.zufallsgenerator.enumeration.Parameter;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZufallData<ParaType, ZufallType> {

  private Ziel ziel;
  private GeneratorType generatorType;
  private BewertungType bewertungType;
  private Map<Parameter<ParaType>, Double> parameter = new HashMap<>();
  private List<ZufallType> zufallszahlen;

  public ZufallData(Ziel ziel, GeneratorType generator,
      HashMap<Parameter<ParaType>, Double> parameter) {
    this.ziel = ziel;
    this.generatorType = generator;
    this.parameter = parameter;
  }

  public ZufallData(Ziel ziel, List<ZufallType> zufallszahlen) {
    this.ziel = ziel;
    this.zufallszahlen = zufallszahlen;
  }

  public BewertungType getBewertungType() {
    return bewertungType;
  }

  public void setBewertungType(BewertungType bewertungType) {
    this.bewertungType = bewertungType;
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

  public Map<Parameter<ParaType>, Double> getParameter() {
    return parameter;
  }

  public void setParameter(
      Map<Parameter<ParaType>, Double> parameter) {
    this.parameter = parameter;
  }

  public List<ZufallType> getZufallszahlen() {
    return zufallszahlen;
  }

  public void setZufallszahlen(List<ZufallType> zufallszahlen) {
    this.zufallszahlen = zufallszahlen;
  }
}

package fhac.bh1978s.zufallsgenerator.enumeration;

public enum GeneratorType implements Parameter<String> {
  LCG("Linear-Kongruenz-Generator"),
  POLAR_METHOD("Polar-Methode");

  private String generatorType;

  GeneratorType(final String generatorType) {
    this.generatorType = generatorType;
  }

  @Override
  public String getParameter() {
    return null;
  }
}

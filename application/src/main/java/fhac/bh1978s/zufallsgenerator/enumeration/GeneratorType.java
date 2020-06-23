package fhac.bh1978s.zufallsgenerator.enumeration;

public enum GeneratorType {
  LCG("Linear-Kongruenz-Generator"),
  POLAR_METHOD("Polar-Methode");

  private String generatorType;

  GeneratorType(final String generatorType) {
    this.generatorType = generatorType;
  }

  public String getGeneratorType() {
    return generatorType;
  }
}

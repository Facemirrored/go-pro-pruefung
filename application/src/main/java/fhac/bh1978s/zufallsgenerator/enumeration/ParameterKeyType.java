package fhac.bh1978s.zufallsgenerator.enumeration;

public enum ParameterKeyType {
  ZIEL("Ziel"),
  GENERATOR("Generator"),
  LCG_PARAMETER("LCG-Parameter"),
  BJARNSCHE_ZUFALLSMETHODE("Bjarnsche-Zufallsmethode-Parameter"),
  N("n"),
  POLAR_METHOD_PARAMETER("Polar-Method-Parameter"),
  BEWERTUNGSART("Bewertungsart"),
  ZUFALLSZAHLEN("Zufallszahlen"),
  KOMMENTAR("#");

  private String parameterKeyType;

  ParameterKeyType(final String parameterKeyType) {
    this.parameterKeyType = parameterKeyType;
  }

  public String getString() {
    return parameterKeyType;
  }
}

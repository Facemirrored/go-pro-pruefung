package fhac.bh1978s.zufallsgenerator.enumeration;

/**
 * Enumeration für die Verwaltung von Parameter der Polar-Methode
 */
public enum PolarMethodParameter {
  GENERATOR("Generator");

  private String polarMethodParameter;

  PolarMethodParameter(final String parameter) {
    this.polarMethodParameter = parameter;
  }

  public String getPolarMethodParameter() {
    return polarMethodParameter;
  }
}

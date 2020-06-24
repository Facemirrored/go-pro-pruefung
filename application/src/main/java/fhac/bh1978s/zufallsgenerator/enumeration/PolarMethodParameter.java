package fhac.bh1978s.zufallsgenerator.enumeration;

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

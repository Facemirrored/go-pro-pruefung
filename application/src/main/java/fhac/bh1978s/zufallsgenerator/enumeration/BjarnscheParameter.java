package fhac.bh1978s.zufallsgenerator.enumeration;

public enum BjarnscheParameter {
  MODUL("m"),
  STARTWERT("x0");

  private String bjarnscheParameter;

  BjarnscheParameter(final String type) {
    this.bjarnscheParameter = type;
  }

  public String getBjarnscheParameter() {
    return bjarnscheParameter;
  }
}

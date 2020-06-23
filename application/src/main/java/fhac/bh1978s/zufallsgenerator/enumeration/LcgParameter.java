package fhac.bh1978s.zufallsgenerator.enumeration;

import java.util.Enumeration;

public enum LcgParameter implements Parameter<String> {
  MODUL("m"),
  MULTIPLIKATOR("a"),
  INKREMENT("c"),
  STARTWERT("x0");

  private String lcgParameter;

  LcgParameter(final String type) {
    this.lcgParameter = type;
  }

  public String getLcgParameter() {
    return lcgParameter;
  }

  @Override
  public String getParameter() {
    return lcgParameter;
  }
}

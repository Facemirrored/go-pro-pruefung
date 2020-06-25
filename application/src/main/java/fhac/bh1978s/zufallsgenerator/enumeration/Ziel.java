package fhac.bh1978s.zufallsgenerator.enumeration;

import fhac.bh1978s.programexception.ParameterException;

public enum Ziel {
  ZUFALLSGENERIERUNG("Zufallsgenerierung"),
  BEWERTUNG("Bewertung");

  private String ziel;

  Ziel(final String ziel) {
    this.ziel = ziel;
  }

  public String getZiel() {
    return ziel;
  }

  public static Ziel fromString(final String z) throws ParameterException {
    for (Ziel zielEnum : Ziel.values()) {
      if (zielEnum.ziel.equalsIgnoreCase(z)) {
        return zielEnum;
      }
    }

    throw new ParameterException("Ziel <" + z + "> unbekannt.");
  }
}

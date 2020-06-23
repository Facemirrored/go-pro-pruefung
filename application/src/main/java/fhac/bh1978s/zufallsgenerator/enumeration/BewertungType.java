package fhac.bh1978s.zufallsgenerator.enumeration;

import fhac.bh1978s.exception.ZufallMappingException;

public enum BewertungType {
  SERIELLE_AUTOKORRELATION("Serielle-Autokorrelation"),
  SEQUENZ_UP_DOWN_TEST("Sequenz-Up-Down-Test");

  BewertungType(final String bewertungType) {
    this.bewertungType = bewertungType;
  }

  private String bewertungType;

  public String getBewertungType() {
    return bewertungType;
  }

  public static BewertungType fromString(final String b) throws ZufallMappingException {
    for (BewertungType bewertungEnum : BewertungType.values()) {
      if (bewertungEnum.bewertungType.equalsIgnoreCase(b)) {
        return bewertungEnum;
      }
    }

    throw new ZufallMappingException("Generator <" + b + "> unbekannt. Bitte Überprüfen.");
  }
}

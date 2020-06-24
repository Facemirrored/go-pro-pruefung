package fhac.bh1978s.zufallsgenerator.enumeration;

import fhac.bh1978s.exception.ParameterException;
import fhac.bh1978s.exception.ZufallMappingException;

public enum BewertungType {
  SERIELLE_AUTOKORRELATION("Serielle-Autokorrelation"),
  SEQUENZ_UP_DOWN_TEST("Sequenz-Up-Down-Test"),
  BJARNSCHE_GUETEFUNKTION("Bjarnsche-Guetefunktion");

  BewertungType(final String bewertungType) {
    this.bewertungType = bewertungType;
  }

  private String bewertungType;

  public String getBewertungType() {
    return bewertungType;
  }

  public static BewertungType fromString(final String b) throws ParameterException {
    for (BewertungType bewertungEnum : BewertungType.values()) {
      if (bewertungEnum.bewertungType.equalsIgnoreCase(b)) {
        return bewertungEnum;
      }
    }

    throw new ParameterException("Bewertung <" + b + "> unbekannt.");
  }
}

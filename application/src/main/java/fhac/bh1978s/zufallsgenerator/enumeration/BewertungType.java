package fhac.bh1978s.zufallsgenerator.enumeration;

import fhac.bh1978s.programexception.ParameterException;

/**
 * Enumeration für die Verwaltung von Namen der Güte-Funktionen
 */
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

  /**
   * Methode für die Suche von Enumeration-Typ anhand eines Strings
   *
   * @param b String, nach dem gesucht werden soll
   * @return Enumeration-Typ, sofern String gefunden wurde
   * @throws ParameterException Wird geworfen, wenn kein Enumeration-Typ zum String gefunden wurde
   */
  public static BewertungType fromString(final String b) throws ParameterException {
    for (BewertungType bewertungEnum : BewertungType.values()) {
      if (bewertungEnum.bewertungType.equalsIgnoreCase(b)) {
        return bewertungEnum;
      }
    }

    throw new ParameterException("Bewertung <" + b + "> unbekannt.");
  }
}

package fhac.bh1978s.zufallsgenerator.enumeration;

import fhac.bh1978s.programexception.ParameterException;

/**
 * Enumeration für die Verwaltung von Namen der Generatoren
 */
public enum GeneratorType {
  LCG("Linear-Kongruenz-Generator"),
  POLAR_METHOD("Polar-Methode"),
  BJARNSCHE_ZUFALLSMETHODE("Bjarnsche-Zufallsmethode");

  private String generatorType;

  GeneratorType(final String generatorType) {
    this.generatorType = generatorType;
  }

  public String getGeneratorType() {
    return generatorType;
  }

  /**
   * Methode für die Suche von Enumeration-Typ anhand eines Strings
   *
   * @param g String, nach dem gesucht werden soll
   * @return Enumeration-Typ, sofern String gefunden wurde
   * @throws ParameterException Wird geworfen, wenn kein Enumeration-Typ zum String gefunden wurde
   */
  public static GeneratorType fromString(final String g) throws ParameterException {
    for (GeneratorType generatorEnum : GeneratorType.values()) {
      if (generatorEnum.generatorType.equalsIgnoreCase(g)) {
        return generatorEnum;
      }
    }

    throw new ParameterException("Generator <" + g + "> unbekannt.");
  }
}

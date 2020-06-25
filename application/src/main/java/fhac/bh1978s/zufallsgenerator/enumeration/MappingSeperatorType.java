package fhac.bh1978s.zufallsgenerator.enumeration;

/**
 * Enumeration für die Verwaltung von Trennungszeichen beim Mapping
 */
public enum MappingSeperatorType {
  KEY_VALUE_SEPERATOR("="),
  SET_SEPERATOR(","),
  PARAMETER_SEPERATOR(":");

  private String mappingSeperatorType;

  MappingSeperatorType(final String mappingSeperatorType) {
    this.mappingSeperatorType = mappingSeperatorType;
  }

  public String getString() {
    return this.mappingSeperatorType;
  }
}

package fhac.bh1978s.zufallsgenerator.enumeration;

import fhac.bh1978s.exception.ParameterException;

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

  public static MappingSeperatorType fromString(final String m) throws ParameterException {
    for (MappingSeperatorType mappingSeperatorEnum : MappingSeperatorType.values()) {
      if (mappingSeperatorEnum.mappingSeperatorType.equalsIgnoreCase(m)) {
        return mappingSeperatorEnum;
      }
    }

    throw new ParameterException("<" + m + "> ist kein gültiges Trennungszeichen.");
  }
}

package fhac.bh1978s.zufallsgenerator.enumeration;

import fhac.bh1978s.exception.ParameterException;
import fhac.bh1978s.exception.ZufallMappingException;

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

  public static GeneratorType fromString(final String g) throws ParameterException {
    for (GeneratorType generatorEnum : GeneratorType.values()) {
      if (generatorEnum.generatorType.equalsIgnoreCase(g)) {
        return generatorEnum;
      }
    }

    throw new ParameterException("Generator <" + g + "> unbekannt.");
  }
}

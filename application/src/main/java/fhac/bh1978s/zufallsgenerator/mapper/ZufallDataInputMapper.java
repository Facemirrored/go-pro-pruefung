package fhac.bh1978s.zufallsgenerator.mapper;

import fhac.bh1978s.exception.ParameterException;
import fhac.bh1978s.exception.ZufallMappingException;
import fhac.bh1978s.view.TextFile;
import fhac.bh1978s.zufallsgenerator.enumeration.BewertungType;
import fhac.bh1978s.zufallsgenerator.enumeration.GeneratorType;
import fhac.bh1978s.zufallsgenerator.enumeration.MappingSeperatorType;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import fhac.bh1978s.zufallsgenerator.mapper.interfaces.I_InputMapper;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;
import java.util.ArrayList;
import java.util.HashMap;

public class ZufallDataInputMapper implements I_InputMapper<TextFile, ZufallData> {

  // TODO: generelle unerlaunte Zeichen? Kein : zB
  @Override
  public ZufallData mapToInternFormat(TextFile externContent) throws ZufallMappingException {
    String content = externContent.getContent();
    ZufallData zufallData = new ZufallData();
    try {
      for (String line : content.split("\n")) {
        if (line.startsWith("Ziel")) {
          zufallData.setZiel(
              Ziel.fromString(seperatorSplit(line, MappingSeperatorType.PARAMETER_SEPERATOR)));
        } else if (line.startsWith("Generator")) {
          zufallData.setGeneratorType(GeneratorType
              .fromString(seperatorSplit(line, MappingSeperatorType.PARAMETER_SEPERATOR)));
        } else if (line.startsWith("LCG-Parameter") ||
            line.startsWith("Bjarnsche-Zufallsmethode-Parameter")) {
          String values = seperatorSplit(line, MappingSeperatorType.PARAMETER_SEPERATOR);
          HashMap<String, String> parameterHashMap = new HashMap<>();

          for (String keyValue : values.trim().split(",")) {
            String[] pair = keyValue.trim().split("=");
            if (pair[0].equalsIgnoreCase("n")) {
              zufallData.setN(Integer.parseInt(pair[1]));
            } else {
              parameterHashMap.put(pair[0], pair[1]);
            }
          }

          zufallData.addParameter(parameterHashMap);
        } else if (line.startsWith("Polar-Method-Parameter")) {
          String value = seperatorSplit(line, MappingSeperatorType.PARAMETER_SEPERATOR);
          zufallData.addParameter(value.split("=")[0], value.split("=")[1]);
        } else if (line.startsWith("Bewertungsart")) {
          zufallData.setBewertungType(BewertungType
              .fromString(seperatorSplit(line, MappingSeperatorType.PARAMETER_SEPERATOR)));
        } else if (line.startsWith("Zufallszahlen")) {
          String values = line.trim().split(":")[1];
          ArrayList<Double> zufallszahlen = new ArrayList<>();
          for (String val : values.trim().split(",")) {
            zufallszahlen.add(Double.valueOf(val.trim()));
          }
          zufallData.setZufallszahlen(zufallszahlen);
        } else {
          // TODO: UNNEKANNTE ZEILE!!!!
        }
      }
    } catch (ParameterException pe) {
      throw new ZufallMappingException(
          "Fehler beim lesen eines Parameters. Bitte Eingabedatei überprüfen.\n\nDetails:\n"
              + pe.getMessage());
    }

    return zufallData;
  }

  private String seperatorSplit(final String line, final MappingSeperatorType type)
      throws ParameterException {
    String[] seperatorSplit = line.trim().split(type.getString());
    if (seperatorSplit.length == 1) {
      throw new ParameterException(
          "Trennung mit <" + type.getString() + "> von String <" + line + "> nicht möglich.");
    }
    return seperatorSplit[1];
  }
}

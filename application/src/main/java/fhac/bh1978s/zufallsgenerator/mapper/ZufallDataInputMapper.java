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
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class ZufallDataInputMapper implements I_InputMapper<TextFile, ZufallData> {

  // TODO: generelle unerlaunte Zeichen? Kein : zB
  @Override
  public ZufallData mapToInternFormat(TextFile externContent) throws ZufallMappingException {
    String content = externContent.getContent();
    ZufallData zufallData = new ZufallData();
    try {
      for (String line : content.split("\n")) {
        if (line.startsWith("Ziel")) {
          zufallData.setZiel(Ziel.fromString(parameterSplit(line)));
        } else if (line.startsWith("Generator")) {
          zufallData.setGeneratorType(GeneratorType
              .fromString(
                  parameterSplit(line)));
        } else if (line.startsWith("LCG-Parameter") ||
            line.startsWith("Bjarnsche-Zufallsmethode-Parameter")) {
          String values = parameterSplit(line);
          HashMap<String, String> parameterHashMap = new HashMap<>();

          for (String keyValue : setSplit(values)) {
            String[] pair = keyValue.trim()
                .split(MappingSeperatorType.KEY_VALUE_SEPERATOR.getString());
            if (pair[0].equalsIgnoreCase("n")) {
              zufallData.setN(Integer.parseInt(pair[1]));
            } else {
              parameterHashMap.put(pair[0], pair[1]);
            }
          }

          zufallData.addParameter(parameterHashMap);
        } else if (line.startsWith("Polar-Method-Parameter")) {
          String value = parameterSplit(line);
          zufallData
              .addParameter(value.split(MappingSeperatorType.KEY_VALUE_SEPERATOR.getString())[0],
                  value.split(MappingSeperatorType.KEY_VALUE_SEPERATOR.getString())[1]);
        } else if (line.startsWith("Bewertungsart")) {
          zufallData.setBewertungType(BewertungType.fromString(parameterSplit(line)));
        } else if (line.startsWith("Zufallszahlen")) {
          String values = parameterSplit(line);
          ArrayList<Double> zufallszahlen = new ArrayList<>();
          for (String val : zahlenSplit(values)) {
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

  private String parameterSplit(final String line)
      throws ParameterException {
    String[] seperatorSplit = line.trim()
        .split(MappingSeperatorType.PARAMETER_SEPERATOR.getString());
    if (seperatorSplit.length == 1) {
      throw new ParameterException(
          "Trennung mit <" + MappingSeperatorType.PARAMETER_SEPERATOR.getString() + "> von <"
              + line + "> nicht möglich.");
    }
    return seperatorSplit[1];
  }

  private String[] zahlenSplit(final String line) throws ParameterException {
    String[] zahlen = line.trim().split(MappingSeperatorType.SET_SEPERATOR.getString());
    for (String z : zahlen) {
      try {
        Double.parseDouble(z);
      } catch (NumberFormatException nfe) {
        throw new ParameterException(
            "Konvertierung von Zahl <" + z + "> in <" + line + "> nicht möglich.");
      }
    }

    return zahlen;
  }

  private String[] setSplit(final String line)
      throws ParameterException {
    final int counter = (int) line.chars()
        .filter(c -> c == (MappingSeperatorType.KEY_VALUE_SEPERATOR.getString().charAt(0)))
        .count();
    String[] setSplit = line.trim().split(MappingSeperatorType.SET_SEPERATOR.getString());
    if (setSplit.length == counter) {
      return setSplit;
    }

    throw new ParameterException(
        "Trennung mit <" + MappingSeperatorType.SET_SEPERATOR.getString() + "> von String <"
            + line + "> nicht möglich. Anzahl an sets stimmt nicht mit Trennungsanzahl überein.");
  }
}

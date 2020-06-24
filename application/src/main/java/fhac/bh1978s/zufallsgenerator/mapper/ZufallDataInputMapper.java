package fhac.bh1978s.zufallsgenerator.mapper;

import static fhac.bh1978s.zufallsgenerator.enumeration.MappingSeperatorType.KEY_VALUE_SEPERATOR;
import static fhac.bh1978s.zufallsgenerator.enumeration.MappingSeperatorType.PARAMETER_SEPERATOR;
import static fhac.bh1978s.zufallsgenerator.enumeration.MappingSeperatorType.SET_SEPERATOR;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.BEWERTUNGSART;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.BJARNSCHE_ZUFALLSMETHODE;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.GENERATOR;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.KOMMENTAR;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.LCG_PARAMETER;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.N;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.POLAR_METHOD_PARAMETER;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.ZIEL;
import static fhac.bh1978s.zufallsgenerator.enumeration.ParameterKeyType.ZUFALLSZAHLEN;

import fhac.bh1978s.exception.ParameterException;
import fhac.bh1978s.exception.ZufallMappingException;
import fhac.bh1978s.view.TextFile;
import fhac.bh1978s.zufallsgenerator.enumeration.BewertungType;
import fhac.bh1978s.zufallsgenerator.enumeration.GeneratorType;
import fhac.bh1978s.zufallsgenerator.enumeration.Ziel;
import fhac.bh1978s.zufallsgenerator.mapper.interfaces.I_InputMapper;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;
import java.util.ArrayList;
import java.util.HashMap;

public class ZufallDataInputMapper implements I_InputMapper<TextFile, ZufallData> {

  @Override
  public ZufallData mapToInternFormat(TextFile externContent) throws ZufallMappingException {
    String content = externContent.getContent();
    ZufallData zufallData = new ZufallData();
    try {
      for (String line : content.split("\n")) {
        if (line.startsWith(ZIEL.getString())) {
          zufallData.setZiel(Ziel.fromString(parameterSplit(line)));
        } else if (line.startsWith(GENERATOR.getString())) {
          zufallData.setGeneratorType(GeneratorType
              .fromString(
                  parameterSplit(line)));
        } else if (line.startsWith(LCG_PARAMETER.getString()) ||
            line.startsWith(BJARNSCHE_ZUFALLSMETHODE.getString())) {
          String values = parameterSplit(line);
          HashMap<String, String> parameterHashMap = new HashMap<>();

          for (String keyValue : setSplit(values)) {
            String[] pair = keyValue.trim()
                .split(KEY_VALUE_SEPERATOR.getString());
            if (pair[0].equalsIgnoreCase(N.getString())) {
              zufallData.setN(Integer.parseInt(pair[1]));
            } else {
              parameterHashMap.put(pair[0], pair[1]);
            }
          }

          zufallData.addParameter(parameterHashMap);
        } else if (line.startsWith(POLAR_METHOD_PARAMETER.getString())) {
          String value = parameterSplit(line);
          zufallData
              .addParameter(value.split(KEY_VALUE_SEPERATOR.getString())[0],
                  value.split(KEY_VALUE_SEPERATOR.getString())[1]);
        } else if (line.startsWith(BEWERTUNGSART.getString())) {
          zufallData.setBewertungType(BewertungType.fromString(parameterSplit(line)));
        } else if (line.startsWith(ZUFALLSZAHLEN.getString())) {
          String values = parameterSplit(line);
          ArrayList<Double> zufallszahlen = new ArrayList<>();
          for (String val : zahlenSplit(values)) {
            zufallszahlen.add(Double.valueOf(val.trim()));
          }
          zufallData.setZufallszahlen(zufallszahlen);
        } else if (!line.startsWith(KOMMENTAR.getString())) {
          throw new ZufallMappingException("Zeile <" + line + "> unbekannt.");
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
        .split(PARAMETER_SEPERATOR.getString());
    if (seperatorSplit.length == 1) {
      throw new ParameterException(
          "Trennung mit <" + PARAMETER_SEPERATOR.getString() + "> von <"
              + line + "> nicht möglich.");
    }
    return seperatorSplit[1];
  }

  private String[] zahlenSplit(final String line) throws ParameterException {
    String[] zahlen = line.trim().split(SET_SEPERATOR.getString());
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
        .filter(c -> c == (KEY_VALUE_SEPERATOR.getString().charAt(0)))
        .count();
    String[] setSplit = line.trim().split(SET_SEPERATOR.getString());
    if (setSplit.length == counter) {
      return setSplit;
    }

    throw new ParameterException(
        "Trennung mit <" + SET_SEPERATOR.getString() + "> von String <"
            + line + "> nicht möglich. Anzahl an sets stimmt nicht mit Trennungsanzahl überein.");
  }
}

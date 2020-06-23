package fhac.bh1978s.zufallsgenerator.mapper;

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
        if (line.startsWith("Ziel")) {
          zufallData.setZiel(Ziel.fromString(line.split(":")[1]));
        } else if (line.startsWith("Generator")) {
          zufallData.setGeneratorType(GeneratorType.fromString(line.split(":")[1]));
        } else if (line.startsWith("Parameter")) {
          String values = line.split(":")[1];
          HashMap<String, Long> parameterHashMap = new HashMap();

          for (String keyValue : values.split(",")) {
            String[] pair = keyValue.split("=");
            if (pair[0].equalsIgnoreCase("n")) {
              zufallData.setN(Integer.parseInt(pair[1]));
            } else {
              parameterHashMap.put(pair[0], Long.parseLong(pair[1]));
            }
          }

          zufallData.setParameterList(parameterHashMap);
        } else if (line.startsWith("Bewertungsart")) {
          zufallData.setBewertungType(BewertungType.fromString(line.split(":")[1]));
        } else if (line.startsWith("Zufallszahlen")) {
          String values = line.split(":")[1];
          ArrayList<Double> zufallszahlen = new ArrayList<>();
          for (String val : values.split(",")) {
            zufallszahlen.add(Double.valueOf(val.trim()));
          }
          zufallData.setZufallszahlen(zufallszahlen);
        }
      }
    } catch (NumberFormatException nfe) {
      throw new ZufallMappingException(
          "Zufallszahlen konnten nicht extrahiert werden, bitte überprüfen.");
    }

    return zufallData;
  }
}

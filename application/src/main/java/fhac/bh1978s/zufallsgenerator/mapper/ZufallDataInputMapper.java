package fhac.bh1978s.zufallsgenerator.mapper;

import fhac.bh1978s.exception.ZufallMappingException;
import fhac.bh1978s.view.TextFile;
import fhac.bh1978s.zufallsgenerator.mapper.interfaces.I_InputMapper;
import fhac.bh1978s.zufallsgenerator.model.ZufallData;

public class ZufallDataInputMapper implements I_InputMapper<TextFile, ZufallData> {

  @Override
  public ZufallData mapToInternFormat(TextFile externContent) throws ZufallMappingException {
    return null;
  }
}

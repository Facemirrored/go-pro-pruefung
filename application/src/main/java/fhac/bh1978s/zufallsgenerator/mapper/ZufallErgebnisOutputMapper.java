package fhac.bh1978s.zufallsgenerator.mapper;

import fhac.bh1978s.view.TextFile;
import fhac.bh1978s.zufallsgenerator.mapper.interfaces.I_OutputMapper;
import fhac.bh1978s.zufallsgenerator.model.ZufallErgebnisData;

public class ZufallErgebnisOutputMapper implements I_OutputMapper<ZufallErgebnisData, TextFile> {

  @Override
  public TextFile mapToExternFormat(ZufallErgebnisData internContent) {
    return null;
  }
}

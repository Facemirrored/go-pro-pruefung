package fhac.bh1978s.nameDerSituation.presenter;

import fhac.bh1978s.nameDerSituation.model.ZufallsData;
import fhac.bh1978s.nameDerSituation.model.ZufallsergebnisData;

public class ZufallsgeneratorPresenter {

  private ZufallsData zufallsData;
  private ZufallsergebnisData zufallsergebnisData;

  public ZufallsgeneratorPresenter(final ZufallsData zufallsData) {
    this.zufallsData = zufallsData;
  }

  public ZufallsergebnisData generiere() {
    return this.zufallsergebnisData;
  }
}

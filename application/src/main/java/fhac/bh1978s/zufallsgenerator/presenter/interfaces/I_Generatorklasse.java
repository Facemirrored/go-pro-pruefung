package fhac.bh1978s.zufallsgenerator.presenter.interfaces;

import fhac.bh1978s.exception.BerechnungException;
import java.util.List;

public interface I_Generatorklasse<T> {

  List<T> generiereZufall() throws BerechnungException;
}

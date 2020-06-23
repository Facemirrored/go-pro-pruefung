package fhac.bh1978s.zufallsgenerator.presenter.interfaces;

import fhac.bh1978s.zufallsgenerator.model.BewertungData;
import java.util.List;

public interface I_Bewertung<T> {

  BewertungData berechneBewertung(List<T> zufallszahlen);
}

package fhac.bh1978s.zufallsgenerator.presenter.interfaces;

import java.util.List;

/**
 * Bewertung-Interface für die Erzeugung einer Gütefunktion auf Basis von Zufallszahlen vom Typ T
 *
 * @param <T> Datentyp der Zufallszahlen
 */
public interface I_Bewertung<T> {

  void berechneBewertung(List<T> zufallszahlen);
}

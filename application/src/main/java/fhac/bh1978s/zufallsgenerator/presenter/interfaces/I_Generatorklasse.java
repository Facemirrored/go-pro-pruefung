package fhac.bh1978s.zufallsgenerator.presenter.interfaces;

import fhac.bh1978s.zufallsgenerator.generatorexception.CalculationException;
import java.util.List;

/**
 * Generator-Interface für die Berechnung von Zufallszahlen des Typ T
 *
 * @param <T> Typangabe für den Datentyp der erzeugten Zufallszahlen
 */
public interface I_Generatorklasse<T> {

  /**
   * Erzeugt die Zufallszahlen auf Basis des Generators
   *
   * @return Liste von Typ T der Zufallszahlen
   * @throws CalculationException Wird geworfen, wenn die Berechnung fehlschlägt
   */
  List<T> generiereZufall() throws CalculationException;
}

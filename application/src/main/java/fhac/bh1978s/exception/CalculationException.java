package fhac.bh1978s.exception;

/**
 * Exception-Klasse für Fehler innerhalb des Zufallsgenerators. Exception dieser Art werden
 * geworfen, wenn beim erstellen von Generatoren bzw. Gütefunktionen oder beim Berechnen selbst
 * Fehler entstehen.
 */
public class CalculationException extends Exception {

  public CalculationException(final String msg) {
    super(msg);
  }
}

package fhac.bh1978s.exception;

/**
 * Wird verwendet, falls beim Schreiben einer Datei unvorhergesehene Probleme auftreten.
 * In solch einem Fall sollte eine Default-Datei geschrieben werden.
 */
public class OutputFileSaveException extends Exception {

  public OutputFileSaveException() {
    super();
  }

  public OutputFileSaveException(final String message) {
    super(message);
  }
}

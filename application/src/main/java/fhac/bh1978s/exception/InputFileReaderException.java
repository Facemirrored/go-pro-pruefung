package fhac.bh1978s.exception;

/**
 * Wird verwendet, falls beim Lesen einer Datei unvorhergesehene Probleme auftreten.
 * In solch einem Fall sollte die Datei Übersprungen werden und ein Inhalt mit passender
 * Info an eine Standard-Ausgabedatei geschrieben werden.
 */
public class InputFileReaderException extends Exception {

  public InputFileReaderException() {
    super();
  }

  public InputFileReaderException(final String message) {
    super(message);
  }
}

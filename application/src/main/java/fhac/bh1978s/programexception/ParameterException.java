package fhac.bh1978s.programexception;

/**
 * Exception-Klasse für Fehler innerhalb des Mappings von externen-Daten in interne. Exception
 * dieser Art werden geworfen, wenn ein Parameter nicht erfolgreich extrahiert werden konnte.
 */
public class ParameterException extends ZufallMappingException {

  public ParameterException(final String msg) {
    super(msg);
  }
}

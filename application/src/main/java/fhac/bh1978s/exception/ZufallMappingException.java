package fhac.bh1978s.exception;

/**
 * Exception-Klasse für Fehler innerhalb des Mappings von externen-Daten in interne. Exception
 * dieser Art werden geworfen, wenn generelle Fehler beim Mapping entstehen.
 */
public class ZufallMappingException extends Exception {

  public ZufallMappingException(final String msg) {
    super(msg);
  }

}

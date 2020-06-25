package fhac.bh1978s.zufallsgenerator.mapper.interfaces;

import fhac.bh1978s.programexception.ZufallMappingException;

/**
 * Generische Mapper-Klasse, welche Methoden für das Mappen vom externen Dateiformat ins interne
 * Datenformat implementiert.
 *
 * @param <E> Externe Datenobjekt
 * @param <I> Interne Datenobjekt
 */
public interface I_InputMapper<E, I> {

  I mapToInternFormat(E externContent) throws ZufallMappingException;
}

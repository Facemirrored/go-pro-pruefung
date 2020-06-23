package fhac.bh1978s.zufallsgenerator.mapper.interfaces;

import fhac.bh1978s.exception.ZufallMappingException;

/**
 * Generische Mapper-Klasse, welche Methoden für das Mappen vom externen Dateiformat ins interne
 * Datenformat implementiert. E definiert das Objekt des externen Formats, I des internen.
 */
public interface I_InputMapper<E, I> {

  I mapToInternFormat(E externContent) throws ZufallMappingException;
}

package fhac.bh1978s.nameDerSituation.mapper;

/**
 * Generische Mapper-Klasse, welche Methoden für das Mappen vom externen Dateiformat ins interne Datenformat
 * sowie umgekehrt implementiert.
 */
public interface I_Mapper<E, I> {
  I mapToInternFormat(E externContent);
  E mapToExternFormat(I internContent);
}

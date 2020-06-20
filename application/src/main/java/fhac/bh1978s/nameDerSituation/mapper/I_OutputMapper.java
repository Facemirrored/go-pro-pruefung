package fhac.bh1978s.nameDerSituation.mapper;

/**
 * Generische Mapper-Klasse, welche Methoden für das Mappen vom internen Dateiformat ins externe
 * Datenformat implementiert. E definiert das Objekt des externen Formats, I_Valid ein valides
 * Objekt des berechneten Ergebnisses und I_Error das von der Eingabe konvertierte Objekt mit
 * Semantikfehlern.
 */
public interface I_OutputMapper<I_Valid, I_Error, E> {

  E mapToExternFormat(I_Valid internContent);

  E mapErrorToExternFormat(I_Error internContent);
}

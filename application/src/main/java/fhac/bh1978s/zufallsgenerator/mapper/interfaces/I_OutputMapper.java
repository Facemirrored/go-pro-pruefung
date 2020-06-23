package fhac.bh1978s.zufallsgenerator.mapper.interfaces;

/**
 * Generische Mapper-Klasse, welche Methoden für das Mappen vom internen Dateiformat ins externe
 * Datenformat implementiert. E definiert das Objekt des externen Formats, I_Valid ein valides
 * Objekt des berechneten Ergebnisses und I_Error das von der Eingabe konvertierte Objekt mit
 * Semantikfehlern.
 */
public interface I_OutputMapper<I, E> {

  E mapToExternFormat(I internContent);
}

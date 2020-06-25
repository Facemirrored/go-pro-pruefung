package fhac.bh1978s.zufallsgenerator.mapper.interfaces;

/**
 * Generische Mapper-Klasse, welche Methoden für das Mappen vom internen Dateiformat ins externe
 * Datenformat implementiert.
 *
 * @param <I> Interne Datenobjekt
 * @param <E> Externe Datenobjekt
 */
public interface I_OutputMapper<I, E> {

  E mapToExternFormat(I internContent);
}

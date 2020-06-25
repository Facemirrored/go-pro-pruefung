package fhac.bh1978s.view.interfaces;

import java.util.List;

/**
 * Interface für die Methodenimplementierung bezüglich dem Lesen von Eingabedateien.
 *
 * @param <T> Datenklasse, welche für die Datenhaltung von zu lesenden Objekten zuständig ist.
 */
public interface I_FileReader<T> {

  List<T> readAllFiles();

  T readSingleFile(final String file);
}

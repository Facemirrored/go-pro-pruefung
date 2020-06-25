package fhac.bh1978s.view.interfaces;

import java.util.List;

/**
 * Interface für die Methodenimplementierung bezüglich dem Schreiben von Ausgabedateien.
 *
 * @param <T> Datenklasse, welche für die Datenhaltung von zu schreibenden Objekten zuständig ist.
 */
public interface I_FileWriter<T> {

  void saveFiles(final List<T> fileContentList);

  void emptyFolder();
}

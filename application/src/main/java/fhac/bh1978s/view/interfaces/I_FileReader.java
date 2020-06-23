package fhac.bh1978s.view.interfaces;

import java.util.List;

public interface I_FileReader<T> {
  List<T> readAllFiles();
  T readSingleFile(final String file);
}

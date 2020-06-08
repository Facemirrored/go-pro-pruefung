package fhac.bh1978s.ioStream;

import java.util.List;

public interface I_FileReader<T> {
  List<T> readAllFiles();
  T readSingleFile(final String file);
}

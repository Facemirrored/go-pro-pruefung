package fhac.bh1978s.ioStream;

import java.util.List;

public interface I_FileWriter<T> {

  void saveFiles(final List<T> fileContentList);
}

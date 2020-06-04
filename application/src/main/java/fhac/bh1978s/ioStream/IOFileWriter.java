package fhac.bh1978s.ioStream;

import java.util.List;

public interface IOFileWriter<T> {

  void saveFiles(final List<T> fileContentList);
}

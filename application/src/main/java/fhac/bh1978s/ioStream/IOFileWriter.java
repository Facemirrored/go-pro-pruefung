package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.OutputFileSaveException;
import java.util.List;

public interface IOFileWriter<T> {

  void saveFiles(final List<T> fileContentList);
}

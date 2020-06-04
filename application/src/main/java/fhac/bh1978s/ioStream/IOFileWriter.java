package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.OutputFileSaveException;
import java.util.List;

public interface IOFileWriter<T> {
  void saveAllFiles(final List<T> fileContentList);
  void saveSingleFile(final T fileContent) throws OutputFileSaveException;
}

package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.InputFileReaderException;
import java.util.List;

public interface IOFileReader<T> {
  List<T> readAllFiles();
  T readSingleFile() throws InputFileReaderException;
}

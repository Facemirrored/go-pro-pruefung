package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.InputFileReaderException;
import java.io.File;
import java.util.List;

public interface IOFileReader<T> {
  List<T> readAllFiles();
  T readSingleFile(final String file) throws InputFileReaderException;
}
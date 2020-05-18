package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.InputFileReaderException;
import fhac.bh1978s.exception.OutputFileSaveException;
import java.util.List;

public interface ioFileReader {

  List<String> readAllFiles();
  String readSingleFile() throws InputFileReaderException;
  void saveAllFiles(final List<String> fileContentList);
  void saveSingleFile(final String fileContent) throws OutputFileSaveException;
}

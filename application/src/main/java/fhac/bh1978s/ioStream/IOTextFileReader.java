package fhac.bh1978s.ioStream;

import fhac.bh1978s.exception.InputFileReaderException;
import fhac.bh1978s.exception.OutputFileSaveException;
import java.util.List;

public class IOTextFileReader implements ioFileReader {

  private IOTextFileReader() {}
  private static IOTextFileReader ioTextFileReader = new IOTextFileReader();

  public static IOTextFileReader getInstance() {
    return ioTextFileReader;
  }

  private String inputFileLocation = "";
  private String outputFileLocation = "";

  public void setInputFileLocation(String inputFileLocation) {
    this.inputFileLocation = inputFileLocation;
  }

  public void setOutputFileLocation(String outputFileLocation) {
    this.outputFileLocation = outputFileLocation;
  }

  @Override
  public List<String> readAllFiles() {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public String readSingleFile() throws InputFileReaderException {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void saveAllFiles(List<String> fileContentList) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void saveSingleFile(String fileContent) throws OutputFileSaveException {
    throw new UnsupportedOperationException("Not implemented.");
  }

  public boolean validatePath(final String path) {
    throw new UnsupportedOperationException("Not implemented.");
  }
}

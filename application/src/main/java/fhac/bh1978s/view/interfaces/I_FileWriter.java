package fhac.bh1978s.view.interfaces;

import java.util.List;

public interface I_FileWriter<T> {

  void saveFiles(final List<T> fileContentList);

  void emptyFolder();
}

package fhac.bh1978s.zufallsgenerator.model;

import java.util.List;

public class ZufallErgebnisData<T> {

  private List<T> zufallszahlen;

  public ZufallErgebnisData() {
  }

  public ZufallErgebnisData(List<T> zufallszahlen) {
    this.zufallszahlen = zufallszahlen;
  }

  public List<T> getZufallszahlen() {
    return zufallszahlen;
  }

  public void setZufallszahlen(List<T> zufallszahlen) {
    this.zufallszahlen = zufallszahlen;
  }
}

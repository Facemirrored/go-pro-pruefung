package fhac.bh1978s.ioStream;

public class TextFile {

  private String name;
  private boolean error;
  private String content;

  public TextFile(String name) {
    this.name = name;
  }

  public TextFile(String name, String content) {
    this.error = false;
    this.content = content;
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void concanateContents(final TextFile textfile) {
    throw new UnsupportedOperationException("Not implemented. (Kombiniere Inhalt einer datei an diese getrennt für eingabeinhalt + ausgabeinhalt)");
  }

  public void print() {
    System.out.println("Text-Datei:\t " + name);
    System.out.println("-----INHALT-----");
    System.out.println(content);
    System.out.println("-----INHALT-----\n");
  }
}

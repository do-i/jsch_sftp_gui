package com.djd.fun.jsch;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class DocumentModels {
  private final Document hostname;
  private final Document username;
  private final Document password;
  private final Document filename;

  public DocumentModels() {
    this.hostname = new PlainDocument();
    this.username = new PlainDocument();
    this.password = new PlainDocument();
    this.filename = new PlainDocument();
  }

  public Document getHostname() {
    return hostname;
  }

  public Document getUsername() {
    return username;
  }

  public Document getPassword() {
    return password;
  }

  public Document getFilename() {
    return filename;
  }

  public String getHostnameText() {
    return getText(hostname);
  }

  public String getUsernameText() {
    return getText(username);
  }

  public String getPasswordText() {
    return getText(password);
  }

  public String getFilenameText() {
    return getText(filename);
  }

  private static String getText(Document doc) {
    try {
      if (doc.getLength() == 0) {
        throw new IllegalArgumentException("Field is empty " + doc);
      }
      return doc.getText(0, doc.getLength());
    } catch (BadLocationException e) {
      throw new RuntimeException(e);
    }
  }
}

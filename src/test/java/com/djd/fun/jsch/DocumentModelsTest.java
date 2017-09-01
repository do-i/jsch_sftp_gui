package com.djd.fun.jsch;

import javax.swing.text.BadLocationException;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DocumentModelsTest {

  private static final String TEST_VALUE = "test_value";
  private DocumentModels documentModels;

  @Before
  public void setUp() {
    documentModels = new DocumentModels();
  }

  @Test
  public void getHostname() {
    assertThat(documentModels.getHostname()).isNotNull();
  }

  @Test
  public void getUsername() {
    assertThat(documentModels.getUsername()).isNotNull();
  }

  @Test
  public void getPassword() {
    assertThat(documentModels.getPassword()).isNotNull();
  }

  @Test
  public void getFilename() {
    assertThat(documentModels.getFilename()).isNotNull();
  }

  @Test
  public void getHostnameText() throws BadLocationException {
    documentModels.getHostname().insertString(0, TEST_VALUE, null);
    assertThat(documentModels.getHostnameText()).isEqualTo(TEST_VALUE);
  }

  @Test
  public void getUsernameText() throws BadLocationException {
    documentModels.getUsername().insertString(0, TEST_VALUE, null);
    assertThat(documentModels.getUsernameText()).isEqualTo(TEST_VALUE);
  }

  @Test
  public void getPasswordText() throws BadLocationException {
    documentModels.getPassword().insertString(0, TEST_VALUE, null);
    assertThat(documentModels.getPasswordText()).isEqualTo(TEST_VALUE);
  }

  @Test
  public void getFilenameText() throws BadLocationException {
    documentModels.getFilename().insertString(0, TEST_VALUE, null);
    assertThat(documentModels.getFilenameText()).isEqualTo(TEST_VALUE);
  }

}
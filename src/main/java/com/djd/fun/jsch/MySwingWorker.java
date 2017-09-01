package com.djd.fun.jsch;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.google.common.annotations.VisibleForTesting;
import com.jcraft.jsch.SftpProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySwingWorker extends SwingWorker<File, Void> {

  private static final Logger log = LoggerFactory.getLogger(MySwingWorker.class);
  private final DocumentModels documentModels;
  private final SftpProgressMonitor progressMonitor;
  private final JTextField textField;
  private final JschHelper jschHelper;

  @VisibleForTesting MySwingWorker(JschHelper jschHelper, DocumentModels documentModels,
      SftpProgressMonitor progressMonitor, JTextField textField) {
    this.jschHelper = jschHelper;
    this.documentModels = documentModels;
    this.progressMonitor = progressMonitor;
    this.textField = textField;
  }

  public MySwingWorker(DocumentModels documentModels, SftpProgressMonitor progressMonitor,
      JTextField textField) {
    this(new JschHelper(), documentModels, progressMonitor, textField);
  }

  /**
   * Slow task that is executed on a worker thread
   *
   * @return downloaded file
   * @throws Exception
   */
  @Override
  protected File doInBackground() throws Exception {
    log.debug("Start slow job");
    ConnectionInfo connectionInfo = ConnectionInfo.builder()
        .hostname(documentModels.getHostnameText())
        .username(documentModels.getUsernameText())
        .password(documentModels.getPasswordText())
        .filename(documentModels.getFilenameText())
        .build();
    return jschHelper.sftp(connectionInfo, progressMonitor);
  }

  /**
   * fast method that is called upon slow job completion.
   */
  @Override
  protected void done() {
    try {
      File download = get();
      textField.setText(download.getAbsolutePath());
      textField.setEnabled(true);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}

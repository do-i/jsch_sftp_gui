package com.djd.fun.jsch;

import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class SwingWorkerFactory {
  private final DocumentModels documentModels;
  private final ProgressMonitor progressMonitor;
  private final JTextField outputTextField;

  public SwingWorkerFactory(DocumentModels documentModels, ProgressMonitor progressMonitor,
      JTextField outputTextField) {
    this.documentModels = documentModels;
    this.progressMonitor = progressMonitor;
    this.outputTextField = outputTextField;
  }

  public SwingWorker create() {
    outputTextField.setText("");
    return new MySwingWorker(documentModels, progressMonitor, outputTextField);
  }
}

package com.djd.fun.jsch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadActionListener implements ActionListener {

  private static final Logger log = LoggerFactory.getLogger(DownloadActionListener.class);
  private final SwingWorkerFactory factory;

  public DownloadActionListener(SwingWorkerFactory factory) {
    this.factory = factory;
  }

  public DownloadActionListener(DocumentModels documentModels, ProgressMonitor progressMonitor,
      JTextField outputTextField) {
    this(new SwingWorkerFactory(documentModels, progressMonitor, outputTextField));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    log.info("Download start");
    factory.create().execute();
  }
}

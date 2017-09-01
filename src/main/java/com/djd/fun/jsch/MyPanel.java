package com.djd.fun.jsch;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.text.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPanel extends JPanel {

  private static final Logger log = LoggerFactory.getLogger(MyPanel.class);
  private static final boolean DEBUG_MODE = true;

  enum Label {
    HOSTNAME("Hostname", "chiba"),
    USERNAME("Username", "dee"),
    PASSWROD("Password", "password"),
    FILENAME("Filename", "/home/dee/2017-01-11-raspbian-jessie-lite.zip");
    private String displayValue;
    private String defaultValue;

    Label(String displayValue, String defaultValue) {
      this.displayValue = displayValue;
      this.defaultValue = defaultValue;
    }

    public String getDisplayValue() {
      return displayValue;
    }

    public String getDefaultValue() {
      return DEBUG_MODE ? defaultValue : "";
    }
  }

  private final DocumentModels documentModels;
  private final ActionListener downloadActionListener;

  public MyPanel() {
    this.documentModels = new DocumentModels();
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridy = 0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(2, 2, 2, 2);
    addWidget(this, constraints, documentModels.getHostname(), Label.HOSTNAME);
    addWidget(this, constraints, documentModels.getUsername(), Label.USERNAME);
    addWidget(this, constraints, documentModels.getPassword(), Label.PASSWROD);
    addWidget(this, constraints, documentModels.getFilename(), Label.FILENAME);
    JTextField outputTextField = new JTextField("output", 10);
    outputTextField.setEnabled(false);
    ProgressMonitor progressMonitor = new ProgressMonitor();

    this.downloadActionListener = new DownloadActionListener(documentModels, progressMonitor, outputTextField);
    addDownloadButton(this, constraints, downloadActionListener);
    constraints.gridy++;
    constraints.gridwidth = 2;
    JProgressBar progressBar = new JProgressBar(progressMonitor.getBoundedRangeModel());
    progressBar.setStringPainted(true);
    add(progressBar, constraints);
    constraints.gridy++;
    add(outputTextField, constraints);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800, 500);
  }

  private static void addWidget(Container container, GridBagConstraints constraints,
      Document document, Label label) {
    log.info("constraints.gridy: {}", constraints.gridy);
    constraints.gridx = 0;

    JLabel jLabel = new JLabel(label.getDisplayValue() + ": ", JLabel.TRAILING);
    container.add(jLabel, constraints);
    JTextField textField = label == Label.PASSWROD
        ? new JPasswordField(document, label.getDefaultValue(), 30)
        : new JTextField(document, label.getDefaultValue(), 30);
    jLabel.setLabelFor(textField);
    constraints.gridx = 1;
    container.add(textField, constraints);
    constraints.gridy++;
  }

  private static void addDownloadButton(Container container, GridBagConstraints constraints,
      ActionListener actionListener) {
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    JButton downloadButton = new JButton("Download");
    downloadButton.addActionListener(actionListener);
    container.add(downloadButton, constraints);
  }
}

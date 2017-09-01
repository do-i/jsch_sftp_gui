package com.djd.fun.jsch;

import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] params) {
    // Use SwingUtilities to run the swing on background thread.
    SwingUtilities.invokeLater(GuiPreper::createAndShowGUI);
  }
}

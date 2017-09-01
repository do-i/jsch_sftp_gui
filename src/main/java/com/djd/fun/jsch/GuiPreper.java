package com.djd.fun.jsch;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuiPreper {

  private static final Logger log = LoggerFactory.getLogger(GuiPreper.class);

  public static void createAndShowGUI() {
    Font f = new Font("sans-serif", Font.PLAIN, 12);
    UIManager.put("Label.font", f);
    UIManager.put("Button.font", f);

    JFrame jFrame = new JFrame("Demo jsch -> sftp -> get"); // Foundation of all graphical components on screen
    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    MyPanel myPanel = new MyPanel();
    jFrame.add(myPanel);
    jFrame.pack(); // This will use myPanel.getPreferredSize() to decide the size of JFrame
    jFrame.setVisible(true);
  }
}

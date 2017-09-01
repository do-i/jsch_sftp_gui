package com.djd.fun.jsch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DownloadActionListenerTest extends Mockito {

  private @Mock SwingWorkerFactory swingWorkerFactory;
  private DownloadActionListener actionListener;

  @Before
  public void setUp() {
    actionListener = new DownloadActionListener(swingWorkerFactory);
  }

  @Test
  public void actionPerformed() {
    // TODO need to figure out how to mock SwingWorker.run()
//    actionListener.actionPerformed();
  }

}
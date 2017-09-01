package com.djd.fun.jsch;

import java.io.File;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.djd.fun.jsch.JschHelper.CHANNEL_TYPE;
import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JschHelperTest extends Mockito {

  private static final ConnectionInfo CONNECTION_INFO = ConnectionInfo.builder()
      .hostname("host")
      .username("user1")
      .password("passwd")
      .filename("/tmp/file1")
      .build();
  private @Mock SftpProgressMonitor monitor;
  private @Mock JSch jsch;
  private @Mock Session session;
  private @Mock ChannelSftp channel;
  private JschHelper jschHelper;

  @Before
  public void setUp() {
    jschHelper = new JschHelper(jsch);
  }

  @Test
  public void sftp() throws SftpException, JSchException {
    when(jsch.getSession("user1", "host", 22)).thenReturn(session);
    when(session.openChannel(CHANNEL_TYPE)).thenReturn(channel);
    File result = jschHelper.sftp(CONNECTION_INFO, monitor);
    assertThat(result.getName()).isEqualTo(".");
    verify(session).setUserInfo(CONNECTION_INFO.getUserInfo());
    verify(session).connect();
    verify(channel).get("/tmp/file1", ".", monitor, ChannelSftp.OVERWRITE);
    verify(session).disconnect();
  }

}
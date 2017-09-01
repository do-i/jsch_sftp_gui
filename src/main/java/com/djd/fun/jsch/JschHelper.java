package com.djd.fun.jsch;

import java.io.File;

import com.google.common.annotations.VisibleForTesting;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class JschHelper {

  @VisibleForTesting static final String CHANNEL_TYPE = "sftp";
  private final JSch jsch;

  public JschHelper() {
    this(new JSch());
  }

  @VisibleForTesting JschHelper(JSch jsch) {
    this.jsch = jsch;
  }

  public File sftp(ConnectionInfo connectionInfo, SftpProgressMonitor monitor) throws JSchException, SftpException {
    Session session = jsch.getSession(connectionInfo.getUsername(), connectionInfo.getHostname(),
        connectionInfo.getPort());
    session.setUserInfo(connectionInfo.getUserInfo());
    session.connect();
    Channel channel = session.openChannel(CHANNEL_TYPE);
    channel.connect();
    ChannelSftp channelSftp = (ChannelSftp)channel;
    String remoteFilename = connectionInfo.getFilename();
    String localFilename = "."; // can be dir or file
    channelSftp.get(remoteFilename, localFilename, monitor, ChannelSftp.OVERWRITE);
    session.disconnect();
    return new File(localFilename);
  }
}

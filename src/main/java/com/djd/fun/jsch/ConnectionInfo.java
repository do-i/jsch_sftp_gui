package com.djd.fun.jsch;

import java.util.Objects;

import com.google.common.annotations.VisibleForTesting;
import com.jcraft.jsch.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class ConnectionInfo {
  private static final Logger log = LoggerFactory.getLogger(ConnectionInfo.class);
  private final int port;
  private final String hostname;
  private final String username;
  private final UserInfo userInfo;
  private final String filename;

  public ConnectionInfo(Builder builder) {
    this.port = builder.port;
    this.hostname = builder.hostname;
    this.username = builder.username;
    this.userInfo = builder.userInfo;
    this.filename = builder.filename;
  }

  public String getHostname() {
    return hostname;
  }

  public String getUsername() {
    return username;
  }

  public String getFilename() {
    return filename;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public int getPort() {
    return port;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectionInfo that = (ConnectionInfo)o;
    return port == that.port
        && hostname.equals(that.hostname)
        && username.equals(that.username)
        && userInfo.equals(that.userInfo)
        && filename.equals(that.filename);
  }

  @Override
  public int hashCode() {
    return Objects.hash(port, hostname, username, userInfo, filename);
  }

  public static Builder builder() {
    return new Builder();
  }

  @VisibleForTesting static Builder builder(ConnectionInfo prototype) {
    return new Builder(prototype);
  }

  public static class Builder {
    private String hostname;
    private String username;
    private UserInfo userInfo;
    private String filename;
    private int port = 22;

    public Builder() {}
    public Builder(ConnectionInfo prototype) {
      hostname = prototype.hostname;
      username = prototype.username;
      userInfo = prototype.userInfo;
      filename = prototype.filename;
      port = prototype.port;
    }

    public Builder port(int port) {
      this.port = port;
      return this;
    }

    public Builder hostname(String hostname) {
      this.hostname = hostname;
      return this;
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder password(String password) {
      this.userInfo = new UserInfoAdapter() {
        @Override public String getPassword() {
          log.info("getPassword: ********");
          return password;
        }
      };
      return this;
    }

    public Builder filename(String filename) {
      this.filename = filename;
      return this;
    }

    public ConnectionInfo build() {
      checkNotNull(hostname);
      checkNotNull(username);
      checkNotNull(userInfo);
      checkNotNull(filename);
      return new ConnectionInfo(this);
    }
  }
}

package com.djd.fun.jsch;

import java.util.Objects;

import com.jcraft.jsch.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UserInfoAdapter implements UserInfo {

  private static final Logger log = LoggerFactory.getLogger(UserInfoAdapter.class);

  @Override
  public String getPassphrase() {
    log.debug("getPassphrase");
    return "";
  }

  @Override
  abstract public String getPassword();

  @Override
  public boolean promptPassword(String message) {
    log.info("promptPassword: " + message);
    return true;
  }

  @Override
  public boolean promptPassphrase(String message) {
    log.info("promptPassphrase: " + message);
    return true;
  }

  @Override
  public boolean promptYesNo(String message) {
    log.info("promptYesNo: " + message);
    return true;
  }

  @Override
  public void showMessage(String message) {
    log.info("showMessage: " + message);
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
     UserInfoAdapter that = (UserInfoAdapter)o;
    return getPassword().equals(that.getPassword()) && getPassphrase().equals(that.getPassphrase());
  }

  @Override public int hashCode() {
    return Objects.hash(getPassword(), getPassphrase());
  }

}

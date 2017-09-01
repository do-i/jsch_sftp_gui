package com.djd.fun.jsch.validator;

import com.djd.fun.jsch.ConnectionInfo;

public class DatabaseBackedMemoryValidator implements ConnectionInfoValidator {

  @Override
  public StatusCode validate(ConnectionInfo connectionInfo) {
    // TODO heavy-weight validation relies on external database
    return StatusCode.GOOD;
  }
}

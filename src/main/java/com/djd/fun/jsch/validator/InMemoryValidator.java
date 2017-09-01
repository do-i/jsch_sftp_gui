package com.djd.fun.jsch.validator;

import com.djd.fun.jsch.ConnectionInfo;

public class InMemoryValidator implements ConnectionInfoValidator {

  @Override
  public StatusCode validate(ConnectionInfo connectionInfo) {
    // TODO light-weight validation based on in-memory data
    return StatusCode.GOOD;
  }
}

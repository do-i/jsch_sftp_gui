package com.djd.fun.jsch.validator;

import com.djd.fun.jsch.ConnectionInfo;

public class EasyValidator implements ConnectionInfoValidator {

  @Override
  public StatusCode validate(ConnectionInfo connectionInfo) {
    // always return GOOD a.k.a no validation
    return StatusCode.GOOD;
  }
}

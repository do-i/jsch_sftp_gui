package com.djd.fun.jsch.validator;

import com.djd.fun.jsch.ConnectionInfo;

public interface ConnectionInfoValidator {
  enum StatusCode {
    GOOD, BAD_HOSTNAME, BAD_USERNAME;
  }

  StatusCode validate(ConnectionInfo connectionInfo);
}

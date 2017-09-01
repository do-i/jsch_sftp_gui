package com.djd.fun.jsch;

import com.google.common.testing.EqualsTester;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ConnectionInfoTest {

  private static final ConnectionInfo CONNECTION_INFO = ConnectionInfo.builder()
      .hostname("host")
      .username("user1")
      .password("passwd")
      .filename("/tmp/file1")
      .build();

  @Test
  public void equalities() {
    new EqualsTester()
        .addEqualityGroup(CONNECTION_INFO, ConnectionInfo.builder(CONNECTION_INFO).build())
        .addEqualityGroup(ConnectionInfo.builder(CONNECTION_INFO).hostname("host2").build())
        .addEqualityGroup(ConnectionInfo.builder(CONNECTION_INFO).username("user2").build())
        .addEqualityGroup(ConnectionInfo.builder(CONNECTION_INFO).password("passwd2").build())
        .addEqualityGroup(ConnectionInfo.builder(CONNECTION_INFO).filename("file2").build())
        .testEquals();
  }

  @Test
  public void getHostname() {
    assertThat(CONNECTION_INFO.getHostname()).isEqualTo("host");
  }

  @Test
  public void getUsername() {
    assertThat(CONNECTION_INFO.getUsername()).isEqualTo("user1");
  }

  @Test
  public void getFilename() {
    assertThat(CONNECTION_INFO.getFilename()).isEqualTo("/tmp/file1");
  }

  @Test
  public void getUserInfo() {
    assertThat(CONNECTION_INFO.getUserInfo().getPassword()).isEqualTo("passwd");
  }

  @Test
  public void getPort() {
    assertThat(CONNECTION_INFO.getPort()).isEqualTo(22);
  }
}
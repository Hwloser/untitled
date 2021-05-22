package com.hwloser.io.echo.server.oio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.io.IOUtils;

public class BlockingEchoServer {
  public static void main(String[] args) {
    int port = 1234;

    try (ServerSocket ss = new ServerSocket(port)) {
      Socket c = ss.accept();
      System.out.printf("address: %s%n", c.getInetAddress());
      IOUtils.copy(c.getInputStream(), c.getOutputStream());
      IOUtils.close(c);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

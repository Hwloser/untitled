package com.hwloser.io.oio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;

public class BlockingEchoClient {
  public static void main(String[] args) {
    int port = 1234;
    String hostName = "localhost";

    try (Socket s = new Socket(hostName, port)) {
      if (s.isConnected()) {
        System.out.println("enter data please!!!");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        System.out.printf("input string: %s", line);
        IOUtils.write(line, s.getOutputStream(), Charset.defaultCharset());
        in.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

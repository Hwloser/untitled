package com.hwloser.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AsynchronousEchoClient {
  public static void main(String[] args) {
    int port = 1234;
    String hostName = "localhost";

    AsynchronousSocketChannel sc = null;
    try {
      sc = AsynchronousSocketChannel.open();
      sc.connect(new InetSocketAddress(hostName, port));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    ByteBuffer readBuffer = ByteBuffer.allocate(128);
    ByteBuffer writeBuffer = ByteBuffer.allocate(128);

    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        writeBuffer.put(line.getBytes(StandardCharsets.UTF_8));
        writeBuffer.flip();
        writeBuffer.rewind();

        // 写消息 到 channel
        sc.write(writeBuffer);
        // 读消息 从 channel
        sc.read(readBuffer);

        writeBuffer.clear();
        readBuffer.clear();

        System.out.printf("user input line:%s", line);
      }
    }
  }
}

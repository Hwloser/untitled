package com.hwloser.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NonBlockingClient {
  public static void main(String[] args) {
    int port = 1234;
    String hostName = "localhost";

    SocketChannel sc;
    try {
      sc = SocketChannel.open();
      sc.connect(new InetSocketAddress(hostName, port));
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    ByteBuffer writeBuffer = ByteBuffer.allocate(128);
    ByteBuffer readBuffer = ByteBuffer.allocate(128);

    try (Scanner input = new Scanner(System.in)) {
      String inputLine;

      while ((inputLine = input.nextLine()) != null) {
        writeBuffer.put(inputLine.getBytes(StandardCharsets.UTF_8));
        writeBuffer.flip();
        writeBuffer.rewind();

        try {
          // 写消息到 channel 中
          sc.write(writeBuffer);

          // 读取消息到 channel 中
          sc.read(readBuffer);

          // 清理缓冲区
          writeBuffer.clear();
          readBuffer.clear();

          System.out.println("echo: " + inputLine);

        } catch (IOException e) {
          e.printStackTrace();
        }


      }
    }

  }
}

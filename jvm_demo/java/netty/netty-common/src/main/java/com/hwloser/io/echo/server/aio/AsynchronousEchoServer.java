package com.hwloser.io.echo.server.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousEchoServer {
  public static void main(String[] args) {
    int port = 1234;

    AsynchronousServerSocketChannel ssc;
    try {
      ssc = AsynchronousServerSocketChannel.open();
      InetSocketAddress address = new InetSocketAddress(port);

      // 为 channel bind address
      ssc.bind(address);

      // 设置 阐述
      ssc.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
      ssc.setOption(StandardSocketOptions.SO_REUSEADDR, true);

      System.out.printf("AsynchronousEchoServer 已启动，端口:%s", port);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    while (true) {
      Future<AsynchronousSocketChannel> future = ssc.accept();
      AsynchronousSocketChannel channel;
      try {
        channel = future.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
        break;
      }

      System.out.printf("AsynchronousEchoServer 接收客户端的链接：%s", channel);

      // 分配缓冲区
      ByteBuffer buffer = ByteBuffer.allocate(128);

      try {
        while (channel.read(buffer).get() != null) {
          buffer.flip();
          // get for wait complicate
          channel.write(buffer).get();

          System.out.printf("AsynchronousEchoServer -> channel:%s  -- buffer:%s", channel, buffer);

          if (buffer.hasRemaining()) {
            buffer.compact();
          } else {
            buffer.clear();
          }
        }

        channel.close();
      } catch (InterruptedException | ExecutionException | IOException e) {
        e.printStackTrace();
      }


    }

  }
}

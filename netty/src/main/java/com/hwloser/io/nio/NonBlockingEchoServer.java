package com.hwloser.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NonBlockingEchoServer {
  public static void main(String[] args)  {
    int port = 1234;

    ServerSocketChannel serverChannel;
    Selector selector;

    try {
      InetSocketAddress address = new InetSocketAddress(port);
      // open with bind address
      serverChannel = ServerSocketChannel.open().bind(address);
      // adjust this channel blocking mode
      serverChannel.configureBlocking(false);

      // create selector
      // then register select to channel
      selector = Selector.open();

      serverChannel.register(selector, SelectionKey.OP_ACCEPT);

      System.out.println("Nonblocking echo server started");

    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    //noinspection InfiniteLoopStatement
    while (true) {
      try {
        selector.select();
      } catch (IOException e) {
        e.printStackTrace();
      }

      Set<SelectionKey> readyKeys = selector.selectedKeys();
      for (SelectionKey readyKey : readyKeys) {
        try {
          // 可连接
          if (readyKey.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) readyKey.channel();
            SocketChannel client = channel.accept();

            System.out.printf("NonBlocking echo server 接收客户端的链接 :%s", client);

            client.configureBlocking(false);

            // 将客户端注册到 selector
            SelectionKey clientSelectionKey = client
                .register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);

            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(128);
            clientSelectionKey.attach(buffer);
          }

          // 可读
          if (readyKey.isReadable()) {
            SocketChannel client = (SocketChannel) readyKey.channel();

            ByteBuffer output = (ByteBuffer) readyKey.attachment();
            client.read(output);

            System.out.printf("client: %s  -> NonBlocking echo server -> output :%s",
                client.getRemoteAddress(), output);

            readyKey.interestOps(SelectionKey.OP_WRITE);
          }

          // 可写
          if (readyKey.isWritable()) {
            SocketChannel client = (SocketChannel) readyKey.channel();

            ByteBuffer output = (ByteBuffer) readyKey.attachment();
            output.flip();
            client.write(output);

            System.out.printf("client: %s  -> NonBlocking echo server -> output :%s",
                client.getRemoteAddress(), output);

            output.compact();

            readyKey.interestOps(SelectionKey.OP_READ);
          }
        } catch (IOException e) {
          try {
            serverChannel.close();
          } catch (IOException ignored) {
          }
          e.printStackTrace();
        }

      }

    }

  }
}

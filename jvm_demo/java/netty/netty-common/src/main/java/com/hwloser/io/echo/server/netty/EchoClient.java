package com.hwloser.io.echo.server.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class EchoClient {
  public static void main(String[] args) {
    int port = 1234;
    String hostName = "localhost";

    NioEventLoopGroup loopGroup = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(loopGroup)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.TCP_NODELAY, true)
          .handler(new EchoClientHandler());

      // 链接到 EchoServer
      ChannelFuture f = b.connect(new InetSocketAddress(hostName, port)).sync();

      Channel channel = f.channel();
      ByteBuffer writeBuffer = ByteBuffer.allocate(128);

      System.out.println("请输入：");

      try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
        String userInput;
        while ((userInput = bf.readLine()) != null) {
          writeBuffer.put(userInput.getBytes(StandardCharsets.UTF_8));
          writeBuffer.flip();
          writeBuffer.rewind();

          // 转换 ByteBuffer 为 netty ByteBuf
          ByteBuf buf = Unpooled.copiedBuffer(writeBuffer);

          channel.writeAndFlush(buf);

          writeBuffer.clear();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }


    } catch (InterruptedException e) {
      e.printStackTrace();
      loopGroup.shutdownGracefully();
    }

  }
}

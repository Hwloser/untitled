package com.hwloser.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class RpcServer {
  public static void main(String[] args) {

  }

  private static void startServer(String hostName, int port) throws InterruptedException {
    NioEventLoopGroup boosGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    ServerBootstrap b = new ServerBootstrap();
    b.group(boosGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            // add handler here
          }
        });

    b.bind(hostName, port).sync();


  }
}

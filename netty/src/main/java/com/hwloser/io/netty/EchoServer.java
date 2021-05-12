package com.hwloser.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
  public static void main(String[] args) {
    int port = 1234;

    // 多线程时间循环器
    NioEventLoopGroup boosGroup = new NioEventLoopGroup();  /* boos */
    NioEventLoopGroup workerGroup = new NioEventLoopGroup(); /* worker */

    try {
      // 启动 NIO 服务的引导类程序类
      ServerBootstrap b = new ServerBootstrap();

      b.group(boosGroup, workerGroup) /* 设置 EventLoopGroup */
          .channel(NioServerSocketChannel.class) /* 声明新的 Channel 类型 */
          .childHandler(new EchoServerHandler()) /* 指明 ChannelHandler */
          .option(ChannelOption.SO_BACKLOG, 128) /* 指明 ServerChannel 的 option */
          .childOption(ChannelOption.SO_KEEPALIVE, true); /* 设置 ServerChannel 的子channel的选项 */

      // 绑定端口，开始接收进来的连接
      ChannelFuture f = b.bind(port).sync();

      System.out.printf("EchoServer已启动，端口：%s", port);

      // 等待服务器 socket 断开连接
      // 优雅的关闭服务器
      f.channel().closeFuture().sync();

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      boosGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }

  }
}

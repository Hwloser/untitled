package com.hwloser.io.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.printf("channel remote address:%s, server msg:%s",
        ctx.channel().remoteAddress(), msg);

    // 写消息到 channel
    String sendMsg = "received msg:" + msg;

    System.out.println("sendMsg -- " + sendMsg);

    ctx.write(sendMsg);   /* write msg */
    ctx.flush(); /* flush msg */

    // 上面的方法等同于  ctx.writeAndFlush(sendMsg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // 当出现 异常就关闭连接
    cause.printStackTrace();

    ctx.close();
  }
}

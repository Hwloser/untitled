package com.hwloser.endian;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TestDemo {
  public static void main(String[] args) {
    byteToBigEndian();
  }

  private static void byteToBigEndian() {

    ByteBuffer buffer = ByteBuffer.allocate(5);

    buffer.put(Integer.valueOf(4).byteValue());

    // 调整字节序
    buffer.order(ByteOrder.BIG_ENDIAN);

  }
}

package com.huanwei;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class TestBuffer {

  public static void main(String[] args) {
    // used 1024 byte buffer
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    // user 1024 char buffer
    CharBuffer charBuffer = CharBuffer.allocate(1024);



  }
}

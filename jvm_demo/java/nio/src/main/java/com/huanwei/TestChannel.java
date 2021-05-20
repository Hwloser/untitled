package com.huanwei;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class TestChannel {

  public static void main(String[] args) throws IOException {
    RandomAccessFile file = new RandomAccessFile(
        "F:\\untitled\\java\\nio\\src\\main\\resources\\example-files\\input.txt", "rw");
    FileChannel fileChannel = file.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate(48);

    while (fileChannel.isOpen() && (fileChannel.read(buffer) != -1)) {
      System.out.println("read " + buffer);
      buffer.flip();

//      buffer.compact();

      while (buffer.hasRemaining()) {
        System.out.print((char) buffer.get());
      }

      buffer.clear();
    }
    file.close();
  }

  void testRead(FileChannel inChannel, ByteBuffer buffer) throws IOException {
    int bytesRead = inChannel.read(buffer);
  }

  void testWrite(FileChannel outChannel, ByteBuffer byteBuffer, byte[] content) throws IOException {
    outChannel.write(byteBuffer.put(content));
  }

  void testFilp(FileChannel channel, ByteBuffer byteBuffer) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    // write from buffer into channel
    channel.write(buffer);

    //
    byteBuffer.flip();
  }
}

package com.huanwei;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * buffer 描述
 * <p>
 * - Capacity : 容量，即可以容纳的最大数据量；在缓冲区创建的时候设定并且不能被改变.
 * </p>
 * <p>
 * - Limit    : 表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操, 且极限时可以修改的
 * </p>
 * <p>
 * - Position : 位置，下一个要被读或写的元素的索引，每次读写缓冲区数据都会改变position值，为下一次的读写做准备
 * </p>
 * <p>
 * - Mark     : 标记，调用mark来设置 mark = position，再调用 reset() 可以让 position 恢复到标记的位置
 * </p>
 */
public class TestBuffer {


  public static void main(String[] args) {
    // used 1024 byte buffer
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    // user 1024 char buffer
    CharBuffer charBuffer = CharBuffer.allocate(1024);


    // demo
    bytebufferTestDemo_1();

  }

  private static void bytebufferTestDemo_1() {
    int capacity = 1024;
    ByteBuffer buffer = ByteBuffer.allocateDirect(capacity);

    buffer.putChar('A'); /* 2 byte */
    buffer.putLong(333L); /* 8 byte */
    buffer.putChar('B'); /* 2 byte */

    // checkpointPosition is 12
    int checkpointPosition = buffer.position(); /* store current position */

    // limit = position; position = 0
    buffer.flip(); /* 切换为读模式, position初始化为0, limit初始化为刻度 数据的最大下标 */

    System.out.println(buffer.getChar());
    System.out.println(buffer.getLong());
    System.out.println(buffer.getChar());

    buffer.limit(capacity); /* init limit */

    buffer.putChar('C');

    System.out.println(buffer.getChar(checkpointPosition));

  }
}

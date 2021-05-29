package com.huanwei;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * buffer 描述
 * <p>
 *   - Capacity : 容量，即可以容纳的最大数据量；在缓冲区创建的时候设定并且不能被改变.
 *   - Limit    : 表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操, 且极限时可以修改的
 *   - Position : 位置，下一个要被读或写的元素的索引，每次读写缓冲区数据都会改变position值，为下一次的读写做准备
 *   - Mark     : 标记，调用mark来设置 mark = position，再调用 reset() 可以让 position 恢复到标记的位置
 * </p>
 */
public class TestBuffer {


  public static void main(String[] args) {
    // used 1024 byte buffer
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    // user 1024 char buffer
    CharBuffer charBuffer = CharBuffer.allocate(1024);



  }
}

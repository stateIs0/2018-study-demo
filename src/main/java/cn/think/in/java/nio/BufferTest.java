package cn.think.in.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {

  /**
   * 注意 buf.flip() 的调用，首先读取数据到 Buffer，然后反转 Buffer, 接着再从 Buffer 中读取数据。下一节会深入讲解 Buffer 的更多细节。
   */
  public static void main(String[] args) throws IOException {
    RandomAccessFile aFile = new RandomAccessFile("/Users/cxs/code/cpu.log", "rw");
    FileChannel inChannel = aFile.getChannel();
    ByteBuffer buf = ByteBuffer.allocate(48);
    int bytesRead = inChannel.read(buf);
    while (bytesRead != -1) {
      System.out.println("Read "  + bytesRead);
      buf.flip();
      while(buf.hasRemaining()){
        System.out.print((char) buf.get());
      }
      buf.clear();
      bytesRead = inChannel.read(buf);
    }
    aFile.close();

  }

}

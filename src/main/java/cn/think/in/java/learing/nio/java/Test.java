package cn.think.in.java.learing.nio.java;

import java.nio.IntBuffer;

public class Test {

  /**
   * 容量 (capacity):表示 Buffer 最大数据容量，缓冲区容量不能为负，并且创建后不能更改。
     限制 (limit):第一个不应该读取或写入的数据的索引，即位于 limit 后的数据不可读写。缓冲区的限制不能为负，并且不能大于其容量。
     位置 (position):下一个要读取或写入的数据的索引。缓冲区的位置不能为负，并且不能大于其限制。

     备注：标记、 位置、 限制、 容量遵守以下不变式： 0 <= position <= limit <= capacity。

   */
  public static void main(String[] args) {
    //第一步，获取IntBuffer，通过IntBuffer.allocate操作
    IntBuffer buf = IntBuffer.allocate(10);    // 准备出10个大小的缓冲区

//第二步未操作前输出属性值
    System.out.println(
        "position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf
            .capacity());

//第三步进行设置数据
    buf.put(6);    // 设置一个数据
    buf.put(16);   // 设置二个数据

//第四步操作后输出属性值
    System.out.println(
        "position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf
            .capacity());

//第五步将Buffer从写模式切换到读模式 postion = 0 ,limit = 原本position
    buf.flip();

//第六步操作后输出属性值
    System.out.println(
        "position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf
            .capacity());

  }

  /**
   * 通道表示打开到 IO 设备(例如：文件、套接字)的连接。若需要使用 NIO 系统，
   * 需要获取用于连接 IO 设备的通道以及用于容纳数据的缓冲区。然后操作缓冲区，对数据进行处理。Channel 负责传输，
   * Buffer 负责存储。通道是由 java.nio.channels 包定义的。 Channel 表示 IO 源与目标打开的连接。Channel 类似于传统的“流”。
   * 只不过 Channel本身不能直接访问数据， Channel 只能与Buffer 进行交互。

   通道都是操作缓存区完成全部的功能的。

   */

}

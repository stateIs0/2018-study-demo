package cn.think.in.java.learing.nio.sendfile;

import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 *
 * @author 莫那·鲁道
 * @date 2018/10/31-下午9:12
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        FileChannel channel = null;
        channel.transferTo(1, 1, null);
    }

}

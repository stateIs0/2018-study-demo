package cn.think.in.java.learing.nio.mmp;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *
 * @author 莫那·鲁道
 * @date 2018/10/31-下午8:49
 */
public class MmpDemo {

    public static void main(String[] args) {
    }

    public void test1() throws Exception {
        String dir = "/Users/cxs/data/";
//        ensureDirOK(dir);
        RandomAccessFile memoryMappedFile;
        int size = 1 * 1024 * 1024;
        try {
            memoryMappedFile = new RandomAccessFile(dir + "testMmap.txt", "rw");
            MappedByteBuffer mappedByteBuffer = memoryMappedFile.getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, size);

            for (int i = 0; i < 100000; i++) {
                mappedByteBuffer.position(i * 4);
                mappedByteBuffer.putInt(i);
            }
            memoryMappedFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

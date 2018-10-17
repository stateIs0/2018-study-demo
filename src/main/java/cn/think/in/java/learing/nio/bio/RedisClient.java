package cn.think.in.java.learing.nio.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import cn.think.in.java.learing.Sleep;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/1-上午7:25
 */
public class RedisClient {

    static Socket client = new Socket();
    static InputStream is;
    static OutputStream os;
    static {
        try {
            client.connect(new InetSocketAddress("127.0.0.1", 6379));
            is = client.getInputStream();
            os = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        writeAndFlush("set baby hello\n");

        respParseHandler(printResult());
        // result : +OK

        writeAndFlush("get baby \n");

        Sleep.sleep(1);// 等 redis 返回数据

        respParseHandler(printResult());
        // result : $5
        // result : hello

        writeAndFlush("del baby \n");

        Sleep.sleep(1);// 等 redis 返回数据

        respParseHandler(printResult());
        // result  :1

        writeAndFlush("xxx \n");

        Sleep.sleep(1);// 等 redis 返回数据

        respParseHandler(printResult());
        // -ERR unknown command `xxx`, with args beginning with:

        writeAndFlush("mget hello baby \r\n");

        Sleep.sleep(1);

        respParseHandler(printResult());

        closeStream();
    }

    static void writeAndFlush(String content) {

        try {
            os.write(content.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeStream() {
        try {
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String printResult() {
        try {

            int a = is.available();
            byte[] b = new byte[a];
            is.read(b);

            String result = new String(b);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void respParseHandler(String result) {
        try {

            if (result.startsWith("+")) {
                // 状态回复
                System.out.println(result.substring(1));
                return;
            }
            if (result.startsWith("-")) {
                // 错误回复
                System.err.println(result.substring(1));
                return;
            }

            if (result.startsWith(":")) {
                // 整数回复
                if (result.substring(1).equalsIgnoreCase("1")) {
                    System.out.println("success");
                } else {
                    System.out.println("error");
                }
                return;
            }

            if (result.startsWith("$")) {

                if (result.substring(1, 3).equalsIgnoreCase("-1")) {
                    System.out.println("no data, redis return -1");
                    return;
                }
                // 批量回复
                int line = result.indexOf("\r\n");
                System.out.println(result.substring(line + 2));
                return;
            }

            if (result.startsWith("*")) {
                // 多条批量回复
                char num = result.charAt(1);
                int first = result.indexOf('$');
                for (int i = 0; i < Integer.valueOf(String.valueOf(num)); i++) {

                    int next = result.indexOf('$', first + 1);
                    if (next != -1) {
                        respParseHandler(result.substring(first, next));
                    } else {
                        respParseHandler(result.substring(first));
                    }
                    first = next;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

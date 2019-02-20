package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-28-14:52
 */
public class FileDemo {

    public static void main(String[] args) throws IOException {
//        File file = new File("/Users/cxs/Downloads/arthas的副本.txt");
//        File file = new File("/Users/cxs/Downloads/2.txt");
        File file = new File("/Users/cxs/Downloads/mybatis 第二次调用");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line = null;
        while ((line = br.readLine()) != null) {
            if (-1 != line.indexOf("[")) {

                int one = line.indexOf("[");
                int two = line.indexOf("]", one + 1);
                if (one == -1) {
                    continue;
                }

                if (one < 0 || two < 0) {
                    System.out.println(line);
                    continue;
                }
                String num = line.substring(one + 1, two - 2);
                if (num.contains("min")) {
                    num = num.substring(4, num.indexOf("ms"));
                    continue;
                }
                try {

                    Double d = Double.valueOf(num);
                    if (d > 1) {
                        System.out.println(line);
                        continue;
                    }else {
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(line);
        }
    }

}

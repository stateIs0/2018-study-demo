package cn.think.in.java.learing.testDemo;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 玄灭
 * @date 2018/12/10-上午9:46
 */
public class JMXDemo {

    public static void main(String[] args) throws InterruptedException {
        final int processor = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
        System.out.println(processor);

        ExecutorService es = Executors.newFixedThreadPool(1000);
        System.out.println(ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage());

        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    for (; ; ) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        for (; ; ) {
            OperatingSystemMXBean o = ManagementFactory.getOperatingSystemMXBean();
            System.out.println(o.getSystemLoadAverage());
            System.out.println(o.getArch());
            System.out.println(o.getName());
            System.out.println(o.getVersion());
            Thread.sleep(1000);
        }


    }

}

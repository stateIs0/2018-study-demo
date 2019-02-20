package cn.think.in.java.learing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-02-14-09:17
 */
public class HashTimeWheel {

    int arrSize = 512;

    List<LinkedList<TimeOut>> arr = new ArrayList<>(arrSize);
    static long tickDuration = 100;// ms
    Worker worker = new Worker();
    Thread workerThread;

    LinkedBlockingQueue queue = new LinkedBlockingQueue();

    public HashTimeWheel() {
        for (int i = 0; i < arrSize; i++) {
            arr.add(new LinkedList<>());
        }
    }

    public void start() {
        workerThread = new Thread(worker);
        workerThread.start();
    }

    public class Worker implements Runnable {

        int tick = 0;
        long s = System.currentTimeMillis();

        @Override
        public void run() {
            for (; ; ) {
                try {
                    Thread.sleep(tickDuration);
                    createTimeout0();
                    int idx = tick % arr.size();
                    LinkedList<TimeOut> linkedLists = arr.get(idx);

                    for (TimeOut timeOut : linkedLists) {
                        if (timeOut.round == 0) {
                            timeOut.task.run();
                            linkedLists.remove(timeOut);
                        } else {
                            timeOut.round -= 1;
                        }
                    }


                } catch (InterruptedException e) {
                    // ignore
                } finally {
                    tick++;
                }

            }
        }
    }

    public void createTimeout(Runnable task, long timeout) {

        long t = System.currentTimeMillis();
        System.out.println(t);
        TimeOut timeOut = new TimeOut(task, t + timeout, 1, 1);

        queue.offer(timeOut);

    }

    public void createTimeout0() {
        for (int i = 0; i < 1; i++) {
            TimeOut timeOut = (TimeOut) queue.poll();

            if (timeOut != null) {
                long deadline = timeOut.timeout - System.currentTimeMillis();
                int currentIndex = worker.tick % arrSize;
                long idx = (deadline / tickDuration + currentIndex) % arr.size();

//                if (idx > arrSize) {
//                    idx = idx % arrSize;
//                    timeOut = new TimeOut(timeOut.task, deadline, 0, (int) ((deadline / tickDuration) / arr.size() + 1));
//                } else {
//
//                }

                timeOut = new TimeOut(timeOut.task, deadline, 0, (int) ((deadline / tickDuration + currentIndex) / arr.size()));
                arr.get((int) idx).add(timeOut);
            }
        }


    }

    public class TimeOut {

        Runnable task;
        long timeout;
        int state;
        int round;

        public TimeOut(Runnable task, long timeout, int state, int round) {
            this.task = task;
            this.timeout = timeout;
            this.state = state;
            this.round = round;
        }
    }

    static ThreadLocal<Long> local = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void main(String[] args) {
        HashTimeWheel wheel = new HashTimeWheel();
        wheel.start();

        wheel.createTimeout(new Runnable() {
            long t = local.get();

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        }, 3000);


    }

}

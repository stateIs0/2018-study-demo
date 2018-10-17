package cn.think.in.java.learing.proxy.proxy;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/4-下午9:43
 */
public class Sleep {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

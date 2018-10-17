package cn.think.in.java.learing;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/1-上午9:13
 */
public class Sleep {

    public static void sleep(int s) {
        try {
            Thread.sleep(1000 * s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

package cn.think.in.java.learing.designPattern.state;

/**
 *
 * @author 莫那·鲁道
 * @date 2018/10/27-上午10:10
 */
public class StateB implements State {

    @Override
    public Object handle(Context context) {
        System.out.println(" B ");
        return null;
    }
}

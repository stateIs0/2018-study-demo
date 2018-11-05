package cn.think.in.java.learing.designPattern.state;

/**
 *
 * @author 莫那·鲁道
 * @date 2018/10/27-上午10:12
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new StateA());
        context.result();

        context.setState(new StateB());
        context.result();
    }

}

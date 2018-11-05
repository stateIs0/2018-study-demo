package cn.think.in.java.learing.designPattern.state;

/**
 *
 * @author 莫那·鲁道
 * @date 2018/10/27-上午10:10
 */
public class Context {

    State state;

    public Context() {
    }

    public Context(State state) {
        this.state = state;
    }



    public Object result() {
        return state.handle(this);

    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

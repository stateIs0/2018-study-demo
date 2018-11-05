package cn.think.in.java.learing.jvm.bytecode;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/12-上午7:40
 */
public abstract class TestBean {

    public String field;

    public abstract String getM();

    public abstract void setF(String f);

    public String getF() {
        return this.field;
    }
}
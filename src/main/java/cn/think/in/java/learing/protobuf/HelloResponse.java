package cn.think.in.java.learing.protobuf;

import io.protostuff.Schema;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-02-20-14:32
 */
public class HelloResponse {

    public static Schema<HelloResponse> getSchema() {
        return null;
    }

    public void setGreeting(String s) {
    }
}

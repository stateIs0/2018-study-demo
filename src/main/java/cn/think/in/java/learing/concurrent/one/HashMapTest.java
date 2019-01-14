package cn.think.in.java.learing.concurrent.one;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        String key = "one";
        int hashcode = key.hashCode();
        int h = 0;
        int value = hashcode ^ (h >>> 16);
        map.put("two", 2);
        Integer integer = map.get("one");
        System.out.println(integer);
        System.out.println(4 ^ 2);
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(6));

        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(6));

        /* 基于Hystrix提供的平均响应时间动态调整权重的负载均衡算法。<br/>
         * <p>首先求得总体的平均响应时间，然后根据 总体平均响应时间/自身平均响应时间 得到权重变化的系数，系数再做一次幂运算，变更后的权重=原始权重*系数.</p>
         * 下为示例。
         * <pre>
         * Provider 原始权重 平均响应时间 算法结果
         * A         150        200       150*(((100+200)/2)/200)^2=84.375
         * B         100        100       100*(((100+200)/2)/100)^2=225
         */

        /**
         * >>> 无符号右移16位
         * ^ 按位异或  就是把两个数按二进制，相同就取0，不同就取1。
         *
         *
         *   比如：0101 ^ 1110 的结果为 1011。（记得以前上数字电路课的时候学过）异或的速度是非常快的。

         把一个数右移16位即丢弃低16位，就是任何小于216的数，右移16后结果都为0（2的16次方再右移刚好就是1）。

         任何一个数，与0按位异或的结果都是这个数本身（很好验证）。

         所以这个hash()函数对于非null的hash值，仅在其大于等于2的16的时候才会重新调整其值。
         */
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 如果不重写 equals 方法，Object 类比较的是内存地址，所以，即使是两个业务上相同的两个对象，返回的也是不同的，因为内存地址不同。
     * 但是如果重写了 equals 方法且没有重写 hashcode 方法，那么在诸如 hashSet 中，将会出现错误，因为容器会首先比对 hashcode, 如果 hashcode 不同，表示这是不同的对象，
     * 但我们的业务逻辑中比如id相同就是一个对象了，因此需要重写 hashcode 方法。
     */
}


/**
 * java中有三种移位运算符

 <<      :     左移运算符，num << 1,相当于num乘以2  低位补0

 >>      :     右移运算符，num >> 1,相当于num除以2  高位补0

 >>>    :     无符号右移，忽略符号位，空位都以0补齐   用0进行补位

 无符号右移的规则只记住一点：忽略了符号位扩展，0补最高位  无符号右移运算符>>> 只是对32位和64位的值有意义

 % 模运算 取余
 ^ 位异或 第一个操作数的的第n位于第二个操作数的第n位相反，那么结果的第n为也为1，否则为0
 & 与运算 第一个操作数的的第n位于第二个操作数的第n位如果都是1，那么结果的第n为也为1，否则为0
 0100100101010
 0000000010000
 | 或运算 第一个操作数的的第n位于第二个操作数的第n位 只要有一个是1，那么结果的第n为也为1，否则为0
 ~ 非运算 操作数的第n位为1，那么结果的第n位为0，反之，也就是取反运算（一元操作符：只操作一个数）
 hello world 曹先胜
 */

class Ajdlf {

    public static void main(String[] args) {
        System.out.println("");
//    System.out.println(10 << 6);
//    System.out.println(10 << 1);
//    System.out.println(32 >> 2);
//    System.out.println(65531 >>> 16);
//    System.out.println(323232 ^ 1);
//    System.out.println(15 & 2121214);
        System.out.println(Integer.toBinaryString(new Float(1).hashCode()));
        System.out.println(Integer.toBinaryString(new Float(2).hashCode()));
        System.out.println(Integer.toBinaryString(new Float(3).hashCode()));
        System.out.println(Integer.toBinaryString(new Float(4).hashCode()));
        System.out.println(Integer.toBinaryString(new Float(5).hashCode()));
        System.out.println(Integer.toBinaryString(new Float(6).hashCode()));

        int h = 0;
        System.out.println(Integer.toBinaryString((h = new Float(2).hashCode()) ^ (h >>> 16)));
        System.out.println((15 & ((h = new Float(2).hashCode()) ^ (h >>> 16))));
        System.out.println(Integer.toBinaryString(15 & ((h = new Integer(1).hashCode()) ^ (h >>> 16))));
        System.out.println(Integer.toBinaryString(15 & ((h = new Integer(17).hashCode()) ^ (h >>> 16))));
    }
}


class Test1 {

    String name;

    public Test1(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Test1)) {
            return false;
        }

        Test1 test1 = (Test1) o;

        return name != null ? name.equals(test1.name) : test1.name == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = name.hashCode() + result * 31;
        return name != null ? name.hashCode() : 0;
    }

    public static void main(String[] args) {
//    Map<Test1, String> map = new HashMap<>(4);
//    map.put(new Test1("hello"), "hello");
//    String hello = map.get(new Test1("hello"));
//    System.out.println(hello);

        System.out.println(Long.toBinaryString(8888888888888888L * 31));
        // result : 1101010011100000110100011001000

        System.out.println(Long.toBinaryString(8888888888888888L * 32));
        // result : 10011111011010111100011100000000

        System.out.println(15 & 1111000000);
        System.out.println(15 & 1000100000);

        System.out.println(1 << 30);

        /**
         * 111111100000000000000000000000 >>> 16
         * 000000000000001111111000000000
         *
         * 1000000100000000000000000000000
         * 0000000000000001000000100000000
         *
         */

    }
}

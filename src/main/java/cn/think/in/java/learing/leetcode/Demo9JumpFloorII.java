package cn.think.in.java.learing.leetcode;

/**
 *
 * 2*(n-1)
 *
 * @author 玄灭
 * @date 2018/11/20-上午9:13
 */
public class Demo9JumpFloorII {

public int JumpFloorII(int target) {
    if (target == 0) {
        return 0;
    }
    if (target == 1) {
        return 1;
    }
    return 2 * JumpFloorII(target - 1);
}


public int jumpFloorII(int target) {
    if (target == 0) {
        return 0;
    }

    int result = 1;
    for (int i = 1; i < target; i++) {
        result *= 2;
    }
    return result;
}

    public static void main(String[] args) {
        System.out.println(new Demo9JumpFloorII().jumpFloorII(3));
    }

}

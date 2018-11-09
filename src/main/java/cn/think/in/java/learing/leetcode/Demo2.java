package cn.think.in.java.learing.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 *
 * @author 莫那·鲁道
 * @date 2018/11/8-下午10:33
 */
public class Demo2 {

//    static int[] nums = {5, 4, 6, 7, 9, 3, 10, 9, 5, 6};
    static int [] nums = {2,3,1,0,2,5,3};


    public static void main(String[] args) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] == i) {
                continue;
            }
            int m = Math.abs(nums[i]);
            if (nums[m] > 0) {
                nums[m] = -nums[m];
            } else {
                list.add(m);
                System.out.println(m);
            }
        }

    }

}

class Demo3 {

    static int[] arr = {2, 3, 1, 0, 2, 5, 3};


    public static void main(String[] args) {
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                int m = arr[i];
                if (arr[m] == m) {
                    System.out.println(m);
                    break;
                } else {
                    int tmp = arr[m];
                    arr[m] = arr[i];
                    arr[i] = tmp;
                }
            }

        }
    }
}

class Demo4 {

    static int[] nums = {5, 4, 6, 7, 9, 3, 10, 9, 5, 6};

    public static void main(String[] args) {
        int l = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int m = Math.abs(i);
            if (nums[m] > 0) {
                nums[m] = -nums[m];
            } else {
                System.out.println(m);
            }
        }
    }
}


























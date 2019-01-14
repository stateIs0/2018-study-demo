package cn.think.in.java.learing.imac;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * @author 莫那·鲁道
 * @date 2018/11/9-上午9:07
 */
public class Demo1 {

    static int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicates(arr));
    }

    private static class Solution {


        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                // i 位置的 m 数, -1, 为的是对应下标;
                int m = Math.abs(nums[i]) - 1;
                if (nums[m] > 0) {
                    nums[m] = -nums[m];
                } else {
                    list.add(m + 1);
                }
            }
            return list;
        }
    }

}


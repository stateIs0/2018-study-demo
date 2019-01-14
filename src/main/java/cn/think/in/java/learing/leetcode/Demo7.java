package cn.think.in.java.learing.leetcode;

/**
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author 玄灭
 * @date 2018/11/15-下午12:02
 */
public class Demo7 {

    public static void main(String[] args) {

    }

    // {4,5,6,1,2,3}
    public int minNumberInRotateArray(int[] array) {
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            // 2 分
            int mid = (low + high) / 2;
            // 中位数大于高位:最小的数字一定在右边
            if (array[mid] > array[high]) {
                low = mid + 1;// 缩小范围
                // 中位数小于低位数:最小的数字一定在左边
            } else if (array[low] > array[mid]) {
                high = mid;// 缩小范围
                // 相等
            } else {
                // high 必然大于等于最小数
                --high;
            }
        }
        return array[low];
    }

}

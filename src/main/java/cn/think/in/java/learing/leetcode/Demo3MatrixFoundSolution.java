package cn.think.in.java.learing.leetcode;

/**
 * 二维数组查找.
 *
 * @author 莫那·鲁道
 * @date 2018/11/9-上午9:44
 */
public class Demo3MatrixFoundSolution {

    public static void main(String[] args) {
        int[][] array = new int[4][4];
        array[0] = new int[]{1, 2, 8, 9};
        array[1] = new int[]{2, 4, 9, 12};
        array[2] = new int[]{4, 7, 10, 13};
        array[3] = new int[]{6, 8, 11, 15};

        System.out.println(find(7, array));

    }

    /**
     * Matrix
     * @param target
     * @param array
     * @return
     */
    public static boolean find(int target, int[][] array) {
        int rows = array.length;// 行
        int columns = array[0].length;// 列

        int column = columns -1;
        int row = 0;
        while (column >= 0 && row < rows) {
            if (array[row][column] == target) {
                return true;
            }
            if (array[row][column] > target) {
                --column;
            } else {
                ++row;
            }

        }
        return false;
    }

}

package cn.think.in.java.learing.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 玄灭
 * @date 2018/11/14-下午2:59
 */
public class Demo5ReConstructBinaryTree {


    static int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
    static int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

    public static void main(String[] args) {
        TreeNode root = new Demo5ReConstructBinaryTree().reConstructBinaryTree(pre, in);
        System.out.println(root);
        post(root, new ArrayList<>());
    }

    public static void post(TreeNode node, List<Integer> list) {
        if (node.left != null) {
            post(node.left, list);
        }
        if (node.right != null) {
            post(node.right, list);
        }
        System.out.print(node.val);

    }

    /**
     * 前序第一位必然是 root, 对应的中序 data 的前半部分是左子树,后半部分是右子树.
     * 而前序根节点后面的第一位 pre[1] 必然是根节点, 对应的中序位置 的 前半部分必然是左子树, 后半部分是右子树.
     * 以此类推,即可递归.
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        return reBuild(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    TreeNode reBuild(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) {
            return null;//如果数组长度为0，返回空
        }
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reBuild(pre, startPre + 1, startPre + (i - startIn), in, startIn, i - 1);
                root.right = reBuild(pre, startPre + (i - startIn) + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }


    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
        }
    }


}

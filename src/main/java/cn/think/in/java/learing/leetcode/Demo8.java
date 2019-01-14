package cn.think.in.java.learing.leetcode;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 *
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * @author 玄灭
 * @date 2018/11/16-上午8:40
 */
public class Demo8 {

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        // 1. 有右子树
        if (pNode.right != null) {
            TreeLinkNode right = pNode.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }


        // 2 左孩子,但无右子树
        if (pNode == pNode.next.left) {
            return pNode.next;
        }

        // 3 右孩子,且无右子数.----- 向上遍历
        while (pNode.next.next != null) {
            if (pNode.next == pNode.next.next.left) {
                return pNode.next.next;
            }
            pNode = pNode.next;
        }
        return null;
    }

    class TreeLinkNode {

        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}

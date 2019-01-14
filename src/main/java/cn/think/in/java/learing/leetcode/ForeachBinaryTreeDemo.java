package cn.think.in.java.learing.leetcode;

import java.util.ArrayList;
import java.util.List;

import cn.think.in.java.learing.jvm.bytecode.B;

/**
 *
 * @author 玄灭
 * @date 2018/11/15-上午7:52
 */
public class ForeachBinaryTreeDemo {

    private static class BinaryTreeNode{
        Object data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(Object data, BinaryTreeNode left,
            BinaryTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void preOrder(BinaryTreeNode node, List<BinaryTreeNode> list) {
        list.add(node);
        if (node.left != null) {
            preOrder(node.left,list);
        }
        if (node.right != null) {
            preOrder(node.right, list);
        }
    }

    public static void inOrder(BinaryTreeNode node, List<BinaryTreeNode> list) {
        if (node.left != null) {
            inOrder(node.left, list);
        }
        list.add(node);
        if (node.right != null) {
            inOrder(node.right, list);
        }
    }

    public static void postOrder(BinaryTreeNode node, List<BinaryTreeNode> list) {
        if (node.left != null) {
            postOrder(node.left, list);
        }
        if (node.right != null) {
            postOrder(node.right, list);
        }
        list.add(node);
    }

    private static BinaryTreeNode root =null;
    private static List<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();

    public static void main(String[] args) {
        init();
        preOrder(root,list);
//        inOrder(root,list);
//        postOrder(root, list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).data + " ");
        }
    }

    // 树的初始化:先从叶节点开始,由叶到根
    public static void init() {
        BinaryTreeNode b = new BinaryTreeNode("b", null, null);
        BinaryTreeNode a = new BinaryTreeNode("a", null, b);
        BinaryTreeNode c = new BinaryTreeNode("c", a, null);

        BinaryTreeNode e = new BinaryTreeNode("e", null, null);
        BinaryTreeNode g = new BinaryTreeNode("g", null, null);
        BinaryTreeNode f = new BinaryTreeNode("f", e, g);
        BinaryTreeNode h = new BinaryTreeNode("h", f, null);

        BinaryTreeNode d = new BinaryTreeNode("d", c, h);

        BinaryTreeNode j = new BinaryTreeNode("j", null, null);
        BinaryTreeNode k = new BinaryTreeNode("k", j, null);
        BinaryTreeNode m = new BinaryTreeNode("m", null, null);
        BinaryTreeNode o = new BinaryTreeNode("o", null, null);
        BinaryTreeNode p = new BinaryTreeNode("p", o, null);
        BinaryTreeNode n = new BinaryTreeNode("n", m, p);
        BinaryTreeNode l = new BinaryTreeNode("l", k, n);

        root = new BinaryTreeNode("i", d, l);
    }


}

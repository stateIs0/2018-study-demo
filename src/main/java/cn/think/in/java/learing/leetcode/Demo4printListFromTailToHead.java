package cn.think.in.java.learing.leetcode;

import java.util.ArrayList;

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 *
 * @author 玄灭
 * @date 2018/11/12-上午8:04
 */
public class Demo4printListFromTailToHead {

    public static void main(String[] args) {

        System.out
            .println(printListFromTailToHead(new ListNode("1", new ListNode("2", new ListNode("3", new ListNode("4", null))))));
        recursion(new ListNode("1", new ListNode("2", new ListNode("3", new ListNode("4", null)))));
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode current = listNode;
        ArrayList list = new ArrayList();

        while (current != null) {
            ListNode next = current.next;
            list.add(0, current.val);
            current = next;
        }
        return list;
    }

    static ArrayList list = new ArrayList();
    /** 递归实现打印, 栈的本质就是递归 */
    public static void recursion(ListNode listNode) {
        if (listNode != null && listNode.next != null) {
            recursion(listNode.next);
        }
        list.add(listNode.val);
        System.out.println(listNode.val);
    }


    public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList list = new ArrayList();
        if (listNode != null && listNode.next != null) {
            printListFromTailToHead(listNode.next);
        }
        list.add(listNode.val);
        return list;
    }


    private static class ListNode {

        Object val;
        ListNode next;

        public ListNode(Object x, ListNode next) {
            this.val = x;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
        }
    }

}

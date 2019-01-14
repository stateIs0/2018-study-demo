package cn.think.in.java.learing.leetcode;

/**
 *
 *
 * @author 莫那·鲁道
 * @date 2018/11/7-下午11:27
 */
public class Demo1ReverseNodeList {

    private static class Node {
        Integer item;
        Node next;

        public Node(Integer item) {
            this.item = item;
        }

        public Node(Integer item, Node next) {
            this.item = item;
            this.next = next;
        }

        public Node(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
        }
    }

    public static Node reverseNode(Node head) {
        Node current = head;
        Node newHead = null;
        Node pre = null;

        while (current != null) {

            Node next = current.next;
            if (next == null) {
                newHead = current;
            }
            current.next = pre;
            pre = current;
            current = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Node node = new Node(new Integer(1));
        node = new Node(2, node);
        System.out.println(node);

        System.out.println(reverseNode(node));

    }
}

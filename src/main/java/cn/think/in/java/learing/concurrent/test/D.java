package cn.think.in.java.learing.concurrent.test;

/**
 *
 *
 * @author 莫那·鲁道
 * @date 2018/11/5-下午8:03
 */
public class D {

}

///评测题目: 要求：
//    （1）一个小时间任选两题；
//    （2）尽可能展示你的编码能力（Java语法、编码风格等），java语言肯定比伪代码得分高
//
//    1 写一个函数将一个单向链表进行反向。要求自行编写反向的过程和设计数据结构，不要外部包和辅助函数来处理。
class Node<T> {

    T item;
    Node<T> next;

    public Node(T item) {
        this.item = item;
    }

    public Node(T item, Node next) {
        this.item = item;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
            "item=" + item +
            ", next=" + next +
            '}';
    }

    public static void main(String[] args) {
        Node node = new Node(10);
        node=  new Node(9, node);
        node=new Node(8, node);
        node=new Node(7, node);
        node=new Node(6, node);
        node=new Node(5, node);
        node=new Node(4, node);
        node=new Node(3, node);
        node=new Node(2, node);
        node=new Node(1, node);
        node=new Node(0, node);

        System.out.println(Util.reversal(node));


    }

}

class Util {

    public static Node reversal(Node node) {
        if (node == null) {
            System.out.println("node is null or Empty !");
            return null;
        }

        Node[] tmp = new Node[128];
        int lastIndex = 0;
        for (int i = 0; node.next != null; i++) {
            if (tmp.length == i) {
//                tmp = dilatation(tmp);
            }
            tmp[i] = node;
            node = node.next;
            lastIndex = i;
        }

        Node newNode = null;
        for (int i = lastIndex; i > 1; i--) {
            newNode = new Node(tmp[i], tmp[i - 1]);
        }

        return newNode;
    }

    private static Node[] dilatatio(Node[] old) {
        if (old == null || old.length == 0) {
            throw new RuntimeException("Node array can not is null or empty !");
        }
        Node[] newArr = new Node[old.length * 2];
        System.arraycopy(old, 0, newArr, 0, old.length - 1);
        return newArr;
    }

    Node f(Node node) {
        Node pre = null;
        Node head = null;
        Node current = node;
        while (current != null) {
            Node next = node.next;
            if (next == null) {
                head = current;
            }



        }

        return head;
    }

}

//2   有三个线程ID分别是A、B、C,请用多线编程实现，在屏幕上循环打印10次ABCABC
//    请补充以下代码

class Test {

    public static void main(String[] args) {
        MajusculeABC maj = new MajusculeABC();
        Thread t_a = new Thread(new Thread_ABC(maj, 'A'));
        Thread t_b = new Thread(new Thread_ABC(maj, 'B'));
        Thread t_c = new Thread(new Thread_ABC(maj, 'C'));
        t_a.start();
        t_b.start();
        t_c.start();
    }
}

class MajusculeABC {

    int flag = 0;

    public synchronized void printChar(char c) {
        System.out.println(c);
        if (flag == 2) {
            flag = 0;
        } else {
            ++flag;// unLock 之后，保证可见。
        }
    }
}

class Thread_ABC implements Runnable {

    static int count = -1;
    MajusculeABC maj;
    char c;
    int flag;

    Thread_ABC(MajusculeABC maj, char c) {
        this.maj = maj;
        this.c = c;
        this.flag = ++count;
    }

    public void run() {
        for (; ; ) {
            if (maj.flag == this.flag) {
                maj.printChar(c);
            } else {
                Thread.yield();
            }
        }
    }
}



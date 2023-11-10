package DSAA.lab3;

import java.io.*;
import java.util.StringTokenizer;

public class Main1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();
        LinkedList linkedList1 = new LinkedList();//链表一储存第一个多项式
        LinkedList linkedList2 = new LinkedList();//链表二储存第二个多项式
        ListNode listNode1 = linkedList1.head;//第一个辅助指针便于链表一添加数据
        ListNode listNode2 = linkedList2.head;//第二个辅助指针便于链表二添加数据
        for (int i = 0; i < n; i++) {
            ListNode temp = new ListNode(in.nextInt(), in.nextInt());
            if (temp.coefficient != 0){
                listNode1.next = temp;
                listNode1 = listNode1.next;
            }
            }
        for (int i = 0; i < m; i++) {
            ListNode temp = new ListNode(in.nextInt(), in.nextInt());
            if (temp.coefficient != 0){
                listNode2.next = temp;
                listNode2 = listNode2.next;
            }
        }


        LinkedList linkedList = LinkedList.add(linkedList1, linkedList2);
        out.println(linkedList.counter);
        if (linkedList.head.next == null) {
            out.close();
            return;
        }
        ListNode temp = linkedList.head.next;
        while (temp != null){
            out.println(temp);
            temp = temp.next;
        }

        out.close();
    }
}

class ListNode{
    public int coefficient;//系数
    public int exponent;//指数
    public ListNode next;//下一节点

    public ListNode(){};
    public ListNode(int coefficient, int exponent){
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        return  coefficient + " "+ exponent ;
    }
}

class LinkedList{
    public ListNode head = new ListNode(0, 0);
    public int counter = 0;//记录链表的有效项数



    public static LinkedList add(LinkedList linkedList1, LinkedList linkedList2){
        LinkedList linkedList = new LinkedList();
        ListNode listNode = linkedList.head;
        ListNode listNode1 = linkedList1.head;
        ListNode listNode2 = linkedList2.head;
        while (true){//两多项式相加，不考虑一方已遍历完而另一方仍有剩余的部分
            if (listNode1.next == null || listNode2.next == null){//其中一个多项式遍历完毕就退出循环
                break;
            }
            //两个指针从两链表头开始往后遍历，谁小谁移动
            if (listNode1.next.exponent > listNode2.next.exponent){
                ListNode temp = new ListNode(listNode2.next.coefficient, listNode2.next.exponent);
                listNode.next = temp;
                listNode = listNode.next;
                linkedList.counter++;
                listNode2 = listNode2.next;
            } else if (listNode1.next.exponent < listNode2.next.exponent) {
                ListNode temp = new ListNode(listNode1.next.coefficient, listNode1.next.exponent);
                listNode.next = temp;
                listNode = listNode.next;
                linkedList.counter++;
                listNode1 = listNode1.next;
            }
            else {//listNode1.next.exponent == listNode2.next.exponent
                ListNode temp = new ListNode();
                temp.exponent = listNode1.next.exponent;
                temp.coefficient = listNode1.next.coefficient + listNode2.next.coefficient;
                if (temp.coefficient != 0){
                    listNode.next = temp;
                    listNode = listNode.next;
                    linkedList.counter++;
                }
                listNode1 = listNode1.next;
                listNode2 = listNode2.next;
            }
        }

        while (  listNode1.next != null ){
            listNode.next = listNode1.next;
            listNode = listNode.next;
            linkedList.counter++;
            listNode1 = listNode1.next;
        }
        while ( listNode2.next != null ){
            listNode.next = listNode2.next;
            listNode = listNode.next;
            linkedList.counter++;
            listNode2 = listNode2.next;
        }
        return linkedList;
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

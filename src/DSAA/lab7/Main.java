package DSAA.lab7;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int N = in.nextInt();
        Node3[] List = new Node3[N + 2];
        List[0] = new Node3(-1, 0);//假头
        List[N + 1] = new Node3(-1, N + 1);//假尾
        for (int i = 1; i < N + 1; i++) {
            List[i] = new Node3(in.nextInt(), i);
        }
        for (int i = 0; i < N + 1; i++) {//双向链表连起来
            List[i].next = List[i + 1];
            List[i + 1].pre = List[i];
        }
        heap3 heap = new heap3(N);
        for (int i = 1; i < N + 1; i++) {//把所有节点压入堆
            heap.insert(List[i]);
        }

        for (int i = 0; i < N - 1; i++) {
            Node3 temp = heap.delete();
            while (!temp.isReal){
                temp = heap.delete();
            }
//            if (temp.pre.value == -1 && temp.next.value == -1){
//                out.println(heap.delete().value);
//                out.close();
//                return;
//            }
            int pre = 0;
            int next = 0;
            if (temp.pre.value != -1 && temp.next.value == -1){
                pre = (temp.pre.value ^ temp.value) + 1;
                temp.value = pre;
                temp.pre.isReal = false;
                heap.insert(temp);
                temp.pre = temp.pre.pre;//链表
                temp.pre.next = temp;
                continue;
            }
            if (temp.next.value != -1 && temp.pre.value == -1){
                next = (temp.next.value ^ temp.value) + 1;
                temp.value = next;
                temp.next.isReal = false;
                heap.insert(temp);
                temp.next = temp.next.next;
                temp.next.pre = temp;
                continue;
            }
            pre = (temp.pre.value ^ temp.value) + 1;
            next = (temp.next.value ^ temp.value) + 1;
            if (  pre >= next ){
                temp.value = pre;
                temp.pre.isReal = false;
                heap.insert(temp);
                temp.pre = temp.pre.pre;//链表
                temp.pre.next = temp;
            }else{
                temp.value = next;
                temp.next.isReal = false;
                heap.insert(temp);
                temp.next = temp.next.next;
                temp.next.pre = temp;
            }
        }
        Node3 temp = heap.delete();
        while (true){
            if (temp.isReal){
                break;
            }
            temp = heap.delete();
        }
        out.println(temp.value);
        out.close();


    }
}

class heap3{//小顶堆
    Node3[] heap;
    int size = 0;
    public heap3(int n){
        heap = new Node3[n + 1];
    }

    void insert(Node3 x){//小顶堆
        size++;
        heap[size] = x;
        x.position = size;
        int cur = size;
        while (cur > 1){
            if (heap[cur].value < heap[cur / 2].value
                || (heap[cur].value == heap[cur / 2].value && heap[cur].index < heap[cur / 2].index)){
                Node3 temp = heap[cur];
                heap[cur] = heap[cur / 2];
                heap[cur / 2] = temp;
                cur = cur / 2;
            }else {
                break;
            }
        }
    }

    Node3 delete(){
        Node3 temp = heap[1];
        heap[1] = heap[size];
        heap[size] = temp;
        size--;
        int cur = 1;
        while (cur * 2 <= size){
            if (cur * 2 + 1 <= size){//两个儿子
                if (heap[2 * cur].value > heap[2 * cur + 1].value){//左儿子大
                    if (heap[cur].value > heap[2 * cur + 1].value
                    || (heap[cur].value == heap[2 * cur + 1].value && heap[cur].index > heap[2 * cur + 1].index)){
                        Node3 tem = heap[cur];
                        heap[cur] = heap[cur * 2 + 1];
                        heap[cur * 2 + 1] = tem;
                        cur = cur * 2 + 1;
                    }else {
                        break;
                    }
                }else {//右儿子大或者一样大
                    if (heap[cur].value > heap[2 * cur].value
                    || (heap[cur].value == heap[2 * cur].value && heap[cur].index > heap[2 * cur].index)){
                        Node3 tem = heap[cur];
                        heap[cur] = heap[cur * 2];
                        heap[cur * 2] = tem;
                        cur = cur * 2 ;
                    }else {
                        break;
                    }
                }
            }else {//一个儿子
                if (heap[cur].value > heap[2 * cur].value
                || (heap[cur].value == heap[2 * cur].value && heap[cur].index > heap[2 * cur].index)){
                    Node3 tem = heap[cur];
                    heap[cur] = heap[cur * 2];
                    heap[cur * 2] = tem;
                    cur = cur * 2 ;
                }else {
                    break;
                }
            }
        }
        return temp;
    }
}

class Node3{
    int value;//值
    int index;//链表中的索引
    int position;//堆中的位置
    Node3 pre;//链表中前一个
    Node3 next;//链表中后一个
    boolean isReal = true;//幽灵节点
    public Node3(int value, int index){
        this.value = value;
        this.index = index;
    }
}

class QReader3 {
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

class QWriter3 implements Closeable {
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

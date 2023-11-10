package DSAA.lab7;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1 {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int N = in.nextInt();
        int M = in.nextInt();
        long K = in.nextLong();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = in.nextInt();
        }
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = in.nextInt();
        }
        Arrays.sort(A);
        heap1 heap = new heap1(M);
        for (int i = 0; i < M; i++) {
            heap.insert(new Node(0, i, (long) A[0] * B[i]));
        }
        for (int i = 0; i < K; i++) {
            Node temp = heap.delete();
            int m = temp.i;
            int n = temp.j;
            out.print(temp.value + " ");
            if (m + 1 >= N){
                continue;
            }

            heap.insert(new Node(m + 1, n, (long) A[m + 1] * B[n]));

        }
        out.close();

    }


}

class Node{
    int i;
    int j;
    long value;
    public Node(int i, int j, long value){
        this.i = i;
        this.j = j;
        this.value = value;
    }
}



class heap1{//小顶堆
    Node[] heap;
    int size = 0;
    public heap1(int n){
        heap = new Node[n + 1];
    }

    void insert(Node x){//小顶堆
        size++;
        heap[size] = x;
        int cur = size;
        while (cur > 1){
            if (heap[cur].value < heap[cur / 2].value){
                Node temp = heap[cur];
                heap[cur] = heap[cur / 2];
                heap[cur / 2] = temp;
                cur = cur / 2;
            }else {
                break;
            }
        }

    }
    Node delete(){

        Node temp = heap[1];
        heap[1] = heap[size];
        heap[size] = temp;
        size--;
        int cur = 1;
        while (cur * 2 <= size){
            if (cur * 2 + 1 <= size){//两个儿子
                if (heap[2 * cur].value > heap[2 * cur + 1].value){//左儿子大
                    if (heap[cur].value > heap[2 * cur + 1].value){
                        Node tem = heap[cur];
                        heap[cur] = heap[cur * 2 + 1];
                        heap[cur * 2 + 1] = tem;
                        cur = cur * 2 + 1;
                    }else {
                        break;
                    }
                }else {//右儿子大或者一样大
                    if (heap[cur].value > heap[2 * cur].value){
                        Node tem = heap[cur];
                        heap[cur] = heap[cur * 2];
                        heap[cur * 2] = tem;
                        cur = cur * 2;
                    }else {
                        break;
                    }
                }
            }else {
                if (heap[cur].value > heap[2 * cur].value){
                    Node tem = heap[cur];
                    heap[cur] = heap[cur * 2];
                    heap[cur * 2] = tem;
                    cur = cur * 2;
                }else {
                    break;
                }
            }
        }
        return temp;
    }
}

class QReader1 {
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

class QWriter1 implements Closeable {
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

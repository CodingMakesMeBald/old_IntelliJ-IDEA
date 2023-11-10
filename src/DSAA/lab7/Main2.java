package DSAA.lab7;

import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        int ans = 0;
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            ans=0;
            heap heap = new heap(N);
            for (int j = 0; j < N; j++) {
                heap.insert(in.nextInt());
            }
            for (int j = 0; j < N - 1; j++) {
                int temp1 = heap.delete();
                int temp2 = heap.delete();
                int temp = temp1 + temp2;
                ans += temp;
                heap.insert(temp);
            }
            out.println(ans);
        }

        out.close();
    }

}

class heap{//小顶堆
    int[] heap;
    int size = 0;
    public heap(int n){
        heap = new int[n + 1];
    }

    void insert(int x){//小顶堆
        size++;
        heap[size] = x;
        int cur = size;
        while (cur > 1){
            if (heap[cur] < heap[cur / 2]){
                int temp = heap[cur];
                heap[cur] = heap[cur / 2];
                heap[cur / 2] = temp;
                cur = cur / 2;
            }else {
                break;
            }
        }

    }
    int delete(){

        int temp = heap[1];
        heap[1] = heap[size];
        heap[size] = temp;
        size--;
        int cur = 1;
        while (cur * 2 <= size){
            if (cur * 2 + 1 <= size){//两个儿子
                if (heap[2 * cur] > heap[2 * cur + 1]){//左儿子大
                    if (heap[cur] > heap[2 * cur + 1]){
                        int tem = heap[cur];
                        heap[cur] = heap[cur * 2 + 1];
                        heap[cur * 2 + 1] = tem;
                        cur = cur * 2 + 1;
                    }else {
                        break;
                    }
                }else {//右儿子大或者一样大
                     if (heap[cur] > heap[2 * cur]){
                        int tem = heap[cur];
                        heap[cur] = heap[cur * 2];
                        heap[cur * 2] = tem;
                        cur = cur * 2;
                    }else {
                         break;
                     }
                }
            }else {
                if (heap[cur] > heap[2 * cur]){
                    int tem = heap[cur];
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

class QReader2 {
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

class QWriter2 implements Closeable {
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

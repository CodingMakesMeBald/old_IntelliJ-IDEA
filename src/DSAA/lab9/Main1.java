package DSAA.lab9;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1 {
    public static void main(String[] args) {
        QReaderA in = new QReaderA();
        QWriterA out = new QWriterA();
        int n = in.nextInt();
        int m = in.nextInt();
        graphNode[] graph = new graphNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new graphNode(i + 1);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            long w = in.nextLong();
            graph[u - 1].child.add(graph[v - 1]);
            graph[u - 1].w.add(w);
        }

        heapA heap = new heapA(m);
        heap.insert(new heapNode(graph[0],0));
        label:while(heap.size > 0){
            heapNode top = new heapNode();
            while(true){
                top = heap.delete();
                if(top.node.mindist == -1 ){
                    break;
                }
                if (heap.size <= 0){
                    break label;
                }
            }
            top.node.mindist = top.dist;
            for(int i = 0; i < top.node.child.size(); i++){
                if(top.node.child.get(i).mindist == -1){
                    heap.insert(new heapNode(top.node.child.get(i),top.dist + top.node.w.get(i)));
                }
            }
        }
        out.println(graph[n - 1].mindist);
        out.close();
    }
}

class graphNode{
    int no;
    long mindist = -1;
    ArrayList<graphNode> child = new ArrayList<>();
    ArrayList<Long> w = new ArrayList<>();
    public graphNode(int no){
        this.no = no;
    }
}

class heapNode{

    graphNode node;
    long dist;
    public heapNode(graphNode graphNode, long dist){
        this.node = graphNode;
        this.dist = dist;
    }
    public heapNode(){}
}


class heapA{//小顶堆
    heapNode[] heap;
    int size = 0;
    public heapA(int n){
        heap = new heapNode[n + 1];
    }

    void insert(heapNode x){//小顶堆
        size++;
        heap[size] = x;
        int cur = size;
        while (cur > 1){
            if (heap[cur].dist < heap[cur / 2].dist){
                heapNode temp = heap[cur];
                heap[cur] = heap[cur / 2];
                heap[cur / 2] = temp;
                cur = cur / 2;
            }else {
                break;
            }
        }
    }

    heapNode delete(){
        heapNode temp = heap[1];
        heap[1] = heap[size];
        heap[size] = temp;
        size--;
        int cur = 1;
        while (cur * 2 <= size){
            if (cur * 2 + 1 <= size){//两个儿子
                if (heap[2 * cur].dist > heap[2 * cur + 1].dist){//左儿子大
                    if (heap[cur].dist > heap[2 * cur + 1].dist){
                        heapNode tem = heap[cur];
                        heap[cur] = heap[cur * 2 + 1];
                        heap[cur * 2 + 1] = tem;
                        cur = cur * 2 + 1;
                    }else {
                        break;
                    }
                }else {//右儿子大或者一样大
                    if (heap[cur].dist > heap[2 * cur].dist ){
                        heapNode tem = heap[cur];
                        heap[cur] = heap[cur * 2];
                        heap[cur * 2] = tem;
                        cur = cur * 2 ;
                    }else {
                        break;
                    }
                }
            }else {//一个儿子
                if (heap[cur].dist > heap[2 * cur].dist){
                    heapNode tem = heap[cur];
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
class QReaderA {
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

class QWriterA implements Closeable {
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

/*
9 13
1 2 1
4 1 6
8 4 3
8 7 1
4 7 1
7 9 1
2 3 1
3 4 2
5 4 5
7 6 2
6 5 1
9 6 2
3 5 10
 */
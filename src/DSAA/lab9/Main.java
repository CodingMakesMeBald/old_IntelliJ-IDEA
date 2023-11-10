package DSAA.lab9;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReaderB in = new QReaderB();
        QWriterB out = new QWriterB();
        int n = in.nextInt();
        int m = in.nextInt();
        graphNodeB[] graph = new graphNodeB[n];
        heapNodeB[] edge = new heapNodeB[m];
        for (int i = 0; i < n; i++) {
            graph[i] = new graphNodeB();
        }
        int minw = 1000000000;
        int uu = 0;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            graph[u - 1].child.add(graph[v - 1]);
            graph[v - 1].child.add(graph[u - 1]);
            graph[u - 1].w.add(w);
            graph[v - 1].w.add(w);
            edge[i] = new heapNodeB(graph[u - 1], graph[v - 1], w);
            if (w < minw){
                uu = u;
            }
        }
        long ji = 0;

        heapB heap = new heapB(m);
        //Let {u,v} be an edge with the smallest weight among all edges
        graphNodeB node = graph[uu - 1];
        node.isColored = true;
        for(int i = 0; i < node.child.size(); i++){
            if(!node.child.get(i).isColored){
                heap.insert(new heapNodeB(node, node.child.get(i), node.w.get(i)));

            }
        }
        label:while(heap.size > 0){
            heapNodeB top = new heapNodeB();
            while(true){
                top = heap.delete();
                if(!top.nodeu.isColored || !top.nodev.isColored){
                    break;
                }
                if (heap.size <= 0){
                    break label;
                }
            }
            if(!top.nodeu.isColored) {
                node = top.nodeu;
            } else {
                node = top.nodev;
            }
            node.isColored = true;
            if (top.w > 0){
                ji += top.w;
            }
            for(int i = 0; i < node.child.size(); i++){
                if(!node.child.get(i).isColored){
                    heap.insert(new heapNodeB(node, node.child.get(i), node.w.get(i)));

                }
            }
        }
        long zheng = 0;
        for (int i = 0; i < m; i++) {
            if (edge[i].w > 0 ){
                zheng += edge[i].w;
            }
        }
        out.println(zheng - ji);
        out.close();
    }
}

class heapB{//小顶堆
    heapNodeB[] heap;
    int size = 0;
    public heapB(int n){
        heap = new heapNodeB[n + 1];
    }

    void insert(heapNodeB x){//小顶堆
        size++;
        heap[size] = x;
        int cur = size;
        while (cur > 1){
            if (heap[cur].w < heap[cur / 2].w){
                heapNodeB temp = heap[cur];
                heap[cur] = heap[cur / 2];
                heap[cur / 2] = temp;
                cur = cur / 2;
            }else {
                break;
            }
        }
    }

    heapNodeB delete(){
        heapNodeB temp = heap[1];
        heap[1] = heap[size];
        heap[size] = temp;
        size--;
        int cur = 1;
        while (cur * 2 <= size){
            if (cur * 2 + 1 <= size){//两个儿子
                if (heap[2 * cur].w > heap[2 * cur + 1].w){//左儿子大
                    if (heap[cur].w > heap[2 * cur + 1].w){
                        heapNodeB tem = heap[cur];
                        heap[cur] = heap[cur * 2 + 1];
                        heap[cur * 2 + 1] = tem;
                        cur = cur * 2 + 1;
                    }else {
                        break;
                    }
                }else {//右儿子大或者一样大
                    if (heap[cur].w > heap[2 * cur].w ){
                        heapNodeB tem = heap[cur];
                        heap[cur] = heap[cur * 2];
                        heap[cur * 2] = tem;
                        cur = cur * 2 ;
                    }else {
                        break;
                    }
                }
            }else {//一个儿子
                if (heap[cur].w > heap[2 * cur].w){
                    heapNodeB tem = heap[cur];
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

class graphNodeB{
    Boolean isColored = false;
    ArrayList<graphNodeB> child = new ArrayList<>();
    ArrayList<Integer> w = new ArrayList<>();
}

class heapNodeB{
    graphNodeB nodeu;
    graphNodeB nodev;
    long w;
    public heapNodeB(graphNodeB u, graphNodeB v, long w ){
        this.nodeu = u;
        this.nodev = v;
        this.w = w;
    }
    public heapNodeB(){}
}

class QReaderB {
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
class QWriterB implements Closeable {
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

package DSAA.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main4 {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int n = in.nextInt();
        Node[] tree = new Node[n];//存放所有节点的数组
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            u[i] = in.nextInt();
            v[i] = in.nextInt();
        }
        long maxPi = 0;
        Node root = new Node();//pi值最大的选为根节点
        for (int i = 0; i < n; i++) {
            long pi = in.nextLong();
            tree[i] = new Node(i + 1, pi);
            if (pi > maxPi){
                root = tree[i];
                maxPi = pi;
            }
        }
        for (int i = 0; i < n - 1; i++) {//将树连起来
            tree[u[i] - 1].next.add(tree[v[i] - 1]);
            tree[v[i] - 1].next.add(tree[u[i] - 1]);
        }
        //从下往上dfs
        root.isVisited = true;
        dfs(root);
        for (int i = 0; i < tree.length; i++) {
            tree[i].isVisited = false;
        }

        //从上往下bfs
        Node[] q = new Node[n];
        int front = 0;
        int rear = 0;
        q[rear++] = root;
        root.isVisited = true;
        long cnt = 0;
        while (front != rear){
            Node head = q[front ++];
            if ( head.next.size() == 1 && head.next.get(0).isVisited){
                cnt += head.p;
            }
            if (head == root && root.next.size() != 1){
                Node temp1 = new Node();
                Node temp2 = new Node();
                for (int i = 0; i < head.next.size(); i++) {
                    if (!head.next.get(i).isVisited){
                        q[rear++] = head.next.get(i);
                        head.next.get(i).isVisited = true;
                        if (head.next.get(i).p >= temp1.p){
                            temp2 = temp1;
                            temp1 = head.next.get(i);
                        } else if (head.next.get(i).p >= temp2.p) {
                            temp2 = head.next.get(i);
                        }
                    }
                }
                temp1.p = head.p;
                temp2.p = head.p;
            }else {
                Node temp = new Node();
                for (int i = 0; i < head.next.size(); i++) {
                    if (!head.next.get(i).isVisited){
                        q[rear++] = head.next.get(i);
                        head.next.get(i).isVisited = true;
                        if (head.next.get(i).p >= temp.p){
                            temp = head.next.get(i);
                        }
                    }
                }
                temp.p = head.p;
            }


        }
        if (root.next.size() == 1){
            cnt += root.p;
        }
        out.println(cnt);
        out.close();
    }
    static void dfs(Node a){
        a.isVisited = true;
        for (int i = 0; i < a.next.size(); i++) {

            if (!a.next.get(i).isVisited ){
                dfs(a.next.get(i));
                if (a.next.get(i).p > a.p){
                    a.p = a.next.get(i).p;
                }
            }
        }
    }
}
/*
11 1 2 1 3 3 6 3 7 2 4 2 5 5 11 4 8 4 9 4 10
10 7 2 6 8 3 9 2 3 4 5
 */

class Node{
    int no;
    long p;
    boolean isVisited;
    ArrayList<Node> next = new ArrayList<>();
    public Node(){}
    public Node(int no, long p){
        this.no = no;
        this.p = p;
    }
}

class QReader4 {
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

class QWriter4 implements Closeable {
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

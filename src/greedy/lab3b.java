package greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lab3b {
    public static void main(String[] args) {
        QReader3b input = new QReader3b();
        QWriter3b out = new QWriter3b();
        int testCases = input.nextInt();
        label:for (int l = 0; l < testCases; l++) {
            int n = input.nextInt();//点数
            int m = input.nextInt();//边数
            Node[] graph = new Node[n + 1];
            for (int i = 0; i < graph.length; i++) {//初始化图
                graph[i] = new Node(i, i);
            }
            for (int i = 0; i < m; i++) {
                int node1 = input.nextInt();//指出点
                int node2 = input.nextInt();//指向点
                graph[node1].son.add(graph[node2]);
                graph[node2].father.add(graph[node1]);
                graph[node2].inDegree++;
            }
            for (int i = n; i > 1; i--) {//合并
                if (graph[i].father.size() > 1) {
                    int temp = graph[i].father.get(0).ancestorNo; //合并到第一个
                    for (int j = 1; j < graph[i].father.size(); j++) {
                        if (graph[i].father.get(j).ancestorNo != temp) {//如果不相等
                            int ancestor1 = find(graph, temp);
                            int ancestor2 = find(graph, graph[i].father.get(j).ancestorNo);
                            if (ancestor1 != ancestor2) {
                                if (graph[ancestor1].rank > graph[ancestor2].rank) {
                                    graph[ancestor2].ancestorNo = ancestor1;
                                } else if (graph[ancestor1].rank < graph[ancestor2].rank) {
                                    graph[ancestor1].ancestorNo = ancestor2;
                                } else {
                                    graph[ancestor2].ancestorNo = ancestor1;
                                    graph[ancestor1].rank++;
                                }
                            }
                        }

                    }
                }
            }
            //建新图
            Node[] newGraph = new Node[n + 1];
            for (int i = 0; i < newGraph.length; i++) {
                newGraph[i] = new Node(i, i);
            }
            for (int i = 1; i < newGraph.length; i++) {
                int temp1 = find(graph, i); //第i个节点的祖宗
                for (int j = 0; j < graph[i].son.size(); j++) {
                    int temp2 = find(graph, graph[i].son.get(j).ancestorNo);
                    newGraph[temp1].son.add(newGraph[temp2]);
                    newGraph[temp2].father.add(newGraph[temp1]);
                    newGraph[temp2].inDegree++;
                }
            }

            //拓扑排序
            Node[] queue = new Node[newGraph.length];
            int rear = 1;
            int front = 1;
            queue[rear] = newGraph[1];
            rear++;
            while (rear != front) {
                Node cur = queue[front];
                for (int j = 0; j < cur.son.size(); j++) {
                    cur.son.get(j).distance = Math.max(cur.son.get(j).distance, cur.distance + 1);
                    cur.son.get(j).inDegree--;
                    if(cur.son.get(j).inDegree == 0){
                        queue[rear] = cur.son.get(j);
                        rear++;
                    }
                }
                front++;
            }
            //有环
            for(int i = 1; i < newGraph.length; i++){
                if(newGraph[i].inDegree != 0){
                    out.println("No");
                    continue label;
                }
            }

            //无环
            graph[1].distance = 1;
            for(int i = 2; i < graph.length; i++){
                int temp = find(graph, i);
                graph[i].distance = newGraph[temp].distance - newGraph[graph[i].father.get(0).ancestorNo].distance;
            }
            out.println("Yes");
            for(int j = 1; j < graph.length; j++){
                out.print(graph[j].distance + " ");
            }
            out.println("");

        }
        out.close();
    }
    public static int find(Node[] graph, int x) {//找到graph[x]的祖先
        if (x != graph[x].ancestorNo) {
            return find(graph ,graph[x].ancestorNo);
        }else {
            return x;
        }
    }
}

class Node{
    int no;
    int ancestorNo;
    ArrayList<Node> father = new ArrayList<>();
    ArrayList<Node> son = new ArrayList<>();
    int rank = 1;
    int distance = 0;
    int inDegree = 0;
    public Node(int no, int ancestorNo){
        this.no = no;
        this.ancestorNo = ancestorNo;
    }
}

class QReader3b {
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

class QWriter3b implements Closeable {
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
3
5 5
1 2
1 3
2 4
3 5
4 5
8 10
1 2
1 3
2 5
2 7
3 4
3 6
5 6
4 7
6 8
7 8
11 16
1 2
1 3
1 4
1 5
2 6
4 6
3 7
4 7
5 8
6 8
2 9
3 9
7 10
8 10
9 11
10 11

2
8 10
1 2
1 3
2 5
2 7
3 4
3 6
5 6
4 7
6 8
7 8
5 5
1 2
1 3
2 4
3 5
4 5

 */
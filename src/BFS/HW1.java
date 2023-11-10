package BFS;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HW1 {
    public static void main(String[] args) {
        QReader1 input = new QReader1();
        QWriter1 out = new QWriter1();
        int numberOfNodes = input.nextInt();
        int numberOfEdges = input.nextInt();
        int initialNode = input.nextInt();
        int numberOfTestCases = input.nextInt();
        Node1[] graph = new Node1[numberOfNodes + 1];
        for (int i = 1; i <= numberOfNodes; i++) {
            graph[i] = new Node1(i);
        }
        for (int i = 0; i < numberOfEdges; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            graph[a].neighbor.add(graph[b]);
            graph[b].neighbor.add(graph[a]);
        }
        int[] ans = new int[numberOfNodes];

        Node1[] queue = new Node1[numberOfNodes];
        int num = 1;//记录可到村庄数量
        int front = 0;
        int rear = 0;
        queue[rear] = graph[initialNode];
        queue[rear].isVisited = true;
        rear++;
        int day = 0;
        ans[0] = 1;
        int lastRear = rear;
        while (num != numberOfNodes){
            int temp = num;

            for (int k = 0; k < queue[front].neighbor.size(); k++) {
                if (!queue[front].neighbor.get(k).isVisited ){
                    queue[rear++] = queue[front].neighbor.get(k);
                    queue[front].neighbor.get(k).isVisited = true;
                    num++;
                }
            }
            if (front != lastRear - 1){
                front++;
                continue;
            }

            day++;
            lastRear = rear;
            ans[day] = num;
        }
        for (int i = 0; i < numberOfTestCases; i++) {
            int temp = input.nextInt();
            if (temp <= day){
                out.print(ans[temp] + " ");
            }else {
                out.print(numberOfNodes + " ");
            }

        }
        out.close();
    }
}
class Node1{
    int number;
    ArrayList<Node1> neighbor = new ArrayList<>();
    boolean isVisited;

    public Node1(int number){
        this.number = number;
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
/*
6 5 4 6
3 1
2 3
4 3
2 5
2 6
0 1 2 3 4 10

6 5 3 7
1 2
2 3
3 4
4 5
5 6
0 1 2 3 4 5 6
 */
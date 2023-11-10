package BFS;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HW2 {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int testCase = input.nextInt();
        for (int l = 0; l < testCase; l++) {
            int v = input.nextInt();//读入点数
            int e = input.nextInt();//读入边数
            //graph[2i]——i-in, graph[2i + 1]——i-out
            int capacity = (2 * v + 2);//0,1空出来，1-in对应graph[2]
            Point[] graph = new Point[capacity];
            for (int i = 0; i < capacity; i++) {//初始化图
                graph[i] = new Point(i);
            }
            for (int i = 0; i < e; i++) {//连接图
                int point1 = input.nextInt();//out
                int point2 = input.nextInt();//in
                graph[2 * point1 + 1].neighbor.add(graph[2 * point2]);
                graph[2 * point2].neighbor.add(graph[2 * point1 + 1]);
            }
            int ans = 1000;

            //BFS
            for (int i = 2; i < capacity; i++) {//graph上每个点为起点做bfs
                Point[] queue = new Point[capacity - 2];
                int rear = 0;
                int front = 0;
                queue[rear] = graph[i];
                queue[rear].isVisited = true;
                rear++;
                while (rear != front){
                    int fuck = 0;
                    for (int j = 0; j < queue[front].neighbor.size(); j++) {
                        if (!queue[front].neighbor.get(j).isVisited ){
                            queue[rear++] = queue[front].neighbor.get(j);//加入队列
                            queue[front].neighbor.get(j).isVisited = true;
                            queue[front].neighbor.get(j).length = queue[front].length + 1;
                        }else{//该点已被访问
                            if (queue[front].neighbor.get(j).length > queue[front].length){
                                int temp = queue[front].length * 2;
                                if (temp < ans){
                                    ans = temp ;
                                }
                                fuck = 1;
                                break;
                            }
                        }
                    }
                    front++;
                    if (fuck == 1){
                        break;
                    }
                }
                for (int j = 2; j < capacity; j++) {
                    graph[j].length = 1;
                    graph[j].isVisited = false;
                }
            }
            if (ans != 1000){
                out.println(ans);
            }else {
                out.println(-1);
            }
        }
        out.close();
    }
}

class Point{
    int no;
    ArrayList<Point> neighbor = new ArrayList<>();
    int length = 1;
    boolean isVisited = false;
    public Point(int no){
        this.no = no;
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

/*
2
6 7
1 2
1 6
5 6
5 4
3 2
3 4
5 2

6 8
1 2
3 2
3 6
1 6
2 5
3 5
4 6
4 1


1 7 9 1 2 2 3 3 4 4 5 4 6 7 5 7 6 1 5 1 6


1
6 6
1 2
1 6
5 6
5 4
3 2
3 4

 */
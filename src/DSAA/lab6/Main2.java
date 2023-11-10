package DSAA.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        city[] tree = new city[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new city(i + 1);
        }

        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            u[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            tree[in.nextInt() - 1].hasGiant = true;
        }
        //把树连起来
        for (int i = 0; i < n - 1; i++) {
            tree[u[i] - 1].next.add(tree[v[i] - 1]);
            tree[v[i] - 1].next.add(tree[u[i] - 1]);
        }
        //bfs
        tree[0].isVisited = true;
        int max = 0;
        for (int i = 0; i < tree[0].next.size(); i++) {
            city[] queue = new city[n];
            int front = 0;
            int rear = 0;
            queue[rear++] = tree[0].next.get(i);
            tree[0].next.get(i).isVisited = true;
            tree[0].next.get(i).level = 1;
            ArrayList<Integer> number = new ArrayList<>();
            number.add(1);
            while (front != rear){
                city head = queue[front ++];
                for (int j = 0; j < head.next.size(); j++) {
                    if (!head.next.get(j).isVisited){
                        queue[rear++] = head.next.get(j);
                        head.next.get(j).level = head.level + 1;
                        head.next.get(j).isVisited = true;
                        if (head.next.get(j).hasGiant){
                            number.add(head.next.get(j).level);
                        }
                    }
                }
            }
            int temp = 0;

            for (int j = 0; j < number.size(); j++) {
                if (temp < number.get(j)){
                    temp = number.get(j);
                }else {
                    temp++;
                }
            }
            if (max < temp){
                max = temp;
            }

        }
        out.println(max);

        out.close();
    }
}

class city{
    int no;
    int level;
    boolean hasGiant;
    boolean isVisited;
    ArrayList<city> next = new ArrayList<>();
    public city(int no){
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
14
1 2 1 3 3 4 3 5 4 6 6 7 6 8 1 9 9 10 9 11 11 12 11 13 11 14
14 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 */

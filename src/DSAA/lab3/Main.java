package DSAA.lab3;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();

        int n = in.nextInt();
        int m = in.nextInt();
        int swapNumber = in.nextInt();
        Node[][] array = new Node[n + 2][m + 2];

        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                if (i == 0 || j == 0 || i == n + 1 || j == m + 1){
                    array[i][j] = new Node(0);
                    continue;
                }
                Node temp = new Node();
                temp.data = in.nextInt();
                array[i][j] = temp;
            }
        }
        for (int i = 0; i < n + 1 ; i++) {
            for (int j = 0; j < m + 1; j++) {
                array[i][j].right = array[i][j + 1];
                array[i][j].down = array[i + 1][j];
            }
        }
        for (int i = 0; i < m + 1; i++) {
            array[n][i].right = array[n][i + 1];
        }
        for (int i = 0; i < n + 1; i++) {
            array[i][m].down = array[i + 1][m];
        }



        for (int i = 0; i < swapNumber; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            int l1 = in.nextInt();
            int l2 = in.nextInt();

            //先找第一个矩阵的四个点位
            Node origin = array[0][0];
            Node up1;
            for (int j = 0; j < x1 - 1; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y1; j++) {
                origin = origin.right;
            }
            up1 = origin;

            origin = array[0][0];
            Node left1;
            for (int j = 0; j < x1; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y1 - 1; j++) {
                origin = origin.right;
            }
            left1 = origin;

            origin = array[0][0];
            Node down1;
            for (int j = 0; j < x1 + l1 - 1; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y1; j++) {
                origin = origin.right;
            }
            down1 = origin;

            origin = array[0][0];
            Node right1;
            for (int j = 0; j < x1; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y1 + l2 - 1; j++) {
                origin = origin.right;
            }
            right1 = origin;

            //第二个矩阵的四个点位同理
            origin = array[0][0];
            Node up2;
            for (int j = 0; j < x2 - 1; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y2; j++) {
                origin = origin.right;
            }
            up2 = origin;

            origin = array[0][0];
            Node left2;
            for (int j = 0; j < x2; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y2 - 1; j++) {
                origin = origin.right;
            }
            left2 = origin;

            origin = array[0][0];
            Node down2;
            for (int j = 0; j < x2 + l1 - 1; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y2; j++) {
                origin = origin.right;
            }
            down2 = origin;

            origin = array[0][0];
            Node right2;
            for (int j = 0; j < x2; j++) {
                origin = origin.down;
            }
            for (int j = 0; j < y2 + l2 - 1; j++) {
                origin = origin.right;
            }
            right2 = origin;

            //换上下边
            for (int j = 0; j < l2; j++) {
                swapDown(up1,up2);
                swapDown(down1,down2);
                up1 = up1.right;
                up2 = up2.right;
                down1 = down1.right;
                down2 = down2.right;
            }
            //换左右边
            for (int j = 0; j < l1; j++) {
                swapRight(left1,left2);
                swapRight(right1,right2);
                left1 = left1.down;
                left2 = left2.down;
                right1 = right1.down;
                right2 = right2.down;
            }








        }
        Node cur = array[0][0];
        cur = cur.down;
        cur = cur.right;
        Node first = cur;


        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                out.print(cur.data + " ");
                cur = cur.right;
            }
            cur = first.down;
            first = first.down;
            out.println("");
        }

        out.close();
    }

    public static void swapRight(Node node1, Node node2){
        Node temp = node1.right;
        node1.right = node2.right;
        node2.right = temp;
    }

    public static void swapDown(Node node1, Node node2){
        Node temp = node1.down;
        node1.down = node2.down;
        node2.down = temp;
    }



}

class Node{
    int data;
    Node right;
    Node down;

    public Node(){}

    public Node(int data){
        this.data = data;
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

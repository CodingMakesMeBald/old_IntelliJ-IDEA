package DSAA.lab4;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        if (n == 1){
            int temp = in.nextInt();
            if (temp > 0){
                out.println(1);
                out.close();
                return;
            }else {
                out.println(0);
                out.close();
                return;
            }
        }
        Node[] array = new Node[n + 1];
        array[0] = new Node(0,0);
        int sum = 0;
        for (int i = 1; i < n + 1; i++) {
            array[i] = new Node();
            sum += in.nextInt();
            array[i].data = sum;
            array[i].pos = i;
        }

        quickSortData(array,0, array.length - 1);
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1].data == array[i].data){
                temp2++;
                if (i + 1 == array.length - 1){
                    quickSortPos(array,temp1,temp2);
                }
            }else if (array[i + 1].data != array[i].data || i + 1  == array.length - 1 ){
                if (temp2 - temp1 > 0){
                    quickSortPos(array,temp1,temp2);
                }
                temp1 = i + 1;
                temp2 = i + 1;
            }
        }
        int ans = 0;
        int min = n;
        for (int i = 1; i < array.length; i++) {
            min = Math.min(min,array[i].pos);
            if (min < array[i].pos){
                ans = Math.max(ans, array[i].pos - min);
            }
        }
        out.println(ans);
        out.close();

    }
    public static void quickSortData(Node[] array, int left, int right){
        if (left >= right){
            return;
        }
        int leftt = left;
        int rightt = right;
        Node basic = array[leftt];
        while (leftt < rightt){
            while (leftt < rightt && array[rightt].data > basic.data){///
                rightt--;
            }
            if (leftt < rightt){
                array[leftt] = array[rightt];
            }
            while (leftt < rightt && array[leftt].data <= basic.data){///
                leftt++;
            }
            if (leftt < rightt){
                array[rightt] = array[leftt];
            }
            if (leftt >= rightt){
                array[leftt] = basic;
            }
        }
        quickSortData(array, left, rightt - 1);
        quickSortData(array, rightt + 1, right);
    }
    public static void quickSortPos(Node[] array, int left, int right){
        if (left >= right){
            return;
        }
        int leftt = left;
        int rightt = right;
        Node basic = array[leftt];
        while (leftt < rightt){
            while (leftt < rightt && array[rightt].pos < basic.pos){///
                rightt--;
            }
            if (leftt < rightt){
                array[leftt] = array[rightt];
            }
            while (leftt < rightt && array[leftt].pos >= basic.pos){///
                leftt++;
            }
            if (leftt < rightt){
                array[rightt] = array[leftt];
            }
            if (leftt >= rightt){
                array[leftt] = basic;
            }
        }
        quickSortPos(array, left, rightt - 1);
        quickSortPos(array, rightt + 1, right);
    }

}

class Node{
    int data;
    int pos;

    public Node(){}
    public Node(int data, int pos){
        this.data = data;
        this.pos = pos;
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

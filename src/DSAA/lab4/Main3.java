package DSAA.lab4;

import java.io.*;
import java.util.StringTokenizer;

public class Main3 {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int arrayLength = in.nextInt();
        int length = in.nextInt();
        int number = in.nextInt();
        int[] max = new int[arrayLength - length + 1];

        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = in.nextInt();
        }
        if (length == 1){
            max[0] = array[0];
        }

        int front = 0;
        int rear = 0;

        for (int i = 0; i < arrayLength - 1; i++) {
            rear++;
            if (rear - front > length - 1){
                front++;
            }
            for (int j = front; j <= rear; j++) {
                if (array[front] < array[j]){
                    front = j;
                }
            }

            if (rear >= length - 1){
                max[i - length + 2] = array[front];
            }
        }



        for (int i = 0; i < number; i++) {
            int temp = in.nextInt();
            out.println(max[temp - 1]);;
        }


        out.close();
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
//1 3 -1 -3 5 3 6 7
/*
8 7 2
1 3 -1 -3 5 3 6 7
1 2

10 3 8 7 4 7 4 6 3 7 2 98 6 1 2 3 4 5 6 7 8
 */
